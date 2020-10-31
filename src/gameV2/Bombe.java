package gameV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bombe extends GraphicObject{
	
	Point2D direction = new Point2D(0,0);
	Image image = null;
	public Bombe(Monster m, Player p) {
		try {
			image = new Image(new FileInputStream("images/bombe.png"));
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		corps = new ImageView(image);
		((ImageView)corps).setFitHeight(50);
		((ImageView)corps).setFitWidth(50);
		corps.setTranslateX(m.corps.getTranslateX());
		corps.setTranslateY(m.corps.getTranslateY() + 20);
		corps.setRotate(90);
		double angle = getAngle(p, m);
		updateDirection(angle, 7);
	}
	
	protected double getAngle(Player p, Monster m) {
		double x = p.corps.getTranslateX();
		double y = p.corps.getTranslateY();
		double xDist = x - m.corps.getTranslateX();
		double yDist = y - m.corps.getTranslateY();
		double angle = Math.toDegrees(Math.atan2(yDist, xDist));
		return angle;
	}
	
	protected void updateDirection(double angle, int vitesse) {
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

}

