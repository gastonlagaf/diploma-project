package com.vehicle.rentservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UpdateCredentialsRequest {
	
	@Getter private String oldPassword;
	
	@Getter private String newPassword;
	
	@Getter private String repeatNewPassword;
	
}
