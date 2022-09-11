package com.garden.helper.controllers;

import java.util.NoSuchElementException;
import java.util.Optional;

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
import com.garden.helper.model.entity.Bonsai;
import com.garden.helper.model.models.BonsaiModel;
import com.garden.helper.services.BonsaiService;

@Controller
@RequestMapping(path = "/api/v1/plants/bonsais")
public class BonsaiRestController {
	
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
		
		int pageNum = optPageNum.orElse(0);
		int pageSize = optPageSize.orElse(9);
		String sortBy = optSortBy.orElse("creationDate");
		
		Page<Bonsai> bonsaisPage = 
				bonsaiService.findAllForLoggedInUser(pageNum, pageSize, sortBy);
		
		PagedModel<BonsaiModel> bonsaiModelPage = pagedResourceAssembler
				.toModel(bonsaisPage, bonsaiModelAssembler);
		
		return ResponseEntity.ok(bonsaiModelPage);
	}
	
	@GetMapping(path = "")
	public ResponseEntity<PagedModel<BonsaiModel>> getBonsaisPageForGivenUser(
			@RequestParam("user_id") Optional<Long> optUserId,
			@RequestParam("pageNum") Optional<Integer> optPageNum,
			@RequestParam("pageSize") Optional<Integer> optPageSize,
			@RequestParam("sortBy") Optional<String> optSortBy) {
		
		Long userId = null;
		try {
			userId = optUserId.get();
		} catch(NoSuchElementException e) {
			return ResponseEntity
					.badRequest()
					.build();
		}
				
		int pageNum = optPageNum.orElse(0);
		int pageSize = optPageSize.orElse(9);
		String sortBy = optSortBy.orElse("creationDate");
		
		Page<Bonsai> bonsaisPage = 
				bonsaiService.findAllForGivenUser(userId, pageNum, pageSize, sortBy);
				
		PagedModel<BonsaiModel> bonsaiModelPage = pagedResourceAssembler
				.toModel(bonsaisPage, bonsaiModelAssembler);
				
		return ResponseEntity.ok(bonsaiModelPage);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<BonsaiModel> getBonsaiById(@PathVariable("id") Long id) {
		
		return bonsaiService.findById(id)
				.map(bonsaiModelAssembler::toModel)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

}
