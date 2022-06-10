package com.garden.helper.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.garden.helper.model.enums.EBonsaiStyle;
import com.garden.helper.model.enums.EBonsaiType;

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

}
