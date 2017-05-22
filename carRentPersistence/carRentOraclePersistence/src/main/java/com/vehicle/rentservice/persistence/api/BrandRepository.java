package com.vehicle.rentservice.persistence.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.rentservice.model.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
	
	Brand findByNameOrderByName(String name);
	
}
