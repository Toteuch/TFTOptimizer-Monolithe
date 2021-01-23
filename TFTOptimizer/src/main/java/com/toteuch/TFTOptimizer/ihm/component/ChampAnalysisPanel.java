package com.toteuch.TFTOptimizer.ihm.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.toteuch.TFTOptimizer.entities.ChampAnalysis;
import com.toteuch.TFTOptimizer.entities.Champion;

public class ChampAnalysisPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public static final String PREFIX_CHAMP_ANALYSIS_PANEL = "PREFIX_CHAMP_ANALYSIS_PANEL_";
	
	public ChampAnalysisPanel(ChampAnalysis champAnalysis) {
		Champion champ = champAnalysis.getChamp();
		this.setName(PREFIX_CHAMP_ANALYSIS_PANEL + champ.getName());
		this.setLayout(new GridBagLayout());
		
		ChampLabel champLabel = new ChampLabel(champ);
		GridBagConstraints cChampLabel = new GridBagConstraints();
		cChampLabel.gridx = 0;
		cChampLabel.gridy = 0;
		this.add(champLabel, cChampLabel);
		
		DetailChampAnalysisPanel detailChampAnalysisPanel = new DetailChampAnalysisPanel(champAnalysis);
		GridBagConstraints cDetails = new GridBagConstraints();
		cDetails.gridx = 0;
		cDetails.gridy = 1;
		this.add(detailChampAnalysisPanel, cDetails);
	}
}
