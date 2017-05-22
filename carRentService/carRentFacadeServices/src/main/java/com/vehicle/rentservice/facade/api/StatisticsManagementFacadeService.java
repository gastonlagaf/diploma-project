package com.vehicle.rentservice.facade.api;

import java.time.LocalDate;
import java.util.Map;

import com.vehicle.rentservice.model.dto.ChartDatasetHolder;
import com.vehicle.rentservice.model.dto.StatisticsTransferObject;

public interface StatisticsManagementFacadeService {
	
	void persistUserSearchParameters(StatisticsTransferObject stat);
	
	Map<String, ChartDatasetHolder> retrieveStatisticsInPeriod(LocalDate from, LocalDate to);
	
}
