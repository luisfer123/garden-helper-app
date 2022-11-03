package com.garden.helper.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.garden.helper.data.models.FertilisationInfoModel;
import com.garden.helper.repositories.FertilisationInfoRepository;

@RestController
@RequestMapping(path = "/api/v1/plants/{plantId}/fertilisation_info")
public class FertilisationInfoController {
	
	// Default values
	private static final int DEFAULT_PAGE_NUM = 0;
	private static final int DEFAULT_PAGE_SIZE = 9;
	private static final String DEFAULT_PAGE_SORT_BY = "fertilizedAt";
	
	@Autowired
	private FertilisationInfoRepository fertilisationInfoRepository;
	
	@GetMapping()
	public ResponseEntity<PagedModel<FertilisationInfoModel>> getFertilisationInfoPage(
			@PathVariable("plantId") Long plantId,
			@RequestParam(name = "user_id", required = true) Long userId,
			@RequestParam("page_num") Optional<Integer> optPageNum,
			@RequestParam("page_size") Optional<Integer> optPageSize,
			@RequestParam("sort_by") Optional<String> optSortBy) {
		
		int pageNum = optPageNum.orElse(DEFAULT_PAGE_NUM);
		int pageSize = optPageSize.orElse(DEFAULT_PAGE_SIZE);
		String sortBy = optSortBy.orElse(DEFAULT_PAGE_SORT_BY);
		
		return null;
	}
	
	

}
