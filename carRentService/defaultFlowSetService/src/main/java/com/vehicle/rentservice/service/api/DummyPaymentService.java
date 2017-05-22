package com.vehicle.rentservice.service.api;

public interface DummyPaymentService {
	
	void subtractFundsFromAccount(Long accountId, Long amount);
	
	void performPayment(Long amount, Long accountId);
	
	Long getAccountFundsAmount(Long userId);
	
}
