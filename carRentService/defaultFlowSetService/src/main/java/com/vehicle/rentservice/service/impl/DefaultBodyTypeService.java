package com.vehicle.rentservice.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.entity.BodyType;
import com.vehicle.rentservice.model.enumerations.BodyTypes;
import com.vehicle.rentservice.persistence.api.BodyTypeRepository;
import com.vehicle.rentservice.service.api.BodyTypeService;

@Service
public class DefaultBodyTypeService implements BodyTypeService {

	@Inject
	private BodyTypeRepository repository;
	
	@Override
	public List<BodyType> getAllBodyTypes() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
	}
	
	@Override
	public BodyType getByName(BodyTypes name) {
		return repository.findByNameOrderByName(name);
	}

}
