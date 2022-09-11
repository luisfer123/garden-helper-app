package com.garden.helper.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {
	
	public static Long getPrincipalId() {
		return ((UserDetailsImpl) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal())
				.getId();
	}

}
