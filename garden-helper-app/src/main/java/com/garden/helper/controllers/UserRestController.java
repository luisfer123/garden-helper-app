package com.garden.helper.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garden.helper.exceptions.UserNotFoundException;
import com.garden.helper.model.entity.User;
import com.garden.helper.model.payloads.user.UpdateUserInfo;
import com.garden.helper.model.payloads.user.UpdateUserPassword;
import com.garden.helper.model.payloads.user.UpdateUserPersonalInfo;
import com.garden.helper.security.JwtUtils;
import com.garden.helper.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping(path = "/{id}")
	public EntityModel<User> getUser(@PathVariable("id") Long userId) 
			throws UserNotFoundException {

		User user = userService.findById(userId);
		
		return EntityModel.of(user);
		
	}
	
	@PutMapping(path = "/{id}/userInfo")
	public ResponseEntity<?> updateUserInfo(
			@Valid @RequestBody UpdateUserInfo updatedData,
			HttpServletResponse response,
			@PathVariable("id") Long userId) throws UserNotFoundException {
		
		userService.updateUserInfo(updatedData, userId);
		
		// Once username or email is updated, we should log out the user
		Cookie cookie = jwtUtils.createJwtCookie(0, null);
		response.addCookie(cookie);
		SecurityContextHolder.getContext().setAuthentication(null);
		
		return ResponseEntity
				.accepted()
				.build();
		
	}
	
	@PutMapping(path = "/{id}/userPassword")
	public ResponseEntity<?> updateUserPassword(
			@Valid @RequestBody UpdateUserPassword updatedData, 
			HttpServletResponse response,
			@PathVariable("id") Long userId) throws UsernameNotFoundException {
		
		userService.updateUserPassword(updatedData, userId);
		
		// Once password is updated, we should log out the user
		Cookie cookie = jwtUtils.createJwtCookie(0, null);
		response.addCookie(cookie);
		SecurityContextHolder.getContext().setAuthentication(null);
				
		return ResponseEntity
				.accepted()
				.build();
		
	}
	
	@PutMapping(path = "/{id}/personalInfo")
	public EntityModel<User> updateUserPersonalInfo(
			@Valid @RequestBody UpdateUserPersonalInfo updatedData ,
			@PathVariable("id") Long userId) throws UserNotFoundException {
		
		User updatedUser = 
				userService.updateUserPersonalInfo(updatedData, userId);
		
		return EntityModel.of(updatedUser);
	}
	
	
	

}
