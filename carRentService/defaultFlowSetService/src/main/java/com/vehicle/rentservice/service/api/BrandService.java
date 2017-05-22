package com.vehicle.rentservice.service.api;

import java.util.List;

import com.vehicle.rentservice.model.entity.Brand;

public interface BrandService {

	List<Brand> getAllBrands();
	
	Brand getByName(String name);
	
}
