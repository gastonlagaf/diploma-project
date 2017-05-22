package com.vehicle.rentservice.notifications.renderer;

import com.vehicle.rentservice.model.entity.Contract;

public interface VehicleOperationsConfirmationEmailRenderer {
	
	String renderNotification(Contract contract);
	
}
