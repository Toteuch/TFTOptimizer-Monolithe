package com.toteuch.TFTOptimizer.constantes;

public enum Quality {
	COMMUN(1),
	INHABITUEL(2),
	RARE(3),
	EPIQUE(4),
	LEGENDAIRE(5);
	
	private int cost;
	private Quality(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
}
