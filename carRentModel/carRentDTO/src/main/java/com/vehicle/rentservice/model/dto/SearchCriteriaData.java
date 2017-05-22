package com.vehicle.rentservice.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteriaData {
	
	@Getter @Setter private List<String> countries;
	
	@Getter @Setter private List<String> brands;
	
	@Getter @Setter private List<String> bodyTypes;
	
	@Getter @Setter private List<String> cities;
	
	@Getter @Setter private List<String> locations;
	
}
