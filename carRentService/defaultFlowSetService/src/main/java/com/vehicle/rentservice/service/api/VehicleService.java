package com.vehicle.rentservice.service.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.vehicle.rentservice.model.dto.SearchEntity;
import com.vehicle.rentservice.model.entity.Location;
import com.vehicle.rentservice.model.entity.Vehicle;

public interface VehicleService {
	
	Page<Vehicle> getVehicles(Integer page);
	
	Page<Vehicle> getVehicles(Integer page, Integer vehiclesPerPage);
	
	Page<Vehicle> getVehicles(Integer page, Boolean archived);
	
	Page<Vehicle> getVehicles(Integer page, Integer vehiclesPerPage, Boolean archived);
	
	List<Vehicle> getVehiclesByIds(List<Long> ids);
	
	List<Vehicle> getOrderedVehiclesInPeriod(LocalDate from, LocalDate to);
	
	List<String> getAllCarsManufactureYears();
	
	Vehicle getVehicle(Long id);
	
	Vehicle getVehicleByUri(String uri);
	
	void insertVehicle(Vehicle vehicle);
	
	void insertVehicles(List<Vehicle> vehicles);
	
	void updateVehiclePrice(Long id, Integer price);
	
	void updateVehicleLocation(Long id, Location location);
	
	Vehicle markVehicleAsOrdered(Long id);
	
	void unmarkVehiclesAsOrdered(List<Vehicle> vehicles);
	
	void archiveVehicle(Long id);
	
	void disarchiveVehicle(Long id);
	
	Page<Vehicle> searchVehicles(SearchEntity searchEntity, Integer page);
}
