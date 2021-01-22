package com.toteuch.TFTOptimizer.ihm;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ChampionStatePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ChampionStatePanel() {
		
		JLabel lblAatrox = new JLabel("Aatrox");
		lblAatrox.setIcon(new ImageIcon(ChampionStatePanel.class.getResource("/img/champion/Aatrox.png")));
		add(lblAatrox);

	}

}
