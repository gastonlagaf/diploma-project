package com.vehicle.rentservice.notifications.executor;

import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.notifications.email.EmailNotificationSender;

@NotificationExecutor
public class PaymentConfirmationEmailNotificationExecutor implements Runnable {

	private EmailNotificationSender emailNotificationSender;
	
	private Contract contract;

	public PaymentConfirmationEmailNotificationExecutor(EmailNotificationSender emailNotificationSender,
			Contract contract) {
		super();
		this.emailNotificationSender = emailNotificationSender;
		this.contract = contract;
	}



	@Override
	public void run() {
		emailNotificationSender.sendPaymentConfirmation(contract);
	}
}
