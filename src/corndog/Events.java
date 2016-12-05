package corndog;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Events implements Initializable {

	private int currentCreatureIndex;
	private Random rand = new Random();
	private int score = 0;
	
	@FXML private Button creature0, creature1, creature2, creature3, creature4, creature5, creature6, creature7, creature8;
	private Button[] creatures = {creature0, creature1, creature2, creature3, creature4, creature5, creature6, creature7, creature8};
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentCreatureIndex = rand.nextInt(9);
		System.out.println("The starting active creature is " + currentCreatureIndex);
	}

	public void creature(ActionEvent e){ //THIS EXECUTES WHEN A CREATURE IS FIRED
		if(getCreatureIndex(e)==currentCreatureIndex) {
			score++;
			setImage(currentCreatureIndex, "/resources/default.png");
			generateNewCreature();
			setImage(currentCreatureIndex, "/resources/c" + currentCreatureIndex + ".png");
		}
		else {
			System.out.println("You got it wrong. The active creature is " + currentCreatureIndex);
		}
	}
	
	public void generateNewCreature() {
		currentCreatureIndex = rand.nextInt(9);
		System.out.println("You got it right. The new active creature is " + currentCreatureIndex + ", and your score is now " + score);
	}
	
	public void setImage(Button creature, String path) {
		// new Image(url)
		Image image = new Image(Events.class.getResource(path).toExternalForm());
		// new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		// new BackgroundImage(image, repeatX, repeatY, position, size)
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		// new Background(images...)
		Background background = new Background(backgroundImage);
		//System.out.println(creature.backgroundProperty());
		creature.setBackground(background);
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
		return Integer.valueOf((e.getSource().toString().charAt(18))-48);
	}
	
	public int getCreatureIndex(Button creature) {
		for(int i = 0;i<creatures.length;i++) {
			if(creature == creatures[i])
				return i;
		}
		return 0; //this should never run
	}
	
}
