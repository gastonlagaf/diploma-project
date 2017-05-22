package com.vehicle.rentservice.notifications.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.notifications.renderer.VehicleOperationsConfirmationEmailRenderer;

@Component
@PropertySource("classpath:email-config.properties")
public class DefaultEmailNotificationSender implements EmailNotificationSender {

	@Inject
	@Qualifier("paymentConfirmationEmailRenderer")
	private VehicleOperationsConfirmationEmailRenderer paymentRenderer;
	
	@Inject
	@Qualifier("orderConfirmationEmailRenderer")
	private VehicleOperationsConfirmationEmailRenderer orderRenderer;
	
	private Properties sessionProperties = new Properties();

	{
		InputStream iStream = this.getClass().getClassLoader().getResourceAsStream("email-config.properties");
		try {
			sessionProperties.load(iStream);
		} catch (IOException e) {
			throw new ExceptionInInitializerError("Failed to load email notification properties");
		}
	}

	@Override
	public void sendOrderConfirmation(Contract contract) {
		Session session = getSession();
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sessionProperties.getProperty("mail.smtp.username")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(contract.getUser().getEmail()));
			message.setSubject(sessionProperties.getProperty("mail.order.subject"));
			message.setContent(orderRenderer.renderNotification(contract), "text/html; charset=UTF-8");
			Transport.send(message);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void sendPaymentConfirmation(Contract contract) {
		Session session = getSession();
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sessionProperties.getProperty("mail.smtp.username")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(contract.getUser().getEmail()));
			message.setSubject(sessionProperties.getProperty("mail.payment.subject"));
			message.setContent(paymentRenderer.renderNotification(contract), "text/html; charset=UTF-8");
			Transport.send(message);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}

	}

	private Session getSession() {
		return Session.getInstance(sessionProperties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sessionProperties.getProperty("mail.smtp.username"),
						sessionProperties.getProperty("mail.smtp.password"));
			}
		});
	}

}
