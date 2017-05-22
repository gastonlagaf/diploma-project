package com.vehicle.rentservice.persistence.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vehicle.rentservice.model.attendency.CountryAttendancyStatisticsData;

public interface CountryAttendancyStatsRepository extends JpaRepository<CountryAttendancyStatisticsData, Long> {
	
	@Query("SELECT casd FROM CountryAttendancyStatisticsData casd " 
			+ "WHERE casd.country.id = :#{#stat.country.id} "
			+ "AND casd.period = :#{#stat.period}")
	CountryAttendancyStatisticsData getStatisticsOfMonth(@Param("stat") CountryAttendancyStatisticsData stat);

	List<CountryAttendancyStatisticsData> findByPeriodBetween(LocalDate from, LocalDate to);
}
