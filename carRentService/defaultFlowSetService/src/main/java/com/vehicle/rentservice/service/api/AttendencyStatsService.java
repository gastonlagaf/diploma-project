package com.vehicle.rentservice.service.api;

import java.time.LocalDate;
import java.util.List;

import com.vehicle.rentservice.model.attendency.AttendancyStatisticsData;

public interface AttendencyStatsService<T extends AttendancyStatisticsData> {
	
	T getStatisticsOfMonth(T stat);
	
	void updateStatData(T stat);
	
	void updateStats(List<T> stats);
	
	List<T> getStatisticsInPeriod(LocalDate from, LocalDate to);
	
}
