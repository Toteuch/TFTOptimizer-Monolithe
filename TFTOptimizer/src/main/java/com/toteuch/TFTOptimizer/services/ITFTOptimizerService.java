package com.toteuch.TFTOptimizer.services;

import java.util.List;
import java.util.Map;

import com.toteuch.TFTOptimizer.entities.ChampAnalysis;
import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.entities.Item;

public interface ITFTOptimizerService {
	public List<Item> getLowItems();
	/**
	 * Ordered by highest score
	 * @param inputMatList
	 * @return
	 */
	public List<ChampAnalysis> getBestScore(Map<Item, Integer> inputMatList);
	
	public String getProjectVersion();
	
	public Map<String, Champion> getChamps();
}
