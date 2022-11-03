package com.garden.helper.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.garden.helper.assemblers.RepottingInfoModelAssembler;
import com.garden.helper.data.entities.RepottingInfo;
import com.garden.helper.data.models.RepottingInfoModel;
import com.garden.helper.services.RepottingInfoService;

@RestController
@RequestMapping(path = "/api/v1/plants/{plantId}/repotting_info")
public class RepottingInfoController {
	
	// Default values
	private static final int DEFAULT_PAGE_NUM = 0;
	private static final int DEFAULT_PAGE_SIZE = 9;
	private static final String DEFAULT_PAGE_SORT_BY = "repotedAt";
	
	@Autowired
	private RepottingInfoService repottingInfoService;
	
	@Autowired
	private RepottingInfoModelAssembler repottingModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<RepottingInfo> pagedResourceAssembler;
	
	@GetMapping()
	public ResponseEntity<PagedModel<RepottingInfoModel>> getRepottingInfoPage(
			@PathVariable("plantId") Long plantId,
			@RequestParam("page_num") Optional<Integer> optPageNum,
			@RequestParam("page_size") Optional<Integer> optPageSize,
			@RequestParam("sort_by") Optional<String> optSortBy) {
		
		int pageNum = optPageNum.orElse(DEFAULT_PAGE_NUM);
		int pageSize = optPageSize.orElse(DEFAULT_PAGE_SIZE);
		String sortBy = optSortBy.orElse(DEFAULT_PAGE_SORT_BY);
		
		Page<RepottingInfo> repottingInfoPage = 
				repottingInfoService.findAll(plantId, pageNum, pageSize, sortBy);
		
		PagedModel<RepottingInfoModel> repottingInfoModelPage =
				pagedResourceAssembler.toModel(repottingInfoPage, repottingModelAssembler);
				
		return ResponseEntity.ok(repottingInfoModelPage);
	}

}
