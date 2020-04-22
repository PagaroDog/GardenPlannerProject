package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Controllers.ConditionsController;
import Controllers.GardenController;
import Controllers.StartupController;
import Model.GardenObj;
import Model.Model;
import Model.Season;
import Model.StageName;
import Views.GardenView;
import Views.StartupView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GardenControllerTests {

	@Test
	public void test() {
		Model model = new Model();
		GardenView gv = new GardenView(new Stage());
		GardenController gc = new GardenController();
		
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
		model.getGardenObjects().add(new GardenObj());
		HashMap <GardenObj> gardenobj = new ArrayList <GardenObj>(); 
		gardenobj = model.getGardenObjects();
		double initxLoc = gardenobj.get(0).getxLoc();
		double inityLoc = gardenobj.get(0).getyLoc();
		gc.ImgDrag(dragEvent);
		double secxLoc = gardenobj.get(0).getxLoc();
		double secyLoc = gardenobj.get(0).getyLoc();
		assertEquals(initxLoc,0);
		assertEquals(inityLoc,0);
		assertEquals(secxLoc,10);
		assertEquals(secyLoc,10);
		
		
		assertEquals(model.getSeason(), Season.FALL);
		
		gc.handleOnPrefButton();
		assertEquals(model.getStageName(), StageName.STATS);
	}
	

}
