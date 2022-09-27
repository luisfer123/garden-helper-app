package com.garden.helper.services;

import com.garden.helper.data.entities.Plant;
import com.garden.helper.exceptions.EntityNotFoundException;

public interface PlantService {
	
	Plant findById(Long plantId) throws EntityNotFoundException;

}
