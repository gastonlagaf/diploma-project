package com.vehicle.rentservice.ui.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.ContextLoader;

import com.vehicle.rentservice.ui.util.AttendancyTrackingService;

public class AttendancyTrackingSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("attendancyStats", new AttendancyTrackingService.AttendancyStatisticsHolder());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		AttendancyTrackingService attendancyTrackingService = (AttendancyTrackingService) ContextLoader
				.getCurrentWebApplicationContext().getBean(AttendancyTrackingService.class);
		attendancyTrackingService.synchronizeStatistics(se.getSession());
	}

}
