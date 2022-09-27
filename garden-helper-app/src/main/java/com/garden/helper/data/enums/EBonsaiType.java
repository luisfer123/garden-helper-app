package com.garden.helper.data.enums;

public enum EBonsaiType {

	BONSAI ("Bonsai"),
	
	PRE_BONSAI ("Pre bonsai")
	
	;
	
	private final String name;
	
	private EBonsaiType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
