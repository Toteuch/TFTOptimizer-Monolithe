package com.toteuch.TFTOptimizer.ihm.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.ihm.utils.ColorUtils;
import com.toteuch.TFTOptimizer.ihm.utils.ImageUtils;

public class ChampLabel extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public static final String PREFIX_CHAMP_LABEL = "PREFIX_CHAMP_LABEL_";
	
	private final static int CHAMP_ICON_TEXT_FONT_SIZE = 10;
	private final static int CHAMP_ICON_WIDTH = 55;
	private final static int CHAMP_ICON_HEIGHT = 55;
	
	public ChampLabel(Champion champ) {
		String name = champ.getName();
		this.setName(PREFIX_CHAMP_LABEL+name);
		String text = name;
		if(text.length() > 9) {
			text = text.substring(0,9);
		}
		
		this.setText(text);
		Font font = new Font("Serial", Font.BOLD, CHAMP_ICON_TEXT_FONT_SIZE);
		this.setFont(font);
		
		this.setIcon(ImageUtils.getScaledImageIcon(champ.getImage(), CHAMP_ICON_WIDTH, CHAMP_ICON_HEIGHT));
		
		this.setVerticalTextPosition(JLabel.TOP);
		this.setHorizontalTextPosition(JLabel.CENTER);
		this.setIconTextGap(0);
		
		Color champColor = champ.getQuality().getColor();
		this.setBackground(champColor);
		this.setForeground(ColorUtils.getReadable(champColor));
	}
}
