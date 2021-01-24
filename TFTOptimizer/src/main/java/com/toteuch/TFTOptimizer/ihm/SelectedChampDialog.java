package com.toteuch.TFTOptimizer.ihm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.ihm.component.MenuPanel;
import com.toteuch.TFTOptimizer.ihm.component.PinLabel;
import com.toteuch.TFTOptimizer.ihm.component.SelectedChampPanel;
import com.toteuch.TFTOptimizer.ihm.component.selectedchamp.ChampDetailsPanel;
import com.toteuch.TFTOptimizer.ihm.utils.ComponentNameUtils;

public class SelectedChampDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private static final int FRAME_SELECTED_CHAMP_W = 300;
	private static final int FRAME_SELECTED_CHAMP_H = MainFrame.MAIN_FRAME_HEIGHT;
	private static Color DEFAULT_BG_COLOR;
	public static final String PIN_LABEL_NAME = "PIN_LABEL_NAME";
	public static final String SHOW_MAIN_FRAME_BUTTON_NAME = "SHOW_MAIN_FRAME_BUTTON_NAME";

	public SelectedChampDialog(Color background, Point location) {
		DEFAULT_BG_COLOR = background;
		setSize(FRAME_SELECTED_CHAMP_W, FRAME_SELECTED_CHAMP_H);
		setBackground(DEFAULT_BG_COLOR);
		setLayout(new GridBagLayout());
		setLocation(location);
		setVisible(true);
		setAlwaysOnTop(true);

		initComponent();
	}

	private void initComponent() {
		// Menu row
		initMenuPanel();
		// Init SelectedChampPanel
		initSelectedChampPanel();
	}

	public void addChamp(Champion champ) {
		ChampDetailsPanel champDetailsPanel = new ChampDetailsPanel(DEFAULT_BG_COLOR, champ);
		SelectedChampPanel selPanel = (SelectedChampPanel)ComponentNameUtils.getComponentByName(SelectedChampPanel.NAME, this);
		selPanel.add(champDetailsPanel);
		selPanel.validate();
		selPanel.repaint();
	}

	public void removeChamp(Champion champ) {
		String champName = champ.getName();
		String cName = ChampDetailsPanel.PREFIX+champName;
		ChampDetailsPanel champDetailPanel = (ChampDetailsPanel) ComponentNameUtils.getComponentByName(cName, this);
		SelectedChampPanel selPanel = (SelectedChampPanel)ComponentNameUtils.getComponentByName(SelectedChampPanel.NAME, this);
		selPanel.remove(champDetailPanel);
		selPanel.validate();
		selPanel.repaint();
	}
	
	private void initSelectedChampPanel() {
		SelectedChampPanel selChampPanel = new SelectedChampPanel(DEFAULT_BG_COLOR);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		c.ipadx = 0;
		c.ipady = 0;
		add(selChampPanel, c);
	}
	
	private void initMenuPanel() {
		MenuPanel menuPanel = new MenuPanel(DEFAULT_BG_COLOR);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3, 3, 0, 0);
		c.ipadx = 0;
		c.ipady = 0;
		add(menuPanel, c);
		PinLabel pinLabel = (PinLabel) ComponentNameUtils.getComponentByName(PIN_LABEL_NAME, this);
		pinLabel.addMouseListener(pinMouseClickAdapter(pinLabel));
	}
	
	private MouseAdapter pinMouseClickAdapter(PinLabel pinLabel) {
		MouseAdapter ret = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (isAlwaysOnTop()) {
						setAlwaysOnTop(false);
						pinLabel.setBorder(null);
					} else {
						setAlwaysOnTop(true);
						pinLabel.setBorder(PinLabel.PIN_ICON_BORDER);
					}
				}
			}
		};
		return ret;
	}
}
