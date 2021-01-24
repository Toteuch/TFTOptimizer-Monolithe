package com.toteuch.TFTOptimizer.ihm.component;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toteuch.TFTOptimizer.entities.ChampAnalysis;
import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.entities.Item;
import com.toteuch.TFTOptimizer.ihm.layout.WrapLayout;
import com.toteuch.TFTOptimizer.ihm.utils.ColorUtils;
import com.toteuch.TFTOptimizer.ihm.utils.ImageUtils;

public class SlamedItemPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public static final String PREFIX_SLAMED_ITEM_PANEL = "PREFIX_SLAMED_ITEM_PANEL_";
	
	private final static int ITEM_ICON_WIDTH_MINI = 20;
	private final static int ITEM_ICON_HEIGHT_MINI = 20;
	
	private final static String FP_PLACEHOLDER_ITEM = "img/item/PLACEHOLDER.png";
	
	public SlamedItemPanel(ChampAnalysis champAnalysis) {
		Champion champ = champAnalysis.getChamp();
		String champName = champ.getName();
		this.setName(PREFIX_SLAMED_ITEM_PANEL+champName);
		this.setLayout(new WrapLayout());
		Color champColor = champ.getQuality().getColor();
		this.setBackground(champColor);
		this.setForeground(ColorUtils.getReadable(champColor));
		for(Item item : champAnalysis.getSlamedItems()) {
			JLabel itemLabel = new JLabel();
			itemLabel.setIcon(ImageUtils.getScaledImageIcon(item.getImage(), ITEM_ICON_WIDTH_MINI, ITEM_ICON_HEIGHT_MINI));
			itemLabel.setBackground(champColor);
			itemLabel.setForeground(ColorUtils.getReadable(champColor));
			this.add(itemLabel);
		}
		if(champAnalysis.getSlamedItems() == null || champAnalysis.getSlamedItems().isEmpty()) {
			JLabel itemLabel = new JLabel();
			itemLabel.setIcon(ImageUtils.getScaledImageIconFromClassLoader(FP_PLACEHOLDER_ITEM, ITEM_ICON_WIDTH_MINI, ITEM_ICON_HEIGHT_MINI));
			itemLabel.setBackground(champColor);
			itemLabel.setForeground(ColorUtils.getReadable(champColor));
			this.add(itemLabel);
		}
	}
}
