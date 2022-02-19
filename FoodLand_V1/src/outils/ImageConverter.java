package outils;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageConverter {

	ImageIcon icon;
	
	public ImageIcon ImageConverter(byte[] source) {
		Image img = Toolkit.getDefaultToolkit().createImage(source);
		BufferedImage bufferedImage = ;
		icon = new ImageIcon(bufferedImage);
		return icon;
	}
	
	
	
}
