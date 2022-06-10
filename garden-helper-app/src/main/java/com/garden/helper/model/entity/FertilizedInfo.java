package com.garden.helper.model.entity;

import java.sql.Date;
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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Fertilized_info")
public class FertilizedInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column(name = "date")
	private Date date;
	
	@ManyToMany(fetch = FetchType.EAGER,
			cascade = CascadeType.PERSIST)
	@JoinTable(name = "Fertilized_info_has_Fertilizer",
			joinColumns = @JoinColumn(name = "fertilized_info_id"),
			inverseJoinColumns = @JoinColumn(name = "fertilizer_id"))
	private Set<Fertilizer> fertilizers = new HashSet<>();
	
	public void addFertilizer(Fertilizer fertilizer) {
		this.fertilizers.add(fertilizer);
		fertilizer.getFertilizedInfos().add(this);
	}
	
	public void removeFertilizer(Fertilizer fertilizer) {
		this.fertilizers.remove(fertilizer);
		fertilizer.getFertilizedInfos().remove(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Fertilizer> getFertilizers() {
		return fertilizers;
	}

	public void setFertilizers(Set<Fertilizer> fertilizers) {
		this.fertilizers = fertilizers;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof FertilizedInfo))
			return false;
		
		FertilizedInfo other = (FertilizedInfo) o;
		return id != null &&
				id.equals(other.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
