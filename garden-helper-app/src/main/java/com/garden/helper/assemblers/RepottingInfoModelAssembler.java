package com.garden.helper.assemblers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.garden.helper.controllers.RepottingInfoController;
import com.garden.helper.data.entities.RepottingInfo;
import com.garden.helper.data.models.RepottingInfoModel;

@Component
public class RepottingInfoModelAssembler 
		extends RepresentationModelAssemblerSupport<RepottingInfo, RepottingInfoModel> {
	
	public RepottingInfoModelAssembler() {
		this(RepottingInfoController.class, RepottingInfoModel.class);
	}

	public RepottingInfoModelAssembler(Class<?> controllerClass, Class<RepottingInfoModel> resourceType) {
		super(controllerClass, resourceType);
	}

	@Override
	public RepottingInfoModel toModel(RepottingInfo repottingInfo) {
		
		RepottingInfoModel repottingInfoModel = instantiateModel(repottingInfo);
		
		repottingInfoModel.setId(repottingInfo.getId());
		repottingInfoModel.setRepotedAt(repottingInfo.getRepotedAt());
		repottingInfoModel.setNextRepottingDate(repottingInfo.getNextRepottingDate());
		repottingInfoModel.setNote(repottingInfo.getNote());
		
		return repottingInfoModel;
	}
	
	@Override
	public CollectionModel<RepottingInfoModel> toCollectionModel(
			Iterable<? extends RepottingInfo> repottingInfos) {
		
		CollectionModel<RepottingInfoModel> repottingInfosModel =
				super.toCollectionModel(repottingInfos);

		return repottingInfosModel;
	}

}
