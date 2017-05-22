package com.vehicle.rentservice.notifications.scheduler;

public interface OperationsNotificationManager {
	
	void scheduleNotification(Runnable task);
	
}
