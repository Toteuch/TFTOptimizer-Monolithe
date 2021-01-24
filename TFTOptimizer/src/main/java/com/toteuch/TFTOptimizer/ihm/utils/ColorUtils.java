package com.toteuch.TFTOptimizer.ihm.utils;

import java.awt.Color;

import com.toteuch.TFTOptimizer.constantes.Quality;

public class ColorUtils {
	
	public static Color getReadable(Color backgroundColor) {
		if(Quality.RARE.getColor() == backgroundColor || Quality.EPIQUE.getColor() == backgroundColor) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}
}
