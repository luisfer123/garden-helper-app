package com.garden.helper.data.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Repotting_info")
public class RepottingInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "repoted_at")
	private Timestamp repotedAt;
	
	@Column(name = "next_repotting_date")
	private Timestamp nextRepottingDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Plant_id")
	private Plant plant;
	
	@OneToMany(mappedBy = "repottingInfo",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<RepottingImage> repottingImages = new HashSet<>();

	private RepottingInfo(Builder builder) {
		this.repotedAt = builder.repotedAt;
		this.id = builder.id;
		this.note = builder.note;
		this.nextRepottingDate = builder.nextRepottingDate;
		this.plant = builder.plant;
		this.repottingImages = builder.repottingImages;
	}

	public RepottingInfo() {
		this(new Timestamp(System.currentTimeMillis()));
	}

	public RepottingInfo(Timestamp repotedAt) {
		super();
		this.repotedAt = repotedAt;
	}

	public void addRepottingImage(RepottingImage image) {
		this.repottingImages.add(image);
		image.setRepottingInfo(this);
	}
	
	public void removeRpottingImage(RepottingImage image) {
		this.repottingImages.remove(image);
		image.setRepottingInfo(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getRepotedAt() {
		return repotedAt;
	}

	public void setRepotedAt(Timestamp repotedAt) {
		this.repotedAt = repotedAt;
	}

	public Timestamp getNextRepottingDate() {
		return nextRepottingDate;
	}

	public void setNextRepottingDate(Timestamp nextRepottingDate) {
		this.nextRepottingDate = nextRepottingDate;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<RepottingImage> getRepottingImages() {
		return repottingImages;
	}

	public void setRepottingImages(Set<RepottingImage> repottingImages) {
		this.repottingImages = repottingImages;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(!(o instanceof RepottingInfo))
			return false;
		
		RepottingInfo other = (RepottingInfo) o;
		return id != null &&
				id.equals(other.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "RepottingInfo [id=" + id + ", repotedAt=" + repotedAt + ", nextRepottingDate=" + nextRepottingDate
				+ ", plant=" + plant + "]";
	}

	public static IRepotedAtStage builder() {
		return new Builder();
	}

	public interface IRepotedAtStage {
		public IBuildStage withRepotedAt(Timestamp repotedAt);
	}

	public interface IBuildStage {
		public IBuildStage withId(Long id);

		public IBuildStage withNote(String note);

		public IBuildStage withNextRepottingDate(Timestamp nextRepottingDate);

		public IBuildStage withPlant(Plant plant);

		public IBuildStage withRepottingImages(Set<RepottingImage> repottingImages);

		public RepottingInfo build();
	}

	public static final class Builder implements IRepotedAtStage, IBuildStage {
		private Timestamp repotedAt;
		private Long id;
		private String note;
		private Timestamp nextRepottingDate;
		private Plant plant;
		private Set<RepottingImage> repottingImages = new HashSet<>();

		private Builder() {
		}

		@Override
		public IBuildStage withRepotedAt(Timestamp repotedAt) {
			this.repotedAt = repotedAt;
			return this;
		}

		@Override
		public IBuildStage withId(Long id) {
			this.id = id;
			return this;
		}

		@Override
		public IBuildStage withNote(String note) {
			this.note = note;
			return this;
		}

		@Override
		public IBuildStage withNextRepottingDate(Timestamp nextRepottingDate) {
			this.nextRepottingDate = nextRepottingDate;
			return this;
		}

		@Override
		public IBuildStage withPlant(Plant plant) {
			this.plant = plant;
			return this;
		}

		@Override
		public IBuildStage withRepottingImages(Set<RepottingImage> repottingImages) {
			this.repottingImages = repottingImages;
			return this;
		}

		@Override
		public RepottingInfo build() {
			return new RepottingInfo(this);
		}
	}

}
