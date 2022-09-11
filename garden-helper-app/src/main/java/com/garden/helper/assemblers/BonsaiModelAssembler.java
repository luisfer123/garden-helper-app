package com.garden.helper.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.garden.helper.controllers.BonsaiRestController;
import com.garden.helper.model.entity.Bonsai;
import com.garden.helper.model.models.BonsaiModel;
import com.garden.helper.services.PlantService;

@Component
public class BonsaiModelAssembler extends RepresentationModelAssemblerSupport<Bonsai, BonsaiModel> {

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private PlantService plantService;
	
	public BonsaiModelAssembler() {
		super(BonsaiRestController.class, BonsaiModel.class);
	}

	@Override
	public BonsaiModel toModel(Bonsai bonsai) {
		
		BonsaiModel bonsaiModel = instantiateModel(bonsai);
		
		byte[] image = null;
		bonsaiModel.add(linkTo(
		        methodOn(BonsaiRestController.class)
		        .getBonsaiById(bonsai.getId()))
		        .withSelfRel());
		
		// Creating a String representation of the thumbnailImage in Bonsai
		if(bonsai.getThumbnailImage() != null 
				&& bonsai.getThumbnailImage().length >= 0) {
			
			// Decompress and encode into string the thumbnailImage 
			image =	plantService.decompressBytes(bonsai.getThumbnailImage());
			
		} else {
			// If there is not thumbnailImage we add the default one
			try {
				Resource imageResource = 
						resourceLoader.getResource("classpath:static/images/no-thumbnail-picture-bonsai.jpg");
				image = Files.readAllBytes(Paths.get(imageResource.getURI()));
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		bonsaiModel.setName(bonsai.getName());
		bonsaiModel.setId(bonsai.getId());
		bonsaiModel.setBonsaiStyle(bonsai.getStyle());
		bonsaiModel.setBonsaiType(bonsai.getType());
		bonsaiModel.setThumbnailImage(image);
		
		return bonsaiModel;
		
	}

}
