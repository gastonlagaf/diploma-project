package com.vehicle.rentservice.service.api;

import java.util.List;

import com.vehicle.rentservice.model.entity.BodyType;
import com.vehicle.rentservice.model.enumerations.BodyTypes;

public interface BodyTypeService {
	
	List<BodyType> getAllBodyTypes();
	
	BodyType getByName(BodyTypes name);
	
}
