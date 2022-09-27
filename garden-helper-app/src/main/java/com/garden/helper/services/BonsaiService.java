package com.garden.helper.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.garden.helper.data.entities.Bonsai;
import com.garden.helper.exceptions.SecurityPrincipalInvalidException;

public interface BonsaiService {
	
	Optional<Bonsai> findById(Long bonsaiId) throws IllegalArgumentException;
	
	Page<Bonsai> findAllForLoggedInUser(int pageNum, int pageSize, String sortBy);
	
	Page<Bonsai> findAllForGivenUser(Long userId, int pageNum, int pageSize, String sortBy);
	
	/**
	 * Saves the given {@linkplain Bonsai} to the data base. The 
	 * new {@linkplain Bonsai} entity is associated with the currently
	 * logged in user.
	 * 
	 * @param newBonsai {@linkplain Bonsai} entity to be saved.
	 * @return
	 * @throws IllegalArgumentException If {@code newBonsai} is {@code null}.
	 * @throws SecurityPrincipalInvalidException 
	 */
	Bonsai saveNewBonsaiForLoggedInUser(Bonsai newBonsai) throws IllegalArgumentException, SecurityPrincipalInvalidException;

}
