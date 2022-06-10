package com.garden.helper.model.entity;

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

import com.garden.helper.model.enums.EFertilizerType;

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
	private Set<FertilizedInfo> fertilizedInfos = new HashSet<>();

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

	public Set<FertilizedInfo> getFertilizedInfos() {
		return fertilizedInfos;
	}

	public void setFertilizedInfos(Set<FertilizedInfo> fertilizedInfos) {
		this.fertilizedInfos = fertilizedInfos;
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

}
