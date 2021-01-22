package com.toteuch.TFTOptimizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.toteuch.TFTOptimizer.constantes.Item;
import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.entities.ChampionState;
import com.toteuch.TFTOptimizer.services.CSVService;
import com.toteuch.TFTOptimizer.services.ResolverService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {        
        List<Champion> champs = CSVService.parseCSV();
//        for(Champion champ : champs) {
//        	System.out.println(champ);
//        }
        
        // Input
        Map<Item, Integer> input = new HashMap<Item, Integer>();
        input.put(Item.SPARRING_GLOVE, 1);
        input.put(Item.SPATULA, 0);
        input.put(Item.GIANTS_BELT, 0);
        input.put(Item.TEAR_OF_THE_GODESS, 0);
        input.put(Item.NEEDLESSLY_LARGE_ROD, 2);
        input.put(Item.NEGATRON_CLOAK, 0);
        input.put(Item.CHAIN_VEST, 1);
        input.put(Item.RECURVE_BOW, 0);
        input.put(Item.BF_SWORD, 3);
        
        List<ChampionState> etat = ResolverService.getOptimizedChampionList(champs, input);
        for(ChampionState champS : etat) {
        	System.out.println(champS);
        }
    }
}
