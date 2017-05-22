package com.vehicle.rentservice.model.request;

import java.time.LocalDate;
import java.util.List;

import com.vehicle.rentservice.model.entity.User;
import com.vehicle.rentservice.model.entity.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class OrderContractRequest {
	
	@Getter private LocalDate openDate;
	
	@Getter private LocalDate closeDate;
	
	@Getter private List<Long> vehicleIds;
	
	@Getter @Setter private List<Vehicle> basket;
	
	@Getter @Setter private User user;
	
}
