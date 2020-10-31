package gameV2;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends GraphicObject{

	Image image = null;
	Player(Zone zone){
		try {
			image = new Image(new FileInputStream("images/giphy.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		corps  = new ImageView(image);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		((ImageView)corps).setFitHeight(100);
		((ImageView)corps).setFitWidth(200);
		
		double x = zone.getX1() + (zone.getX2() - zone.getX1()) * Math.random();
		System.out.println(x);
		double y = zone.getY1() + (zone.getY2() - zone.getY1()) * Math.random();
		System.out.println(y);
		corps.setTranslateX(x);
		corps.setTranslateY(y);
	}
}
