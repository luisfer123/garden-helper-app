package com.garden.helper.data.enums;

public enum EBonsaiStyle {
	
	MOYOGI ("Moyogi", "", ""),
	
	CHOKAN ("Cokan", "", ""),
	
	SHAKAN ("Shakan", "", "")
	
	;
	
	private final String name;
	
	private final String nameInEnglish;
	
	private final String nameInSpanish;
	
	private EBonsaiStyle(String name, String nameInEnglish, String nameInSpanish) {
		this.name = name;
		this.nameInEnglish = nameInEnglish;
		this.nameInSpanish = nameInSpanish;
	}
	
	public String getName() {
		return this.name;
	}

	public String getNameInEnglish() {
		return nameInEnglish;
	}

	public String getNameInSpanish() {
		return nameInSpanish;
	}
}
