package com.garden.helper.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.garden.helper.model.entity.User;
import com.garden.helper.services.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		User user = userService.findByUsernameWithAuthorities(username);
		
		return UserDetailsImpl.build(user);
	}

}
