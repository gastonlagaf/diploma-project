package com.vehicle.rentservice.service.api;

import java.time.LocalDate;
import java.util.List;

import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.model.entity.User;

public interface ContractService {
	
	List<Contract> getContracts(int page);
	
	List<Contract> getContracts(int page, int contractsPerPage);
	
	Contract aquireContract(Long id);
	
	Contract insertContract(Contract contract);
	
	void updateContractDetails(Contract contract);
	
	void closeContract(Long id);
	
	List<Contract> getContractsInPeriod(LocalDate from, LocalDate to);
	
	List<Contract> getContractsByUser(User user);
}
