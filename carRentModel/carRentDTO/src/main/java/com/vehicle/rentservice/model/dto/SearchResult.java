package com.vehicle.rentservice.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {
	
	@Getter @Setter private VehiclesPageData vehiclesPage;
	
	@Getter @Setter private List<String> cities;
	
	@Getter @Setter private List<String> locations;
	
}
