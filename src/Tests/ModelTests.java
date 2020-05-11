package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import Model.GardenPref;
import Model.Model;
import Model.Plant;
import Model.PlantType;
import Model.Season;
import Model.Sun;
import Model.Water;
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
		Model test = new Model();
		
		double center = test.calcX(250, 100, 100);
		assertEquals(250,center,0.01);
		
		double left = test.calcX(30, 50, 100);
		assertEquals(50,left,0.01);
		
		double right = test.calcX(test.getCanvasWidth(), 50, 100);
		assertEquals(test.getCanvasWidth()-50-100,right,0.01);
		
	}
	@Test
	public void calcYTest() {
		Model test = new Model();
		
		double center = test.calcY(250, 40, 100);
		assertEquals(250,center,0.01);
		
		double top = test.calcY(0, 50, 100);
		assertEquals(50,top,0.01);
		
		double bottom = test.calcY(100000, 100, 100);
		assertEquals(test.getCanvasHeight()-100-200,bottom,0.01);
	}
	
	@Test
	public void importPlantsFromCSVTest() {
		Model test = new Model();
		
		test.importPlantsFromCSV("test.csv");
		
		Plant plant0 = test.getPlants().get("ScienceName0");
		Plant plant1 = test.getPlants().get("ScienceName1");
		Plant plant2 = test.getPlants().get("ScienceName2");
		Plant plant3 = test.getPlants().get("ScienceName3");
		Plant plant4 = test.getPlants().get("ScienceName4");
		
		assertEquals("CommonName0", plant0.getCommonNames()[0]);
		assertEquals("CommonName0", plant1.getCommonNames()[0]);
		assertEquals("CommonName1", plant1.getCommonNames()[1]);
		assertEquals("CommonName2", plant1.getCommonNames()[2]);
		assertEquals("CommonName0", plant2.getCommonNames()[0]);
		assertEquals("CommonName1", plant2.getCommonNames()[1]);
		assertEquals("CommonName0", plant3.getCommonNames()[0]);
		assertEquals("CommonName1", plant3.getCommonNames()[1]);
		assertEquals("CommonName2", plant3.getCommonNames()[2]);
		assertEquals("CommonName3", plant3.getCommonNames()[3]);
		assertEquals("CommonName0", plant4.getCommonNames()[0]);
		
		assertEquals("Perennial", plant0.getDuration());
		assertEquals("Annual", plant1.getDuration());
		assertEquals("Perennial", plant2.getDuration());
		assertEquals("Perennial", plant3.getDuration());
		assertEquals("Perennial", plant4.getDuration());
		
		assertEquals(PlantType.HERB, plant0.getType());
		assertEquals(PlantType.VINE, plant1.getType());
		assertEquals(PlantType.TREE, plant2.getType());
		assertEquals(PlantType.SHRUB, plant3.getType());
		assertEquals(PlantType.HERB, plant4.getType());
		
		assertEquals(12, plant0.getHeight()[0]);
		assertEquals(36, plant0.getHeight()[1]);
		assertEquals(144, plant1.getHeight()[0]);
		assertEquals(864, plant1.getHeight()[1]);
		assertEquals(864, plant2.getHeight()[0]);
		assertEquals(1200, plant2.getHeight()[1]);
		assertEquals(72, plant3.getHeight()[0]);
		assertEquals(144, plant3.getHeight()[1]);
		assertEquals(12, plant4.getHeight()[0]);
		assertEquals(36, plant4.getHeight()[1]);
		
		assertEquals(true, plant0.getColor().contains("White"));
		assertEquals(true, plant0.getColor().contains("Pink"));
		assertEquals(true, plant1.getColor().contains("Red"));
		assertEquals(true, plant1.getColor().contains("Orange"));
		assertEquals(true, plant1.getColor().contains("Yellow"));
		assertEquals(true, plant2.getColor().contains("Yellow"));
		assertEquals(true, plant3.getColor().contains("White"));
		assertEquals(true, plant4.getColor().contains("Pink"));
		assertEquals(true, plant4.getColor().contains("Purple"));
		
		System.out.println(plant0.getBloomtime()[0]);
		System.out.println(plant0.getBloomtime()[1]);

		assertEquals(Season.SPRING, plant0.getBloomtime()[0]);
		assertEquals(Season.SUMMER, plant0.getBloomtime()[1]);
		assertEquals(Season.SUMMER, plant1.getBloomtime()[0]);		
		assertEquals(Season.SPRING, plant2.getBloomtime()[0]);
		assertEquals(Season.SPRING, plant3.getBloomtime()[0]);
		assertEquals(Season.SUMMER, plant3.getBloomtime()[1]);	
		assertEquals(Season.WINTER, plant4.getBloomtime()[0]);		
		assertEquals(Season.SPRING, plant4.getBloomtime()[1]);
		assertEquals(Season.SUMMER, plant4.getBloomtime()[2]);	
		assertEquals(Season.FALL, plant4.getBloomtime()[3]);

		assertEquals(Water.MESIC, plant0.getWaterLevel()[0]);
		assertEquals(Water.DRYMES, plant0.getWaterLevel()[1]);
		assertEquals(Water.DRY, plant0.getWaterLevel()[2]);
		assertEquals(Water.MESIC, plant1.getWaterLevel()[0]);
		assertEquals(Water.DRYMES, plant1.getWaterLevel()[1]);
		assertEquals(Water.DRY, plant1.getWaterLevel()[2]);
		assertEquals(Water.WET, plant2.getWaterLevel()[0]);
		assertEquals(Water.WETMES, plant2.getWaterLevel()[1]);
		assertEquals(Water.MESIC, plant2.getWaterLevel()[2]);
		assertEquals(Water.MESIC, plant3.getWaterLevel()[0]);
		assertEquals(Water.DRYMES, plant3.getWaterLevel()[1]);
		assertEquals(Water.DRY, plant3.getWaterLevel()[2]);
		assertEquals(Water.MESIC, plant4.getWaterLevel()[0]);
		assertEquals(Water.DRYMES, plant4.getWaterLevel()[1]);
		assertEquals(Water.DRY, plant4.getWaterLevel()[2]);

		assertEquals(Sun.FULL, plant0.getLight()[0]);
		assertEquals(Sun.FULL, plant1.getLight()[0]);
		assertEquals(Sun.FULL_PARTIAL, plant1.getLight()[1]);
		assertEquals(Sun.FULL, plant2.getLight()[0]);
		assertEquals(Sun.FULL_PARTIAL, plant2.getLight()[1]);
		assertEquals(Sun.PARTIAL, plant2.getLight()[2]);
		assertEquals(Sun.PARTIAL_NONE, plant2.getLight()[3]);
		assertEquals(Sun.NONE, plant2.getLight()[4]);
		assertEquals(Sun.PARTIAL, plant3.getLight()[0]);
		assertEquals(Sun.FULL, plant4.getLight()[0]);
		assertEquals(Sun.FULL_PARTIAL, plant4.getLight()[1]);
		
		assertEquals(24, plant0.getSpread()[0]);
		assertEquals(36, plant0.getSpread()[1]);
		assertEquals(360, plant1.getSpread()[0]);
		assertEquals(360, plant1.getSpread()[1]);
		assertEquals(300, plant2.getSpread()[0]);
		assertEquals(420, plant2.getSpread()[1]);
		assertEquals(0, plant3.getSpread()[0]);
		assertEquals(0, plant3.getSpread()[1]);
		assertEquals(12, plant4.getSpread()[0]);
		assertEquals(36, plant4.getSpread()[1]);
	}
	
	@Test
	public void parseLineTest0() {
		List<String> results = Model.parseLine("result0,result1,result2,result3");
		assertEquals("result0", results.get(0));
		assertEquals("result1", results.get(1));
		assertEquals("result2", results.get(2));
		assertEquals("result3", results.get(3));
	}
	
	@Test
	public void parseLineTest1() {
		List<String> results = Model.parseLine("result0,\"result1-0, result1-1\",result2,result3");
		assertEquals("result0", results.get(0));
		assertEquals("result1-0, result1-1", results.get(1));
		assertEquals("result2", results.get(2));
		assertEquals("result3", results.get(3));
	}
	
	@Test
	public void parseLineTest2() {
		List<String> results = Model.parseLine("\"result0-0, result0-1\",result1,result2,\"result3-0, result3-1, result3-2, result3-3\"");
		assertEquals("result0-0, result0-1", results.get(0));
		assertEquals("result1", results.get(1));
		assertEquals("result2", results.get(2));
		assertEquals("result3-0, result3-1, result3-2, result3-3", results.get(3));
	}
	
	@Test
	public void createSuggestionsTest() {
		Model test = new Model();
		ArrayList<Plant> startUp = new ArrayList<Plant>();
		//StartUp
		test.createSuggestions(true);
		startUp.addAll(test.getSuggestedPlants());
		int index = 0;
		for(Plant p: test.getSuggestedPlants()) {
			assertEquals(startUp.get(index),p);
			index++;
			
		}
		//No GardenPrefs
		test.createSuggestions(false);
		index = 0;
		for(Plant p: test.getSuggestedPlants()) {
			assertEquals(startUp.get(index),p);
			index++;
		}
		//One gardenPref
		GardenPref gp = new GardenPref();
		gp.setUserBloom("Winter");
		HashSet<String> colors = new HashSet<String>();
		colors.add("Blue");
		colors.add("Pink");
		gp.setUserColor(colors);
		gp.setUserLight("Any");
		gp.setUserSoil("Wet");
		ArrayList<GardenPref> testPref = new ArrayList<GardenPref>();
		testPref.add(gp);
		test.setGardenPreferences(testPref);
		test.setCurrPref(gp);
		test.createSuggestions(false);
		assertNotEquals(startUp,test.getSuggestedPlants());
		
		
	}
	@Test
	public void generateRelevantPlantsTest() {
		
		Model test = new Model();
		ArrayList<Plant> startUp = new ArrayList<Plant>();
		GardenPref gp = new GardenPref();
		gp.setUserBloom("Winter");
		HashSet<String> colors = new HashSet<String>();
		colors.add("Blue");
		colors.add("Pink");
		gp.setUserColor(colors);
		gp.setUserLight("Any");
		gp.setUserSoil("Wet");
		ArrayList<GardenPref> testPref = new ArrayList<GardenPref>();
		testPref.add(gp);
		test.setGardenPreferences(testPref);
		test.setCurrPref(gp);
		test.createSuggestions(false);
		assertNotEquals(startUp,test.getSuggestedPlants());
		
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
	
	@Test
	public void updateRectCoordinatesTest() {
		Model test = new Model();
		
		double[] coords = test.updateRectCoordinates(10, 20, 110, 220, 1000, 1000);
		assertEquals(10, coords[0], 0.01);
		assertEquals(20, coords[1], 0.01);
		assertEquals(100, coords[2], 0.01);
		assertEquals(200, coords[3], 0.01);

		coords = test.updateRectCoordinates(110, 220, 10, 20, 1000, 1000);
		assertEquals(10, coords[0], 0.01);
		assertEquals(20, coords[1], 0.01);
		assertEquals(100, coords[2], 0.01);
		assertEquals(200, coords[3], 0.01);
		
		coords = test.updateRectCoordinates(-1, -1, 100, 200, 1000, 1000);
		assertEquals(0, coords[0], 0.01);
		assertEquals(0, coords[1], 0.01);
		assertEquals(100, coords[2], 0.01);
		assertEquals(200, coords[3], 0.01);
		
		coords = test.updateRectCoordinates(10, 20, 1001, 1001, 1000, 1000);
		assertEquals(10, coords[0], 0.01);
		assertEquals(20, coords[1], 0.01);
		assertEquals(990, coords[2], 0.01);
		assertEquals(980, coords[3], 0.01);
	}

	@Test
	public void moveRectCoordinatesTest() {
		Model test = new Model();
		
		double[] coords = test.moveRectCoordinates(10, 20, 110, 220, 1000, 1000);
		assertEquals(10, coords[0], 0.01);
		assertEquals(20, coords[1], 0.01);
		
		coords = test.moveRectCoordinates(-1, -1, 100, 200, 1000, 1000);
		assertEquals(0, coords[0], 0.01);
		assertEquals(0, coords[1], 0.01);
		
		coords = test.moveRectCoordinates(1001, 1001, 100, 200, 1000, 1000);
		assertEquals(900, coords[0], 0.01);
		assertEquals(800, coords[1], 0.01);
	}
	
	@Test
	public void updateCircleCoordinatesTest() {
		Model test = new Model();
		
		double[] radii = test.updateCircleCoordinates(10, 20, 110, 220, 1000, 1000);
		assertEquals(100, radii[0], 0.01);
		assertEquals(200, radii[1], 0.01);

		radii = test.updateCircleCoordinates(110, 220, 10, 20, 1000, 1000);
		assertEquals(10, radii[0], 0.01);
		assertEquals(20, radii[1], 0.01);
		
		radii = test.updateCircleCoordinates(-1, -1, 100, 200, 1000, 1000);
		assertEquals(100, radii[0], 0.01);
		assertEquals(200, radii[1], 0.01);
		
		radii = test.updateCircleCoordinates(1001, 1001, 400, 550, 1000, 1000);
		assertEquals(400, radii[0], 0.01);
		assertEquals(450, radii[1], 0.01);
	}
	
	@Test
	public void moveCircleCoordinatesTest() {
		Model test = new Model();
		
		double[] coords = test.moveCircleCoordinates(10, 20, 110, 220, 1000, 1000);
		assertEquals(110, coords[0], 0.01);
		assertEquals(220, coords[1], 0.01);

		coords = test.moveCircleCoordinates(110, 220, 10, 20, 1000, 1000);
		assertEquals(110, coords[0], 0.01);
		assertEquals(220, coords[1], 0.01);
		
		coords = test.moveCircleCoordinates(-1, -1, 100, 200, 1000, 1000);
		assertEquals(100, coords[0], 0.01);
		assertEquals(200, coords[1], 0.01);
		
		coords = test.moveCircleCoordinates(1001, 1001, 400, 450, 1000, 1000);
		assertEquals(600, coords[0], 0.01);
		assertEquals(550, coords[1], 0.01);
	}
}
