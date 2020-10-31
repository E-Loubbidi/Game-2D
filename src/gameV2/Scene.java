package gameV2;

import java.util.Timer;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Scene {

	private AnchorPane bg = new AnchorPane();
	private AnchorPane bg1 = new AnchorPane();
	private AnchorPane bg2 = new AnchorPane();
	private Image img;
	private Background bgImg;
	private BackgroundSize bgSize;
	private AnimationTimer animationTimer;
	private Timer timer;
	
	public AnchorPane getBG() {
		return bg;
	}
	
	public void changeBackground() {
		timer = new Timer();
		img = new Image("BG8.gif", 1000, 700, false, false);
		bgSize = new BackgroundSize(1000, 700, false, false, false, false);
		bgImg = new Background(new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, bgSize));
		bg1.setBackground(bgImg);
		bg1.setTranslateX(0);
		bg2.setBackground(bgImg);
		bg2.setTranslateX(1000);
		bg1.setPrefWidth(1000);
		bg1.setPrefHeight(700);
		bg2.setPrefWidth(1000);
		bg2.setPrefHeight(700);
		bg.setPrefWidth(1000);
		bg.setPrefHeight(700);
		bg.getChildren().add(bg1);
		bg.getChildren().add(bg2);
		
		animationTimer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				if(bg1.getTranslateX() > bg.getWidth() * (-1)) {
					bg1.setTranslateX(bg1.getTranslateX() - 1);
				}
				else {
					bg1.setTranslateX(bg1.getTranslateX() * (-1) - 1);
				}
				if(bg2.getTranslateX() > bg.getWidth()* (-1)) {
					bg2.setTranslateX(bg2.getTranslateX() - 1);
				}
				else {
					bg2.setTranslateX(bg2.getTranslateX() * (-1) - 1);
				}
			}
		};
		animationTimer.start();
	}
}
