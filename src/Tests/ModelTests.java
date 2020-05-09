package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import Model.GardenPref;
import Model.Model;
import Model.Plant;
import Model.Season;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author IanMcCabe
 *
 */
public class ModelTests {



	@Test
	public void copyTest() {
		fail("method not made");
	}

	@Test
	public void undoTest() {
		fail("method not made");
	}
	@Test
	public void redoTest() {
		fail("method not made");
	}
	@Test 
	public void calcXTest() {
		
	}
	@Test
	public void calcYTest() {
		
	}
	@Test
	public void createSuggestionsTest() {
		Model test = new Model();
		ArrayList<Plant> startUp = new ArrayList<Plant>();
		test.createSuggestions(true);
		startUp.addAll(test.getSuggestedPlants());
		int index = 0;
		for(Plant p: test.getSuggestedPlants()) {
			assertEquals(startUp.get(index),p);
			index++;
			System.out.println("JUNK");
		}
		
	}
	@Test
	public void generateRelevantPlantsTest() {
		
		
		GardenPref gp = new GardenPref();
		
		
		gp.setUserBloom("Winter");
		HashSet<String> colors = new HashSet<String>();
		colors.add("Blue");
		colors.add("Pink");
		gp.setUserColor(colors);
		gp.setUserLight("Any");
		gp.setUserSoil("Wet");
		
	}
	@Test
	public void userCheckTest() {
		Model test = new Model();
		Season[] seasons = {Season.WINTER,Season.FALL};
		String pass = "Winter";
		String fail = "Summer";
		String any = "Any";
		String nu = null;
		assertEquals(true, test.userCheck(seasons, pass));
		assertEquals(true, test.userCheck(seasons, any));
		assertEquals(true, test.userCheck(seasons, nu));
		assertEquals(false, test.userCheck(seasons, fail));
	}
	@Test
	public void getUserPicksTest() {
		Model test  = new Model();
		int rows = 3;
		int cols = 7;
		int pick = (int)Math.random()*(rows*cols);
		Plant plant = test.getSuggestedPlants().get(pick);
		int index = 0;
		GridPane grid = new GridPane();
		String style = "-fx-background-color: BLACK;";
		for(int i = 0;i<rows;i++) {
			for(int j= 0;j<cols;j++) {
				if(pick == index) {
					ImageView iv = new ImageView();
					iv.setStyle(style);
					grid.getChildren().add(new ImageView());
				}
				else
					grid.getChildren().add(new ImageView());
			}
		}
		assertEquals(plant, test.getSuggestedPlants().get(0));
	}

}
