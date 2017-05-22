package com.vehicle.rentservice.service.api;

import java.util.List;

import com.vehicle.rentservice.model.entity.Location;

public interface LocationService {
	
	List<Location> getAllLocations();
	
	Location getByName(String name);
	
	List<String> getAllLocationsOfCity(String cityName); 
	
}
