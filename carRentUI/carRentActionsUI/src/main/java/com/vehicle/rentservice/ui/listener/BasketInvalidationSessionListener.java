package com.vehicle.rentservice.ui.listener;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.ContextLoader;

import com.vehicle.rentservice.model.entity.Vehicle;
import com.vehicle.rentservice.service.api.VehicleService;

public class BasketInvalidationSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		//do nothing
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		List<Vehicle> preorderedVehicles = (List)session.getAttribute("basket");
		if(preorderedVehicles != null) {
			VehicleService vehicleService = (VehicleService) ContextLoader
					.getCurrentWebApplicationContext().getBean(VehicleService.class);
			vehicleService.unmarkVehiclesAsOrdered(preorderedVehicles);
		}
		
	}
	
}
