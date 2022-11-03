package com.garden.helper.services;

import org.springframework.data.domain.Page;

import com.garden.helper.data.entities.RepottingInfo;

public interface RepottingInfoService {
	
	Page<RepottingInfo> findAll(
			Long plantId, int pageNum, int pageSize, String sortBy);

}
