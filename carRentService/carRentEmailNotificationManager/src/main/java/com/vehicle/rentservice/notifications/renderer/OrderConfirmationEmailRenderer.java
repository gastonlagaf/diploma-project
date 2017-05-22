package com.vehicle.rentservice.notifications.renderer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.model.entity.Vehicle;

@Component("orderConfirmationEmailRenderer")
@PropertySource("classpath:email-config.properties")
public class OrderConfirmationEmailRenderer implements VehicleOperationsConfirmationEmailRenderer {

	@Value("${mail.order.template.location}")
	private String templateName;

	@Override
	public String renderNotification(Contract contract) {
		String templatePath = "templates/" + templateName + ".html";
		InputStream iStream = this.getClass().getClassLoader().getResourceAsStream(templatePath);
		String contentHtml = new BufferedReader(new InputStreamReader(iStream)).lines()
				.collect(Collectors.joining("\n"));
		return replacePlaceholders(contentHtml, contract);
	}

	private String replacePlaceholders(String contentHtml, Contract contract) {
		String result = contentHtml.replace("${user}", contract.getUser().getName() + " " + contract.getUser().getPatronymic())
				.replace("${from}", contract.getOpenDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
				.replace("${to}", contract.getCloseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
				.replace("${price}", Long.toString(contract.getTotalPrice()));
		String vehiclesList = "";
		for(Vehicle vehicle : contract.getVehicles()) {
			vehiclesList += "<li class='collection-item'>" + vehicle.getBrand().getName() 
				+ " " + vehicle.getModel() + " (" + vehicle.getManufactureYear() + ")" + "</li>";
		}
		return result.replace("${vehicles}", vehiclesList);
	}

}
