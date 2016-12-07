package corndog;

import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;

public class Main extends Application implements Initializable {

	public boolean gameState = false;
	public long gameBeginTime;
	public AudioClip bite = new AudioClip(Main.class.getResource("/resources/bite.wav").toExternalForm());
	public AudioClip buzz = new AudioClip(Main.class.getResource("/resources/sound7.wav").toExternalForm());
	public AudioClip error = new AudioClip(Main.class.getResource("/resources/error.wav").toExternalForm());
	private int currentCreatureIndex = -1;
	private Random rand = new Random();
	private int score = 0;

	@FXML public Label timeDisplay;
	@FXML private Button startGame;
	@FXML private Button creature0, creature1, creature2, creature3, creature4, creature5, creature6, creature7, creature8;
	private Button[] creatures = new Button[9]; /* = {creature0, creature1, creature2, creature3, creature4, creature5, creature6, creature7, creature8}*/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		creatures[0] = creature0;
		creatures[1] = creature1;
		creatures[2] = creature2;
		creatures[3] = creature3;
		creatures[4] = creature4;
		creatures[5] = creature5;
		creatures[6] = creature6;
		creatures[7] = creature7;
		creatures[8] = creature8;
		
		System.out.println("The starting active creature is " + currentCreatureIndex);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/corndog/Layout.fxml"));
			root.getStylesheets().add(Main.class.getResource("/corndog/application.css").toExternalForm());
			Scene primaryScene = new Scene(root);
			primaryStage.setScene(primaryScene);
			primaryStage.centerOnScreen();
			primaryStage.setTitle("Corndog Crunch - The Game: Javafx Edition");
			primaryStage.show();
			
			
			Parent scoreParent = FXMLLoader.load(getClass().getResource("/ScoreboardLayout.fxml"));
			Stage scoreStage = new Stage();
			scoreStage.setScene(new Scene(scoreParent));
			scoreStage.centerOnScreen();
			scoreStage.hide();
			System.out.println("Init completed successfully.");
		
			Timeline gameLoop = new Timeline();
			gameLoop.setCycleCount(Timeline.INDEFINITE);
			final long timeStart = System.currentTimeMillis();
			
			new AnimationTimer() {

				@Override
				public void handle(long now) {
					if(gameState) {		
						long gameCurrentTime = System.currentTimeMillis();
						long timeRemaining = (8000-(gameCurrentTime-timeStart));
						System.out.print(timeRemaining);
						timeDisplay.setText(String.valueOf(timeRemaining));
						if(timeRemaining<=0) {
							//setGameState(false);
							buzz.play();
						}
					}
				}
			}.start();	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		launch(args);
	}
	
	public void creature(ActionEvent e){ //THIS EXECUTES WHEN A CREATURE IS FIRED
		if(gameState) {
			if(getCreatureIndex(e)==currentCreatureIndex) {
				score++;
				bite.play();
				setImage(currentCreatureIndex, "/resources/default.png");
				generateNewCreature();
				setImage(currentCreatureIndex, "/resources/c" + rand.nextInt(9) + ".png");
			}
			else {
				System.out.println("You got it wrong. The active creature is " + currentCreatureIndex);
			}
		}
		else {
			System.out.println("Don't push my buttons!");
		}
		
	}
	
	public void startGame() {
		if(!gameState) {
			setGameState(true);	
		}
	}
	
	public void setGameState(boolean state) {
		gameState = state;
		if(state) { //game start code
			gameBeginTime = System.currentTimeMillis();
			currentCreatureIndex = rand.nextInt(9);
			System.out.println("Started with active creature " + currentCreatureIndex + " at " + gameBeginTime + ".");
			setImage(getCreature(currentCreatureIndex), "/resources/c" + rand.nextInt(9) + ".png");	
			timeDisplay.setText("input needed");
		}
		else { //game end code
			gameBeginTime = 0;
		}
	}
	
	public void generateNewCreature() {
		currentCreatureIndex = rand.nextInt(9);
		System.out.println("You got it right. The new active creature is " + currentCreatureIndex + ", and your score is now " + score);
	}
	
	public void setImage(Button creature, String path) {
		creature.setStyle("-fx-background-image: url(" + path + ")");
	}
	
	public void setImage(int creatureIndex, String path) {
		setImage(getCreature(creatureIndex), path);
	}
	
	public Button getCreature(ActionEvent e) {
		return creatures[getCreatureIndex(e)];
	}
	
	public Button getCreature(int index) {
		return creatures[index];
	}
	
	public int getCreatureIndex(ActionEvent e) {
		return getCreatureIndex((Button) e.getSource());
	//	return Integer.valueOf((e.getSource().toString().charAt(18))-48); //as fun as this way is, it's kind of sketchy
	}
	
	public int getCreatureIndex(Button creature) {
		return Arrays.asList(creatures).indexOf(creature);
	}
}
