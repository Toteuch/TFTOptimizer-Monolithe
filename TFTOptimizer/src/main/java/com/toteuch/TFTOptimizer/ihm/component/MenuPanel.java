package com.toteuch.TFTOptimizer.ihm.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toteuch.TFTOptimizer.ihm.MainFrame;
import com.toteuch.TFTOptimizer.ihm.utils.ImageUtils;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static final String MENU_PANEL_NAME = "MENU_PANEL_NAME";
	public static final String MAIN_FRAME_LABEL_NAME = "MAIN_FRAME_LABEL_NAME";
	public static final int CHILD_COMPONENT_WIDTH = 15;
	public static final int CHILD_COMPONENT_HEIGHT = 15;
	
	private static Color BG_COLOR;
	
	public MenuPanel(Color background) {
		BG_COLOR = background;
		setLayout(new GridBagLayout());
		
		this.setName(MENU_PANEL_NAME);
		
		initializeComponent();
	}
	
	private void initializeComponent() {
		// Pin Label
		initPinLabel();
		// Main Frame Label
		initMainFrameLabel();
	}
	
	private void initMainFrameLabel() {
		JLabel mainFrameLabel = new JLabel();
		mainFrameLabel.setName(MAIN_FRAME_LABEL_NAME);
		mainFrameLabel.setIcon(ImageUtils.getScaledImageIconFromClassLoader(MainFrame.ICON_FP, CHILD_COMPONENT_WIDTH, CHILD_COMPONENT_HEIGHT));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(3, 0, 0, 3);
		c.ipadx = 0;
		c.ipady = 0;
		add(mainFrameLabel, c);
	}
	
	private void initPinLabel() {
		PinLabel pinLabel = new PinLabel(BG_COLOR);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(3, 3, 0, 0);
		c.ipadx = 0;
		c.ipady = 0;
		add(pinLabel, c);
	}

}
