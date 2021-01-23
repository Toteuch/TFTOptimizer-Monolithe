package com.toteuch.TFTOptimizer.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.toteuch.TFTOptimizer.entities.ChampAnalysis;
import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.entities.Item;
import com.toteuch.TFTOptimizer.entities.ItemState;

public class TFTOptimizerService implements ITFTOptimizerService {
	
	private static final int CENT = 100;
	
	final Properties properties = new Properties();
	
	public TFTOptimizerService() {
		try {
			properties.load(ClassLoader.getSystemResourceAsStream("tftoptimizer.properties"));
		} catch (IOException e) {
			System.err.println("Error reading properties file");
			e.printStackTrace();
		}
	}
	
	
	@Override
 	public List<Item> getLowItems() {
		List<Item> lowItems = new ArrayList<Item>();
		Map<String, Item> items = HtmlService.getItems();
		for(Map.Entry<String, Item> entry : items.entrySet()) {
			if(entry.getValue().getCombination() == null) {
				lowItems.add(entry.getValue());
			}
		}
		return lowItems;
	}

	@Override
	public List<ChampAnalysis> getBestScore(Map<Item, Integer> inputMatList) {
		List<ChampAnalysis> champsAnalysisList = new ArrayList<ChampAnalysis>();
		for(Champion champ : HtmlService.getChampions()) {
			int score = 0;
			List<Item> slamedItems = new ArrayList<Item>();
			List<Item> unusedItems = new ArrayList<Item>();
			
			Map<Item, Integer> workingMap = new HashMap<Item, Integer>();
			for (Item key : inputMatList.keySet()) {
				int prevValue = (inputMatList.get(key) != null?inputMatList.get(key):0); 
				workingMap.put(key, prevValue);
			}
			
			for(ItemState itemState : champ.getItemsStates()) {
				if(slamedItems.size() >= 3) {
					break;
				}
				Item combinedItem = itemState.getItem();
				Item item1 = combinedItem.getCombination().get(0);
				Item item2 = combinedItem.getCombination().get(1);
				int item1Count = getItemCountInMats(workingMap, item1);
				int item2Count = getItemCountInMats(workingMap, item2);
				if((StringUtils.equals(item1.getName(), item2.getName()) && item1Count >= 2)
						|| (!StringUtils.equals(item1.getName(), item2.getName()) && item1Count >= 1 && item2Count >= 1)) {
					score += (CENT*itemState.getPresence());
					slamedItems.add(combinedItem);
					workingMap = decreaseItemNbr(workingMap, item1);
					workingMap = decreaseItemNbr(workingMap, item2);
				} else if(item1Count >= 1) {
					score += ((CENT*itemState.getPresence())/2);
					workingMap = decreaseItemNbr(workingMap, item1);
				} else if(item2Count >= 1) {
					score += ((CENT*itemState.getPresence())/2);
					workingMap = decreaseItemNbr(workingMap, item2);
				}
			}
			for(Item item : workingMap.keySet()) {
				int nb = workingMap.get(item);
				for(int i = 0; i < nb; i++) {
					unusedItems.add(item);
				}
			}
			
			ChampAnalysis champAnalysis = new ChampAnalysis();
			champAnalysis.setChamp(champ);
			champAnalysis.setScore(score);
			champAnalysis.setSlamedItems(slamedItems);
			champAnalysis.setUnusedItems(unusedItems);
			champsAnalysisList.add(champAnalysis);
		}
		return sortByHighestScore(champsAnalysisList);
	}
	
	private int getItemCountInMats(Map<Item, Integer> rawMats, Item item) {
		for(Item key : rawMats.keySet()) {
			if(StringUtils.equals(item.getName(), key.getName())) {
				return rawMats.get(key);
			}
		}
		return 0;
	}
	
	private List<ChampAnalysis> sortByHighestScore(List<ChampAnalysis> champsAnalysisList) {
		Collections.sort(champsAnalysisList, new Comparator<ChampAnalysis>() {
			public int compare(ChampAnalysis c1, ChampAnalysis c2) {
				return Double.compare(c1.getScore(), c2.getScore());
			}
		});
		return champsAnalysisList;
	}

	private static Map<Item, Integer> decreaseItemNbr(Map<Item, Integer> map, Item item) {
		Item key = null;
		for(Item keyItem : map.keySet()) {
			if(StringUtils.equals(item.getName(), keyItem.getName())) {
				key = keyItem;
				break;
			}
		}		
		Integer nbr = map.get(key);
		if (nbr == null || nbr == 0) {
			System.err.println("Can't find any " + item + " in the actual map of Item");
		}
		if (nbr == 1) {
			map.remove(item);
		} else {
			nbr--;
			map.put(item, nbr);
		}
		return map;
	}

	@Override
	public String getProjectVersion() {
		return properties.getProperty("tftoptimizer.version");
	}
}
