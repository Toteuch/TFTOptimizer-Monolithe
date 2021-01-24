package com.toteuch.TFTOptimizer.ihm.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import com.toteuch.TFTOptimizer.ihm.utils.ImageUtils;

public class PinLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	
	private static final String PIN_ICON_FP = "icon/pin.png";
	
	public static final Border PIN_ICON_BORDER = BorderFactory.createLineBorder(Color.RED, 1);
	public static final String PIN_LABEL_NAME = "PIN_LABEL_NAME";
	
	public PinLabel(Color background) {
		this.setName(PIN_LABEL_NAME);
		this.setIcon(ImageUtils.getScaledImageIconFromClassLoader(PIN_ICON_FP, MenuPanel.CHILD_COMPONENT_WIDTH, MenuPanel.CHILD_COMPONENT_HEIGHT));
		this.setBackground(background);
		this.setBorder(PIN_ICON_BORDER);
	}
}
