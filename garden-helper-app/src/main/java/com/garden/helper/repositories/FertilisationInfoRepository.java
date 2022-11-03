package com.garden.helper.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.garden.helper.data.entities.FertilisationInfo;

public interface FertilisationInfoRepository extends JpaRepository<FertilisationInfo, Long> {
	
	Page<FertilisationInfo> getPageByPlantId(Long plantId, Pageable pageable);

}
