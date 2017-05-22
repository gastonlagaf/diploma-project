package com.vehicle.rentservice.persistence.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vehicle.rentservice.model.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {
	
	City findByNameOrderByName(String name);
	
	@Query("SELECT c.name FROM City c WHERE c.country.name = ?1")
	List<String> findByCountry(String country);
	
}
