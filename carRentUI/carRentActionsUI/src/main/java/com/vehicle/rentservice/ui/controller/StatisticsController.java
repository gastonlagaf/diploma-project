package com.vehicle.rentservice.ui.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.rentservice.facade.api.StatisticsManagementFacadeService;
import com.vehicle.rentservice.model.dto.ChartDatasetHolder;

@Controller
public class StatisticsController {

	@Inject
	private StatisticsManagementFacadeService statisticsService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/statistics", method = RequestMethod.GET)
	public String handleStatisticsRequest(@RequestParam(name = "from", required = false) LocalDate fromDate,
			@RequestParam(name = "to", required = false) LocalDate toDate, ModelMap model)
			throws JsonProcessingException {
		LocalDate from = Optional.ofNullable(fromDate).orElse(LocalDate.now().withMonth(1).withDayOfMonth(1));
		LocalDate to = Optional.ofNullable(toDate).orElse(LocalDate.now().withMonth(1).withDayOfMonth(1).plusYears(1));
		Map<String, ChartDatasetHolder> viewStats = statisticsService.retrieveStatisticsInPeriod(from, to);
		Map<String, String> viewStatsJson = convertChartDataToJson(viewStats);
		model.addAttribute(from).addAttribute(to).addAttribute("viewStats", viewStatsJson);

		return "admin/view-stats";
	}

	private Map<String, String> convertChartDataToJson(Map<String, ChartDatasetHolder> originalMap)
			throws JsonProcessingException {
		Map<String, String> resultMap = new HashMap<>();
		for (String key : originalMap.keySet()) {
			resultMap.put(key, objectMapper.writeValueAsString(originalMap.get(key)));
		}
		return resultMap;
	}

}
