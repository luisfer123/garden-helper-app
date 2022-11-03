package com.garden.helper.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.garden.helper.data.entities.RepottingInfo;
import com.garden.helper.repositories.RepottingInfoRepository;
import com.garden.helper.services.RepottingInfoService;

@Service
public class RepottingInfoServiceImpl implements RepottingInfoService {
	
	@Autowired
	private RepottingInfoRepository repottingInfoRepo;
	
	@Override
	@Transactional(readOnly = true)
	@PreAuthorize("@securityService.plantBelongsToUser(authentication.principal, #plantId) or "
			+ "hasRole('ROLE_ADMIN')")
	public Page<RepottingInfo> findAll(
			Long plantId, int pageNum, int pageSize, String sortBy) {
		
		Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		
		return repottingInfoRepo.findAll(plantId, paging);
		
	}

}
