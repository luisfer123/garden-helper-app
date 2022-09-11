package com.garden.helper.model.models;

import java.util.Arrays;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.garden.helper.model.enums.EBonsaiStyle;
import com.garden.helper.model.enums.EBonsaiType;

@JsonInclude(Include.NON_NULL)
public class BonsaiModel extends RepresentationModel<BonsaiModel> {
	
	private Long id;
	
	private String name;
	
	private byte[] thumbnailImage;
	
	private EBonsaiStyle bonsaiStyle;
	
	private EBonsaiType bonsaiType;

	public BonsaiModel() {
		super();
	}

	public BonsaiModel(Long id, String name, byte[] thumbnailImage, 
			EBonsaiStyle bonsaiStyle, EBonsaiType bonsaiType) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnailImage = thumbnailImage;
		this.bonsaiStyle = bonsaiStyle;
		this.bonsaiType = bonsaiType;
	}

	public BonsaiModel(Iterable<Link> initialLinks) {
		super(initialLinks);
	}

	public BonsaiModel(Link initialLink) {
		super(initialLink);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(byte[] thumbnailImage) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bonsaiStyle == null) ? 0 : bonsaiStyle.hashCode());
		result = prime * result + ((bonsaiType == null) ? 0 : bonsaiType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(thumbnailImage);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonsaiModel other = (BonsaiModel) obj;
		if (bonsaiStyle != other.bonsaiStyle)
			return false;
		if (bonsaiType != other.bonsaiType)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(thumbnailImage, other.thumbnailImage))
			return false;
		return true;
	}

}
