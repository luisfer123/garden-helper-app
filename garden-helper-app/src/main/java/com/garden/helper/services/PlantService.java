package com.garden.helper.services;

import com.garden.helper.exceptions.EntityNotFoundException;
import com.garden.helper.model.entity.Plant;

public interface PlantService {
	
	Plant findById(Long plantId) throws EntityNotFoundException;
	
	byte[] compressBytes(byte[] data);
	
	byte[] decompressBytes(byte[] data);

}
