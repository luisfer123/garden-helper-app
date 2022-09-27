package com.garden.helper.data.payloads.user;

import javax.persistence.Persistence;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.xml.crypto.Data;

import org.hibernate.type.Type;

import com.garden.helper.data.validation.FieldMatch;
import com.garden.helper.data.validation.MatchCurrentPassword;

@GroupSequence({
	Type.class, 
	Data.class, 
	Persistence.class, 
	UpdateUserPassword.class})
@FieldMatch(
		groups = Data.class, 
		first = "newPassword", 
		second = "repeatNewPassword", 
		message = "The Password and Confirm Password fields must match.")
public class UpdateUserPassword {
	
	@MatchCurrentPassword
	private String currentPassword;
	
	@NotBlank
	private String newPassword;
	
	private String repeatNewPassword;

	public UpdateUserPassword() {
		super();
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatNewPassword() {
		return repeatNewPassword;
	}

	public void setRepeatNewPassword(String repeatNewPassword) {
		this.repeatNewPassword = repeatNewPassword;
	}

}
