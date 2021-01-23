package com.toteuch.TFTOptimizer.ihm.component;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toteuch.TFTOptimizer.entities.ChampAnalysis;
import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.entities.Item;
import com.toteuch.TFTOptimizer.ihm.layout.WrapLayout;
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
		for(Item item : champAnalysis.getSlamedItems()) {
			JLabel itemLabel = new JLabel();
			Image itemImg = ImageUtils.getScaledImage(item.getImage(), ITEM_ICON_WIDTH_MINI, ITEM_ICON_HEIGHT_MINI);
			itemLabel.setIcon(new ImageIcon(itemImg));
			this.add(itemLabel);
		}
		if(champAnalysis.getSlamedItems() == null || champAnalysis.getSlamedItems().isEmpty()) {
			JLabel itemLabel = new JLabel();
			Image itemImg = null;
			try {
				itemImg = ImageUtils.getScaledImage(ImageIO.read( ClassLoader.getSystemResource(FP_PLACEHOLDER_ITEM)), ITEM_ICON_WIDTH_MINI, ITEM_ICON_HEIGHT_MINI);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Can't load Item image placeholder" + FP_PLACEHOLDER_ITEM);
			}
			itemLabel.setIcon(new ImageIcon(itemImg));
			this.add(itemLabel);
		}
	}
}
