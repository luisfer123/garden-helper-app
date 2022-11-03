package com.garden.helper.assemblers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.garden.helper.controllers.RepottingImageController;
import com.garden.helper.data.entities.RepottingImage;
import com.garden.helper.data.models.RepottingImageModel;

public class RepottingImageModelAssembler extends RepresentationModelAssemblerSupport<RepottingImage, RepottingImageModel> {

	public RepottingImageModelAssembler() {
		this(RepottingImageController.class, RepottingImageModel.class);
	}
	
	public RepottingImageModelAssembler(Class<?> controllerClass, Class<RepottingImageModel> resourceType) {
		super(controllerClass, resourceType);
	}

	@Override
	public RepottingImageModel toModel(RepottingImage image) {
		
		RepottingImageModel imageModel = instantiateModel(image);
		
		imageModel.setId(image.getId());
		imageModel.setImage(image.getImage());
		imageModel.setCreationDate(image.getCreationDate());
		imageModel.setNote(image.getNote());
		
		return imageModel;
	}
	
	@Override
	public CollectionModel<RepottingImageModel> toCollectionModel(
			Iterable<? extends RepottingImage> images) {
		
		CollectionModel<RepottingImageModel> repottingImages =
				super.toCollectionModel(images);

		return repottingImages;
	}
	

}
