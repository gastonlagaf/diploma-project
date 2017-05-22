package com.vehicle.rentservice.model.dto;

import java.util.List;

import com.vehicle.rentservice.model.entity.Vehicle;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class VehiclesPageData {
	
	@Getter @Setter private List<Vehicle> vehicles;
	
	@Getter @Setter private Integer pages;
	
}
