package com.garden.helper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garden.helper.exceptions.UserNotFoundException;
import com.garden.helper.model.entity.User;
import com.garden.helper.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/{id}")
	public EntityModel<User> getUser(@PathVariable("id") Long userId) 
			throws UserNotFoundException {

		User user = userService.findById(userId);
		
		return EntityModel.of(user);
		
	}

}
