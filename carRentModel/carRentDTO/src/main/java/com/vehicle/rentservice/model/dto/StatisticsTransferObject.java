package com.vehicle.rentservice.model.dto;

import java.util.List;

import com.vehicle.rentservice.model.attendency.BodyTypeAttendancyStatisticsData;
import com.vehicle.rentservice.model.attendency.BrandAttendancyStatisticsData;
import com.vehicle.rentservice.model.attendency.CountryAttendancyStatisticsData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class StatisticsTransferObject {
	
	@Getter @Setter private List<CountryAttendancyStatisticsData> countryStats;
	
	@Getter @Setter private List<BrandAttendancyStatisticsData> brandStats;
	
	@Getter @Setter private List<BodyTypeAttendancyStatisticsData> bodyTypeStats;
	
}
