package com.garden.helper.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.garden.helper.data.entities.User;
import com.garden.helper.data.payloads.user.UpdateUsernameEmail;
import com.garden.helper.data.payloads.user.UpdateUserPassword;
import com.garden.helper.data.payloads.user.UpdateUserPersonalInfo;
import com.garden.helper.exceptions.UserNotFoundException;

public interface UserService {
	
	User findByUsernameWithAuthorities(String username)	throws UsernameNotFoundException;
	
	User findById(Long id) throws UserNotFoundException;
	
	List<User> findAll();
	
	Page<User> findPage(int pageNum, int pageSize, String sortBy);
	
	/**
	 * Updates the {@code username} and/or {@code email} of a given {@linkplain User}. <br>
	 * Since username is used by default in Spring Security to identify the
	 * current logged in user, once {@code username} is updated, the {@code User} 
	 * should be logged out and asked to log in again, now with the new
	 * {@code username}.<br>
	 * {@code email} could be also used as identifier for the logged in user. So, after
	 * changing {@code email} it is also recommended to log out the user.
	 * 
	 * @param updatedData
	 * @param userId
	 * @throws UserNotFoundException
	 */
	void updateUserInfo(UpdateUsernameEmail updatedData, Long userId) throws UserNotFoundException;
	
	void updateUserPassword(UpdateUserPassword updatedData, Long userId) throws UserNotFoundException;
	
	User updateUserPersonalInfo(UpdateUserPersonalInfo updatedData, Long userId) throws UserNotFoundException;
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);

}
