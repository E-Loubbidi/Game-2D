package gameV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Explosion extends GraphicObject{

	Image image = null;
	public Explosion(GraphicObject player) {
		try {
			image = new Image(new FileInputStream("images/explosion.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		corps = new ImageView(image);
		((ImageView)corps).setFitHeight(200);
		((ImageView)corps).setFitWidth(200);
		updateCorps(player);
	}
	public void updateCorps(GraphicObject player) {
		corps.setTranslateX(player.corps.getTranslateX());
		corps.setTranslateY(player.corps.getTranslateY());
	}
}
