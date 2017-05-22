package com.vehicle.rentservice.facade.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.rentservice.facade.api.SearchCriteriaFacadeService;
import com.vehicle.rentservice.model.dto.SearchCriteriaData;
import com.vehicle.rentservice.model.dto.SearchEntity;
import com.vehicle.rentservice.model.dto.SearchResult;
import com.vehicle.rentservice.model.dto.VehiclesPageData;
import com.vehicle.rentservice.model.entity.City;
import com.vehicle.rentservice.model.entity.Country;
import com.vehicle.rentservice.model.entity.Vehicle;
import com.vehicle.rentservice.service.api.BodyTypeService;
import com.vehicle.rentservice.service.api.BrandService;
import com.vehicle.rentservice.service.api.CityService;
import com.vehicle.rentservice.service.api.CountryService;
import com.vehicle.rentservice.service.api.LocationService;
import com.vehicle.rentservice.service.api.VehicleService;

@Service
public class DefaultSearchCriteriaFacadeService implements SearchCriteriaFacadeService {

	@Inject
	private VehicleService vehicleService;
	
	@Inject
	private BrandService brandService;
	
	@Inject
	private BodyTypeService bodyTypeService;
	
	@Inject
	private LocationService locationService;
	
	@Inject
	private CountryService countryService;
	
	@Inject
	private CityService cityService;

	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public SearchCriteriaData getSearchVariants() {
		List<String> brands = brandService.getAllBrands().stream().map(brand -> brand.getName())
				.collect(Collectors.toList());
		List<String> bodyTypes = bodyTypeService.getAllBodyTypes().stream()
				.map(bodyType -> bodyType.getName().toString()).collect(Collectors.toList());
		List<String> countries = countryService.getAllCountries().stream()
				.map(country -> country.getName().toString()).collect(Collectors.toList());
		List<String> cities = cityService.getAllCities().stream()
				.map(location -> location.getName().toString()).collect(Collectors.toList());
		List<String> locations = locationService.getAllLocations().stream()
				.map(location -> location.getName().toString()).collect(Collectors.toList());
		SearchCriteriaData resultData = new SearchCriteriaData();
		resultData.setCities(cities);
		resultData.setBrands(brands);
		resultData.setBodyTypes(bodyTypes);
		resultData.setCountries(countries);
		resultData.setLocations(locations);
		return resultData;
	}
	
	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public SearchResult searchForVehicles(SearchEntity searchEntity, Integer page) {
		SearchResult searchResult = new SearchResult();
		Page<Vehicle> resultPage = vehicleService.searchVehicles(searchEntity, page);
		VehiclesPageData vehicles = new VehiclesPageData(resultPage.getContent(), resultPage.getTotalPages());
		searchResult.setVehiclesPage(vehicles);
		if(Objects.nonNull(searchEntity.getCountry())) {
			Country selectedCountry = countryService.getCountryByName(searchEntity.getCountry());
			List<String> cityNames = selectedCountry.getCities().stream()
					.map(city -> city.getName()).collect(Collectors.toList());
			searchResult.setCities(cityNames);
		}
		if(Objects.nonNull(searchEntity.getCity())) {
			City city = cityService.getCityByName(searchEntity.getCity());
			List<String> locationNames = city.getLocations().stream()
					.map(location -> location.getName()).collect(Collectors.toList());
			searchResult.setLocations(locationNames);
		}
		return searchResult;
	}

}
