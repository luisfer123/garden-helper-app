package com.garden.helper.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.garden.helper.assemblers.BonsaiModelAssembler;
import com.garden.helper.data.entities.Bonsai;
import com.garden.helper.data.enums.EBonsaiStyle;
import com.garden.helper.data.enums.EBonsaiType;
import com.garden.helper.data.models.BonsaiModel;
import com.garden.helper.services.BonsaiService;

@Controller
@RequestMapping(path = "/api/v1/plants/bonsais")
public class BonsaiRestController {
	
	private static final Logger log = 
			LoggerFactory.getLogger(BonsaiRestController.class);
	
	// Default values
	private static final int DEFAULT_PAGE_NUM = 0;
	private static final int DEFAULT_PAGE_SIZE = 9;
	private static final String DEFAULT_PAGE_SORT_BY = "creationDate";
	
	@Autowired
	private BonsaiService bonsaiService;
	
	@Autowired
	private BonsaiModelAssembler bonsaiModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Bonsai> pagedResourceAssembler;
	
	@GetMapping(path = "/my-bonsais")
	public ResponseEntity<PagedModel<BonsaiModel>> getBonsaisPageForLoggedInUser(
			@RequestParam("pageNum") Optional<Integer> optPageNum,
			@RequestParam("pageSize") Optional<Integer> optPageSize,
			@RequestParam("sortBy") Optional<String> optSortBy) {
		
		int pageNum = optPageNum.orElse(DEFAULT_PAGE_NUM);
		int pageSize = optPageSize.orElse(DEFAULT_PAGE_SIZE);
		String sortBy = optSortBy.orElse(DEFAULT_PAGE_SORT_BY);
		
		Page<Bonsai> bonsaisPage = 
				bonsaiService.findAllForLoggedInUser(pageNum, pageSize, sortBy);
		
		PagedModel<BonsaiModel> bonsaiModelPage = pagedResourceAssembler
				.toModel(bonsaisPage, bonsaiModelAssembler);
		
		return ResponseEntity.ok(bonsaiModelPage);
	}
	
	@GetMapping(path = "")
	public ResponseEntity<PagedModel<BonsaiModel>> getBonsaisPageForGivenUser(
			@RequestParam(name = "user_id", required = true) Long userId,
			@RequestParam("page_num") Optional<Integer> optPageNum,
			@RequestParam("page_size") Optional<Integer> optPageSize,
			@RequestParam("sort_by") Optional<String> optSortBy) {
				
		int pageNum = optPageNum.orElse(DEFAULT_PAGE_NUM);
		int pageSize = optPageSize.orElse(DEFAULT_PAGE_SIZE);
		String sortBy = optSortBy.orElse(DEFAULT_PAGE_SORT_BY);
		
		Page<Bonsai> bonsaisPage = 
				bonsaiService.findAllForGivenUser(userId, pageNum, pageSize, sortBy);
				
		PagedModel<BonsaiModel> bonsaiModelPage = pagedResourceAssembler
				.toModel(bonsaisPage, bonsaiModelAssembler);
				
		return ResponseEntity.ok(bonsaiModelPage);
	}
	
	@GetMapping(path = "/filtered")
	public ResponseEntity<PagedModel<BonsaiModel>> getBonsaisPageFilteredForGivenUser(
			@RequestParam(name = "user_id", required = true) Long userId,
			@RequestParam("page_num") Optional<Integer> optPageNum,
			@RequestParam("page_size") Optional<Integer> optPageSize,
			@RequestParam("sort_by") Optional<String> optSortBy,
			@RequestParam("bonsai_style") Optional<EBonsaiStyle> optBonsaiStyle,
			@RequestParam("bonsai_type") Optional<EBonsaiType> optBonsaiType) {
		
		int pageNum = optPageNum.orElse(DEFAULT_PAGE_NUM);
		int pageSize = optPageSize.orElse(DEFAULT_PAGE_SIZE);
		String sortBy = optSortBy.orElse(DEFAULT_PAGE_SORT_BY);
		EBonsaiStyle bonsaiStyle = optBonsaiStyle.orElse(null);
		EBonsaiType bonsaiType = optBonsaiType.orElse(null);
				
		Page<Bonsai> bonsaisPage = 
				bonsaiService.findFilteredForGivenUser(
						userId, bonsaiStyle, bonsaiType, pageNum, pageSize, sortBy);
		
		PagedModel<BonsaiModel> bonsaiModelPage = pagedResourceAssembler
				.toModel(bonsaisPage, bonsaiModelAssembler);
				
		return ResponseEntity.ok(bonsaiModelPage);
	
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<BonsaiModel> getBonsaiById(@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(
				bonsaiModelAssembler.toModel(
						bonsaiService.findById(id)));
	}

}
