package com.vehicle.rentservice.persistence.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.rentservice.model.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Modifying
	@Transactional
	@Query("UPDATE Account acc SET acc.balance = acc.balance + ?1 WHERE acc.id = ?2")
	void updateAccountFunds(Long amount, Long accountId);
	
	@Query("SELECT acc.balance FROM Account acc WHERE acc.user.id = ?1")
	Long findBalanceByUserId(Long userId);
	
}
