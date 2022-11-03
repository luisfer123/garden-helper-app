package com.garden.helper.services.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.garden.helper.data.entities.Bonsai;
import com.garden.helper.data.entities.User;
import com.garden.helper.data.enums.EBonsaiStyle;
import com.garden.helper.data.enums.EBonsaiType;
import com.garden.helper.exceptions.EntityNotFoundException;
import com.garden.helper.exceptions.SecurityPrincipalInvalidException;
import com.garden.helper.repositories.BonsaiRepository;
import com.garden.helper.repositories.UserRepository;
import com.garden.helper.repositories.specifications.BonsaiSpecifications;
import com.garden.helper.security.SecurityHelper;
import com.garden.helper.services.BonsaiService;

@Service
public class BonsaiServiceImpl implements BonsaiService {
	
	@Autowired
	private BonsaiRepository bonsaiRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public Bonsai findById(Long bonsaiId) 
			throws IllegalArgumentException, EntityNotFoundException {
		return bonsaiRepo.findById(bonsaiId)
				.orElseThrow(() -> new EntityNotFoundException("Entity Bonsai with id " + bonsaiId + " was not found."));
	}
	
	@Override
	@Transactional(readOnly = true)
	@PreAuthorize("hasRole('ROLE_USER')")
	public Page<Bonsai> findAllForLoggedInUser(int pageNum, int pageSize, String sortBy) {
		Pageable paging = 
				PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		
		return bonsaiRepo.findAllForGivenUser(
				SecurityHelper.getPrincipalId(), paging);
	}
	
	@Override
	@Transactional(readOnly = true)
	@PreAuthorize("hasRole('ROLE_ADMIN') or #userId == authentication.principal.id")
	public Page<Bonsai> findAllForGivenUser(
			Long userId, int pageNum, int pageSize, String sortBy) {
		
		Pageable paging = 
				PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		
		return bonsaiRepo.findAllForGivenUser(
				userId, paging);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	@PreAuthorize("hasRole('ROLE_ADMIN') or #userId == authentication.principal.id")
	public Page<Bonsai> findByStyleForGivenUser(
			Long userId, EBonsaiStyle bonsaiStyle, int pageNum, int pageSize, String sortBy) {
		
		Pageable paging = 
				PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		
		return bonsaiRepo.findByStyleForGivenUser(userId, bonsaiStyle, paging);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	@PreAuthorize("hasRole('ROLE_ADMIN') or #userId == authentication.principal.id")
	public Page<Bonsai> findFilteredForGivenUser(
			Long userId, EBonsaiStyle bonsaiStyle, EBonsaiType bonsaiType, 
			int pageNum, int pageSize, String sortBy) {
		
		Pageable paging = 
				PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		
		Specification<Bonsai> specification = 
				Specification.where(bonsaiStyle == null ? null : BonsaiSpecifications.withBonsaiStyle(bonsaiStyle))
				.and(bonsaiType == null ? null : BonsaiSpecifications.withBonsaiType(bonsaiType))
				.and(userId == null ? null : BonsaiSpecifications.belongsToUserWithId(userId));
		
		return bonsaiRepo.findAll(specification, paging);
	}
	
	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public Bonsai saveNewBonsaiForLoggedInUser(Bonsai newBonsai) 
			throws IllegalArgumentException, SecurityPrincipalInvalidException {
		
		newBonsai.setCreationDate(new Timestamp(System.currentTimeMillis()));
		newBonsai.setLastUpdatedAt(new Timestamp(System.currentTimeMillis()));
		
		User user = userRepo.findById(SecurityHelper.getPrincipalId())
				.orElseThrow(() -> new SecurityPrincipalInvalidException("Principas object does not have a valid User Id"));
		user.addPlant(newBonsai);
				
		return bonsaiRepo.save(newBonsai);
		
	}

}
