package com.toteuch.TFTOptimizer.entities;

import java.awt.Image;
import java.util.List;

import com.toteuch.TFTOptimizer.constantes.Quality;

public class Champion {
	private String name;
	private String detailUrl;
	private Quality quality;
	private Image image;
	private String urlImage;
	private List<ItemState> itemsStates;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public Quality getQuality() {
		return quality;
	}
	public void setQuality(Quality quality) {
		this.quality = quality;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public List<ItemState> getItemsStates() {
		return itemsStates;
	}
	public void setItemsStates(List<ItemState> itemsStates) {
		this.itemsStates = itemsStates;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
}
