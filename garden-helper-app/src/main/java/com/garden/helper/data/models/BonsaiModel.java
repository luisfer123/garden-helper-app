package com.garden.helper.data.models;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.garden.helper.data.enums.EBonsaiStyle;
import com.garden.helper.data.enums.EBonsaiType;

@JsonInclude(Include.NON_NULL)
public class BonsaiModel extends RepresentationModel<BonsaiModel> {
	
	private Long id;
	
	private String name;
	
	private byte[] thumbnailImage;
	
	private EBonsaiStyle bonsaiStyle;
	
	private EBonsaiType bonsaiType;
	
	private Timestamp lastUpdatedAt;

	public BonsaiModel() {
		super();
	}

	public BonsaiModel(Long id, String name, byte[] thumbnailImage, 
			EBonsaiStyle bonsaiStyle, EBonsaiType bonsaiType, Timestamp lastUpdatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnailImage = thumbnailImage;
		this.bonsaiStyle = bonsaiStyle;
		this.bonsaiType = bonsaiType;
		this.lastUpdatedAt = lastUpdatedAt;
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

	public Timestamp getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(Timestamp lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

	@Override
	public String toString() {
		return "BonsaiModel [id=" + id + ", name=" + name + ", thumbnailImage=" + Arrays.toString(thumbnailImage)
				+ ", bonsaiStyle=" + bonsaiStyle + ", bonsaiType=" + bonsaiType + ", lastUpdatedAt=" + lastUpdatedAt
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(thumbnailImage);
		result = prime * result + Objects.hash(bonsaiStyle, bonsaiType, id, lastUpdatedAt, name);
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
		return bonsaiStyle == other.bonsaiStyle && bonsaiType == other.bonsaiType && Objects.equals(id, other.id)
				&& Objects.equals(lastUpdatedAt, other.lastUpdatedAt) && Objects.equals(name, other.name)
				&& Arrays.equals(thumbnailImage, other.thumbnailImage);
	}

}
