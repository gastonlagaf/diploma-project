package com.vehicle.rentservice.service.impl;

import javax.inject.Inject;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.vehicle.rentservice.persistence.api.AccountRepository;
import com.vehicle.rentservice.service.api.DummyPaymentService;

@Service
@PropertySource("classpath:serviceConfiguration.properties")
public class DefaultPaymentService implements DummyPaymentService {
	
	@Inject
	private AccountRepository repository;
	
	@Override
	public void subtractFundsFromAccount(Long accountId, Long amount) {
		repository.updateAccountFunds(-amount, accountId);
	}
	
	@Override
	public void performPayment(Long amount, Long accountId) {
		repository.updateAccountFunds(amount, accountId);
	}
	
	@Override
	public Long getAccountFundsAmount(Long accountId) {
		return repository.findOne(accountId).getBalance();
	}
	
}
