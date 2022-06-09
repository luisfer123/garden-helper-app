package com.garden.helper.model.payloads.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.garden.helper.model.validation.UniqueEmail;
import com.garden.helper.model.validation.UniqueUsername;

public class UpdateUserInfo {
	
	@NotBlank
	@UniqueUsername
	private String username;
	
	@Email
	@UniqueEmail
	private String email;

	public UpdateUserInfo() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
