package com.vehicle.rentservice.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class PrimaryController {

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String viewContextRootView() {
		return "general/view";
	}
	
	@RequestMapping(path = "/about", method = RequestMethod.GET)
	public String viewAboutPage() {
		return "general/about";
	}
	
	@RequestMapping(path = "/reply", method = RequestMethod.GET)
	public String viewReplyPage() {
		return "forms/replyForm";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(path = "/locale/{locale}")
	public void changeLocale(@PathVariable("locale") String locale, HttpServletRequest request, HttpServletResponse response) {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		localeResolver.setLocale(request, response, StringUtils.parseLocaleString(locale));
	}
	
}
