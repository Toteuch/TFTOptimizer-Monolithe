package com.toteuch.TFTOptimizer.entities;

import java.awt.Image;
import java.util.List;

public class Item {
	private String name;
	private Image image;
	private String urlImage;
	private List<Item> combination;
	
	@Override
	public String toString() {
		String ret = "Item : " + name + "\n"
					+"ImgURL : " + (image == null? "null":"found") + "\n"
					+"Combination : ";
		if(null != combination) {
			ret += combination.get(0).getName() + " + " + combination.get(1).getName();
		} else {
			ret += "non";
		}
		return ret;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public List<Item> getCombination() {
		return combination;
	}
	public void setCombination(List<Item> combination) {
		this.combination = combination;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
}
