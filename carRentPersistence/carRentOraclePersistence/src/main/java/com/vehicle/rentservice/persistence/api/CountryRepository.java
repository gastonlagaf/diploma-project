package com.vehicle.rentservice.persistence.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.rentservice.model.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
	
	Country findByNameOrderByName(String name);
	
}
