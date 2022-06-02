package com.garden.helper.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.garden.helper.model.entity.Authority;
import com.garden.helper.model.entity.User;
import com.garden.helper.repositories.AuthorityRepository;
import com.garden.helper.repositories.UserRepository;
import com.garden.helper.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthorityRepository authRepo;
	
	@Override
	@Transactional(readOnly = true)
	public User findByUsernameWithAuthorities(String username)
			throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("No user with username: " + username + " was found."));
		
		Set<Authority> authorities = 
				authRepo.findUserAuthoritiesByUsername(username);
		
		authorities.forEach(auth -> user.addAuthority(auth));
		
		return user;
	}

}
