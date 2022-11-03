package com.garden.helper.assemblers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.garden.helper.controllers.FertilisationInfoController;
import com.garden.helper.data.entities.FertilisationInfo;
import com.garden.helper.data.entities.Fertilizer;
import com.garden.helper.data.models.FertilisationInfoModel;
import com.garden.helper.data.models.FertilizerModel;

@Component
public class FertilisationInfoModelAssembler extends RepresentationModelAssemblerSupport<FertilisationInfo, FertilisationInfoModel> {

	public FertilisationInfoModelAssembler(Class<?> controllerClass, Class<FertilisationInfoModel> resourceType) {
		super(controllerClass, resourceType);
	}
	
	public FertilisationInfoModelAssembler() {
		this(FertilisationInfoController.class, FertilisationInfoModel.class);
	}

	@Override
	public FertilisationInfoModel toModel(FertilisationInfo fertilisationInfo) {
		
		FertilisationInfoModel fertilisationInfoModel = instantiateModel(fertilisationInfo);
		
		fertilisationInfoModel.setId(fertilisationInfo.getId());
		fertilisationInfoModel.setFertilizedAt(fertilisationInfo.getFertilizedAt());
		fertilisationInfoModel.setFertilizers(
				fertilizersToModel(new ArrayList<>(fertilisationInfo.getFertilizers())));
		
		return fertilisationInfoModel;
	}
	
	@Override
	public CollectionModel<FertilisationInfoModel> toCollectionModel(
			Iterable<? extends FertilisationInfo> entities) {
		
		CollectionModel<FertilisationInfoModel> fertilizationInfoModels = 
				super.toCollectionModel(entities);
		
		return fertilizationInfoModels;
	}

	private List<FertilizerModel> fertilizersToModel(List<Fertilizer> fertilizers) {
		return fertilizers.stream()
				.map(fertilizer -> FertilizerModel.builder()
					.id(fertilizer.getId())
					.name(fertilizer.getName())
					.nitrogen(fertilizer.getNitrogen())
					.phosphorus(fertilizer.getPhosphorus())
					.potassium(fertilizer.getPotassium())
					.type(fertilizer.getType())
					.build())
				.collect(Collectors.toList());
	}

}
