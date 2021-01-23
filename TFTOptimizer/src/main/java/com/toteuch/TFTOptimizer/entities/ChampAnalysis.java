package com.toteuch.TFTOptimizer.entities;

import java.util.List;

public class ChampAnalysis {
	private Champion champ;
	private double score;
	private List<Item> slamedItems;
	private List<Item> unusedItems;
	public Champion getChamp() {
		return champ;
	}
	public void setChamp(Champion champ) {
		this.champ = champ;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public List<Item> getSlamedItems() {
		return slamedItems;
	}
	public void setSlamedItems(List<Item> slamedItems) {
		this.slamedItems = slamedItems;
	}
	public List<Item> getUnusedItems() {
		return unusedItems;
	}
	public void setUnusedItems(List<Item> unusedItems) {
		this.unusedItems = unusedItems;
	}
}
