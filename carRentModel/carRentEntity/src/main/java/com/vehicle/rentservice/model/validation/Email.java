package com.vehicle.rentservice.model.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vehicle.rentservice.model.constraints.EmailConstraint;

@Documented
@Constraint(validatedBy = EmailConstraint.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface Email {
	String message() default "Forbidden email address format detected";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
