package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.GardenController;
import Controllers.StartupController;
import Model.Model;
import Views.GardenView;
import Views.StartupView;
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
		
		//TODO: figure out what this should do
		gc.handleOnStatsButton();
		assertEquals(model.getSEASON(), SEASON.FALL);
		
		//TODO: figure out what this should effect.
		gc.handleOnYear1Button();
		assertEquals(model.getSEASON(), SEASON.FALL);
		
		//TODO: figure out what this should effect.
		gc.handleOnYear2Button();
		assertEquals(model.getSEASON(), SEASON.FALL);
				
		//TODO: figure out what this should effect.
		gc.handleOnYear3Button();
		assertEquals(model.getSEASON(), SEASON.FALL);
		
		
		//TODO: figure out what this should effect.
		gc.handleOnImgDrag();
		assertEquals(model.getSEASON(), SEASON.FALL);
		
		//TODO: figure out what this should effect.
		gc.handleOnPrefButton();
		assertEquals(model.getSEASON(), SEASON.FALL);
	}

}
