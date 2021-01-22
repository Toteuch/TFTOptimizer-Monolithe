package com.toteuch.TFTOptimizer.entities;

import java.util.List;

import com.toteuch.TFTOptimizer.constantes.FinalItem;
import com.toteuch.TFTOptimizer.constantes.Item;

public class ChampionState {
	private Champion champ;
	private int score;
	private List<FinalItem> recapFinalItems;
	private List<FinalItem> slamableItems;
	private List<Item> uncraftableItems;
	
	@Override
	public String toString() {
		String s = "-------------------------------------------------------------------------\n"
				+ champ.getName() + " : " +score +"\n"
				+ "Recap items finaux :";
		for(FinalItem fItem : recapFinalItems) {
			s+= fItem.getName() + " | ";
		}
		s+= "\n" + "Slamable Items : ";
		for(FinalItem fItem: slamableItems) {
			s+= fItem.getName() + " | ";
		}
		s+= "\n" + "UncraftableItems : ";
		for(Item item: uncraftableItems) {
			s+= item.toString() + " | ";
		}
		return s;
	}
	
	public Champion getChamp() {
		return champ;
	}
	public void setChamp(Champion champ) {
		this.champ = champ;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<FinalItem> getRecapFinalItems() {
		return recapFinalItems;
	}
	public void setRecapFinalItems(List<FinalItem> recapFinalItems) {
		this.recapFinalItems = recapFinalItems;
	}
	public List<FinalItem> getSlamableItems() {
		return slamableItems;
	}
	public void setSlamableItems(List<FinalItem> slamableItems) {
		this.slamableItems = slamableItems;
	}
	public List<Item> getUncraftableItems() {
		return uncraftableItems;
	}
	public void setUncraftableItems(List<Item> uncraftableItems) {
		this.uncraftableItems = uncraftableItems;
	}
}
