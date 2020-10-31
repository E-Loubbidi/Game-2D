package gameV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sortie extends GraphicObject{

	Image image = null;
	public Sortie(Arme arme){
		try {
			image = new Image(new FileInputStream("images/bomb.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		corps = new ImageView(image);
		((ImageView)corps).setFitHeight(75);
		((ImageView)corps).setFitWidth(75);
		updateSortie(arme);
	}
	
	public void updateSortie(Arme arme) {
		corps.setTranslateX(arme.corps.getTranslateX() + 35);
		corps.setTranslateY(arme.corps.getTranslateY() + 35);
	}
	
	public void rotate(double angle) {
		corps.setRotate(angle);
	}
}
