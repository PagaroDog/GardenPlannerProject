package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import Controllers.GardenController;
import Controllers.Main;
import Controllers.StartupController;
import Model.GardenObj;
import Model.Model;
import Model.Season;
import Model.StageName;
import Views.GardenView;
import Views.StartupView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * 
 * @author matt cohen
 *
 */

public class GardenControllerTests {

	@Test
	public void test() {
		Model model = new Model();
		GardenView gv = new GardenView(new Stage());
		Main main = new Main();
		GardenController gc = new GardenController(model, gv,main);
		
		gc.handleOnFallButton();
		assertEquals(model.getSeason(), Season.FALL);
		
		gc.handleOnWinterButton();
		assertEquals(model.getSeason(), Season.WINTER);
		
		gc.handleOnSpringButton();
		assertEquals(model.getSeason(), Season.SPRING);
		
		gc.handleOnSummerButton();
		assertEquals(model.getSeason(), Season.SUMMER);
		
		gc.handleOnStatsButton();
		assertEquals(model.getStageName(), StageName.STATS);
		
		
		gc.handleOnYear1Button();
		assertEquals(model.getYear(), 1);
		
		
		gc.handleOnYear2Button();
		assertEquals(model.getYear(), 2);
				
		
		gc.handleOnYear3Button();
		assertEquals(model.getYear(), 3);
		
		
		MouseEvent dragEvent = new MouseEvent(null, null, null, 10, 10, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);
		HashMap <Integer, GardenObj> gardenobj = new HashMap <Integer, GardenObj>(); 
		gardenobj = model.getGardenObjects();
		gardenobj.put(0, new GardenObj());
		double initxLoc = gardenobj.get(0).getxLoc();
		double inityLoc = gardenobj.get(0).getyLoc();
		gc.imgDrag(dragEvent);
		double secxLoc = gardenobj.get(0).getxLoc();
		double secyLoc = gardenobj.get(0).getyLoc();
		assertEquals(0, initxLoc, 0.01);
		assertEquals(0, inityLoc, 0.01);
		assertEquals(10, secxLoc, 0.01);
		assertEquals(10, secyLoc, 0.01);
		
		
		assertEquals(model.getSeason(), Season.FALL);
		
		gc.handleOnPrefButton();
		assertEquals(model.getStageName(), StageName.STATS);
	}
	

}
