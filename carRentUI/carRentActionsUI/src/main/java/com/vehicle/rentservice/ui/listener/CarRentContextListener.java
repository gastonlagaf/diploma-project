package com.vehicle.rentservice.ui.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoader;

import com.vehicle.rentservice.facade.api.SearchCriteriaFacadeService;
import com.vehicle.rentservice.model.dto.SearchCriteriaData;

public class CarRentContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		SearchCriteriaFacadeService searchCriteriaService = (SearchCriteriaFacadeService) ContextLoader
				.getCurrentWebApplicationContext().getBean(SearchCriteriaFacadeService.class);

		SearchCriteriaData searchCriteriaData = searchCriteriaService.getSearchVariants();
		sce.getServletContext().setAttribute("searchCriteriaData", searchCriteriaData);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// do nothing
	}

}
