package gameV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster extends GraphicObject{

	String color;
	Image image = null;
	public Monster(Zone zone) {
		color = "red";
		try {
			image = new Image(new FileInputStream("images/dragon.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		corps = new ImageView(image);
		//corps.setRotate(90);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		((ImageView)corps).setFitHeight(150);
		((ImageView)corps).setFitWidth(200);
		
		double x = zone.getX1() + (zone.getX2() - zone.getX1()) * Math.random();
		double y = zone.getY1() + (zone.getY2() - zone.getY1()) * Math.random();
		
		corps.setTranslateX(x);
		corps.setTranslateY(y);
}
}
