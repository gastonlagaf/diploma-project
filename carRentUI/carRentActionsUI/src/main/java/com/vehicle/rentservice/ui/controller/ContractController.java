package com.vehicle.rentservice.ui.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vehicle.rentservice.facade.api.ContractFacadeService;
import com.vehicle.rentservice.facade.exception.InsufficientFundsException;
import com.vehicle.rentservice.model.entity.Contract;
import com.vehicle.rentservice.model.entity.User;
import com.vehicle.rentservice.model.entity.Vehicle;
import com.vehicle.rentservice.model.request.OrderContractRequest;
import com.vehicle.rentservice.model.request.PerformPaymentOrShutdownRequest;
import com.vehicle.rentservice.service.api.ContractService;
import com.vehicle.rentservice.ui.converter.RequestConverter;

@Controller
public class ContractController {

	@Inject
	private ContractService contractService;

	@Inject
	private ContractFacadeService contractFacadeService;

	@Inject
	@Qualifier("insertContractRequestConverter")
	private RequestConverter<Contract, OrderContractRequest> requestConverter;

	@Secured("ROLE_USER")
	@RequestMapping(path = "/contract/form", method = RequestMethod.GET)
	public String viewContractForm() {
		return "forms/order";
	}

	@Secured("ROLE_USER")
	@RequestMapping(path = "/contract/payment/go", method = RequestMethod.POST)
	public ResponseEntity<String> performContractPayment(@RequestBody PerformPaymentOrShutdownRequest request,
			HttpSession session) {
		User user = (User)session.getAttribute("user");
		try {
			session.setAttribute("user", contractFacadeService.executePayment(request.getContractId(), user.getId()));
		} catch (InsufficientFundsException e) {
			return new ResponseEntity<>("InsufficientFunds", HttpStatus.PAYMENT_REQUIRED);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_USER")
	@RequestMapping(path = "/contract/submit", method = RequestMethod.POST)
	public void submitContract(@RequestBody OrderContractRequest request, HttpSession session) {
		User user = (User) session.getAttribute("user");
		request.setUser(user);
		request.setBasket((List) session.getAttribute("basket"));
		Contract contract = requestConverter.convertRequest(request);
		List<Vehicle> vehiclesToCancel = new ArrayList<>(request.getBasket());
		vehiclesToCancel.removeAll(contract.getVehicles());
		Contract submittedContract = contractFacadeService.submitContract(contract, vehiclesToCancel);
		session.removeAttribute("basket");
		user.getContracts().add(submittedContract);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_USER")
	@RequestMapping(path = "/contract/removed/make/available", method = RequestMethod.POST)
	public void eliminateRemovedVehicles(@RequestBody OrderContractRequest request, HttpSession session) {
		List<Vehicle> orderedVehicles = (List) session.getAttribute("basket");
		List<Vehicle> vehiclesToRemove = new ArrayList<>();
		Iterator<Vehicle> it = orderedVehicles.iterator();
		while (it.hasNext()) {
			Vehicle iterableVehicle = it.next();
			if (request.getVehicleIds().contains(iterableVehicle.getId())) {
				vehiclesToRemove.add(iterableVehicle);
				it.remove();
			}
		}
		contractFacadeService.eliminateRemovedVehiclesFromContract(vehiclesToRemove);
		session.setAttribute("basket", orderedVehicles);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/contracts/page/{page}", method = RequestMethod.GET)
	public String viewContracts(ModelMap model, @PathVariable("page") int page) {
		List<Contract> contracts = contractService.getContracts(page);
		model.addAttribute("contracts", contracts);
		model.addAttribute("page", page);
		return "admin/view-contracts";
	}

	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/contract/shutdown", method = RequestMethod.POST)
	public void closeContract(@RequestBody PerformPaymentOrShutdownRequest request) {
		contractFacadeService.shutdownContract(request.getContractId());
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/contract/update", method = RequestMethod.GET)
	public String viewContractUpdateSuccess() {
		return "contractUpdateSuccess";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "*/admin/contract", method = RequestMethod.PUT)
	public String updateContractDetails(@ModelAttribute("contract") Contract contract) {
		contractService.updateContractDetails(contract);
		return "redirect:admin/contract/update";
	}
}
