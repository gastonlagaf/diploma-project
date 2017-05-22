package com.vehicle.rentservice.notifications.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.vehicle.rentservice.notifications.executor.NotificationExecutor;

@Service
@PropertySource("classpath:email-config.properties")
public class DefaultOperationsNotificationManager implements OperationsNotificationManager {
	
	@SuppressWarnings("unused")
	private final Integer nThreads;
	
	private ExecutorService executorService;
	
	public DefaultOperationsNotificationManager(@Value("${mail.manager.threads:5}") Integer nThreads) {
		super();
		this.nThreads = nThreads;
		executorService = Executors.newFixedThreadPool(nThreads);
	}

	@Override
	public void scheduleNotification(Runnable task) {
		if(task.getClass().getAnnotation(NotificationExecutor.class) != null) {
			executorService.submit(task);
		}
	}

}
