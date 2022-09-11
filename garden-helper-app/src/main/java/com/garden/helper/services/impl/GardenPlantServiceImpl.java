package com.garden.helper.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garden.helper.repositories.GardenPlantRepository;
import com.garden.helper.services.GardenPlantService;

@Service
public class GardenPlantServiceImpl implements GardenPlantService {
	
	@Autowired
	private GardenPlantRepository gardenPlantRepo;

}
