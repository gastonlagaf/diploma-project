package com.vehicle.rentservice.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.entity.Brand;
import com.vehicle.rentservice.persistence.api.BrandRepository;
import com.vehicle.rentservice.service.api.BrandService;

@Service
public class DefaultBrandService implements BrandService {

	@Inject
	private BrandRepository repository;
	
	@Override
	public List<Brand> getAllBrands() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
	}
	
	@Override
	public Brand getByName(String name) {
		return repository.findByNameOrderByName(name);
	}

}
