package com.garden.helper.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.garden.helper.repositories.PlantRepository;
import com.garden.helper.security.UserDetailsImpl;
import com.garden.helper.services.SecurityService;

@Service(value = "securityService")
public class SecurityServiceImpl implements SecurityService {
	
	@Autowired
	private PlantRepository plantRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean plantBelongsToUser(Object opjPrincipal, Long plantId) {
		
		UserDetailsImpl principal = (UserDetailsImpl) opjPrincipal;
		
		return plantRepo.belongsToGivenUser(plantId, principal.getId());
	}

}
