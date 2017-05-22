package com.vehicle.rentservice.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.attendency.CountryAttendancyStatisticsData;
import com.vehicle.rentservice.persistence.api.CountryAttendancyStatsRepository;
import com.vehicle.rentservice.service.api.AttendencyStatsService;

@Service("defaultCountryAttendancyStatsService")
public class DefaultCountryAttendancyStatsService implements AttendencyStatsService<CountryAttendancyStatisticsData> {

	@Inject
	private CountryAttendancyStatsRepository repository;
	
	@Override
	public CountryAttendancyStatisticsData getStatisticsOfMonth(CountryAttendancyStatisticsData stat) {
		return repository.getStatisticsOfMonth(stat);
	}
	
	@Override
	public void updateStatData(CountryAttendancyStatisticsData stat) {
		repository.save(stat);	
	}
	
	@Override
	public void updateStats(List<CountryAttendancyStatisticsData> stats) {
		repository.save(stats);
	}
	
	@Override
	public List<CountryAttendancyStatisticsData> getStatisticsInPeriod(LocalDate from, LocalDate to) {
		return repository.findByPeriodBetween(from, to);
	}

}
