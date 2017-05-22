package com.vehicle.rentservice.model.dto;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ChartDatasetHolder {
	
	@Getter @Setter private String type;
	
	@Getter private ChartData data = new ChartData();
	
	public class ChartData {
		
		@Getter private List<String> labels = new LinkedList<>();
		
		@Getter private List<ChartDataSet> datasets = new LinkedList<>();
		
	}
	
	public class ChartDataSet {
		
		@Getter @Setter private String label;
		
		@Getter @Setter private String backgroundColor;
		
		@Getter private List<Integer> data = new LinkedList<>();
		
	}
	
}
