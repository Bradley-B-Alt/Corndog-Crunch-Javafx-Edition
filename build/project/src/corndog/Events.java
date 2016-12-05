package corndog;

import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Events implements Initializable {

	private int currentCreatureIndex;
	private Random rand = new Random();
	private int score = 0;
	
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
		
		currentCreatureIndex = rand.nextInt(9);
		
		System.out.println("The starting active creature is " + currentCreatureIndex);
	}

	public void creature(ActionEvent e){ //THIS EXECUTES WHEN A CREATURE IS FIRED
		if(getCreatureIndex(e)==currentCreatureIndex) {
			score++;
			setImage(currentCreatureIndex, "/resources/default.png");
			generateNewCreature();
			setImage(currentCreatureIndex, "/resources/c" + rand.nextInt(9) + ".png");
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
	//	return Integer.valueOf((e.getSource().toString().charAt(18))-48); //as fun as this way is, it's kind of sketchy :)
	}
	
	public int getCreatureIndex(Button creature) {
		return Arrays.asList(creatures).indexOf(creature);
	}
	
}
