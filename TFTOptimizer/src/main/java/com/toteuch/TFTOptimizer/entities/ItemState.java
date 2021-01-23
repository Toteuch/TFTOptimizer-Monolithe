package com.toteuch.TFTOptimizer.entities;

public class ItemState {
	private Item item;
	private double presence;
	
	public ItemState(Item item, double presence) {
		this.item = item;
		this.presence = presence;
	}

	public Item getItem() {
		return item;
	}

	public double getPresence() {
		return presence;
	}
}
