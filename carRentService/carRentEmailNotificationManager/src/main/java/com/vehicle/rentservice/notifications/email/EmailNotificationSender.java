package com.vehicle.rentservice.notifications.email;

import com.vehicle.rentservice.model.entity.Contract;

public interface EmailNotificationSender {
	
	void sendOrderConfirmation(Contract contract);
	
	void sendPaymentConfirmation(Contract contract);
	
}
