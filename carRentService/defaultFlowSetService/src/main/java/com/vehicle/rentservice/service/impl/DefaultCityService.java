package com.vehicle.rentservice.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.entity.City;
import com.vehicle.rentservice.persistence.api.CityRepository;
import com.vehicle.rentservice.service.api.CityService;

@Service
public class DefaultCityService implements CityService {

	@Inject
	private CityRepository repository;
	
	@Override
	public List<City> getAllCities() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	@Override
	public City getCityByName(String name) {
		return repository.findByNameOrderByName(name);
	}
	
	@Override
	public List<String> getAllCitiesOfCountry(String countryName) {
		return repository.findByCountry(countryName);
	}

}
