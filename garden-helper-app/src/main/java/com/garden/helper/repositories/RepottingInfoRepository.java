package com.garden.helper.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.garden.helper.data.entities.RepottingInfo;

public interface RepottingInfoRepository extends JpaRepository<RepottingInfo, Long> {
	
	@Query(value = "select ri from RepottingInfo ri join ri.plant p where p.id = :plantId",
			countQuery = "select count(ri) from RepottingInfo ri join ri.plant p where p.id = :plantId")
	Page<RepottingInfo> findAll(@Param("plantId") Long plantId, Pageable page);
	
	List<RepottingInfo> findByPlantId(long id);

}
