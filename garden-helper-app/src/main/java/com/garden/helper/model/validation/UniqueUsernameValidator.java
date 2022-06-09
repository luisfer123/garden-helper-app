package com.garden.helper.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.garden.helper.services.UserService;

public class UniqueUsernameValidator
		implements ConstraintValidator<UniqueUsername, String> {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if(userService == null)
			return true;
		
		return value != null &&
				!userService.existsByUsername(value);
	}

}
