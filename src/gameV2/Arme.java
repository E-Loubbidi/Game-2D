package gameV2;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Arme extends GraphicObject{
	
	Image image = null;
	public Arme(GraphicObject player) {
		try {
			image = new Image(new FileInputStream("images/shoot.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		corps = new ImageView(image);
		((ImageView)corps).setFitHeight(150);
		((ImageView)corps).setFitWidth(150);
		updateCorps(player);
	}
	public void updateCorps(GraphicObject player) {
		corps.setTranslateX(player.corps.getTranslateX() + 10);
		corps.setTranslateY(player.corps.getTranslateY() - 5);
	}
	
	public void rotate(double angle) {
		corps.setRotate(angle);
	}
	
	public double getRotate() {
		return corps.getRotate();
	}
}
