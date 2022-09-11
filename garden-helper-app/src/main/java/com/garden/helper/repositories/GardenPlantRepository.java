package com.garden.helper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garden.helper.model.entity.GardenPlant;

public interface GardenPlantRepository extends JpaRepository<GardenPlant, Long> {

}
