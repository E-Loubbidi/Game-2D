package gameV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Health extends GraphicObject{

	Image image = null;
	public Health(Zone zone) {
		try {
			image = new Image(new FileInputStream("images/health.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		corps = new ImageView(image);
		((ImageView)corps).setFitHeight(50);
		((ImageView)corps).setFitWidth(35);
		
		double x = zone.getX1() + (zone.getX2() - zone.getX1()) * Math.random();
		double y = zone.getY1() + (zone.getY2() - zone.getY1()) * Math.random();
		
		corps.setTranslateX(x);
		corps.setTranslateY(y);
	}
}
