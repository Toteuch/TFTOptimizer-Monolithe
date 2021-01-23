package com.toteuch.TFTOptimizer.ihm.component;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.commons.lang3.StringUtils;

import com.toteuch.TFTOptimizer.entities.Champion;
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
		if (StringUtils.contains(text, " ")) {
			text = "<html><body>" + text.replace(" ", "<br/>") + "</body></html>";
		}
		this.setText(text);
		Font font = new Font("Serial", Font.BOLD, CHAMP_ICON_TEXT_FONT_SIZE);
		this.setFont(font);
		
		Image scaledImage = ImageUtils.getScaledImage(champ.getImage(), CHAMP_ICON_WIDTH, CHAMP_ICON_HEIGHT);
		this.setIcon(new ImageIcon(scaledImage));
		
		this.setVerticalTextPosition(JLabel.TOP);
		this.setHorizontalTextPosition(JLabel.CENTER);
		this.setIconTextGap(0);
	}
}
