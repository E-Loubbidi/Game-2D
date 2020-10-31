package gameV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rocket extends GraphicObject{

	Image image = null;
	public Rocket(Zone zone) {
		try {
			Image images[] = new Image[3];
			images[0] = new Image(new FileInputStream("images/rocketgif1.gif"));
			images[1] = new Image(new FileInputStream("images/rocketgif.gif"));
			//images[2] = new Image(new FileInputStream("images/rocketgif2.gif"));
			
			image = images[(int) Settings.getRandom(0, 2)];
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		corps = new ImageView(image);
		//corps.setRotate(90);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		((ImageView)corps).setFitHeight(85);
		((ImageView)corps).setFitWidth(85);
		
		double x = zone.getX1() + (zone.getX2() - zone.getX1()) * Math.random();
		double y = zone.getY1() + (zone.getY2() - zone.getY1()) * Math.random();
		
//		corps.setTranslateX(x);
//		corps.setTranslateY(y);
		
		corps.setTranslateX(10);
		corps.setTranslateY(Settings.getRandom(zone.getY1(),zone.getY2()));
}
	
}
