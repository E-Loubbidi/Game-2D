package gameV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shoot extends GraphicObject{
	
	Point2D direction = new Point2D(0,0);
	Image image = null;
	public Shoot(Arme arme, Sortie sortie) {
		try {
			image = new Image(new FileInputStream("images/bomb.png"));
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		corps = new ImageView(image);
		((ImageView)corps).setFitHeight(75);
		((ImageView)corps).setFitWidth(75);
		corps.setTranslateX(sortie.corps.getTranslateX());
		corps.setTranslateY(sortie.corps.getTranslateY());
		updateDirection(arme.getRotate());
	}
	
	private void updateDirection(double angle) {
		Point2D p;
		double x = Math.cos(Math.toRadians(angle));
		double y = Math.sin(Math.toRadians(angle));
		p = new Point2D(x, y);
		direction = p.normalize().multiply(7);
	}
	
	public void update() {
		corps.setTranslateX(corps.getTranslateX() + direction.getX());
		corps.setTranslateY(corps.getTranslateY() + direction.getY());
	}
	
	public void rotate(double angle) {
		corps.setRotate(angle);
	}
}
