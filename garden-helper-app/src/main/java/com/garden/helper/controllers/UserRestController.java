package com.garden.helper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garden.helper.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserRestController {
	
	@Autowired
	private UserService userService;

}
