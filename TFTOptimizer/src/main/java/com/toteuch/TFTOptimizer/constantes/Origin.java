package com.toteuch.TFTOptimizer.constantes;

public enum Origin {
	CULTIST("Cultist"), 
	DIVINE("Divine"), 
	DUSK("Dusk"), 
	ELDERWOOD("Elderwood"), 
	ENLIGHTENED("Enlightened"), 
	EXILE("Exile"), 
	FORTUNE("Fortune"), 
	MOONLIGHT("Moonlight"), 
	NINJA("Ninja"), 
	SPIRIT("Spirit"), 
	TORMENTED("Tormented"), 
	WARLORD("Warlord"), 
	THE_BOSS("The Boss");
	
	private String name;
	private Origin(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
