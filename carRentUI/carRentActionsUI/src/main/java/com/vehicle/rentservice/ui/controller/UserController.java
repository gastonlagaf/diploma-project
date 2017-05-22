package com.vehicle.rentservice.ui.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vehicle.rentservice.model.entity.User;
import com.vehicle.rentservice.model.request.AddFundsAmountRequest;
import com.vehicle.rentservice.model.request.PerformRegistrationRequest;
import com.vehicle.rentservice.model.request.UpdateCredentialsRequest;
import com.vehicle.rentservice.service.api.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Inject
	private UserService userService;
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String viewLogin() {
		return "forms/login";
	}
	
	
	@Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(path = "/login/ok", method = RequestMethod.GET)
	public String authentificationSuccess(HttpSession session, Authentication authentication) {
		String name = authentication.getName();
		User user = userService.getByUsername(name);
		session.setAttribute("user", user);
		return "custom/login-response";
	}
	
	@RequestMapping(path = "/logout/ok", method = RequestMethod.GET)
	public String logoutSuccess(HttpSession session) {
		return "custom/login-response";
	}
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@RequestMapping(path = "/login/fail",method = RequestMethod.GET)
	public void authentificationFailure() {
		//just send the failure message
	}

	@ResponseStatus(value = HttpStatus.OK) 
	@RequestMapping(path = "/register" , method = RequestMethod.POST)
	public void performRegistration(@RequestBody PerformRegistrationRequest registrationRequest) {
		if(registrationRequest.getCheckPassword().equals(registrationRequest.getUser().getPassword())) {
			userService.registerUser(registrationRequest.getUser());
		} else {
			throw new InsufficientAuthenticationException("Password for check not matching to original one");
		}
	}

	@Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(path = "/password/change", method = RequestMethod.POST)
	public void updatePassword(@RequestBody UpdateCredentialsRequest updateRequest, HttpSession session) {
		if(updateRequest.getNewPassword().equals(updateRequest.getRepeatNewPassword())) {
			User user = (User)session.getAttribute("user");
			userService.updatePassword(user, updateRequest.getNewPassword(), updateRequest.getOldPassword());
		} else {
			throw new InsufficientAuthenticationException("New Password not matching to repeated one");
		}
	}
	
	@Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(path = "/account/funds/add", method = RequestMethod.POST)
	public void updateAccountBalance(@RequestBody AddFundsAmountRequest request, HttpSession session) {
		User user = (User)session.getAttribute("user");
		userService.updateAccountInfo(user.getAccount(), request.getAmount());
	}
	
	@Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(path = "/cabinet", method = RequestMethod.GET)
	public String viewUserCabinet(HttpServletRequest request) {
		if(request.isUserInRole("ROLE_ADMIN")) {
			return "admin/admin-cabinet";
		} else {
			return "user/user-cabinet";
		}
	}
	
	@ExceptionHandler(InsufficientAuthenticationException.class)
	public ResponseEntity<String> handleAuthentificationException() {
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
