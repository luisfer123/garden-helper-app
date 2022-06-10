package com.garden.helper.model.enums;

public enum EFertilizerType {
	
	ORGANIC ("Organico"),
	
	CHEMICAL ("Quimico")
	
	;
	
	private final String nameInSpanish;
	
	private EFertilizerType(String nameInSpanish) {
		this.nameInSpanish = nameInSpanish;
	}
	
	public String getNameInSpanish() {
		return nameInSpanish;
	}

}
