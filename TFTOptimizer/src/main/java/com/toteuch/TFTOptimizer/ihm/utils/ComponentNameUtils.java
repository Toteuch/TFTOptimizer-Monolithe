package com.toteuch.TFTOptimizer.ihm.utils;

import java.awt.Component;
import java.awt.Container;

import org.apache.commons.lang3.StringUtils;

public class ComponentNameUtils {
	
	public static String getObjectName(String prefix, Component component) {
		String componentName = component.getName();
		String name = componentName.substring(prefix.length(), componentName.length());
		return name;		
	}
	
	public static Component getComponentByName(String name, Container parent) {
		for(Component cur : parent.getComponents()) {
			if(StringUtils.equals(name, cur.getName())) {
				return cur;
			}
			if(cur instanceof Container) {
				Component c = getComponentByName(name, ((Container)cur));
				if(null != c) {
					return c;
				}
			}
		}
		return null;
	}
}
