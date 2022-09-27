package com.garden.helper.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.garden.helper.data.entities.Authority;
import com.garden.helper.data.entities.User;
import com.garden.helper.data.payloads.user.UpdateUsernameEmail;
import com.garden.helper.data.payloads.user.UpdateUserPassword;
import com.garden.helper.data.payloads.user.UpdateUserPersonalInfo;
import com.garden.helper.exceptions.UserNotFoundException;
import com.garden.helper.repositories.AuthorityRepository;
import com.garden.helper.repositories.UserRepository;
import com.garden.helper.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthorityRepository authRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
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

	@Override
	@Transactional(readOnly = true)
	@PreAuthorize(value = "hasRole('ADMIN') or principal.id == #userId")
	public User findById(Long userId) throws UserNotFoundException {
		return userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User with id " + userId + " does not exist."));
	}
	
	@Override
	@Transactional(readOnly = true)
	@PreAuthorize(value = "hasRole('ADMIN')")
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	@PreAuthorize(value = "hasRole('ADMIN')")
	public Page<User> findPage(int pageNum, int pageSize, String sortBy) {
		
		Pageable paging = 
				PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		
		return userRepo.findAll(paging);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	@PreAuthorize(value = "hasRole('ADMIN') or principal.id == #userId")
	public void updateUserInfo(UpdateUsernameEmail updatedData, Long userId) 
			throws UserNotFoundException {
		
		User user = this.findById(userId);
		
		user.setUsername(updatedData.getUsername());
		user.setEmail(updatedData.getEmail());
		
		userRepo.save(user);
		
	}
	
	@Override
	@Transactional
	@PreAuthorize(value = "hasRole('ADMIN') or principal.id == #userId")
	public void updateUserPassword(UpdateUserPassword updatedData, Long userId)
			throws UserNotFoundException {
		
		User user = this.findById(userId);
		user.setPassword(encoder.encode(updatedData.getNewPassword()));
		
		userRepo.save(user);
	}
	
	@Override
	@Transactional
	@PreAuthorize(value = "hasRole('ADMIN') or principal.id == #userId")
	public User updateUserPersonalInfo(UpdateUserPersonalInfo updatedData, Long userId)
			throws UserNotFoundException {
		
		User user = this.findById(userId);
		
		user.setFirstName(updatedData.getFirstName());
		user.setMiddleName(updatedData.getMiddleName());
		user.setLastName(updatedData.getLastName());
		user.setSecondLastName(updatedData.getSecondLastName());
		
		return userRepo.save(user);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean existsByUsername(String username) {
		return userRepo.existsByUsername(username);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}

}
