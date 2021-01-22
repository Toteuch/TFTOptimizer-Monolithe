package com.toteuch.TFTOptimizer.ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;

import com.toteuch.TFTOptimizer.constantes.FinalItem;
import com.toteuch.TFTOptimizer.constantes.Item;
import com.toteuch.TFTOptimizer.entities.Champion;
import com.toteuch.TFTOptimizer.entities.ChampionState;
import com.toteuch.TFTOptimizer.ihm.layout.WrapLayout;
import com.toteuch.TFTOptimizer.services.CSVService;
import com.toteuch.TFTOptimizer.services.ResolverService;

public class MainFrame {

	private JFrame frame;
	private List<Champion> champList;

	private final static int MAIN_FRAME_WIDTH = 900;
	private final static int MAIN_FRAME_HEIGHT = 700;
	
	private final static String EXTENSION_PNG = ".png";

	private final static String NAME_ITEM_PANEL = "NAME_ITEM_PANEL";
	private final static String NAME_RESULT_PANEL = "NAME_RESULT_PANEL";
	private final static String PREFIX_ITEM_LABEL = "PREFIX_ITEM_LABEL_";
	private final static String PREFIX_CS_PANEL = "CS_PANEL_";
	private final static String PREFIX_CS_CHAMP_LABEL = "PREFIX_CS_CHAMP_LABEL_";
	private final static String PREFIX_CS_DETAIL_PANEL = "PREFIX_CS_DETAIL_PANEL_";
	
	private final static Color DEFAULT_BG_COLOR = Color.GRAY;

	private final static int CHAMP_ICON_WIDTH = 55;
	private final static int CHAMP_ICON_HEIGHT = 55;
	private final static int CHAMP_ICON_TEXT_FONT_SIZE = 10;
	private final static int ITEM_ICON_WIDTH = 50;
	private final static int ITEM_ICON_HEIGHT = 50;
	private final static int ITEM_ICON_WIDTH_MINI = 20;
	private final static int ITEM_ICON_HEIGHT_MINI = 20;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		champList = CSVService.parseCSV();

		frame = new JFrame();
		frame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setBackground(DEFAULT_BG_COLOR);
		
		frame.setLayout(new WrapLayout(WrapLayout.CENTER));
		JPanel itemPanel = constructItemPanel();
		frame.getContentPane().add(itemPanel);
	}

	private JPanel constructResultPanel(List<ChampionState> csList) {
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new WrapLayout());
		resultPanel.setName(NAME_RESULT_PANEL);
		//resultPanel.setBackground(DEFAULT_BG_COLOR);

		for (ChampionState cs : csList) {
			resultPanel.add(constructChampionStateComponent(cs), WrapLayout.LEFT);
		}
		return resultPanel;
	}

	private JPanel constructChampionStateComponent(ChampionState cs) {
		JPanel csPanel = new JPanel();
		Champion champ = cs.getChamp();
		String champName = champ.getName();
		csPanel.setName(PREFIX_CS_PANEL + champName);
		csPanel.setLayout(new GridBagLayout());

		JLabel champLabel = constructChampionStatementLabel(cs);
		GridBagConstraints cChampLabel = new GridBagConstraints();
		cChampLabel.gridx = 0;
		cChampLabel.gridy = 0;
		csPanel.add(champLabel, cChampLabel);

		JPanel detailedOptimPanel = constructChampionStateDetailComponent(cs);
		GridBagConstraints cDetails = new GridBagConstraints();
		cDetails.gridx = 0;
		cDetails.gridy = 1;
		csPanel.add(detailedOptimPanel, cDetails);

		return csPanel;
	}

	private JLabel constructChampionStatementLabel(ChampionState cs) {
		JLabel champLabel = new JLabel();
		Champion champ = cs.getChamp();
		String champName = champ.getName();
		champLabel.setName(PREFIX_CS_CHAMP_LABEL + champName);

		String text = champName;
		if (StringUtils.contains(text, " ")) {
			text = "<html><body>" + text.replace(" ", "<br/>") + "</body></html>";
		}
		champLabel.setText(text);

		champLabel.setForeground(Color.WHITE);
		Font font = new Font("Serial", Font.BOLD, CHAMP_ICON_TEXT_FONT_SIZE);
		champLabel.setFont(font);

		champLabel.setIcon(
				getScaledImageIcon("/img/champion/Aatrox.png", champName, CHAMP_ICON_WIDTH, CHAMP_ICON_HEIGHT));

		champLabel.setVerticalTextPosition(JLabel.CENTER);
		champLabel.setHorizontalTextPosition(JLabel.CENTER);
		champLabel.setIconTextGap(0);

		// sets the text to the upper left corner
		// champLabel.setVerticalAlignment(SwingConstants.NORTH);
		return champLabel;
	}

	private JPanel constructChampionStateDetailComponent(ChampionState cs) {
		JPanel CSDComponent = new JPanel();
		Champion champ = cs.getChamp();
		String champName = champ.getName();
		CSDComponent.setName(PREFIX_CS_DETAIL_PANEL + champName);
		CSDComponent.setLayout(new GridBagLayout());
		
		// Score du champion
		JLabel scoreLabel = new JLabel(String.valueOf(cs.getScore()));
		GridBagConstraints cScoreLabel = new GridBagConstraints();
		cScoreLabel.gridx = 0;
		cScoreLabel.gridy = 0;
		CSDComponent.add(scoreLabel, cScoreLabel);
		
		// Item slamed
		JPanel slamedItemPanel = constructChampionStateDetailSlamedPanel(cs);
		GridBagConstraints cSlamedItemLabel = new GridBagConstraints();
		cSlamedItemLabel.gridx = 0;
		cSlamedItemLabel.gridy = 1;
		CSDComponent.add(slamedItemPanel, cSlamedItemLabel);
		
		return CSDComponent;
	}
	
	private JPanel constructChampionStateDetailSlamedPanel(ChampionState cs) {
		JPanel slamedPanel = new JPanel();
		slamedPanel.setLayout(new WrapLayout());
		
		
		List<FinalItem> slamedItems = cs.getSlamableItems();
		// Pour chaque item slamed
		for(FinalItem item : slamedItems) {
			JLabel itemLabel = new JLabel();
			itemLabel.setIcon(getScaledImageIcon("/img/item/" + item.name() + EXTENSION_PNG, "", ITEM_ICON_WIDTH_MINI, ITEM_ICON_HEIGHT_MINI));
			slamedPanel.add(itemLabel);
		}
		
		return slamedPanel;
	}

	private JPanel constructItemPanel() {
		JPanel itemPanel = new JPanel();
		itemPanel.setLayout(new WrapLayout());
		itemPanel.setBackground(DEFAULT_BG_COLOR);
		itemPanel.setName(NAME_ITEM_PANEL);
		int index = 0;
		// Création des icones items
		for (Item item : Item.values()) {
			JLabel label = new JLabel("0",
					getScaledImageIcon(item.getPathToImg(), item.getPathToImg(), ITEM_ICON_WIDTH, ITEM_ICON_HEIGHT),
					JLabel.CENTER);
			label.setName(PREFIX_ITEM_LABEL + item.name());
			label.setVerticalTextPosition(JLabel.BOTTOM);
			label.setHorizontalTextPosition(JLabel.CENTER);
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
			itemPanel.add(label, WrapLayout.LEFT, index);
			index++;
		}
		return itemPanel;
	}

	private void optimize() {
		// get All Initial Item in map
		Map<Item, Integer> items = new HashMap<Item, Integer>();
		JPanel itemPanel = null;
		JPanel resultPanel = null;
		for (Component c : frame.getContentPane().getComponents()) {
			if (c instanceof JPanel) {
				if (StringUtils.equals(((JPanel) c).getName(), NAME_ITEM_PANEL)) {
					itemPanel = (JPanel) c;
				} else if (StringUtils.equals(((JPanel) c).getName(), NAME_RESULT_PANEL)) {
					resultPanel = (JPanel) c;
				}

			}
		}
		for (int index = 0; index < Item.values().length; index++) {
			JLabel label = (JLabel) itemPanel.getComponent(index);
			Item item = Item.valueOf(label.getName().substring(PREFIX_ITEM_LABEL.length(), label.getName().length()));
			items.put(item, Integer.parseInt(label.getText()));
		}
		List<ChampionState> result = ResolverService.getOptimizedChampionList(champList, items);
		if (resultPanel != null) {
			frame.getContentPane().remove(resultPanel);
		}
		resultPanel = constructResultPanel(result);
		frame.getContentPane().add(resultPanel);
		frame.repaint();

	}

	/**************************/
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIconP(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private ImageIcon getScaledImageIcon(String path, String description, int w, int h) {
		ImageIcon icon = createImageIconP(path, description);
		if (icon == null) {
			return null;
		}
		Image rawImg = icon.getImage();
		Image scaledImg = getScaledImage(rawImg, w, h);
		icon.setImage(scaledImg);
		return icon;
	}

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

}
