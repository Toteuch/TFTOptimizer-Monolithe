package com.toteuch.TFTOptimizer.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.toteuch.TFTOptimizer.constantes.FinalItem;
import com.toteuch.TFTOptimizer.constantes.Item;
import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.entities.ChampionState;

public class ResolverService {
	private static final int HALF_ITEM = 50;
	private static final int COMPLETED_ITEM = 110;
	
	@SuppressWarnings("unchecked")
	public static List<ChampionState> getOptimizedChampionList(List<Champion> championsList, Map<Item, Integer> mapItems) {
		List<ChampionState> retList = new ArrayList<ChampionState>();
		for (Champion champ : championsList) {
			// List finales
			List<FinalItem> recapFinalItems = champ.getFinalItemList();
			List<FinalItem> slamableItems = new ArrayList<FinalItem>();
			List<Item> uncraftableItems = new ArrayList<Item>();

			// Obtenir le meilleur score
			Map<Item, Integer> workingMap = new HashMap<Item, Integer>();
			int score = 0;
			for (Item key : mapItems.keySet()) {
				int prevValue = (mapItems.get(key) != null?mapItems.get(key):0); 
				workingMap.put(key, prevValue++);
			}
			
			List<FinalItem> finalItems = champ.getFinalItemList();
			for (FinalItem finalItem : finalItems) {
				if(slamableItems.size()>=3) {
					break;
				}
				Item item1 = finalItem.getItem1();
				Item item2 = finalItem.getItem2();
				try {
					if ((item1 == item2 && workingMap.get(item1) != null && workingMap.get(item1) >= 2)
							|| (item1 != item2 
								&& workingMap.get(item1) != null 
								&& workingMap.get(item1) > 0 
								&& workingMap.get(item2) != null
								&& workingMap.get(item2) > 0)) {
						score += COMPLETED_ITEM;
						slamableItems.add(finalItem);
						workingMap = decreaseItemNbr(workingMap, item1);
						workingMap = decreaseItemNbr(workingMap, item2);
					} else if (workingMap.get(item1) != null && workingMap.get(item1) > 0) {
						score += HALF_ITEM;
						workingMap = decreaseItemNbr(workingMap, item1);
					} else if (workingMap.get(item2) != null && workingMap.get(item2) > 0) {
						score += HALF_ITEM;
						workingMap = decreaseItemNbr(workingMap, item2);
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}

			}
			for(Item item : workingMap.keySet()) {
				int nb = workingMap.get(item);
				for(int i = 0; i < nb; i++) {
					uncraftableItems.add(item);
				}
			}

			// Enregistrer
			ChampionState cState = new ChampionState();
			cState.setChamp(champ);
			cState.setRecapFinalItems(recapFinalItems);
			cState.setScore(score);
			cState.setSlamableItems(slamableItems);
			cState.setUncraftableItems(uncraftableItems);
			retList.add(cState);

		}
		Collections.sort(retList, new Comparator<ChampionState>() {
			public int compare(ChampionState cs1, ChampionState cs2) {
				return cs1.getScore()-cs2.getScore();
			}
		});
		
		return retList;
	}

	private static Map<Item, Integer> decreaseItemNbr(Map<Item, Integer> map, Item item) throws Exception {
		Integer nbr = map.get(item);
		if (nbr == null || nbr == 0) {
			throw new Exception("Can't find any " + item + " in the actual map of Item");
		}
		if (nbr == 1) {
			map.remove(item);
		} else {
			nbr--;
			map.put(item, nbr);
		}
		return map;
	}

	// key = item enum
	// value = nombre
	private Map<Item, Integer> genererMapItem(List<Item> itemList) {
		Map<Item, Integer> mapItem = new HashMap<Item, Integer>();
		for (Item item : itemList) {
			Integer prevValue = mapItem.get(item);
			if (prevValue == null) {
				prevValue = 0;
			}
			mapItem.put(item, prevValue+=1);
		}
		return mapItem;
	}

//	private boolean isFinalItemSlamable(FinalItem finalItem, List<Item> itemList) {
//		Item item1 = finalItem.getItem1();
//		Item item2 = finalItem.getItem2();
//		if (!itemList.contains(item1) || !itemList.contains(item2)) {
//			return false;
//		} else if (item1 != item2) {
//			return true;
//		} else {
//			int count = 0;
//			for (Item item : itemList) {
//				if (item == item1) {
//					count++;
//				}
//			}
//			if (count >= 2) {
//				return true;
//			} else {
//				return false;
//			}
//		}
//	}
}
