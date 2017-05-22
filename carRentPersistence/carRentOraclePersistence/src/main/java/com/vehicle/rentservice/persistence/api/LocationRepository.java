package com.vehicle.rentservice.persistence.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vehicle.rentservice.model.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	
	Location findByNameOrderByName(String name);
	
	@Query("SELECT l.name FROM Location l WHERE l.city.name = ?1")
	List<String> findByCity(String city);
	
}
