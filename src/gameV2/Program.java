package gameV2;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class Program{

	private Pane root =new Pane();
	private Scene scene = new Scene(root);
	private double widthWindow = 1000;
	private double heightWindow = 700;
	
	gameV2.Scene sceneBg = new gameV2.Scene();
	
	private Line line = new Line(800, 0, 800, heightWindow);
	private Zone zone1 = new Zone(0, 0, line.getEndX() - 250, line.getEndY() - 250);
	private Zone zone2 = new Zone(line.getStartX(), line.getStartY(), widthWindow - 150, heightWindow - 100);
	private Zone zone22 = new Zone(line.getStartX(), line.getStartY(), widthWindow - 350, heightWindow - 300);
	private Player player = new Player(zone1);
	private Arme arme = new Arme(player);
	private Sortie sortie = new Sortie(arme);
	
	private List<Shoot> shoots = new ArrayList<Shoot>();
	private List<Monster> monsters = new ArrayList<Monster>();
	private List<Bombe> bombes = new ArrayList<Bombe>();
	private List<Health> healths = new ArrayList<Health>();
	private List<Rocket> rockets = new ArrayList<Rocket>();
	
	private ProgressBar pBar = new ProgressBar();
	
	private Explosion exp = new Explosion(player);
	
	private HBox hboxHeader = new HBox();
	
	private Image imageBomb = null;
	private ImageView imageViewBomb = null;
	private Label labelNbreShooter = new Label("0");
	private long nbreShooter = 0;
	
	private Image imageMonster = null;
	private ImageView imageViewMonster = null;
	private Label labelNbreMonster = new Label("0");
	private long nbreMonster = 0;
	
	private Button gameOverBtn = new Button("Game over");
	private Label scoreLabel = new Label();
	
	private Image imageMonsterBlue = null;
	private ImageView imageViewMonsterBlue = null;
	private Label labelNbreMonsterBlue = new Label("0");
	private long nbreMonsterblue = 0;
	
//	public static Boolean start = false; 
	
	public Program() {
		
		PlayGame.appRoot.setWidth(widthWindow);
		PlayGame.appRoot.setHeight(heightWindow);
		PlayGame.appRoot.setTitle("Game V2");
		
		//PlayGame.appRoot.getIcons().add(new Image("images/giphy.gif"));
		
		sceneBg.changeBackground();
		createContent();
		
		//cursor	
		Image image = new Image("cursor.png");
		scene.setCursor(new ImageCursor(image));
		
		PlayGame.appRoot.setScene(scene);
		
		scene.setOnKeyPressed(event);
		
		scene.setOnMouseMoved(mevent);
		
		scene.setOnMouseClicked(mcevent);
		
		animation.start();
	}
	
	public Pane getPane() {
		return root;
	}
	
	//event
	
	EventHandler<KeyEvent> event = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			
			if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.Z) {
				player.corps.setTranslateY(player.corps.getTranslateY() - 20);
				arme.updateCorps(player);
				sortie.updateSortie(arme);
			}
			
			if(event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
				player.corps.setTranslateY(player.corps.getTranslateY() + 20);
				arme.updateCorps(player);
				sortie.updateSortie(arme);
			}
			
			if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
				player.corps.setTranslateX(player.corps.getTranslateX() + 20);
				arme.updateCorps(player);
				sortie.updateSortie(arme);
			}
			
			if(event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q) {
				player.corps.setTranslateX(player.corps.getTranslateX() - 20);
				arme.updateCorps(player);
				sortie.updateSortie(arme);
			}
		}
	};
	
	EventHandler<MouseEvent> mevent = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			
			double x = event.getSceneX();
			double y = event.getSceneY();
			double xDist = x - arme.corps.getTranslateX();
			double yDist = y - arme.corps.getTranslateY();
			double angle = Math.toDegrees(Math.atan2(yDist, xDist));
			arme.rotate(angle);
			sortie.rotate(angle);
		}
	};
	
	EventHandler<MouseEvent> mcevent = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
				Shoot shoot = new Shoot(arme, sortie);
				shoot.rotate(arme.getRotate());
				root.getChildren().add(shoot.corps);
				shoots.add(shoot);
				nbreShooter++;
				labelNbreShooter.setText(Long.toString(nbreShooter));
				Settings.shootSound();
				}
			}
		}
	};
	
	private void createContent() {

		root.getChildren().add(sceneBg.getBG());
		root.getChildren().add(line);
		root.getChildren().add(player.corps);
		root.getChildren().add(arme.corps);
		root.getChildren().add(sortie.corps);
		pBar.setPrefHeight(30);
		pBar.setPrefWidth(300);
		pBar.setProgress(1.0);
		hboxHeader.getChildren().add(pBar);
		try {
			imageBomb = new Image(new FileInputStream("images/bomb.png"));
			imageViewBomb = new ImageView(imageBomb);
			imageViewBomb.setFitWidth(100);
			imageViewBomb.setFitHeight(100);
			imageViewBomb.setTranslateY(-30);
			hboxHeader.getChildren().add(imageViewBomb);
			hboxHeader.setMargin(imageViewBomb, new Insets(0, 0, 0, 30));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		labelNbreShooter.setStyle(" -fx-font: 24px \"Serif\";\r\n" + 
				"    -fx-padding: 5;\r\n" + 
				"    -fx-text-fill:white;\r\n" + 
				"    -fx-font-weight:bold;");
		hboxHeader.getChildren().add(labelNbreShooter);
		try {
			imageMonster = new Image(new FileInputStream("images/dragon.gif"));
			imageViewMonster = new ImageView(imageMonster);
			imageViewMonster.setFitWidth(70);
			imageViewMonster.setFitHeight(70);
			imageViewMonster.setTranslateY(-10);
			hboxHeader.getChildren().add(imageViewMonster);
			hboxHeader.setMargin(imageViewMonster, new Insets(0, 0, 0, 30));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		labelNbreMonster.setStyle(" -fx-font: 24px \"Serif\";\r\n" + 
				"    -fx-padding: 5;\r\n" + 
				"    -fx-text-fill:white;\r\n" + 
				"    -fx-font-weight:bold;");
		hboxHeader.getChildren().add(labelNbreMonster);
		
		try {
			imageMonsterBlue = new Image(new FileInputStream("images/drag1.gif"));
			imageViewMonsterBlue = new ImageView(imageMonsterBlue);
			imageViewMonsterBlue.setFitWidth(70);
			imageViewMonsterBlue.setFitHeight(70);
			imageViewMonsterBlue.setTranslateY(-10);
			hboxHeader.getChildren().add(imageViewMonsterBlue);
			hboxHeader.setMargin(imageViewMonsterBlue, new Insets(0, 0, 0, 30));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		labelNbreMonsterBlue.setStyle(" -fx-font: 24px \"Serif\";\r\n" + 
				"    -fx-padding: 5;\r\n" + 
				"    -fx-text-fill:white;\r\n" + 
				"    -fx-font-weight:bold;");
		hboxHeader.getChildren().add(labelNbreMonsterBlue);
		
		root.getChildren().add(hboxHeader);
	}
	
	//AnimationTimer
		AnimationTimer animation = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				refreshContent();
			}
		};
	
	private void refreshContent() {
		

		ifShootTouchMonster();
		
		ifBombeTouchPlayer();
		
		ifShootTouchBombe();
		
		setpBar();
		
		shoots.removeIf(GraphicObject::isDead);
		monsters.removeIf(GraphicObject::isDead);
		bombes.removeIf(GraphicObject::isDead);
		removeThingsOrScene();
		
		for(Shoot shoot:shoots) {
			shoot.update();
		}
		
		if(Math.random()<0.003) {
			Health health = new Health(zone1);
			root.getChildren().add(health.corps);
			healths.add(health);
			}
		
		ifPlayerTouchHealth();
		
		if(Math.random()<0.007) {
			Monster m = new Monster(zone2);
			root.getChildren().add(m.corps);
			monsters.add(m);
			}
		
		
			for(Monster monster:monsters){
				if(monster.color=="red") {
					if(Math.random() < 0.01) {
						Bombe b = new Bombe(monster, player);
						root.getChildren().add(b.corps);
						bombes.add(b);
					}
				}
			}
			for(Bombe bo:bombes){
				bo.update();
			}
			
			setRockets();
			ifBombesTouchRockets();
			moveRockets();
			rockets.removeIf(GraphicObject::isDead);
			
			displayBigMonster();
			
			displayFlame();
	}
	
	private void ifShootTouchMonster() {
	
		for(Shoot shoot : shoots) {
			for(Monster monster : monsters) {
				if(shoot.touch(monster)) {
					root.getChildren().removeAll(shoot.corps, monster.corps);
					shoot.setAlive(false);
					monster.setAlive(false);
					if(monster.color == "red") nbreMonster++;
					else nbreMonsterblue++;
					labelNbreMonster.setText(Long.toString(nbreMonster));
					labelNbreMonsterBlue.setText(Long.toString(nbreMonsterblue));
				}
			}
		}
		
	}
	
	private void ifShootTouchBombe() {
		for(Shoot shoot : shoots) {
			for(Bombe bombe : bombes) {
				if(shoot.touch(bombe)) {
					root.getChildren().removeAll(shoot.corps, bombe.corps);
					shoot.setAlive(false);
					bombe.setAlive(false);
				}
			}
		}
		
	}
	
	private void ifBombeTouchPlayer() {
		for(Bombe b:bombes){
			if(b.touch(player)){
				root.getChildren().remove(b.corps);
				b.setAlive(false);
//				try {
//					TimeUnit.SECONDS.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				pBar.setProgress(pBar.getProgress() - 0.1);
			}
		}
	}
	
	private void ifPlayerTouchHealth() {
		for(Health h:healths){
			if(h.touch(player)){
				Settings.healthSound();
				root.getChildren().remove(h.corps);
				h.setAlive(false);
				if(pBar.getProgress() < 1) {
				pBar.setProgress(pBar.getProgress() + 0.1);
				}
			}
		}
		healths.removeIf(GraphicObject::isDead);
	}
	
	private void setpBar() {
		if(pBar.getProgress() < 0.1) {
			Settings.boomSound();
			PlayGame.clipStartGame.stop();
			PlayGame.clipStartGame.close();
			PlayGame.clipGameOver = Settings.gameOverSound();
			pBar.setProgress(0);
			root.getChildren().removeAll(player.corps, arme.corps, sortie.corps);
			exp.corps.setTranslateX(player.corps.getTranslateX());
			exp.corps.setTranslateY(player.corps.getTranslateY());
			root.getChildren().add(exp.corps);
			animation.stop();
			gameOverBtn.setStyle("   -fx-background-color: \r\n" + 
					"        #000000,\r\n" + 
					"        linear-gradient(#7ebcea, #2f4b8f),\r\n" + 
					"        linear-gradient(#426ab7, #263e75),\r\n" + 
					"        linear-gradient(#395cab, #223768);\r\n" + 
					"    -fx-background-insets: 0,1,2,3;\r\n" + 
					"    -fx-background-radius: 3,2,2,2;\r\n" + 
					"    -fx-padding: 12 30 12 30;\r\n" + 
					"    -fx-text-fill: white;\r\n" + 
					"    -fx-font-size: 12px;");
			gameOverBtn.setPrefWidth(200);
			root.getChildren().add(gameOverBtn);
			gameOverBtn.setTranslateX(400);
			gameOverBtn.setTranslateY(300);
			scoreLabel.setStyle(" -fx-font: 35px \"Serif\";\r\n" + 
				"    -fx-padding: 5;\r\n" + 
				"    -fx-text-fill:white;\r\n" + 
				"    -fx-font-weight:bold;");
			root.getChildren().add(scoreLabel);
			scoreLabel.setText("Votre score est : " + (nbreMonster + nbreMonsterblue));
			scoreLabel.setTranslateX(350);
			scoreLabel.setTranslateY(200);
			//cursor	
			Image image = new Image("mouse-cursor-png.png");
			scene.setCursor(new ImageCursor(image));
			setEvents();
		}
		else if(pBar.getProgress() < 0.4) {
			pBar.setStyle("-fx-accent: red");
		}
		else if(pBar.getProgress() < 0.6) {
			pBar.setStyle("-fx-accent: orange");
		}
		else if(pBar.getProgress() < 0.8) {
			pBar.setStyle("-fx-accent: yellow");
		}
		else{
			pBar.setStyle("-fx-accent: green");
		}
	}
	
	private void setEvents() {
		gameOverBtn.setOnAction((event) -> {
			PlayGame.back();
			PlayGame.clipGameOver.stop();
			PlayGame.clipGameOver.close();
			Settings.startSound();
		});
	}
	
	private void removeThingsOrScene() {
		for(int i = 0; i < shoots.size(); i++) {
			Shoot s = shoots.get(i);
			if(s.corps.getTranslateX() < 0 || s.corps.getTranslateX() > widthWindow || s.corps.getTranslateY() < 0 || s.corps.getTranslateY() > heightWindow) {
				shoots.remove(s);
				root.getChildren().remove(s.corps);
			}
		}
		
		for(int i = 0; i<bombes.size(); i++) {
			Bombe b = bombes.get(i);
			if(b.corps.getTranslateX() < 0 || b.corps.getTranslateX() > widthWindow || b.corps.getTranslateY() < 0 || b.corps.getTranslateY() > heightWindow) {
				bombes.remove(b);
				root.getChildren().remove(b.corps);
			}
		}
		
		for(int i = 0; i<rockets.size(); i++) {
			Rocket r = rockets.get(i);
			if(r.corps.getTranslateX() < 0 || r.corps.getTranslateX() > widthWindow || r.corps.getTranslateY() < 0 || r.corps.getTranslateY() > heightWindow) {
				rockets.remove(r);
				root.getChildren().remove(r.corps);
			}
		}
	}
	
	private void setRockets() {
		if(Math.random()<0.0055) {
				Rocket rocket = new Rocket(zone1);
				root.getChildren().add(rocket.corps);
				rockets.add(rocket);
				rocket.corps.setTranslateX(rocket.corps.getTranslateX()+Math.random()*0.9);
				if(Math.random()<0.1) {
					rocket.corps.setTranslateY(rocket.corps.getTranslateY()-Math.random()*7);
				}
			}
	}
	
	private void ifBombesTouchRockets() {
		for(Bombe bombe : bombes) {
			for(Rocket rocket : rockets) {
				if(bombe.touch(rocket)) {
					root.getChildren().removeAll(bombe.corps, rocket.corps);
					rocket.setAlive(false);
					bombe.setAlive(false);
				}
			}
		}
		
	}
	
	private void moveRockets() {
		for(int i = 0; i<rockets.size(); i++){
			Rocket r = rockets.get(i);
			r.corps.setTranslateX(r.corps.getTranslateX()+Math.random()*0.9+Math.random());
			if(r.corps.getTranslateY()>350) {
				r.corps.setTranslateY(r.corps.getTranslateY()-0.19);
			}else {
				r.corps.setTranslateY(r.corps.getTranslateY()+0.19);
			}
			// remove Rocket if X > Window width
			if(r.corps.getTranslateX()>widthWindow-30) {
				root.getChildren().remove(r.corps);
				r.setAlive(false);
			}
		}
	}
	
	private void displayBigMonster() {
		if(nbreMonster > 10) {
			if(Math.random()<0.005) {
				Monster m = new BigMonster(zone22);
				root.getChildren().add(m.corps);
				monsters.add(m);
				}
		}
		else if(nbreMonster > 20) {
				if(Math.random()<0.7) {
					Monster m = new BigMonster(zone2);
					root.getChildren().add(m.corps);
					monsters.add(m);
					}
			
		}
	}
	
	private void displayFlame() {
		for(Monster monster:monsters){
			if(monster.color == "blue") {
				if(Math.random() < 0.01) {
					Bombe b = new Flame(monster, player);
					root.getChildren().add(b.corps);
					bombes.add(b);
				}
			}
		}
	}
	
//	@Override
//	public void start(Stage window) throws Exception {
//		
//		Program prog = new Program(window);
//		
//		PlayGame.appRoot.show();
//		
//	}
//	
//	public static void main(String[] args) {
//		Application.launch(args);
//	}

}
