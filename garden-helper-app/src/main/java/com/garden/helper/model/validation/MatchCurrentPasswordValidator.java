package com.garden.helper.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MatchCurrentPasswordValidator
		implements ConstraintValidator<MatchCurrentPassword, String> {
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(encoder == null)
			return true;
		
		UserDetails principal = 
				(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal == null)
			return true;
		
		return encoder.matches(value, principal.getPassword());
	}

}
