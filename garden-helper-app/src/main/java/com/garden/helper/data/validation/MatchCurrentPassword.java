package com.garden.helper.data.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchCurrentPasswordValidator.class)
public @interface MatchCurrentPassword {

	public String message() default "Password does not match current user password";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
	
}
