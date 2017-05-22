package com.vehicle.rentservice.notifications.renderer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.vehicle.rentservice.model.entity.Contract;

@Component("paymentConfirmationEmailRenderer")
@PropertySource("classpath:email-config.properties")
public class PaymentConfirmationEmailRenderer implements VehicleOperationsConfirmationEmailRenderer {

	@Value("${mail.payment.template.location}")
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
				.replace("${price}", Long.toString(contract.getTotalPrice()));
		return result;
	}

}
