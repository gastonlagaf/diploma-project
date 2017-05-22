package com.vehicle.rentservice.notifications.executor;

import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.notifications.email.EmailNotificationSender;

@NotificationExecutor
public class OrderConfirmationEmailNotificationExecutor implements Runnable {

	private EmailNotificationSender emailNotificationSender;
	
	private Contract contract;

	public OrderConfirmationEmailNotificationExecutor(EmailNotificationSender emailNotificationSender,
			Contract contract) {
		super();
		this.emailNotificationSender = emailNotificationSender;
		this.contract = contract;
	}

	@Override
	public void run() {
		emailNotificationSender.sendOrderConfirmation(contract);
	}
	
}
