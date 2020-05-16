package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import Model.GardenPref;
import Model.Model;
import Model.Plant;
import Model.PlantTypeEnum;
import Model.SeasonEnum;
import Model.SunEnum;
import Model.WaterEnum;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author IanMcCabe
 * @author Brandon Wu
 * @author Tommy White
 *
 */
public class ModelTests {

	@Test 
	public void calcXTest() {
		Model test = new Model();
		
		double center = test.calcX(250, 100, 100, 1130);
		assertEquals(250,center,0.01);
		
		double left = test.calcX(30, 50, 100, 1130);
		assertEquals(50,left,0.01);
		
		double right = test.calcX(1130, 50, 100, 1130);
		assertEquals(980,right,0.01);
		
	}
	@Test
	public void calcYTest() {
		Model test = new Model();
		
		double center = test.calcY(250, 40, 100, 570);
		assertEquals(250,center,0.01);
		
		double top = test.calcY(0, 50, 100, 570);
		assertEquals(50,top,0.01);
		
		double bottom = test.calcY(100000, 100, 100, 570);
		assertEquals(270,bottom,0.01);
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
		
		assertEquals(PlantTypeEnum.HERB, plant0.getType());
		assertEquals(PlantTypeEnum.VINE, plant1.getType());
		assertEquals(PlantTypeEnum.TREE, plant2.getType());
		assertEquals(PlantTypeEnum.SHRUB, plant3.getType());
		assertEquals(PlantTypeEnum.HERB, plant4.getType());
		
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

		assertEquals(SeasonEnum.SPRING, plant0.getBloomtime()[0]);
		assertEquals(SeasonEnum.SUMMER, plant0.getBloomtime()[1]);
		assertEquals(SeasonEnum.SUMMER, plant1.getBloomtime()[0]);		
		assertEquals(SeasonEnum.SPRING, plant2.getBloomtime()[0]);
		assertEquals(SeasonEnum.SPRING, plant3.getBloomtime()[0]);
		assertEquals(SeasonEnum.SUMMER, plant3.getBloomtime()[1]);	
		assertEquals(SeasonEnum.WINTER, plant4.getBloomtime()[0]);		
		assertEquals(SeasonEnum.SPRING, plant4.getBloomtime()[1]);
		assertEquals(SeasonEnum.SUMMER, plant4.getBloomtime()[2]);	
		assertEquals(SeasonEnum.FALL, plant4.getBloomtime()[3]);

		assertEquals(WaterEnum.MESIC, plant0.getWaterLevel()[0]);
		assertEquals(WaterEnum.DRYMES, plant0.getWaterLevel()[1]);
		assertEquals(WaterEnum.DRY, plant0.getWaterLevel()[2]);
		assertEquals(WaterEnum.MESIC, plant1.getWaterLevel()[0]);
		assertEquals(WaterEnum.DRYMES, plant1.getWaterLevel()[1]);
		assertEquals(WaterEnum.DRY, plant1.getWaterLevel()[2]);
		assertEquals(WaterEnum.WET, plant2.getWaterLevel()[0]);
		assertEquals(WaterEnum.WETMES, plant2.getWaterLevel()[1]);
		assertEquals(WaterEnum.MESIC, plant2.getWaterLevel()[2]);
		assertEquals(WaterEnum.MESIC, plant3.getWaterLevel()[0]);
		assertEquals(WaterEnum.DRYMES, plant3.getWaterLevel()[1]);
		assertEquals(WaterEnum.DRY, plant3.getWaterLevel()[2]);
		assertEquals(WaterEnum.MESIC, plant4.getWaterLevel()[0]);
		assertEquals(WaterEnum.DRYMES, plant4.getWaterLevel()[1]);
		assertEquals(WaterEnum.DRY, plant4.getWaterLevel()[2]);

		assertEquals(SunEnum.FULL, plant0.getLight()[0]);
		assertEquals(SunEnum.FULL, plant1.getLight()[0]);
		assertEquals(SunEnum.FULL_PARTIAL, plant1.getLight()[1]);
		assertEquals(SunEnum.FULL, plant2.getLight()[0]);
		assertEquals(SunEnum.FULL_PARTIAL, plant2.getLight()[1]);
		assertEquals(SunEnum.PARTIAL, plant2.getLight()[2]);
		assertEquals(SunEnum.PARTIAL_NONE, plant2.getLight()[3]);
		assertEquals(SunEnum.NONE, plant2.getLight()[4]);
		assertEquals(SunEnum.PARTIAL, plant3.getLight()[0]);
		assertEquals(SunEnum.FULL, plant4.getLight()[0]);
		assertEquals(SunEnum.FULL_PARTIAL, plant4.getLight()[1]);
		
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
		test.importPlantsFromCSV("plantInfo.csv");
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
		test.importPlantsFromCSV("plantInfo.csv");
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
		SeasonEnum[] seasons = {SeasonEnum.WINTER,SeasonEnum.FALL};
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
		test.importPlantsFromCSV("plantInfo.csv");
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
		
		double[] radii = test.updateEllipseCoordinates(10, 20, 110, 220, 1000, 1000);
		assertEquals(100, radii[0], 0.01);
		assertEquals(200, radii[1], 0.01);


		radii = test.updateEllipseCoordinates(110, 220, 10, 20, 1000, 1000);
		assertEquals(10, radii[0], 0.01);
		assertEquals(20, radii[1], 0.01);
		
		radii = test.updateEllipseCoordinates(-1, -1, 100, 200, 1000, 1000);
		assertEquals(100, radii[0], 0.01);
		assertEquals(200, radii[1], 0.01);
		
		radii = test.updateEllipseCoordinates(1001, 1001, 400, 550, 1000, 1000);
		assertEquals(400, radii[0], 0.01);
		assertEquals(450, radii[1], 0.01);
	}
	
	@Test
	public void moveCircleCoordinatesTest() {
		Model test = new Model();
		
		double[] coords = test.moveEllipseCoordinates(10, 20, 110, 220, 1000, 1000);
		assertEquals(110, coords[0], 0.01);
		assertEquals(220, coords[1], 0.01);

		coords = test.moveEllipseCoordinates(110, 220, 10, 20, 1000, 1000);
		assertEquals(110, coords[0], 0.01);
		assertEquals(220, coords[1], 0.01);
		
		coords = test.moveEllipseCoordinates(-1, -1, 100, 200, 1000, 1000);
		assertEquals(100, coords[0], 0.01);
		assertEquals(200, coords[1], 0.01);
		
		coords = test.moveEllipseCoordinates(1001, 1001, 400, 450, 1000, 1000);
		assertEquals(600, coords[0], 0.01);
		assertEquals(550, coords[1], 0.01);
	}
	
	@Test
	public void generateStatsTest() {
		Model test = new Model();
		test.importPlantsFromCSV("plantInfo.csv");
		Pane garden = new Pane();
		double defaultRadius =10;
		HashSet<String> expectedNames = new HashSet<String>();
		//Herb, Herb, Tree, Tree, Shrub, Vine, Shrub
		String[] plants = {"Achillea millefolium","Achillea millefolium","Acer negundo","Acer spicatum","Ceanothus americanus","Lonicera sempervirens","Staphylea trifolia"};
		
		for(String s : plants) {
			Circle p = new Circle();
			p.setRadius(defaultRadius);
			garden.getChildren().add(p);
			garden.getChildren().get(garden.getChildren().size()-1).setUserData(s);
			expectedNames.add(s);
		}
		HashSet<String> expectedColors = new HashSet<String>();
		expectedColors.add("White");expectedColors.add("Pink");
		expectedColors.add("Yellow");expectedColors.add("Green");expectedColors.add("Brown");
		expectedColors.add("Red");expectedColors.add("Brown");
		expectedColors.add("Brown");
		expectedColors.add("Red");expectedColors.add("Yellow");
		expectedColors.add("White");
		
		HashSet<SeasonEnum> expectedSeasons = new HashSet<SeasonEnum>();
		expectedSeasons.add(SeasonEnum.SUMMER);
		expectedSeasons.add(SeasonEnum.SPRING);
		
		HashSet<String> expectedPlantsRemoved = new HashSet<String>();
		expectedPlantsRemoved.addAll(test.getPlants().keySet());
		expectedPlantsRemoved.removeAll(expectedNames);
		
		
		
		double expectedCoverage = Math.pow(defaultRadius,2) * Math.PI * plants.length / test.getPropertyHeightInches()/test.getPropertyWidthInches()*100;
		
		test.generateStats(garden.getChildren());
		
		assertEquals(2,test.getNumHerbs());
		assertEquals(2,test.getNumTrees());
		assertEquals(2,test.getNumShrubs());
		assertEquals(1,test.getNumVines());
		
		assertEquals(1,test.getUniqueHerbs());
		assertEquals(2,test.getUniqueTrees());
		assertEquals(2,test.getUniqueShrubs());
		assertEquals(1,test.getUniqueVines());
		
		assertEquals(true,expectedColors.containsAll(test.getAllColors()));
		assertEquals(false,test.getAllColors().contains("Blue"));
		assertEquals(false,test.getAllColors().contains("Purple"));
		assertEquals(false,test.getAllColors().contains("Orange"));
		assertEquals(false,test.getAllColors().contains("Black"));
		
		assertEquals(true,expectedSeasons.containsAll(test.getAllSeasons()));
		assertEquals(false,test.getAllSeasons().contains(SeasonEnum.FALL));
		assertEquals(false,test.getAllSeasons().contains(SeasonEnum.WINTER));
		
		assertEquals(true,expectedNames.containsAll(test.getAllNames()));
		for(String p: expectedPlantsRemoved) {
			assertEquals(false,test.getAllNames().contains(p));
		}
		
		
		assertEquals(expectedCoverage,test.getGardenCoveredPercent(),0.01);
		
		
	}
	
	@Test
	public void isPlantMatchTest() {
		Model test = new Model();
		test.importPlantsFromCSV("plantInfo.csv");
		
		Rectangle area0 = new Rectangle();
		Rectangle area1 = new Rectangle();
		Rectangle area2 = new Rectangle();
		Rectangle area3 = new Rectangle();
		
		area0.setX(0);
		area0.setY(0);
		area1.setX(500);
		area1.setY(0);
		area2.setX(0);
		area2.setY(500);
		area3.setX(500);
		area3.setY(500);
		
		area0.setWidth(499);
		area1.setWidth(499);
		area2.setWidth(499);
		area3.setWidth(499);
		area0.setHeight(499);
		area1.setHeight(499);
		area2.setHeight(499);
		area3.setHeight(499);
		
		GardenPref pref0 = new GardenPref();
		GardenPref pref1 = new GardenPref();
		GardenPref pref2 = new GardenPref();
		GardenPref pref3 = new GardenPref();
		
		pref0.setArea(area0);
		pref1.setArea(area1);
		pref2.setArea(area2);
		pref3.setArea(area3);
		
		HashSet<String> colors0 = new HashSet<String>();
		HashSet<String> colors1 = new HashSet<String>();
		HashSet<String> colors2 = new HashSet<String>();
		HashSet<String> colors3 = new HashSet<String>();
		
		pref0.setUserLight("Full Sun");
		pref0.setUserWater("Wet Mesic");
		pref0.setUserBloom("Spring");
		colors0.add("Yellow");
		pref0.setUserColor(colors0);
		
		pref1.setUserLight("Full Shade");
		pref1.setUserWater("Dry");
		pref1.setUserBloom("Winter");
		colors1.add("Pink");
		pref1.setUserColor(colors1);
		
		pref2.setUserLight("Full Sun to Partial Shade");
		pref2.setUserWater("Dry");
		pref2.setUserBloom("Summer");
		colors2.add("White");
		pref2.setUserColor(colors2);
		
		pref3.setUserLight("Partial Shade");
		pref3.setUserWater("Mesic");
		pref3.setUserBloom("Fall");
		colors3.add("Orange");
		pref3.setUserColor(colors3);
		
		ArrayList<GardenPref> prefs = new ArrayList<GardenPref>();
		prefs.add(pref0);
		prefs.add(pref1);
		prefs.add(pref2);
		prefs.add(pref3);
		
		test.setGardenPreferences(prefs);
		
		String result = test.isPlantMatch("Acer negundo", 50, 50);
		assertEquals("Plant matches light requirement.\nPlant matches soil moisture.\nPlant blooms in desired season.\nPlant matches desired color.", result);
		
		result = test.isPlantMatch("Acer negundo", 550, 50);
		assertEquals("Plant does not match light requirement.\nPlant matches soil moisture.\nPlant does not bloom in desired season.", result);
		
		result = test.isPlantMatch("Amelanchier arborea", 50, 550);
		assertEquals("Plant matches light requirement.\nPlant does not match soil moisture.\nPlant does not bloom in desired season.\nPlant matches desired color.", result);
		
		result = test.isPlantMatch("Helianthus angustifolius", 550, 550);
		assertEquals("Plant does not match light requirement.\nPlant matches soil moisture.\nPlant blooms in desired season.", result);		
	}
	
	@Test
	public void isInAreaTest() {
		Model test = new Model();
		
		Rectangle area = new Rectangle();
		
		area.setX(0);
		area.setY(0);
		
		area.setWidth(499);
		area.setHeight(499);
		
		assertEquals(false, test.isInArea(-1, -1, area));
		assertEquals(false, test.isInArea(500, 500, area));
		assertEquals(false, test.isInArea(500, 250, area));
		assertEquals(false, test.isInArea(250, 500, area));
		assertEquals(true, test.isInArea(250, 250, area));
		assertEquals(true, test.isInArea(0, 0, area));
		assertEquals(true, test.isInArea(499, 499, area));
	}
}
