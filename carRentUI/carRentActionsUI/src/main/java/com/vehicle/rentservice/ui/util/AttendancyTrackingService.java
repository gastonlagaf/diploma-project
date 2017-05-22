package com.vehicle.rentservice.ui.util;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.vehicle.rentservice.facade.api.StatisticsManagementFacadeService;
import com.vehicle.rentservice.model.attendency.BodyTypeAttendancyStatisticsData;
import com.vehicle.rentservice.model.attendency.BrandAttendancyStatisticsData;
import com.vehicle.rentservice.model.attendency.CountryAttendancyStatisticsData;
import com.vehicle.rentservice.model.dto.SearchEntity;
import com.vehicle.rentservice.model.dto.StatisticsTransferObject;
import com.vehicle.rentservice.model.entity.BodyType;
import com.vehicle.rentservice.model.entity.Brand;
import com.vehicle.rentservice.model.entity.Country;
import com.vehicle.rentservice.model.enumerations.BodyTypes;

@Service
public class AttendancyTrackingService {

	@Inject
	private StatisticsManagementFacadeService statisticsService;

	public void registerSearchOptions(SearchEntity entity, HttpSession session) {
		AttendancyStatisticsHolder statHolder = (AttendancyStatisticsHolder)session.getAttribute("attendancyStats");
		statHolder.selectedCountries.add(entity.getCountry());
		if(Objects.nonNull(entity.getBrand())) {
			statHolder.selectedBrands.add(entity.getBrand());
		}
		if(Objects.nonNull(entity.getBodyType())) {
			statHolder.selectedBodyTypes.add(entity.getBodyType().toString());
		}
	}

	public void registerVehicleAttendancy(String uri, HttpSession session) {
		AttendancyStatisticsHolder statHolder = (AttendancyStatisticsHolder)session.getAttribute("attendancyStats");
		statHolder.visitedVehicles.add(uri);
	}

	public void synchronizeStatistics(HttpSession session) {
		AttendancyStatisticsHolder statHolder = (AttendancyStatisticsHolder)session.getAttribute("attendancyStats");
		StatisticsTransferObject stats = formStatistics(statHolder);
		statisticsService.persistUserSearchParameters(stats);
	}

	private List<CountryAttendancyStatisticsData> formCountrySearchStatistics(Set<String> stats) {
		return stats.stream().map(el -> {
			Country country = new Country();
			country.setName(el);
			CountryAttendancyStatisticsData data = new CountryAttendancyStatisticsData();
			data.setCountry(country);
			data.setTimesAttended(1);
			data.setPeriod(LocalDate.now().withDayOfMonth(1));
			return data;
		}).collect(Collectors.toList());
	}
	
	private List<BrandAttendancyStatisticsData> formBrandSearchStatistics(Set<String> stats) {
		return stats.stream().map(el -> {
			Brand brand = new Brand();
			brand.setName(el);
			BrandAttendancyStatisticsData data = new BrandAttendancyStatisticsData();
			data.setBrand(brand);
			data.setTimesAttended(1);
			data.setPeriod(LocalDate.now().withDayOfMonth(1));
			return data;
		}).collect(Collectors.toList());
	}
	
	private List<BodyTypeAttendancyStatisticsData> formBodyTypeSearchStatistics(Set<String> stats) {
		return stats.stream().map(el -> {
			BodyType bodyType = new BodyType();
			bodyType.setName(BodyTypes.valueOf(el));
			BodyTypeAttendancyStatisticsData data = new BodyTypeAttendancyStatisticsData();
			data.setBodyType(bodyType);
			data.setTimesAttended(1);
			data.setPeriod(LocalDate.now().withDayOfMonth(1));
			return data;
		}).collect(Collectors.toList());
	}
	
	private StatisticsTransferObject formStatistics(AttendancyStatisticsHolder stats) {
		StatisticsTransferObject transferObject = new StatisticsTransferObject();
		transferObject.setCountryStats(formCountrySearchStatistics(stats.getSelectedCountries()));
		transferObject.setBrandStats(formBrandSearchStatistics(stats.getSelectedBrands()));
		transferObject.setBodyTypeStats(formBodyTypeSearchStatistics(stats.getSelectedBodyTypes()));
		return transferObject;
	}

	public static class AttendancyStatisticsHolder {

		private Set<String> visitedVehicles = new HashSet<>();
		private Set<String> selectedBodyTypes = new HashSet<>();
		private Set<String> selectedBrands = new HashSet<>();
		private Set<String> selectedCountries = new HashSet<>();

		public Set<String> getVisitedVehicles() {
			return visitedVehicles;
		}

		public Set<String> getSelectedBodyTypes() {
			return selectedBodyTypes;
		}

		public Set<String> getSelectedBrands() {
			return selectedBrands;
		}

		public Set<String> getSelectedCountries() {
			return selectedCountries;
		}

	}

}
