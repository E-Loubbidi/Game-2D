package gameV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayGame extends  Application{

	public static Stage appRoot = null;
	public static List<Pane> recentHistoryPanes = null;
	
	private BorderPane root = new BorderPane();
	
	private Image img;
	private ImageView imgViewBg;

	private Image imgTitle;
	private ImageView imgViewTitle;
	
	private Image imgPlayer;
	private ImageView imgViewPlayer;
	
	private Image imgMonster;
	private ImageView imgViewMonster;
	
	private Image imgBombe;
	private ImageView imgViewBombe;
	
	private HBox hboxTitle = new HBox();
	private HBox hboxCenter = new HBox();
	private HBox hboxBtn = new HBox();

	
	private Button startBtn = new Button("Start");
	
	public static Clip clipStartGame = null;
	public static Clip clipStart = null;
	public static Clip clipGameOver = null;
	
	public PlayGame() {
		
		img = new Image("space3.jpg", 1000, 700, false, false, false);
		imgViewBg = new ImageView(img);
		//root.setBackground(bgImg);
		root.getChildren().add(imgViewBg);
		root.setPrefWidth(1000);
		root.setPrefHeight(700);
		//root.setStyle("-fx-background-color: rgba(5, 102, 141, 1);");
		
		try {
			imgTitle = new Image(new FileInputStream("images/spaceWar1.png"));
			imgViewTitle = new ImageView(imgTitle);
			imgViewTitle.setFitWidth(700);
			imgViewTitle.setFitHeight(600);
			hboxTitle.getChildren().add(imgViewTitle);
			hboxTitle.setMargin(imgViewTitle, new Insets(100, 0, 0, 300));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		root.getChildren().add(hboxTitle);
		startBtn.setStyle("   -fx-background-color: \r\n" + 
				"        #000000,\r\n" + 
				"        linear-gradient(#7ebcea, #2f4b8f),\r\n" + 
				"        linear-gradient(#426ab7, #263e75),\r\n" + 
				"        linear-gradient(#395cab, #223768);\r\n" + 
				"    -fx-background-insets: 0,1,2,3;\r\n" + 
				"    -fx-background-radius: 3,2,2,2;\r\n" + 
				"    -fx-padding: 12 30 12 30;\r\n" + 
				"    -fx-text-fill: white;\r\n" + 
				"    -fx-font-size: 12px;");
		startBtn.setPrefWidth(200);
		hboxBtn.getChildren().add(startBtn);
		hboxBtn.setMargin(startBtn, new Insets(-200, 0, 0, 400));
		try {
			imgPlayer = new Image(new FileInputStream("images/giphy.gif"));
			imgViewPlayer = new ImageView(imgPlayer);
			imgViewPlayer.setFitWidth(270);
			imgViewPlayer.setFitHeight(130);
			hboxCenter.getChildren().add(imgViewPlayer);
			hboxCenter.setMargin(imgViewPlayer, new Insets(-400, 0, 0, 200));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			imgBombe = new Image(new FileInputStream("images/bombe.png"));
			imgViewBombe = new ImageView(imgBombe);
			imgViewBombe.setFitWidth(70);
			imgViewBombe.setFitHeight(70);
			hboxCenter.getChildren().add(imgViewBombe);
			hboxCenter.setMargin(imgViewBombe, new Insets(-420, 0, 0, 0));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			imgMonster = new Image(new FileInputStream("images/dragon.gif"));
			imgViewMonster = new ImageView(imgMonster);
			imgViewMonster.setFitWidth(200);
			imgViewMonster.setFitHeight(200);
			hboxCenter.getChildren().add(imgViewMonster);
			hboxCenter.setMargin(imgViewMonster, new Insets(-480, 0, 0, 0));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		root.setTop(hboxTitle);
		root.setCenter(hboxCenter);
		root.setBottom(hboxBtn);
		setEvents();
	}
	
	private void setEvents() {
		startBtn.setOnAction((event) -> {
			Program prog = new Program();
			clipStart.stop();
			clipStart.close();
			clipStartGame = Settings.gameSound();
			showViews(prog.getPane());
		});
	}
	
	public static void showViews(Pane view) {
		AnchorPane root = new AnchorPane();
		root.getChildren().add(view);
		Scene scene = appRoot.getScene();
		scene.setRoot(root);
		appRoot.setScene(scene);
		recentHistoryPanes.add(view);
		appRoot.show();
	}
	
	public static void back() {
		if(recentHistoryPanes.size() >= 1) {
			recentHistoryPanes.remove(recentHistoryPanes.size() - 1);
			AnchorPane root = new AnchorPane();
			root.getChildren().add(recentHistoryPanes.get(recentHistoryPanes.size() - 1));
			appRoot.hide();
			Scene scene = appRoot.getScene();
			scene.setRoot(root);
			appRoot.setScene(scene);
			appRoot.show();
		}
	}

	@Override
	public void start(Stage window) throws Exception {
		appRoot = window;
		
		AnchorPane root = new AnchorPane();
		Scene defaultScene = new Scene(root);
		appRoot.setScene(defaultScene);
		appRoot.getIcons().add(new Image("space3.jpg"));
		recentHistoryPanes = new ArrayList<Pane>();
		
//		Program prog = new Program(window);
//		showViews(prog.getPane());
		clipStart = Settings.startSound();
		showViews(this.root);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
