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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "Fertilisation_info")
public class FertilisationInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column(name = "fertilized_at")
	private Timestamp fertilizedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plant_id")
	private Plant plant;
	
	@ManyToMany(fetch = FetchType.EAGER,
			cascade = CascadeType.PERSIST)
	@JoinTable(name = "Fertilized_info_has_Fertilizer",
			joinColumns = @JoinColumn(name = "fertilized_info_id"),
			inverseJoinColumns = @JoinColumn(name = "fertilizer_id"))
	private Set<Fertilizer> fertilizers = new HashSet<>();
	
	public void addFertilizer(Fertilizer fertilizer) {
		this.fertilizers.add(fertilizer);
		fertilizer.getFertilisationInfos().add(this);
	}
	
	public void removeFertilizer(Fertilizer fertilizer) {
		this.fertilizers.remove(fertilizer);
		fertilizer.getFertilisationInfos().remove(this);
	}

	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof FertilisationInfo))
			return false;
		
		FertilisationInfo other = (FertilisationInfo) o;
		return id != null &&
				id.equals(other.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
