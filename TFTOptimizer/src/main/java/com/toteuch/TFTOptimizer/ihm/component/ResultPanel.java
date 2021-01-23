package com.toteuch.TFTOptimizer.ihm.component;

import java.util.List;

import javax.swing.JPanel;

import com.toteuch.TFTOptimizer.entities.ChampAnalysis;
import com.toteuch.TFTOptimizer.ihm.layout.WrapLayout;


public class ResultPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public final static String NAME_RESULT_PANEL = "NAME_RESULT_PANEL";

	public ResultPanel(List<ChampAnalysis> champsAnalysisList) {
		this.setLayout(new WrapLayout());
		this.setName(NAME_RESULT_PANEL);
		for(ChampAnalysis championAnalysis : champsAnalysisList) {
			this.add(new ChampAnalysisPanel(championAnalysis), WrapLayout.LEFT);
		}
	}
	

}
