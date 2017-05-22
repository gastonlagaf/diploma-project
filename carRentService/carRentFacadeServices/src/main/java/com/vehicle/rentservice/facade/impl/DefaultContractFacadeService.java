package com.vehicle.rentservice.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.rentservice.facade.api.ContractFacadeService;
import com.vehicle.rentservice.facade.exception.InsufficientFundsException;
import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.model.entity.User;
import com.vehicle.rentservice.model.entity.Vehicle;
import com.vehicle.rentservice.notifications.email.EmailNotificationSender;
import com.vehicle.rentservice.notifications.executor.OrderConfirmationEmailNotificationExecutor;
import com.vehicle.rentservice.notifications.executor.PaymentConfirmationEmailNotificationExecutor;
import com.vehicle.rentservice.notifications.scheduler.OperationsNotificationManager;
import com.vehicle.rentservice.service.api.ContractService;
import com.vehicle.rentservice.service.api.DummyPaymentService;
import com.vehicle.rentservice.service.api.UserService;
import com.vehicle.rentservice.service.api.VehicleService;

@Service
@PropertySource("classpath:facadeService.properties")
public class DefaultContractFacadeService implements ContractFacadeService {

	private final Long PAYMENT_ENDPOINT_ACCOUNT_ID;

	@Inject
	private ContractService contractService;

	@Inject
	private VehicleService vehicleService;

	@Inject
	private DummyPaymentService paymentService;
	
	@Inject
	private UserService userService;

	@Inject
	private OperationsNotificationManager notificationManager;

	@Inject
	private EmailNotificationSender notificationSender;
	
	public DefaultContractFacadeService(@Value("${payment.endpoint.user.id}") Long mainAdminUserId) {
		this.PAYMENT_ENDPOINT_ACCOUNT_ID = mainAdminUserId;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Contract submitContract(Contract contract, List<Vehicle> vehiclesToCancel) {
		contract = contractService.insertContract(contract);
		for(Vehicle vehicle : contract.getVehicles()) {
			vehicle.setCurrentContract(contract);
		}
		vehicleService.insertVehicles(contract.getVehicles());
		eliminateRemovedVehiclesFromContract(vehiclesToCancel);

		notificationManager
				.scheduleNotification(new OrderConfirmationEmailNotificationExecutor(notificationSender, contract));

		return contract;
	}
	
	@Override
	public void eliminateRemovedVehiclesFromContract(List<Vehicle> vehicles) {
		vehicleService.unmarkVehiclesAsOrdered(vehicles);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void shutdownContract(Long contractId) {
		Contract contract = contractService.aquireContract(contractId);
		List<Vehicle> vehicles = contract.getVehicles().stream().map(vehicle -> {
			vehicle.setCurrentContract(null);
			vehicle.setPreordered(false);
			return vehicle;
		}).collect(Collectors.toList());
		contract.setClosed(true);
		vehicleService.insertVehicles(vehicles);
		contractService.updateContractDetails(contract);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public User executePayment(Long contractId, Long userId) throws InsufficientFundsException {
		User user = userService.getUser(userId);
		user.setContracts(contractService.getContractsByUser(user));
		Contract contract = user.getContracts().stream()
				.filter(c -> c.getId().equals(contractId)).findFirst().get();
		Long fundAmount = user.getAccount().getBalance();
		if (fundAmount < contract.getTotalPrice()) {
			throw new InsufficientFundsException();
		}
		paymentService.subtractFundsFromAccount(user.getAccount().getId(), contract.getTotalPrice());
		user.getAccount().setBalance(fundAmount - contract.getTotalPrice());
		paymentService.performPayment(contract.getTotalPrice(), PAYMENT_ENDPOINT_ACCOUNT_ID);
		contract.setPaid(true);
		contractService.insertContract(contract);

		notificationManager
				.scheduleNotification(new PaymentConfirmationEmailNotificationExecutor(notificationSender, contract));
		return user;
	}

}
