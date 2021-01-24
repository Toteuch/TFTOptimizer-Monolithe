package com.toteuch.TFTOptimizer.ihm.component;

import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toteuch.TFTOptimizer.entities.Item;
import com.toteuch.TFTOptimizer.ihm.layout.WrapLayout;
import com.toteuch.TFTOptimizer.ihm.utils.ImageUtils;

public class LowItemPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public final static String NAME_ITEM_PANEL = "NAME_LOW_ITEM_PANEL";
	public final static String PREFIX_ITEM_LABEL = "PREFIX_LOW_ITEM_LABEL_";
	
	private final static int ITEM_ICON_WIDTH = 50;
	private final static int ITEM_ICON_HEIGHT = 50;
	
	public LowItemPanel(Color defaultBgColor, List<Item> lowItems) {
		this.setLayout(new WrapLayout());
		this.setBackground(defaultBgColor);
		this.setName(NAME_ITEM_PANEL);
		int index = 0;
		// Création des icones items
		for (Item item : lowItems) {
			JLabel label = new JLabel("0", // Nombre possédé
					ImageUtils.getScaledImageIcon(item.getImage(), ITEM_ICON_WIDTH, ITEM_ICON_HEIGHT),
					JLabel.CENTER);
			label.setName(PREFIX_ITEM_LABEL + item.getName());
			label.setVerticalTextPosition(JLabel.BOTTOM);
			label.setHorizontalTextPosition(JLabel.CENTER);
			this.add(label, WrapLayout.LEFT, index);
			index++;
		}
	}
}
