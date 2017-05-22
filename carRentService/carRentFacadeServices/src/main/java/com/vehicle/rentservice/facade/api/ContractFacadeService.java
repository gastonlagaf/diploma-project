package com.vehicle.rentservice.facade.api;

import java.util.List;

import com.vehicle.rentservice.facade.exception.InsufficientFundsException;
import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.model.entity.User;
import com.vehicle.rentservice.model.entity.Vehicle;

public interface ContractFacadeService {

	Contract submitContract(Contract contract, List<Vehicle> vehiclesToCancel);
	
	void shutdownContract(Long contractId);
	
	void eliminateRemovedVehiclesFromContract(List<Vehicle> vehicles);
	
	User executePayment(Long contractId, Long userId) throws InsufficientFundsException;
	
}
