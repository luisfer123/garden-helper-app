package com.garden.helper.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garden.helper.model.entity.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long> {
	
	Optional<Plant> findById(Long plantId);

}