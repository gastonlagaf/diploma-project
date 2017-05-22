package com.vehicle.rentservice.model.request;

import com.vehicle.rentservice.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PerformRegistrationRequest {
	
	@Getter private String checkPassword;
	
	@Getter private User user;
	
}
