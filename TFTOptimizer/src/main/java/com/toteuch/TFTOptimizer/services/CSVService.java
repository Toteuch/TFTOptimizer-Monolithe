package com.toteuch.TFTOptimizer.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.toteuch.TFTOptimizer.constantes.FinalItem;
import com.toteuch.TFTOptimizer.constantes.Origin;
import com.toteuch.TFTOptimizer.constantes.Trait;
import com.toteuch.TFTOptimizer.entities.Champion;

public class CSVService {

	private static File sheet = new File("E:\\projets\\TFTOptimizer\\ressources\\builds.csv");
	
	@SuppressWarnings("unchecked")
	public static List<Champion> parseCSV() {
		List<Champion> retList = new ArrayList<>();
		BufferedReader br = null;
		try {
			FileInputStream fis = new FileInputStream(sheet);
			InputStreamReader isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String line = null;
			boolean first = true;
			while ((line = br.readLine()) != null) {
				if(first) {
					first = false;
					continue;
				}
				String[] fields = line.split(",");
				Champion champ = new Champion();
				champ.setName(fields[0]);
				champ.setRole(fields[1]);
				champ.setOrigin1(parseOrigin(fields[2]));
				champ.setTrait1(parseTrait(fields[3]));
				Object thirdStat = parseTraitOrOrigin(fields[4]);
				if(thirdStat instanceof Trait) {
					champ.setTrait2((Trait)thirdStat);
				} else if(thirdStat instanceof Origin) {
					champ.setOrigin2((Origin)thirdStat);
				}
				
				champ.getFinalItemList().add(parseItem(fields[5]));
				champ.getFinalItemList().add(parseItem(fields[6]));
				champ.getFinalItemList().add(parseItem(fields[7]));
				champ.getFinalItemList().add(parseItem(fields[8]));
				champ.getFinalItemList().add(parseItem(fields[9]));
				retList.add(champ);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			retList = null;
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return retList;
	}

	private static Origin parseOrigin(String text) throws Exception {
		if (null == text || text.isEmpty()) {
			return null;
		}
		for (Origin cur : Origin.values()) {
			if (cur.getName().compareTo(text) == 0) {
				return cur;
			}
		}
		throw new Exception("Parsing origin failed : " + text);
	}

	private static Trait parseTrait(String text) throws Exception {
		if (null == text || text.isEmpty()) {
			return null;
		}
		for (Trait cur : Trait.values()) {
			if (cur.toString().compareTo(StringUtils.upperCase(text)) == 0) {
				return cur;
			}
		}
		throw new Exception("Parsing trait failed : " + text);
	}

	private static FinalItem parseItem(String text) throws Exception {
		if (null == text || text.isEmpty()) {
			return null;
		}
		for (FinalItem cur : FinalItem.values()) {
			if (cur.getName().compareTo(text) == 0) {
				return cur;
			}
		}
		throw new Exception("Parsing item failed : " + text);
	}
	
	private static Object parseTraitOrOrigin(String text) throws Exception {
		if(null == text || text.isEmpty()) {
			return null;
		}
		for(Trait cur : Trait.values()) {
			if (cur.toString().compareTo(StringUtils.upperCase(text)) == 0) {
				return cur;
			}
		}
		for (Origin cur : Origin.values()) {
			if (cur.getName().compareTo(text) == 0) {
				return cur;
			}
		}
		throw new Exception("Parsing third stat failed : " + text);
	}
}
