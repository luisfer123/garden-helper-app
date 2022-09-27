package com.garden.helper.services.impl;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.garden.helper.data.entities.Bonsai;
import com.garden.helper.data.entities.User;
import com.garden.helper.exceptions.SecurityPrincipalInvalidException;
import com.garden.helper.repositories.BonsaiRepository;
import com.garden.helper.repositories.UserRepository;
import com.garden.helper.security.SecurityHelper;
import com.garden.helper.services.BonsaiService;

@Service
public class BonsaiServiceImpl implements BonsaiService {
	
	@Autowired
	private BonsaiRepository bonsaiRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public Optional<Bonsai> findById(Long bonsaiId) throws IllegalArgumentException {
		return bonsaiRepo.findById(bonsaiId);
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
