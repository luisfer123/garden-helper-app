package com.garden.helper.data.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.garden.helper.data.enums.EBonsaiStyle;
import com.garden.helper.data.enums.EBonsaiType;

@Entity
@Table(name = "Bonsai")
@PrimaryKeyJoinColumn(name = "Plant_id")
public class Bonsai extends Plant {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", columnDefinition = "ENUM('BONSAI', 'PRE_BONSAI')")
	private EBonsaiType type;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "style", columnDefinition = "ENUM('MOYOGI', 'CHOKAN', 'SHAKAN')")
	private EBonsaiStyle style;

	public Bonsai() {
		super();
	}

	private Bonsai(Builder builder) {
		this.type = builder.type;
		this.style = builder.style;
		super.setCreationDate(builder.creationDate);
		super.setLastUpdatedAt(builder.lastUpdatedAt);
		super.setName(builder.name);
		super.setThumbnailImage(builder.thumbnailImage);
	}

	public EBonsaiType getType() {
		return type;
	}

	public void setType(EBonsaiType type) {
		this.type = type;
	}

	public EBonsaiStyle getStyle() {
		return style;
	}

	public void setStyle(EBonsaiStyle style) {
		this.style = style;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof Bonsai))
			return false;
		
		Bonsai other = (Bonsai) o;
		return getId() != null &&
				getId().equals(other.getId());
				
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	public static ICreationDateStage builder() {
		return new Builder();
	}

	public interface ICreationDateStage {
		public ILastUpdatedAtStage withCreationDate(Timestamp creationDate);
	}

	public interface ILastUpdatedAtStage {
		public IBuildStage withLastUpdatedAt(Timestamp lastUpdatedAt);
	}

	public interface IBuildStage {
		public IBuildStage withName(String name);

		public IBuildStage withType(EBonsaiType type);

		public IBuildStage withStyle(EBonsaiStyle style);

		public IBuildStage withThumbnailImage(byte[] thumbnailImage);

		public Bonsai build();
	}

	public static final class Builder implements ICreationDateStage, ILastUpdatedAtStage, IBuildStage {
		private Timestamp creationDate;
		private Timestamp lastUpdatedAt;
		private String name;
		private EBonsaiType type;
		private EBonsaiStyle style;
		private byte[] thumbnailImage;

		private Builder() {
		}

		@Override
		public ILastUpdatedAtStage withCreationDate(Timestamp creationDate) {
			this.creationDate = creationDate;
			return this;
		}

		@Override
		public IBuildStage withLastUpdatedAt(Timestamp lastUpdatedAt) {
			this.lastUpdatedAt = lastUpdatedAt;
			return this;
		}

		@Override
		public IBuildStage withName(String name) {
			this.name = name;
			return this;
		}

		@Override
		public IBuildStage withType(EBonsaiType type) {
			this.type = type;
			return this;
		}

		@Override
		public IBuildStage withStyle(EBonsaiStyle style) {
			this.style = style;
			return this;
		}

		@Override
		public IBuildStage withThumbnailImage(byte[] thumbnailImage) {
			this.thumbnailImage = thumbnailImage;
			return this;
		}

		@Override
		public Bonsai build() {
			return new Bonsai(this);
		}
	}

}
