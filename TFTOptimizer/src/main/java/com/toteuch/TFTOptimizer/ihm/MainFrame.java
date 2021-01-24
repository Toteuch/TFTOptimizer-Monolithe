package com.toteuch.TFTOptimizer.ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;

import com.toteuch.TFTOptimizer.entities.ChampAnalysis;
import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.entities.Item;
import com.toteuch.TFTOptimizer.ihm.component.ChampAnalysisPanel;
import com.toteuch.TFTOptimizer.ihm.component.LowItemPanel;
import com.toteuch.TFTOptimizer.ihm.component.MenuPanel;
import com.toteuch.TFTOptimizer.ihm.component.ResultPanel;
import com.toteuch.TFTOptimizer.ihm.layout.WrapLayout;
import com.toteuch.TFTOptimizer.ihm.utils.ComponentNameUtils;
import com.toteuch.TFTOptimizer.ihm.utils.ImageUtils;
import com.toteuch.TFTOptimizer.services.ITFTOptimizerService;
import com.toteuch.TFTOptimizer.services.TFTOptimizerService;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public final static int MAIN_FRAME_WIDTH = 900;
	public final static int MAIN_FRAME_HEIGHT = 800;
	public final static Color DEFAULT_BG_COLOR = Color.GRAY;

	public final static String ICON_FP = "icon/mainFrameIcon.jpg";

	private Map<String, Champion> selChampionMap = new HashMap<String, Champion>();

	private ITFTOptimizerService service;
	private SelectedChampDialog selectedChampPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.setVisible(true);
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
		setIconImage(ImageUtils.getScaledImageIconFromClassLoader(ICON_FP, 128, 128).getImage());
		String version = service.getProjectVersion();
		setTitle("TFTOptimizer v" + version + " made by Toteuch");
		setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(DEFAULT_BG_COLOR);
		setLayout(new WrapLayout(WrapLayout.CENTER));

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		getContentPane().add(resetButton);
		initializeSelectedChampFrame();
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e)
            {
                int x = getX();
                int y=  getY();
                selectedChampPanel.setLocation(new Point(x + getWidth(),y));
            }
		});

		SwingUtilities.invokeLater(asynchronousInit());
	}

	private void initializeSelectedChampFrame() {
		selectedChampPanel = new SelectedChampDialog(DEFAULT_BG_COLOR, getSelectedChampFrameLocation());
		JLabel mainFrameLabel = (JLabel) ComponentNameUtils.getComponentByName(MenuPanel.MAIN_FRAME_LABEL_NAME,
				selectedChampPanel);
		mainFrameLabel.addMouseListener(mainFrameLabelClickAdapter());
	}

	private MouseAdapter mainFrameLabelClickAdapter() {
		MouseAdapter ret = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				giveFocus();
			}
		};
		return ret;
	}

	private Runnable asynchronousInit() {
		return new Runnable() {
			@Override
			public void run() {
				List<Item> lowItems = service.getLowItems();
				LowItemPanel lowItemPanel = new LowItemPanel(DEFAULT_BG_COLOR, lowItems);
				getContentPane().add(lowItemPanel);
				addMouseListenerToLowItemPanel(lowItemPanel);
			}
		};
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

	private void addMouseListenerToAllChampAnalysisPanel(ResultPanel resultPanel) {
		for (Component c : resultPanel.getComponents()) {
			if (c instanceof ChampAnalysisPanel) {
				ChampAnalysisPanel capanel = (ChampAnalysisPanel) c;
				capanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isLeftMouseButton(e)) {
							selectChamp(capanel, true);
						} else if (SwingUtilities.isRightMouseButton(e)) {
							selectChamp(capanel, false);
						}
					}
				});
			}
		}
	}

	private void selectChamp(ChampAnalysisPanel capanel, boolean select) {
		String champName = ComponentNameUtils.getObjectName(ChampAnalysisPanel.PREFIX_CHAMP_ANALYSIS_PANEL, capanel);
		Champion champ = service.getChamps().get(champName);
		if (select && selChampionMap.get(champName) == null) {
			this.selChampionMap.put(champName, champ);
			capanel.setBorder(ChampAnalysisPanel.BORDER_SELECTED_CHAMP);
			capanel.repaint();
			selectedChampPanel.addChamp(champ);
		} else if (!select && selChampionMap.get(champName) != null) {
			this.selChampionMap.remove(champName);
			capanel.setBorder(null);
			capanel.repaint();
			selectedChampPanel.removeChamp(champ);
		}

	}

	private Point getSelectedChampFrameLocation() {
		Point p = getLocation();
		p.x = p.x + MAIN_FRAME_WIDTH;
		return p;
	}

	private void optimize() {
		LowItemPanel lowItemPanel = null;
		ResultPanel resultPanel = null;
		for (Component c : getContentPane().getComponents()) {
			if (c instanceof LowItemPanel) {
				lowItemPanel = (LowItemPanel) c;
			} else if (c instanceof ResultPanel) {
				resultPanel = (ResultPanel) c;
			}
		}
		Map<Item, Integer> rawMats = getRawMats(lowItemPanel);
		List<ChampAnalysis> champsAnalysisList = service.getBestScore(rawMats);

		if (resultPanel != null) {
			getContentPane().remove(resultPanel);
		}
		resultPanel = new ResultPanel(champsAnalysisList);
		addMouseListenerToAllChampAnalysisPanel(resultPanel);
		getContentPane().add(resultPanel);
		repaint();
	}

	private Map<Item, Integer> getRawMats(LowItemPanel lowItemPanel) {
		Map<Item, Integer> rawMats = new HashMap<Item, Integer>();
		for (Component c : lowItemPanel.getComponents()) {
			if (c instanceof JLabel) {
				JLabel itemLabel = (JLabel) c;
				String itemName = StringUtils.substring(itemLabel.getName(), LowItemPanel.PREFIX_ITEM_LABEL.length(),
						itemLabel.getName().length());
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

	private void reset() {
		LowItemPanel lowItemPanel = null;
		for (Component c : getContentPane().getComponents()) {
			if (c instanceof LowItemPanel) {
				lowItemPanel = (LowItemPanel) c;
				break;
			}
		}
		for (Component c : lowItemPanel.getComponents()) {
			if (c instanceof JLabel) {
				JLabel itemLabel = (JLabel) c;
				itemLabel.setText("0");
			}
		}
		for(Champion c : selChampionMap.values()) {
			selectedChampPanel.removeChamp(c);
		}
		selChampionMap.clear();
		optimize();
	}

	public void giveFocus() {
		setAlwaysOnTop(true);
		setAlwaysOnTop(false);
	}
}
