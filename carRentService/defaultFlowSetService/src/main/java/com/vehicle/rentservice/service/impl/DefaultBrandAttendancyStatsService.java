package com.vehicle.rentservice.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.attendency.BrandAttendancyStatisticsData;
import com.vehicle.rentservice.persistence.api.BrandAttendancyStatsRepository;
import com.vehicle.rentservice.service.api.AttendencyStatsService;

@Service("defaultBrandAttendancyStatsService")
public class DefaultBrandAttendancyStatsService implements AttendencyStatsService<BrandAttendancyStatisticsData> {
	
	@Inject
	private BrandAttendancyStatsRepository repository;
	
	@Override
	public BrandAttendancyStatisticsData getStatisticsOfMonth(BrandAttendancyStatisticsData stat) {
		return repository.getStatisticsOfMonth(stat);
	}
	
	@Override
	public void updateStatData(BrandAttendancyStatisticsData stat) {
		repository.save(stat);
	}
	
	@Override
	public void updateStats(List<BrandAttendancyStatisticsData> stats) {
		repository.save(stats);
	}
	
	@Override
	public List<BrandAttendancyStatisticsData> getStatisticsInPeriod(LocalDate from, LocalDate to) {
		return repository.findByPeriodBetween(from, to);
	}
	
}
