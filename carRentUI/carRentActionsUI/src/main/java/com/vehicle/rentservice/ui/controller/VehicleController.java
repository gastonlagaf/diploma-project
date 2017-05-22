package com.vehicle.rentservice.ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.rentservice.facade.api.SearchCriteriaFacadeService;
import com.vehicle.rentservice.facade.api.VehicleOperationsFacadeService;
import com.vehicle.rentservice.model.dto.SearchEntity;
import com.vehicle.rentservice.model.dto.SearchResult;
import com.vehicle.rentservice.model.entity.Vehicle;
import com.vehicle.rentservice.model.request.AddVehicleToBasketRequest;
import com.vehicle.rentservice.model.request.InsertVehicleRequest;
import com.vehicle.rentservice.service.api.CityService;
import com.vehicle.rentservice.service.api.LocationService;
import com.vehicle.rentservice.service.api.VehicleService;
import com.vehicle.rentservice.ui.converter.RequestConverter;
import com.vehicle.rentservice.ui.util.AttendancyTrackingService;

@Controller
public class VehicleController {
	
	@Inject
	private AttendancyTrackingService trackingService;

	@Inject
	private VehicleService vehicleService;

	@Inject
	private VehicleOperationsFacadeService vehicleFacadeService;

	@Inject
	private SearchCriteriaFacadeService searchService;
	
	@Inject
	private LocationService locationService;
	
	@Inject
	private CityService cityService;

	@Inject
	@Qualifier("vehicleInsertRequestConverter")
	private RequestConverter<Vehicle, InsertVehicleRequest> converter;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Inject
    private MessageSource messageSource;

	@RequestMapping(path = "/vehicles/page/{page}", method = RequestMethod.GET)
	public String viewVehicles(@PathVariable("page") Integer page, ModelMap model, HttpServletRequest req) {
		Page<Vehicle> vehicles = vehicleService.getVehicles(page);
		model.addAttribute("page", page);
		model.addAttribute("pages", vehicles.getTotalPages());
		model.addAttribute("vehicles", vehicles.getContent());
		return "vehicle/vehiclesList";
	}

	@RequestMapping(path = "/vehicles/search/page/{page}", method = RequestMethod.GET)
	public String performVehiclesSearch(@RequestParam("searchEntity") String jsonSearchEntity,
			@PathVariable("page") Integer page, ModelMap model, HttpSession session)
			throws JsonParseException, JsonMappingException, IOException {
		SearchEntity searchEntity = new ObjectMapper().readValue(jsonSearchEntity, SearchEntity.class);
		SearchResult searchResult = searchService.searchForVehicles(searchEntity, page);
		trackingService.registerSearchOptions(searchEntity, session);
		model.addAttribute("searchResult", searchResult);
		return "custom/search-response";
	}

	@RequestMapping(path = "/vehicle/{uri}", method = RequestMethod.GET)
	public String viewVehicle(@PathVariable("uri") String uri, ModelMap model, HttpSession session) {
		Vehicle vehicle = vehicleService.getVehicleByUri(uri);
		trackingService.registerVehicleAttendancy(uri, session);
		model.addAttribute("vehicle", vehicle);
		return "vehicle/viewVehicle";
	}

	@Secured("ROLE_USER")
	@SuppressWarnings("unchecked")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(path = "/vehicle/preorder", method = RequestMethod.POST)
	public void preorderVehicle(@RequestBody AddVehicleToBasketRequest preorderRequest, ModelMap model,
			HttpSession session) {
		List<Vehicle> vehiclesBasket = List.class.cast(session.getAttribute("basket"));
		if (Objects.isNull(vehiclesBasket)) {
			vehiclesBasket = new ArrayList<>();
			session.setAttribute("basket", vehiclesBasket);
		}

		Vehicle retrievedVehicle = vehicleService.markVehicleAsOrdered(preorderRequest.getVehicleId());
		vehiclesBasket.add(retrievedVehicle);
	}

	/****** ADMIN ACTIONS *****/

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/vehicle/insert", method = RequestMethod.GET)
	public String viewInsertVehicle() {
		return "forms/insertVehicle";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/vehicle/post", method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
	public String insertVehicle(@ModelAttribute InsertVehicleRequest insertRequest) {
		Vehicle vehicle = converter.convertRequest(insertRequest);
		vehicleFacadeService.insertVehicle(vehicle);
		return "forms/insertVehicle";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/vehicle/insert/search")
	public String searchCitiesAndLocations(@RequestParam(required = false, name = "country") String country,
			@RequestParam(required = false, name = "city") String city, ModelMap model) {
		if(Objects.nonNull(country)) {
			model.addAttribute("cities", cityService.getAllCitiesOfCountry(country));
		}
		if(Objects.nonNull(city)) {
			model.addAttribute("locations", locationService.getAllLocationsOfCity(city));
		}
		return "custom/vehicle-insert-data";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/vehicle", method = RequestMethod.PUT)
	public String updateVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
		return "vehicleUpdateSuccess";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/vehicle/update/price", method = RequestMethod.POST)
	public void updatePrice(@RequestBody String requestAsJson) throws JsonParseException, JsonMappingException, IOException {
		Map<String, String> request = objectMapper.readValue(requestAsJson, new TypeReference<Map<String, String>>(){});
		Long vehicleId = new Long(request.get("id"));
		Integer newPrice = new Integer(request.get("price"));
		vehicleService.updateVehiclePrice(vehicleId, newPrice);;
	}
	
	@ResponseBody
	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/vehicle/realocate", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String realocateVehicle(@RequestBody String requestAsJson) throws JsonParseException, JsonMappingException, IOException {
		Map<String, String> request = objectMapper.readValue(requestAsJson, new TypeReference<Map<String, String>>(){});
		Long vehicleId = new Long(request.get("id"));
		String location = request.get("location");
		vehicleFacadeService.realocateVehicle(vehicleId, location);
		return messageSource.getMessage(location, null, LocaleContextHolder.getLocale());
	}

	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/vehicle/archive", method = RequestMethod.POST)
	public void archiveVehicle(@RequestBody String requestAsJson) throws JsonParseException, JsonMappingException, IOException {
		Map<String, String> request = objectMapper.readValue(requestAsJson, new TypeReference<Map<String, String>>(){});
		Long vehicleId = new Long(request.get("id"));
		vehicleService.archiveVehicle(vehicleId);
	}

}
