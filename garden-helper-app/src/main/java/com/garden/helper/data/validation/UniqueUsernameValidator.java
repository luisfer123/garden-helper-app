package com.garden.helper.data.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.garden.helper.security.UserDetailsImpl;
import com.garden.helper.services.UserService;

public class UniqueUsernameValidator
		implements ConstraintValidator<UniqueUsername, String> {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		UserDetailsImpl principal =
				(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(userService == null || principal == null)
			return true;
		
		/* Username can already exist only if it belongs to the current logged in user.
		 *  It makes easier to implement update user functionality. */
		return value != null &&
				!userService.existsByUsername(value) ||
				(userService.existsByUsername(value) && principal.getUsername().equalsIgnoreCase(value));
	}

}
