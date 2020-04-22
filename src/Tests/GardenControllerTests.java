package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Controllers.ConditionsController;
import Controllers.GardenController;
import Controllers.StartupController;
import Model.GardenObj;
import Model.Model;
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
		assertEquals(model.getSEASON(), SEASON.FALL);
		
		gc.handleOnWinterButton();
		assertEquals(model.getSEASON(), SEASON.WINTER);
		
		gc.handleOnSpringButton();
		assertEquals(model.getSEASON(), SEASON.SPRING);
		
		gc.handleOnSummerButton();
		assertEquals(model.getSEASON(), SEASON.SUMMER);
		
		gc.handleOnStatsButton();
		assertEquals(model.getStageName(), StageName.STATS);
		
		
		gc.handleOnYear1Button();
		assertEquals(model.getYear(), 1);
		
		
		gc.handleOnYear2Button();
		assertEquals(model.getYear(), 2);
				
		
		gc.handleOnYear3Button();
		assertEquals(model.getYear(), 3);
		
		
		MouseEvent dragEvent = new MouseEvent(null, null, null, 10, 10, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);
		model.getGardenObj().add(new GardenObj());
		ArrayList <GardenObj> gardenobj = new ArrayList <GardenObj>(); 
		gardenobj = model.getGardenObj();
		double initxLoc = gardenobj.get(0).getxLoc();
		double inityLoc = gardenobj.get(0).getyLoc();
		gc.ImgDrag(dragEvent);
		double secxLoc = gardenobj.get(0).getxLoc();
		double secyLoc = gardenobj.get(0).getyLoc();
		assertEquals(initxLoc,0);
		assertEquals(inityLoc,0);
		assertEquals(secxLoc,10);
		assertEquals(secyLoc,10);
		
		
		assertEquals(model.getSEASON(), SEASON.FALL);
		
		gc.handleOnPrefButton();
		assertEquals(model.getStageName(), StageName.STATS);
	}
	

}
