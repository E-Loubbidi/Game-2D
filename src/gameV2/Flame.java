package gameV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Flame extends Bombe{

	public Flame(Monster m, Player p) {
		super(m,p);
		try {
			image = new Image(new FileInputStream("images/flamepng1.png"));
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		corps = new ImageView(image);
		((ImageView)corps).setFitHeight(70);
		((ImageView)corps).setFitWidth(70);
		corps.setTranslateX(m.corps.getTranslateX());
		corps.setTranslateY(m.corps.getTranslateY() + 20);
		//corps.setRotate(90);
		double angle = getAngle(p, m);
		updateDirection(angle, 15);
	}
}
