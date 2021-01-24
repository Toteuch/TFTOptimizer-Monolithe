package com.toteuch.TFTOptimizer.ihm.component;

import java.awt.Color;

import javax.swing.JPanel;

public class SelectedChampPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static Color BG_COLOR;
	public static final String NAME = "SelectedChampPanelNAME";
	
	public SelectedChampPanel(Color background) {
		BG_COLOR = background;
		setBackground(BG_COLOR);
		//setLayout(new WrapLayout(WrapLayout.LEFT));
		setName(NAME);
	}
}
