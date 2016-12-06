package corndog;

	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;

public class Main extends Application {

	public static boolean gameState = false;
	public static long gameBeginTime;
	public static AudioClip bite = new AudioClip(Main.class.getResource("/resources/bite.wav").toExternalForm());
	public static AudioClip buzz = new AudioClip(Main.class.getResource("/resources/sound7.wav").toExternalForm());
	public static AudioClip error = new AudioClip(Main.class.getResource("/resources/error.wav").toExternalForm());
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/corndog/Layout.fxml"));
			root.getStylesheets().add(Main.class.getResource("/corndog/application.css").toExternalForm());
			primaryStage.setScene(new Scene(root));
			primaryStage.centerOnScreen();
			primaryStage.setTitle("Corndog Crunch - The Game: Javafx Edition");
			primaryStage.show();
			
			
			Parent scoreParent = FXMLLoader.load(getClass().getResource("/ScoreboardLayout.fxml"));
			Stage scoreStage = new Stage();
			scoreStage.setScene(new Scene(scoreParent));
			scoreStage.centerOnScreen();
			scoreStage.hide();
			System.out.println("Init completed successfully.");
			
			new AnimationTimer() {
				@Override
				public void handle(long arg0) {
					if(gameState) {		
						long gameCurrentTime = System.currentTimeMillis();
						long timeRemaining = (8000-(gameCurrentTime-gameBeginTime));
						Events.timeDisplay.setText(String.valueOf(timeRemaining));
						if(timeRemaining<=0) {
							gameState = false;
							buzz.play();
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {}
				}
			}.start();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		launch(args);
	}
	
	public static boolean getGameState() {
		return gameState;
	}
	
	public static void setGameState(boolean state) {
		gameState = state;
		if(state) { //game start code
			gameBeginTime = System.currentTimeMillis();
		}
		else { //game end code
			gameBeginTime = 0;
		}
	}
}
