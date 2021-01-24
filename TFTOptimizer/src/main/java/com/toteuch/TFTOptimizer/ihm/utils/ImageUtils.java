package com.toteuch.TFTOptimizer.ihm.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageUtils {
	public static ImageIcon getScaledImageIcon(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return new ImageIcon(resizedImg);
	}

	public static ImageIcon getScaledImageIconFromClassLoader(String fp, int w, int h) {
		Image img = new ImageIcon(ClassLoader.getSystemResource(fp)).getImage();
		return getScaledImageIcon(img, w, h);
	}
}
