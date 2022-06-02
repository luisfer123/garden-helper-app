package com.garden.helper.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.garden.helper.model.entity.Authority;
import com.garden.helper.model.entity.User;
import com.garden.helper.model.enums.EAuthority;
import com.garden.helper.model.payloads.MessageResponse;
import com.garden.helper.model.payloads.security.IsAuthenticatedResponse;
import com.garden.helper.model.payloads.security.LoginRequest;
import com.garden.helper.model.payloads.security.LoginResponse;
import com.garden.helper.model.payloads.security.SignupRequest;
import com.garden.helper.repositories.AuthorityRepository;
import com.garden.helper.repositories.UserRepository;
import com.garden.helper.security.JwtUtils;
import com.garden.helper.security.UserDetailsImpl;

@Controller
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthorityRepository authRepo;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping(path = "/login")
	public ResponseEntity<LoginResponse> authenticateUser(
			@Valid @RequestBody LoginRequest loginRequest,
			HttpServletResponse response) throws AuthenticationException {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext()
			.setAuthentication(authentication);
		
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		Cookie cookie = new Cookie("jwt-token", jwt);
		cookie.setPath("/api");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(1800);
		// TODO: when in production must do cookie.setSecure(true);
		response.addCookie(cookie);
		
		UserDetailsImpl userDetails = 
				(UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities()
				.stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(
				new LoginResponse(
						userDetails.getId(),
						userDetails.getUsername(),
						userDetails.getEmail(),
						roles));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(
			@Valid @RequestBody SignupRequest signupRequest) {
		
		if(userRepo.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		
		if(userRepo.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		User user = new User(signupRequest.getUsername(),
				encoder.encode(signupRequest.getPassword()),
				signupRequest.getEmail());
		
		Set<String> strRoles = signupRequest.getRoles();
		Set<Authority> authorities = new HashSet<>();
		
		if(strRoles == null || strRoles.size() == 0 || !strRoles.contains("user")) {
			Authority userAuth = authRepo.findByName(EAuthority.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: ROLE_USER was not found."));
			authorities.add(userAuth);
		} else {
			strRoles.forEach(role -> {
				switch(role) {
				case "admin":
					Authority adminAuth = authRepo.findByName(EAuthority.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: ROLE_ADMIN was not found."));
					authorities.add(adminAuth);
					break;
					
				// If more Roles are added, add here the corresponding cases					
				}
			});
		}
		
		user.setAuthorities(authorities);
		userRepo.save(user);
		
		return ResponseEntity.ok(
				new MessageResponse("User registered successfully!"));
		
	}
	
	@GetMapping(path = "/logout")
	public ResponseEntity<MessageResponse> logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("jwt-token", null);
		cookie.setPath("/api");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0);
		// TODO: When in production must do cookie.setSecure(true);
		response.addCookie(cookie);
		
		SecurityContextHolder.getContext().setAuthentication(null);
		
		return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
	}
	
	@GetMapping(path="/isAuthenticated")
	public ResponseEntity<?> isLoggedin(HttpServletRequest request) {
		
		boolean response = false;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			
			Cookie tokenCookie = null;
			for(Cookie cookie : cookies) {
				if(cookie.getName().equalsIgnoreCase("jwt-token"))
					tokenCookie = cookie;
			}
			
			if(tokenCookie != null) {
				String jwt = tokenCookie.getValue();
				if(jwt != null && jwtUtils.validateJwtToken(jwt))
					response = true;
			}
		}
		
		return ResponseEntity.ok(new IsAuthenticatedResponse(response));
	}

}
