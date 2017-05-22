package com.vehicle.rentservice.facade.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.rentservice.facade.api.StatisticsManagementFacadeService;
import com.vehicle.rentservice.model.attendency.BodyTypeAttendancyStatisticsData;
import com.vehicle.rentservice.model.attendency.BrandAttendancyStatisticsData;
import com.vehicle.rentservice.model.attendency.CountryAttendancyStatisticsData;
import com.vehicle.rentservice.model.dto.ChartDatasetHolder;
import com.vehicle.rentservice.model.dto.StatisticsTransferObject;
import com.vehicle.rentservice.model.entity.BodyType;
import com.vehicle.rentservice.model.entity.Brand;
import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.model.entity.Country;
import com.vehicle.rentservice.model.entity.Vehicle;
import com.vehicle.rentservice.service.api.AttendencyStatsService;
import com.vehicle.rentservice.service.api.BodyTypeService;
import com.vehicle.rentservice.service.api.BrandService;
import com.vehicle.rentservice.service.api.ContractService;
import com.vehicle.rentservice.service.api.CountryService;

@Service
public class DefaultStatisticsManagementFacadeService implements StatisticsManagementFacadeService {

	@Inject
	@Qualifier("defaultCountryAttendancyStatsService")
	private AttendencyStatsService<CountryAttendancyStatisticsData> countryStatsService;

	@Inject
	@Qualifier("defaultBrandAttendancyStatsService")
	private AttendencyStatsService<BrandAttendancyStatisticsData> brandStatsService;

	@Inject
	@Qualifier("defaultBodyTypeAttendancyService")
	private AttendencyStatsService<BodyTypeAttendancyStatisticsData> bodyTypeStatsService;

	@Inject
	private CountryService countryService;

	@Inject
	private BrandService brandService;

	@Inject
	private BodyTypeService bodyTypeService;

	@Inject
	private ContractService contractService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void persistUserSearchParameters(StatisticsTransferObject stats) {
		stats.getCountryStats().forEach(data -> {
			Country country = countryService.getCountryByName(data.getCountry().getName());
			data.setCountry(country);
			CountryAttendancyStatisticsData persistedData = countryStatsService.getStatisticsOfMonth(data);
			if (Objects.nonNull(persistedData)) {
				persistedData.setTimesAttended(persistedData.getTimesAttended() + data.getTimesAttended());
				countryStatsService.updateStatData(persistedData);
			} else {
				countryStatsService.updateStatData(data);
			}
		});

		stats.getBrandStats().forEach(data -> {
			Brand brand = brandService.getByName(data.getBrand().getName());
			data.setBrand(brand);
			BrandAttendancyStatisticsData persistedData = brandStatsService.getStatisticsOfMonth(data);
			if (Objects.nonNull(persistedData)) {
				persistedData.setTimesAttended(persistedData.getTimesAttended() + data.getTimesAttended());
				brandStatsService.updateStatData(persistedData);
			} else {
				brandStatsService.updateStatData(data);
			}
		});

		stats.getBodyTypeStats().forEach(data -> {
			BodyType bodyType = bodyTypeService.getByName(data.getBodyType().getName());
			data.setBodyType(bodyType);
			;
			BodyTypeAttendancyStatisticsData persistedData = bodyTypeStatsService.getStatisticsOfMonth(data);
			if (Objects.nonNull(persistedData)) {
				persistedData.setTimesAttended(persistedData.getTimesAttended() + data.getTimesAttended());
				bodyTypeStatsService.updateStatData(persistedData);
			} else {
				bodyTypeStatsService.updateStatData(data);
			}
		});
	}

	@Override
	public Map<String, ChartDatasetHolder> retrieveStatisticsInPeriod(LocalDate from, LocalDate to) {
		Map<String, ChartDatasetHolder> result = new HashMap<>();

		result.putAll(processClickStatisticsCalculation(from, to));
		result.putAll(processContractStatisticsCalculation(from, to));

		return result;
	}

	private Map<String, ChartDatasetHolder> processClickStatisticsCalculation(LocalDate from, LocalDate to) {
		Map<String, ChartDatasetHolder> result = new HashMap<>();

		List<CountryAttendancyStatisticsData> countryData = countryStatsService.getStatisticsInPeriod(from, to);
		List<BrandAttendancyStatisticsData> brandData = brandStatsService.getStatisticsInPeriod(from, to);
		List<BodyTypeAttendancyStatisticsData> bodyTypeData = bodyTypeStatsService.getStatisticsInPeriod(from, to);

		ChartDatasetHolder chartData = new ChartDatasetHolder();
		chartData.getData().getDatasets().add(chartData.new ChartDataSet());
		chartData.getData().getDatasets().get(0).setLabel("Countries");
		chartData.setType("bar");
		chartData.getData().getDatasets().get(0).setBackgroundColor("#ef5350");
		int idx = 0;
		for (CountryAttendancyStatisticsData it : countryData) {
			if ((idx = chartData.getData().getLabels().indexOf(it.getCountry().getName())) != -1) {
				Integer curVal = chartData.getData().getDatasets().get(0).getData().get(idx);
				chartData.getData().getDatasets().get(0).getData().set(idx, curVal + it.getTimesAttended());
			} else {
				chartData.getData().getLabels().add(it.getCountry().getName());
				chartData.getData().getDatasets().get(0).getData().add(it.getTimesAttended());
			}
		}
		result.put("countries", chartData);

		chartData = new ChartDatasetHolder();
		chartData.getData().getDatasets().add(chartData.new ChartDataSet());
		chartData.getData().getDatasets().get(0).setLabel("Brands");
		chartData.setType("bar");
		chartData.getData().getDatasets().get(0).setBackgroundColor("#5c6bc0");
		for (BrandAttendancyStatisticsData it : brandData) {
			if ((idx = chartData.getData().getLabels().indexOf(it.getBrand().getName())) != -1) {
				Integer curVal = chartData.getData().getDatasets().get(0).getData().get(idx);
				chartData.getData().getDatasets().get(0).getData().set(idx, curVal + it.getTimesAttended());
			} else {
				chartData.getData().getLabels().add(it.getBrand().getName());
				chartData.getData().getDatasets().get(0).getData().add(it.getTimesAttended());
			}
		}
		result.put("brands", chartData);

		chartData = new ChartDatasetHolder();
		chartData.getData().getDatasets().add(chartData.new ChartDataSet());
		chartData.getData().getDatasets().get(0).setLabel("BodyTypes");
		chartData.setType("bar");
		chartData.getData().getDatasets().get(0).setBackgroundColor("#26a69a");
		for (BodyTypeAttendancyStatisticsData it : bodyTypeData) {
			if ((idx = chartData.getData().getLabels().indexOf(it.getBodyType().getName().toString())) != -1) {
				Integer curVal = chartData.getData().getDatasets().get(0).getData().get(idx);
				chartData.getData().getDatasets().get(0).getData().set(idx, curVal + it.getTimesAttended());
			} else {
				chartData.getData().getLabels().add(it.getBodyType().getName().toString());
				chartData.getData().getDatasets().get(0).getData().add(it.getTimesAttended());
			}
		}
		result.put("bodyTypes", chartData);

		return result;
	}

	private Map<String, ChartDatasetHolder> processContractStatisticsCalculation(LocalDate from, LocalDate to) {
		Map<String, ChartDatasetHolder> result = new HashMap<>();

		List<Contract> orderedContractsOfPeriod = contractService.getContractsInPeriod(from, to);

		ChartDatasetHolder orededBodyTypeChartData = new ChartDatasetHolder();
		orededBodyTypeChartData.getData().getDatasets().add(orededBodyTypeChartData.new ChartDataSet());
		orededBodyTypeChartData.getData().getDatasets().get(0).setLabel("BodyTypes");
		orededBodyTypeChartData.setType("doughnut");
		orededBodyTypeChartData.getData().getDatasets().get(0).setBackgroundColor("yellow");

		ChartDatasetHolder orderedBrandChartData = new ChartDatasetHolder();
		orderedBrandChartData.getData().getDatasets().add(orededBodyTypeChartData.new ChartDataSet());
		orderedBrandChartData.getData().getDatasets().get(0).setLabel("Brands");
		orderedBrandChartData.setType("doughnut");
		orderedBrandChartData.getData().getDatasets().get(0).setBackgroundColor("pink");

		ChartDatasetHolder orderedCountriesChartData = new ChartDatasetHolder();
		orderedCountriesChartData.getData().getDatasets().add(orededBodyTypeChartData.new ChartDataSet());
		orderedCountriesChartData.getData().getDatasets().get(0).setLabel("Countries");
		orderedCountriesChartData.setType("polarArea");
		orderedCountriesChartData.getData().getDatasets().get(0).setBackgroundColor("purple");

		for (Contract contract : orderedContractsOfPeriod) {
			for (Vehicle vehicle : contract.getVehicles()) {
				processVehicleBrandChartDataFullfilment(vehicle, orderedBrandChartData);
				processVehicleBodyTypeChartDataFullfilment(vehicle, orededBodyTypeChartData);
				processVehicleCountryChartDataFullfilment(vehicle, orderedCountriesChartData);
			}
		}
		result.put("orderedCountries", orderedCountriesChartData);
		result.put("orderedBodyTypes", orededBodyTypeChartData);
		result.put("orderedBrands", orderedBrandChartData);

		return result;
	}

	private void processVehicleCountryChartDataFullfilment(Vehicle vehicle, ChartDatasetHolder chartData) {
		int idx = 0;
		if ((idx = chartData.getData().getLabels()
				.indexOf(vehicle.getLocation().getCity().getCountry().getName())) != -1) {
			Integer curVal = chartData.getData().getDatasets().get(0).getData().get(idx);
			chartData.getData().getDatasets().get(0).getData().set(idx, ++curVal);
		} else {
			chartData.getData().getLabels().add(vehicle.getLocation().getCity().getCountry().getName());
			chartData.getData().getDatasets().get(0).getData().add(1);
		}
	}

	private void processVehicleBrandChartDataFullfilment(Vehicle vehicle, ChartDatasetHolder chartData) {
		int idx = 0;
		if ((idx = chartData.getData().getLabels().indexOf(vehicle.getBrand().getName())) != -1) {
			Integer curVal = chartData.getData().getDatasets().get(0).getData().get(idx);
			chartData.getData().getDatasets().get(0).getData().set(idx, ++curVal);
		} else {
			chartData.getData().getLabels().add(vehicle.getBrand().getName());
			chartData.getData().getDatasets().get(0).getData().add(1);
		}
	}

	private void processVehicleBodyTypeChartDataFullfilment(Vehicle vehicle, ChartDatasetHolder chartData) {
		int idx = 0;
		if ((idx = chartData.getData().getLabels().indexOf(vehicle.getBodyType().getName().toString())) != -1) {
			Integer curVal = chartData.getData().getDatasets().get(0).getData().get(idx);
			chartData.getData().getDatasets().get(0).getData().set(idx, ++curVal);
		} else {
			chartData.getData().getLabels().add(vehicle.getBodyType().getName().toString());
			chartData.getData().getDatasets().get(0).getData().add(1);
		}
	}

}
