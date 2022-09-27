package com.garden.helper.controllers;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import com.garden.helper.assemblers.UserModelAssembler;
import com.garden.helper.data.entities.User;
import com.garden.helper.data.models.UserModel;
import com.garden.helper.data.payloads.user.UpdateUserPassword;
import com.garden.helper.data.payloads.user.UpdateUserPersonalInfo;
import com.garden.helper.data.payloads.user.UpdateUsernameEmail;
import com.garden.helper.exceptions.UserNotFoundException;
import com.garden.helper.security.JwtUtils;
import com.garden.helper.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserRestController {
	
	private static final int USER_PAGE_NUM = 0;
	private static final int USER_PAGE_SIZE = 9;
	private static final String USER_PAGE_SORT_BY = "lastName";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserModelAssembler  userModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<User> pagedResourcesAssembler;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserModel> findUserById(@PathVariable("id") Long userId) 
			throws UserNotFoundException {
		
		return ResponseEntity.ok(userModelAssembler
				.toModel(userService.findById(userId)));
	}
	
	@GetMapping
	public RedirectView findPageDefault(
			@RequestParam("page_num") Optional<Integer> optPageNum,
			@RequestParam("page_size") Optional<Integer> optPageSize,
			@RequestParam("sort_by") Optional<String> optSortBy,
			HttpServletRequest request) {

		int pageNum = optPageNum.orElse(USER_PAGE_NUM);
		int pageSize = optPageSize.orElse(USER_PAGE_SIZE);
		String sortBy = optSortBy.orElse(USER_PAGE_SORT_BY);
		
		URI uri = UriComponentsBuilder
				.fromUriString(request.getRequestURI())
				.queryParam("page_num", pageNum)
				.queryParam("page_size", pageSize)
				.queryParam("sort_by", sortBy)
				.build()
				.toUri();
		
		return new RedirectView(uri.toString());
	}
	
	@GetMapping(params = {"page_num", "page_size", "sort_by"})
	ResponseEntity<PagedModel<UserModel>> findPage(
			@RequestParam("page_num") Integer pageNum,
			@RequestParam("page_size") Integer pageSize,
			@RequestParam("sort_by") String sortBy) {
		
		return ResponseEntity.ok(pagedResourcesAssembler
				.toModel(userService.findPage(pageNum, pageSize, sortBy), userModelAssembler));
	}
	
	@PutMapping(path = "/{id}/userInfo")
	public ResponseEntity<?> updateUserInfo(
			@Valid @RequestBody UpdateUsernameEmail updatedData,
			HttpServletResponse response,
			@PathVariable("id") Long userId) throws UserNotFoundException {
		
		userService.updateUserInfo(updatedData, userId);
		
		// Once username or email is updated, we should log out the user
		Cookie cookie = JwtUtils.createJwtCookie(0, null);
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
		Cookie cookie = JwtUtils.createJwtCookie(0, null);
		response.addCookie(cookie);
		SecurityContextHolder.getContext().setAuthentication(null);
				
		return ResponseEntity
				.accepted()
				.build();
		
	}
	
	@PutMapping(path = "/{id}/personalInfo")
	public ResponseEntity<UserModel> updateUserPersonalInfo(
			@Valid @RequestBody UpdateUserPersonalInfo updatedData,
			@PathVariable("id") Long userId) throws UserNotFoundException {
		
		User updatedUser = 
				userService.updateUserPersonalInfo(updatedData, userId);
		
		return new ResponseEntity<>(
				userModelAssembler.toModel(updatedUser), HttpStatus.ACCEPTED);
	}
	
}
