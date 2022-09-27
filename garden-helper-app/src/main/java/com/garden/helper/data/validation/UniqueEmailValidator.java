package com.garden.helper.data.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.garden.helper.security.UserDetailsImpl;
import com.garden.helper.services.UserService;

public class UniqueEmailValidator
		implements ConstraintValidator<UniqueEmail, String> {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		UserDetailsImpl principal = 
				(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(userService == null || principal == null)
			return true;
		
		/* Email can already exist only if it belongs to the current logged in user.
		 *  It makes easier to implement update user functionality. */
		return value != null &&
				!userService.existsByEmail(value) ||
				(userService.existsByEmail(value) && principal.getEmail().equalsIgnoreCase(value));
	}

}
