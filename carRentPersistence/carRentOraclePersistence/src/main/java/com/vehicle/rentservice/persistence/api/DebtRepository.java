package com.vehicle.rentservice.persistence.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.rentservice.model.entity.Debt;

public interface DebtRepository extends JpaRepository<Debt, Long> {

}
