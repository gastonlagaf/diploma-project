package com.vehicle.rentservice.persistence.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vehicle.rentservice.model.attendency.BodyTypeAttendancyStatisticsData;

public interface BodyTypeAttendancyStatsRepository extends JpaRepository<BodyTypeAttendancyStatisticsData, Long> {
	
	@Query("SELECT casd FROM BodyTypeAttendancyStatisticsData casd " 
			+ "WHERE casd.bodyType.id = :#{#stat.bodyType.id} "
			+ "AND casd.period = :#{#stat.period}")
	BodyTypeAttendancyStatisticsData getStatisticsOfMonth(@Param("stat") BodyTypeAttendancyStatisticsData stat);
	
	List<BodyTypeAttendancyStatisticsData> findByPeriodBetween(LocalDate from, LocalDate to);
}
