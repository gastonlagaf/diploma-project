package com.vehicle.rentservice.model.dto;

import com.vehicle.rentservice.model.enumerations.BodyTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class SearchEntity {
	
	@Getter @Setter private String country;
	
	@Getter @Setter private String city;
	
	@Getter @Setter private String location;
	
	@Getter @Setter private String brand;
	
	@Getter @Setter private BodyTypes bodyType;
	
	@Getter @Setter private Integer fromYear;
	
	@Getter @Setter private Integer toYear;
	
}
