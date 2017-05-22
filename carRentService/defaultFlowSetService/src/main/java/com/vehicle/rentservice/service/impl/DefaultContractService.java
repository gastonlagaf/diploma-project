package com.vehicle.rentservice.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.model.entity.User;
import com.vehicle.rentservice.persistence.api.ContractRepository;
import com.vehicle.rentservice.service.api.ContractService;

@Service
@PropertySource("classpath:serviceConfiguration.properties")
public class DefaultContractService implements ContractService {
	
	private final Integer DEFAULT_PAGE_CAPACITY;
	
	private @Autowired ContractRepository repository;
	
	@Autowired
	public DefaultContractService(@Value("${contract.page.capacity:15}") Integer defaultCapacity) {
		this.DEFAULT_PAGE_CAPACITY = defaultCapacity;
	}
	
	@Override
	public List<Contract> getContracts(int page) {
		return getContracts(page, DEFAULT_PAGE_CAPACITY);
	}

	@Override
	public List<Contract> getContracts(int page, int contractsPerPage) {
		PageRequest pageRequest = new PageRequest(page - 1, contractsPerPage, Direction.ASC, "closed");
		List<Contract> contracts = repository.findAll(pageRequest).getContent();
		return contracts;
	}

	@Override
	public Contract aquireContract(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Contract insertContract(Contract contract) {
		return repository.save(contract);
	}

	@Override
	public void updateContractDetails(Contract contract) {
		Contract updateContract = repository.findOne(contract.getId());
		updateContract.setTotalPrice(contract.getTotalPrice());
		updateContract.setActive(contract.getActive());
		updateContract.setOpenDate(contract.getOpenDate());
		updateContract.setCloseDate(contract.getCloseDate());
		updateContract.setDebts(contract.getDebts());
		updateContract.setPaid(contract.getPaid());
		updateContract.setVehicles(contract.getVehicles());
		repository.save(contract);
	}
	
	@Override
	public List<Contract> getContractsInPeriod(LocalDate from, LocalDate to) {
		return repository.findByOpenDateBetween(from, to);
	}
	
	@Override
	public List<Contract> getContractsByUser(User user) {
		return repository.findByUserAndClosedFalse(user);
	}

	@Override
	public void closeContract(Long id) {
		Contract contract = repository.findOne(id);
		contract.setActive(false);
	}

}
