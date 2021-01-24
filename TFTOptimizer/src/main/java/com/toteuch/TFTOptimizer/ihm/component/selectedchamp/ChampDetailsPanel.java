package com.toteuch.TFTOptimizer.ihm.component.selectedchamp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.entities.ItemState;
import com.toteuch.TFTOptimizer.ihm.utils.ImageUtils;

public class ChampDetailsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final int ICON_CHAMP_WIDTH = 70;
	private static final int ICON_CHAMP_HEIGHT = 70;
	private static final int ICON_ITEM_WIDTH = 15;
	private static final int ICON_ITEM_HEIGHT = 15;
	
	public static final String PREFIX = "ChampDetailsPanel_";
	
	public static Color BG_COLOR; 
	
	public ChampDetailsPanel(Color background, Champion champ) {
		BG_COLOR = background;
		String name = champ.getName();
		setName(PREFIX+name);
		setLayout(new GridBagLayout());
		setBackground(BG_COLOR);
		
		initChampLabel(champ);
		initChampItems(champ);
	}
	
	private void initChampItems(Champion champ) {
		int index = 1;
		for(ItemState itemState : champ.getItemsStates()) {
			initChampItemLabel(itemState, index);
			index++;
		}
	}
	
	private void initChampItemLabel(ItemState itemState, int index) {
		JLabel itemLabel = new JLabel();
		itemLabel.setIcon(ImageUtils.getScaledImageIcon(itemState.getItem().getImage(), ICON_ITEM_WIDTH, ICON_ITEM_HEIGHT));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = index;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0, 3, 0, 3);
		c.ipadx = 0;
		c.ipady = 0;
		add(itemLabel, c);
	}
	
	private void initChampLabel(Champion champ) {
		JLabel label = new JLabel();
		label.setIcon(ImageUtils.getScaledImageIcon(champ.getImage(), ICON_CHAMP_WIDTH, ICON_CHAMP_HEIGHT));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0, 0, 0, 0);
		c.ipadx = 0;
		c.ipady = 0;
		add(label, c);
	}

}
