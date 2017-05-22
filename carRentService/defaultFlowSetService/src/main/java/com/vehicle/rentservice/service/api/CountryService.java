package com.vehicle.rentservice.service.api;

import java.util.List;

import com.vehicle.rentservice.model.entity.Country;

public interface CountryService {
	
	List<Country> getAllCountries();
	
	Country getCountryByName(String name);
	
}
