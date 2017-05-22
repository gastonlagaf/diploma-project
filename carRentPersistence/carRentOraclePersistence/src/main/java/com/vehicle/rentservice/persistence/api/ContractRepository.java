package com.vehicle.rentservice.persistence.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.model.entity.User;

public interface ContractRepository extends JpaRepository<Contract, Long> {
	
	List<Contract> findByOpenDateBetween(LocalDate from, LocalDate to);
	
	List<Contract> findByUserAndClosedFalse(User user);
	
}
