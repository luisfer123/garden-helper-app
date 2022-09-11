package com.garden.helper.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.garden.helper.exceptions.EntityNotFoundException;
import com.garden.helper.model.entity.Plant;
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
	
	@Override
	@Transactional
	public byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}
	
	@Override
	@Transactional
	public byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}
