package corndog;

	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/corndog/Layout.fxml"));
			root.getStylesheets().add(Main.class.getResource("/corndog/application.css").toExternalForm());
			primaryStage.setScene(new Scene(root));
			primaryStage.centerOnScreen();
			primaryStage.setTitle("Corndog Crunch - The Game: Javafx Edition");
			primaryStage.show();
			
			/*
			Parent scoreParent = FXMLLoader.load(getClass().getResource("/ScoreboardLayout.fxml"));
			Stage scoreStage = new Stage();
			scoreStage.setScene(new Scene(scoreParent));
			scoreStage.centerOnScreen();
			scoreStage.hide();
			System.out.println("Init completed successfully.");
			*/
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
