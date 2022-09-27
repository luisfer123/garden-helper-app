package com.garden.helper.data.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.garden.helper.data.enums.EFertilizerType;

@Entity
@Table(name = "Fertilizer")
public class Fertilizer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", columnDefinition = "ENUM('ORGANIC', 'CHEMICAL')")
	private EFertilizerType type;
	
	@Column(name = "N-nitrogen")
	private int nitrogen;
	
	@Column(name = "P-phosphorus")
	private int phosphorus;
	
	@Column(name = "k-potassium")
	private int potassium;
	
	@ManyToMany(mappedBy = "fertilizers")
	private Set<FertilisationInfo> fertilisationInfos = new HashSet<>();

	public Fertilizer() {
		super();
	}

	private Fertilizer(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.type = builder.type;
		this.nitrogen = builder.nitrogen;
		this.phosphorus = builder.phosphorus;
		this.potassium = builder.potassium;
		this.fertilisationInfos = builder.fertilisationInfos;
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

	public EFertilizerType getType() {
		return type;
	}

	public void setType(EFertilizerType type) {
		this.type = type;
	}

	public int getNitrogen() {
		return nitrogen;
	}

	public void setNitrogen(int nitrogen) {
		this.nitrogen = nitrogen;
	}

	public int getPhosphorus() {
		return phosphorus;
	}

	public void setPhosphorus(int phosphorus) {
		this.phosphorus = phosphorus;
	}

	public int getPotassium() {
		return potassium;
	}

	public void setPotassium(int potassium) {
		this.potassium = potassium;
	}
	
	public Set<FertilisationInfo> getFertilisationInfos() {
		return fertilisationInfos;
	}

	public void setFertilisationInfos(Set<FertilisationInfo> fertilisationInfos) {
		this.fertilisationInfos = fertilisationInfos;
	}

	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof Fertilizer))
			return false;
		
		Fertilizer other = (Fertilizer) o;
		return id != null &&
				id.equals(other.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "Fertilizer [id=" + id + ", name=" + name + ", type=" + type + ", nitrogen=" + nitrogen + ", phosphorus="
				+ phosphorus + ", potassium=" + potassium + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private String name;
		private EFertilizerType type;
		private int nitrogen;
		private int phosphorus;
		private int potassium;
		private Set<FertilisationInfo> fertilisationInfos = new HashSet<>();

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withType(EFertilizerType type) {
			this.type = type;
			return this;
		}

		public Builder withNitrogen(int nitrogen) {
			this.nitrogen = nitrogen;
			return this;
		}

		public Builder withPhosphorus(int phosphorus) {
			this.phosphorus = phosphorus;
			return this;
		}

		public Builder withPotassium(int potassium) {
			this.potassium = potassium;
			return this;
		}

		public Builder withFertilisationInfos(Set<FertilisationInfo> fertilisationInfos) {
			this.fertilisationInfos = fertilisationInfos;
			return this;
		}

		public Fertilizer build() {
			return new Fertilizer(this);
		}
	}

}
