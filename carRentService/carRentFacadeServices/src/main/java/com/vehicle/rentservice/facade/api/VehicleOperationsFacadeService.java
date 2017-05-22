package com.vehicle.rentservice.facade.api;

import com.vehicle.rentservice.model.entity.Vehicle;

public interface VehicleOperationsFacadeService {
	
	void insertVehicle(Vehicle vehicle);
	
	void realocateVehicle(Long id, String locationName);
	
}
