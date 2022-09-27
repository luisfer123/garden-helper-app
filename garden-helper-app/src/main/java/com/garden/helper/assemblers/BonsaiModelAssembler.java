package com.garden.helper.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.garden.helper.controllers.BonsaiRestController;
import com.garden.helper.data.entities.Bonsai;
import com.garden.helper.data.models.BonsaiModel;
import com.garden.helper.helpers.ImageHelper;

@Component
public class BonsaiModelAssembler extends RepresentationModelAssemblerSupport<Bonsai, BonsaiModel> {

	@Autowired
	private ResourceLoader resourceLoader;
	
	public BonsaiModelAssembler() {
		this(BonsaiRestController.class, BonsaiModel.class);
	}

	public BonsaiModelAssembler(Class<?> controllerClass, Class<BonsaiModel> resourceType) {
		super(controllerClass, resourceType);
	}

	@Override
	public BonsaiModel toModel(Bonsai bonsai) {
		
		BonsaiModel bonsaiModel = instantiateModel(bonsai);
		
		byte[] image = null;
		bonsaiModel.add(linkTo(
		        methodOn(BonsaiRestController.class)
		        .getBonsaiById(bonsai.getId()))
		        .withSelfRel());
		
		// try to get the image from the entity
		if(bonsai.getThumbnailImage() != null 
				&& bonsai.getThumbnailImage().length >= 0) {
			// Decompress thumbnailImage 
			image =	ImageHelper.decompressBytes(bonsai.getThumbnailImage());
			
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
		bonsaiModel.setLastUpdatedAt(bonsai.getLastUpdatedAt());
		
		return bonsaiModel;
		
	}
	
	@Override
	public CollectionModel<BonsaiModel> toCollectionModel(
			Iterable<? extends Bonsai> bonsais) {
		
		CollectionModel<BonsaiModel> bonsaiModels =
				super.toCollectionModel(bonsais);

		return bonsaiModels;
	}

}
