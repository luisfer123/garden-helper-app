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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JoinFormula;

@Entity
@Table(name = "Plant")
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {
	
	@Id
	@GeneratedValue(generator = "native", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "creation_date")
	private Timestamp creationDate;
	
	@Column(name = "last_updated_at")
	private Timestamp lastUpdatedAt;
	
	@Lob
	@Column(name = "thumbnail_image", columnDefinition = "MEDIUMBLOB")
	private byte[] thumbnailImage;
	
	@OneToMany(
			mappedBy = "plant",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<RepotedDate> repotedDates = new HashSet<>();
	
	@ManyToOne
	@JoinFormula(
			"(" +
				"SELECT rd.id " +
				"FROM Repoted_date rd " +
				"WHERE rd.plant_id = id " +
				"ORDER BY rd.date DESC " +
				"LIMIT 1" +
			")")
	private RepotedDate lastRepotedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "User_id")
	private User user;
	
	@OneToMany(
			mappedBy = "plant",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<FertilisationInfo> fertilisationInfos = new HashSet<>();

	public Plant() {
		super();
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
	
	public Set<RepotedDate> getRepotedDates() {
		return repotedDates;
	}

	public void setRepotedDates(Set<RepotedDate> repotedDates) {
		this.repotedDates = repotedDates;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public byte[] getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(byte[] thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public Timestamp getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(Timestamp lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof Plant))
			return false;
		
		Plant other = (Plant) o;
		return id != null &&
				id.equals(other.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
