package com.vehicle.rentservice.model.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vehicle.rentservice.model.validation.Email;

public class EmailConstraint implements ConstraintValidator<Email, String> {
	
	private final String EMAIL_REGEX = "[a-zA-Z_0-9]+@[a-zA-Z_0-9]+.[a-zA-Z]";
	
	//According to RFC 2821
	private final int MAX_EMAIL_LENGTH = 254;
	
	@Override
	public void initialize(Email constraintAnnotation) {
		//There is no annotation properties to process	
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.length() == 0 || value.length() == MAX_EMAIL_LENGTH) {
			return false;
		} else if(!value.matches(EMAIL_REGEX)) {
			return false;
		}
		return true;
	}
}
