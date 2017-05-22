package com.vehicle.rentservice.service.api;

import java.util.List;

import com.vehicle.rentservice.model.entity.City;

public interface CityService {
	
	List<City> getAllCities();
	
	City getCityByName(String name);
	
	List<String> getAllCitiesOfCountry(String countryName);
	
}
