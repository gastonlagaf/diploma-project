package com.vehicle.rentservice.persistence.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vehicle.rentservice.model.attendency.BrandAttendancyStatisticsData;

public interface BrandAttendancyStatsRepository extends JpaRepository<BrandAttendancyStatisticsData, Long> {
	
	@Query("SELECT casd FROM CountryAttendancyStatisticsData casd " 
			+ "WHERE casd.country.id = :#{#stat.brand.id} "
			+ "AND casd.period = :#{#stat.period}")
	BrandAttendancyStatisticsData getStatisticsOfMonth(@Param("stat") BrandAttendancyStatisticsData stat);
	
	List<BrandAttendancyStatisticsData> findByPeriodBetween(LocalDate from, LocalDate to);
}
