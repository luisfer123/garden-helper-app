package com.garden.helper.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.garden.helper.model.entity.User;

public interface UserService {
	
	User findByUsernameWithAuthorities(String username)	throws UsernameNotFoundException;

}
