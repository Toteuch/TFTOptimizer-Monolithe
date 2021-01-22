package com.toteuch.TFTOptimizer.entities;

import java.util.ArrayList;
import java.util.List;

import com.toteuch.TFTOptimizer.constantes.FinalItem;
import com.toteuch.TFTOptimizer.constantes.Origin;
import com.toteuch.TFTOptimizer.constantes.Trait;

public class Champion {
	private String name;
	private String role;
	private Origin origin1;
	private Origin origin2;
	private Trait trait1;
	private Trait trait2;
	private List<FinalItem> finalItemList;
	
	@Override
	public String toString() {
		String ret = name + " " + role + " " + origin1 + " " + origin2 + " " + trait1 + " " + trait2;
		for(FinalItem item : finalItemList) {
			ret += " " + item;
		}
		return ret;
	}
	
	public Champion() {
		finalItemList = new ArrayList<FinalItem>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Trait getTrait1() {
		return trait1;
	}
	public void setTrait1(Trait trait1) {
		this.trait1 = trait1;
	}
	public Trait getTrait2() {
		return trait2;
	}
	public void setTrait2(Trait trait2) {
		this.trait2 = trait2;
	}

	public Origin getOrigin1() {
		return origin1;
	}

	public void setOrigin1(Origin origin1) {
		this.origin1 = origin1;
	}

	public Origin getOrigin2() {
		return origin2;
	}

	public void setOrigin2(Origin origin2) {
		this.origin2 = origin2;
	}

	public List<FinalItem> getFinalItemList() {
		return finalItemList;
	}

	public void setFinalItemList(List<FinalItem> finalItemList) {
		this.finalItemList = finalItemList;
	}
}
