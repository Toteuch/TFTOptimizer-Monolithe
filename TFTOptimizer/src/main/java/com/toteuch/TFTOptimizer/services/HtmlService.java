package com.toteuch.TFTOptimizer.services;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.toteuch.TFTOptimizer.constantes.Quality;
import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.entities.Item;
import com.toteuch.TFTOptimizer.entities.ItemState;

public class HtmlService {

	private static final String URL_LOLCHESS_STAT = "https://lolchess.gg/statistics/items";
	private static Map<String, Item> items;
	private static List<Champion> champions;

	private static void parseLolChessStat() { 
		String output = getUrlContents(URL_LOLCHESS_STAT);
		Document doc = Jsoup.parse(output);
		items = getItems(doc);
		champions = getChampions(doc);
	}
	
	public static Map<String, Item> getItems() {
		if(null == items) {
			parseLolChessStat();
		}
		return items;
	}
	
	public static List<Champion> getChampions() {
		if(null == champions) {
			parseLolChessStat();
		}
		return champions;
	}

	private static List<Champion> getChampions(Document doc) {
		List<Champion> champList = new ArrayList<Champion>();
		Elements tdChampionElements = doc.getElementsByClass("champion");
		for(Element tdChampion : tdChampionElements) {
			Champion champ = new Champion();
			
			champ.setName(tdChampion.getElementsByTag("span").get(0).ownText());
			champ.setDetailUrl(tdChampion.getElementsByTag("a").get(0).attr("href"));
			champ.setUrlImage(tdChampion.getElementsByTag("img").get(0).attr("src"));
			champ.setImage(getImage(champ.getUrlImage()));
			
			String classQuality = null;
			Set<String> classes = tdChampion.getElementsByTag("div").get(0).classNames();
			for(String clas : classes) {
				if(StringUtils.startsWith(clas, "cost-")) {
					classQuality = clas;
					break;
				}
			}
			int cost = Integer.parseInt(StringUtils.substringAfter(classQuality, "cost-"));
			for(Quality q : Quality.values()) {
				if(q.getCost() == cost) {
					champ.setQuality(q);
					break;
				}
			}
			
			
			Elements tdsItem = tdChampion.parent().getElementsByClass("items");
			List<ItemState> itemStateList = new ArrayList<ItemState>();
			for(Element tdItem : tdsItem) {
				Item item = items.get(tdItem.getElementsByClass("name").get(0).ownText());
				Double presence = Double.valueOf(tdItem.getElementsByClass("ratio").get(0).ownText().replace("%", ""));
				itemStateList.add(new ItemState(item, presence));
			}
			
			champ.setItemsStates(itemStateList);
			champList.add(champ);
		}
		return champList;
	}
	
	private static Map<String, Item> getItems(Document doc) {
		Map<String, Item> items = new HashMap<String, Item>();
		Elements tdItemElements = doc.getElementsByClass("items");
		for(Element tdItem : tdItemElements) {
			Item combinedItem = new Item();
			combinedItem.setUrlImage(tdItem.getElementsByClass("desc").get(0).getElementsByClass("img").get(0).attr("src"));
			combinedItem.setImage(getImage(combinedItem.getUrlImage()));
			Element divCombination = tdItem.getElementsByClass("combination").get(0);
			combinedItem.setName(divCombination.getElementsByClass("name").get(0).ownText());
			List<Item> matsList = new ArrayList<Item>();
			Elements imgTags = divCombination.getElementsByTag("img");
			for(Element imgTag : imgTags) {
				Item item = new Item();
				item.setName(imgTag.attr("alt"));
				item.setUrlImage(imgTag.attr("src"));
				item.setImage(getImage(item.getUrlImage()));
				items.put(item.getName(), item);
				matsList.add(item);
			}
			combinedItem.setCombination(matsList);
			items.put(combinedItem.getName(), combinedItem);
		}
		
		return items;
	}
	
	private static String getUrlContents(String theUrl) {
		StringBuilder content = new StringBuilder();
		// many of these calls can throw exceptions, so i've just
		// wrapped them all in one try/catch statement.
		try {
			// create a url object
			URL url = new URL(theUrl);
			// create a urlconnection object
			URLConnection urlConnection = url.openConnection();
			// wrap the urlconnection in a bufferedreader
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			// read from the urlconnection via the bufferedreader
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}
	
	private static Image getImage(String sUrl) {
		int attempt = 0;
		Image image = null;
		URL url = null;
		while(image == null && attempt < 3) {
			try {
				url = new URL("https:" + sUrl);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			try {
				if(url != null)
				image = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			attempt++;
		}
		if(image == null) {
			System.err.println("IMAGE NOT FOUND : " + sUrl);
		}
		return image;
	}
}
