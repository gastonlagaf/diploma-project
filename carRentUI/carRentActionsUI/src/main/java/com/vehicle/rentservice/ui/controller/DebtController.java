package com.vehicle.rentservice.ui.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DebtController {
	
	@RequestMapping(path = "/debt/*", method = RequestMethod.GET)
	public String viewDebtInfo() {
		return "debtView";
	}
	
	/****** ADMIN ACTIONS *****/
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/debt", method = RequestMethod.GET)
	public String viewDebtRegistrationForm() {
		return "debtRegistrationView";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/debt", method = RequestMethod.POST)
	public String registerDebt() {
		return "debtRegistrationSuccess";
	}
	
}
