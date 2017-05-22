package com.vehicle.rentservice.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.entity.Country;
import com.vehicle.rentservice.persistence.api.CountryRepository;
import com.vehicle.rentservice.service.api.CountryService;

@Service
public class DefaultCountryService implements CountryService {

	@Inject
	private CountryRepository repository;
	
	@Override
	public List<Country> getAllCountries() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	@Override
	public Country getCountryByName(String name) {
		return repository.findByNameOrderByName(name);
	}

}
