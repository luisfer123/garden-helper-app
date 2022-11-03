package com.garden.helper.services;

import org.springframework.data.domain.Page;

import com.garden.helper.data.entities.Bonsai;
import com.garden.helper.data.enums.EBonsaiStyle;
import com.garden.helper.data.enums.EBonsaiType;
import com.garden.helper.exceptions.EntityNotFoundException;
import com.garden.helper.exceptions.SecurityPrincipalInvalidException;

public interface BonsaiService {
	
	/**
	 * 
	 * @param bonsaiId
	 * @return
	 * @throws IllegalArgumentException if {@code bonsaiId} is null
	 * @throws EntityNotFoundException if no {@linkplain Bonsai} with id {@code bonsaiId}
	 * is found in database.
	 */
	Bonsai findById(Long bonsaiId) throws IllegalArgumentException, EntityNotFoundException;
	
	Page<Bonsai> findAllForLoggedInUser(int pageNum, int pageSize, String sortBy);
	
	Page<Bonsai> findAllForGivenUser(Long userId, int pageNum, int pageSize, String sortBy);
	
	/**
	 * Filters the result by including in the {@linkplain Page} only the 
	 * {@linkplain Bonsai}s which belong to given user and with {@code Bonsai.style} 
	 * property matching {@code bonsaiStyle} parameter.
	 * 
	 * @param userId
	 * @param bonsaiStyle
	 * @param pageNum
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	Page<Bonsai> findByStyleForGivenUser(Long userId, EBonsaiStyle bonsaiStyle, int pageNum, int pageSize, String sortBy);
	
	Page<Bonsai> findFilteredForGivenUser(
			Long userId, EBonsaiStyle bonsaiStyle, EBonsaiType bonsaiType, int pageNum, int pageSize, String sortBy);
	
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
