package com.garden.helper.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.garden.helper.data.entities.Plant;
import com.garden.helper.exceptions.EntityNotFoundException;
import com.garden.helper.repositories.PlantRepository;
import com.garden.helper.services.PlantService;

@Service
public class PlantServiceImpl implements PlantService {
	
	@Autowired
	private PlantRepository plantRepo;
	
	@Override
	@Transactional(readOnly = true)
	public Plant findById(Long plantId) throws EntityNotFoundException {
		return plantRepo.findById(plantId)
				.orElseThrow(() -> new EntityNotFoundException("Entity of type Plant with id: " + plantId + " does not exists."));
	}

}
