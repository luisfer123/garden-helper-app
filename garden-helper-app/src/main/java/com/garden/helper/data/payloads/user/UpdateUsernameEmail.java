package com.garden.helper.data.payloads.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.garden.helper.data.validation.UniqueEmail;
import com.garden.helper.data.validation.UniqueUsername;

public class UpdateUsernameEmail {
	
	@NotBlank
	@UniqueUsername
	private String username;
	
	@Email
	@UniqueEmail
	private String email;

	public UpdateUsernameEmail() {
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
