package com.toteuch.TFTOptimizer.constantes;

public enum Item {
	BF_SWORD("/img/item/BF.png"), 
	RECURVE_BOW("/img/item/BOW.png"), 
	CHAIN_VEST("/img/item/CHAIN.png"), 
	NEGATRON_CLOAK("/img/item/CLOAK.png"), 
	NEEDLESSLY_LARGE_ROD("/img/item/ROD.png"),
	TEAR_OF_THE_GODESS("/img/item/TEAR.png"),
	GIANTS_BELT("/img/item/BELT.png"),
	SPATULA("/img/item/SPAT.png"), 
	SPARRING_GLOVE("/img/item/GLOVES.png");
	
	private String pathToImg;
	private Item(String pathToImg) {
		this.pathToImg = pathToImg;
	}
	
	public String getPathToImg() {
		return this.pathToImg;
	}
}
