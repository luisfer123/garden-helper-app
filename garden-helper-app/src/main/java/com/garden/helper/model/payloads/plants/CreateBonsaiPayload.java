package com.garden.helper.model.payloads.plants;

import com.garden.helper.model.enums.EBonsaiStyle;
import com.garden.helper.model.enums.EBonsaiType;

public class CreateBonsaiPayload {
	
	private String name;
	
	private String thumbnailImage;
	
	private EBonsaiStyle bonsaiStyle;
	
	private EBonsaiType bonsaiType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public EBonsaiStyle getBonsaiStyle() {
		return bonsaiStyle;
	}

	public void setBonsaiStyle(EBonsaiStyle bonsaiStyle) {
		this.bonsaiStyle = bonsaiStyle;
	}

	public EBonsaiType getBonsaiType() {
		return bonsaiType;
	}

	public void setBonsaiType(EBonsaiType bonsaiType) {
		this.bonsaiType = bonsaiType;
	}

}
