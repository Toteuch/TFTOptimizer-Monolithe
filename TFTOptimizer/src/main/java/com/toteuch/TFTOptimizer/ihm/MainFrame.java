package com.toteuch.TFTOptimizer.ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;

import com.toteuch.TFTOptimizer.entities.ChampAnalysis;
import com.toteuch.TFTOptimizer.entities.Item;
import com.toteuch.TFTOptimizer.ihm.component.LowItemPanel;
import com.toteuch.TFTOptimizer.ihm.component.ResultPanel;
import com.toteuch.TFTOptimizer.ihm.layout.WrapLayout;
import com.toteuch.TFTOptimizer.services.ITFTOptimizerService;
import com.toteuch.TFTOptimizer.services.TFTOptimizerService;

public class MainFrame {

	private JFrame frame;

	private final static int MAIN_FRAME_WIDTH = 900;
	private final static int MAIN_FRAME_HEIGHT = 800;
	private final static Color DEFAULT_BG_COLOR = Color.GRAY;

	private ITFTOptimizerService service;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		service = new TFTOptimizerService();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(DEFAULT_BG_COLOR);
		frame.setLayout(new WrapLayout(WrapLayout.CENTER));

		Runnable lowItemPanelThread = new Runnable() {
			public void run() {
				List<Item> lowItems = service.getLowItems();
				LowItemPanel lowItemPanel = new LowItemPanel(DEFAULT_BG_COLOR, lowItems);
				frame.getContentPane().add(lowItemPanel);
				addMouseListenerToLowItemPanel(lowItemPanel);
			}
		};
		SwingUtilities.invokeLater(lowItemPanelThread);
	}

	private void addMouseListenerToLowItemPanel(LowItemPanel panel) {
		for (Component c : panel.getComponents()) {
			if (c instanceof JLabel) {
				JLabel label = (JLabel) c;
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int value = Integer.parseInt(label.getText());
						int newValue = 0;
						if (SwingUtilities.isLeftMouseButton(e)) {
							newValue = value + 1;
						} else if (SwingUtilities.isRightMouseButton(e)) {
							if (value > 0) {
								newValue = value - 1;
							}
						}
						if (value != newValue) {
							label.setText(String.valueOf(newValue));
							optimize();
						}
					}
				});
			}
		}
	}

	private void optimize() {
		LowItemPanel lowItemPanel = null;
		ResultPanel resultPanel = null;
		for (Component c : frame.getContentPane().getComponents()) {
			if (c instanceof LowItemPanel) {
				lowItemPanel = (LowItemPanel) c;
			} else if (c instanceof ResultPanel) {
				resultPanel = (ResultPanel) c;
			}
		}
		Map<Item, Integer> rawMats = getRawMats(lowItemPanel);
		List<ChampAnalysis> champsAnalysisList = service.getBestScore(rawMats);

		if (resultPanel != null) {
			frame.getContentPane().remove(resultPanel);
		}
		resultPanel = new ResultPanel(champsAnalysisList);
		frame.getContentPane().add(resultPanel);
		frame.repaint();
	}

	private Map<Item, Integer> getRawMats(LowItemPanel lowItemPanel) {
		Map<Item, Integer> rawMats = new HashMap<Item, Integer>();
		for(Component c : lowItemPanel.getComponents()) {
			if(c instanceof JLabel) {
				JLabel itemLabel = (JLabel) c;
				String itemName = StringUtils.substring(itemLabel.getName(), LowItemPanel.PREFIX_ITEM_LABEL.length(), itemLabel.getName().length());
				for (Item lowItem : service.getLowItems()) {
					if (StringUtils.compare(itemName, lowItem.getName()) == 0) {
						int nb = Integer.parseInt(itemLabel.getText());
						rawMats.put(lowItem, nb);
						break;
					}
				}
			}
		}
		return rawMats;
	}

}
