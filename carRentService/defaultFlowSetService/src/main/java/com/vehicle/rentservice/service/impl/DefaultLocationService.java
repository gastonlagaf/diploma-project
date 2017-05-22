package com.vehicle.rentservice.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.entity.Location;
import com.vehicle.rentservice.persistence.api.LocationRepository;
import com.vehicle.rentservice.service.api.LocationService;

@Service
public class DefaultLocationService implements LocationService {

	@Inject
	private LocationRepository repository;
	
	@Override
	public List<Location> getAllLocations() {
		return repository.findAll();
	}
	
	@Override
	public Location getByName(String name) {
		return repository.findByNameOrderByName(name);
	}
	
	@Override
	public List<String> getAllLocationsOfCity(String cityName) {
		return repository.findByCity(cityName);
	}

}
