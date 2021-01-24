package com.toteuch.TFTOptimizer.ihm.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toteuch.TFTOptimizer.entities.ChampAnalysis;
import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.ihm.utils.ColorUtils;

public class DetailChampAnalysisPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public static final String PREFIX_CHAMP_DETAILS_ANALYSIS_PANEL = "PREFIX_CHAMP_DETAILS_ANALYSIS_PANEL_";
	
	public DetailChampAnalysisPanel(ChampAnalysis champAnalysis) {
		Champion champ = champAnalysis.getChamp();
		String champName = champ.getName();
		this.setName(PREFIX_CHAMP_DETAILS_ANALYSIS_PANEL+champName);
		this.setLayout(new GridBagLayout());
		
		// Score du champion
		DecimalFormat df = new DecimalFormat("#");
		JLabel scoreLabel = new JLabel(df.format(champAnalysis.getScore()));
		Color champColor = champ.getQuality().getColor();
		scoreLabel.setBackground(champColor);
		scoreLabel.setForeground(ColorUtils.getReadable(champColor));
		GridBagConstraints cScoreLabel = new GridBagConstraints();
		cScoreLabel.gridx = 0;
		cScoreLabel.gridy = 0;
		this.add(scoreLabel, cScoreLabel);
		
		// Item slamed
		JPanel slamedItemPanel = new SlamedItemPanel(champAnalysis);
		GridBagConstraints cSlamedItemLabel = new GridBagConstraints();
		cSlamedItemLabel.gridx = 0;
		cSlamedItemLabel.gridy = 1;
		this.add(slamedItemPanel, cSlamedItemLabel);
		
		
		this.setBackground(champColor);
		this.setForeground(ColorUtils.getReadable(champColor));
	}
}
