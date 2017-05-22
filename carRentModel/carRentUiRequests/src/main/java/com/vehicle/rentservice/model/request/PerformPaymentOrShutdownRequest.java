package com.vehicle.rentservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PerformPaymentOrShutdownRequest {

	@Getter private Long contractId;
	
}
