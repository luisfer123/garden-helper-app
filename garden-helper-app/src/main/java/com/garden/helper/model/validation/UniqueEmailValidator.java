package com.garden.helper.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.garden.helper.services.UserService;

public class UniqueEmailValidator
		implements ConstraintValidator<UniqueEmail, String> {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(userService == null)
			return true;
		
		return value != null &&
				!userService.existsByEmail(value);
	}

}
