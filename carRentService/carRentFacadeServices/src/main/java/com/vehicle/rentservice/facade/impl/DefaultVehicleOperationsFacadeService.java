package com.vehicle.rentservice.facade.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.rentservice.facade.api.VehicleOperationsFacadeService;
import com.vehicle.rentservice.model.entity.BodyType;
import com.vehicle.rentservice.model.entity.Brand;
import com.vehicle.rentservice.model.entity.Location;
import com.vehicle.rentservice.model.entity.Vehicle;
import com.vehicle.rentservice.service.api.BodyTypeService;
import com.vehicle.rentservice.service.api.BrandService;
import com.vehicle.rentservice.service.api.ImageService;
import com.vehicle.rentservice.service.api.LocationService;
import com.vehicle.rentservice.service.api.VehicleService;

@Service
public class DefaultVehicleOperationsFacadeService implements VehicleOperationsFacadeService {

	@Inject
	private VehicleService vehicleService;
	
	@Inject
	private BrandService brandService;
	
	@Inject
	private BodyTypeService bodyTypeService;
	
	@Inject
	private ImageService imageService;
	
	@Inject
	private LocationService locationService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertVehicle(Vehicle vehicle) {
		Brand brand = brandService.getByName(vehicle.getBrand().getName());
		vehicle.setBrand(brand);
		BodyType bodyType = bodyTypeService.getByName(vehicle.getBodyType().getName());
		vehicle.setBodyType(bodyType);
		Location location = locationService.getByName(vehicle.getLocation().getName());
		vehicle.setLocation(location);
		vehicleService.insertVehicle(vehicle);
		imageService.saveImages(vehicle.getImages());
	}
	
	@Override
	public void realocateVehicle(Long id, String locationName) {
		Location location = locationService.getByName(locationName);
		vehicleService.updateVehicleLocation(id, location);
	}

}
