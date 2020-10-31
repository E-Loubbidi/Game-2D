package gameV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BigMonster extends Monster{

	public BigMonster(Zone zone) {
		super(zone);
		color = "blue";
		try {
			image = new Image(new FileInputStream("images/drag1.gif"));
			corps = new ImageView(image);
			((ImageView)corps).setX(0);
			((ImageView)corps).setY(0);
			((ImageView)corps).setFitHeight(250);
			((ImageView)corps).setFitWidth(300);
			
			double x = zone.getX1() + (zone.getX2() - zone.getX1()) * Math.random();
			double y = zone.getY1() + (zone.getY2() - zone.getY1()) * Math.random();
			
			corps.setTranslateX(x);
			corps.setTranslateY(y);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
