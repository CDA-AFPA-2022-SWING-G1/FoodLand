package view;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class LogoFoodland {
	
	private ImageIcon foodland_Image;
	
	public ImageIcon getFoodland_Image() {
		return this.foodland_Image;
	}

	public void setFoodland_Image(ImageIcon foodland_Image) {
		this.foodland_Image = foodland_Image;
	}
	
	public LogoFoodland() {
		this.foodland_Image = new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(
						LogoFoodland.class.getResource("/images/foodland_logo.png"))
				.getScaledInstance(116, 80, Image.SCALE_DEFAULT)
				);
	}
}
