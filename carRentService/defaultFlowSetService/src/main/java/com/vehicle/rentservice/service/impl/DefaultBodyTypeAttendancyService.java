package com.vehicle.rentservice.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.attendency.BodyTypeAttendancyStatisticsData;
import com.vehicle.rentservice.persistence.api.BodyTypeAttendancyStatsRepository;
import com.vehicle.rentservice.service.api.AttendencyStatsService;

@Service("defaultBodyTypeAttendancyService")
public class DefaultBodyTypeAttendancyService implements AttendencyStatsService<BodyTypeAttendancyStatisticsData> {

	@Inject
	private BodyTypeAttendancyStatsRepository repository;
	
	@Override
	public BodyTypeAttendancyStatisticsData getStatisticsOfMonth(BodyTypeAttendancyStatisticsData stat) {
		return repository.getStatisticsOfMonth(stat);
	}

	@Override
	public void updateStatData(BodyTypeAttendancyStatisticsData stat) {
		repository.save(stat);
	}

	@Override
	public void updateStats(List<BodyTypeAttendancyStatisticsData> stats) {
		repository.save(stats);
	}
	
	@Override
	public List<BodyTypeAttendancyStatisticsData> getStatisticsInPeriod(LocalDate from, LocalDate to) {
		return repository.findByPeriodBetween(from, to);
	}
	
}
