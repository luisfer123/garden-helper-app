package com.garden.helper.data.models;

import java.sql.Timestamp;
import java.util.Objects;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "repottingImage")
@Relation(collectionRelation = "repottingImages", itemRelation = "repottingImage")
@JsonInclude(Include.NON_NULL)
public class RepottingImageModel extends RepresentationModel<RepottingImageModel> {
	
	private Long id;
	
	private byte[] image;
	
	private String note;
	
	private Timestamp creationDate;

	private RepottingImageModel(Builder builder) {
		this.id = builder.id;
		this.image = builder.image;
		this.creationDate = builder.creationDate;
		this.note = builder.note;
	}

	public RepottingImageModel(Iterable<Link> initialLinks) {
		super(initialLinks);
	}

	public RepottingImageModel(Link initialLink) {
		super(initialLink);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(creationDate, id);
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
		RepottingImageModel other = (RepottingImageModel) obj;
		return Objects.equals(creationDate, other.creationDate) && Objects.equals(id, other.id);
	}

	public static IId builder() {
		return new Builder();
	}

	public interface IId {
		public IImage id(Long id);
	}

	public interface IImage {
		public ICreationDate image(byte[] image);
	}

	public interface ICreationDate {
		public IBuild creationDate(Timestamp creationDate);
	}

	public interface IBuild {
		public IBuild note(String note);

		public RepottingImageModel build();
	}

	public static final class Builder implements IId, IImage, ICreationDate, IBuild {
		private Long id;
		private byte[] image;
		private Timestamp creationDate;
		private String note;

		private Builder() {
		}

		@Override
		public IImage id(Long id) {
			this.id = id;
			return this;
		}

		@Override
		public ICreationDate image(byte[] image) {
			this.image = image;
			return this;
		}

		@Override
		public IBuild creationDate(Timestamp creationDate) {
			this.creationDate = creationDate;
			return this;
		}

		@Override
		public IBuild note(String note) {
			this.note = note;
			return this;
		}

		@Override
		public RepottingImageModel build() {
			return new RepottingImageModel(this);
		}
	}


}
