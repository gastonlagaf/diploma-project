package com.vehicle.rentservice.persistence.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.rentservice.model.entity.BodyType;
import com.vehicle.rentservice.model.enumerations.BodyTypes;

public interface BodyTypeRepository extends JpaRepository<BodyType, Long> {
	BodyType findByNameOrderByName(BodyTypes type);
}
