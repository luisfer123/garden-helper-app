package com.garden.helper.data.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Repotting_image")
public class RepottingImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Lob
	@Column(name = "image", columnDefinition = "MEDIUMBLOB")
	private byte[] image;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "creation_date")
	private Timestamp creationDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Repotting_info_id")
	private RepottingInfo repottingInfo;

	public RepottingImage() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public RepottingInfo getRepottingInfo() {
		return repottingInfo;
	}

	public void setRepottingInfo(RepottingInfo repottingInfo) {
		this.repottingInfo = repottingInfo;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(!(o instanceof RepottingImage))
			return false;
		
		RepottingImage other = (RepottingImage) o;
		return id != null &&
				id.equals(other.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
