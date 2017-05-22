package com.vehicle.rentservice.ui.converter;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.model.entity.Vehicle;
import com.vehicle.rentservice.model.request.OrderContractRequest;

@Service("insertContractRequestConverter")
public class InsertContractRequestConverter implements RequestConverter<Contract, OrderContractRequest> {
	
	private final Integer HOURS_IN_DAY = 24;
	
	@Override
	public Contract convertRequest(OrderContractRequest request) {
		List<Vehicle> vehiclesToOrder = request.getBasket().stream()
				.filter(vehicle -> request.getVehicleIds().contains(vehicle.getId())).collect(Collectors.toList());

		Integer pricePerHour = vehiclesToOrder.stream().mapToInt(obj -> obj.getPrice()).sum();
		Integer days = Period.between(request.getOpenDate(), request.getCloseDate()).getDays();

		Contract contract = new Contract();
		contract.setOpenDate(request.getOpenDate());
		contract.setCloseDate(request.getCloseDate());
		contract.setVehicles(vehiclesToOrder);
		Long totalPrice = new Long(HOURS_IN_DAY * days * pricePerHour);
		contract.setTotalPrice(totalPrice);
		contract.setUser(request.getUser());
		contract.setVehicles(vehiclesToOrder);
		contract.setActive(false);
		contract.setPaid(false);
		contract.setClosed(false);
		return contract;
	}

}
