package Tests;

import static org.junit.Assert.fail;

import java.awt.event.MouseEvent;

import org.junit.Test;


import Controllers.PreferencesController;
import Model.GardenPref;
import Model.Model;
import Model.StageName;
import Views.PreferencesView;
import javafx.stage.Stage;

public class PreferencesControllerTests {

	@Test
	public void nextButtonTest() {
		Model Mod = new Model();
		PreferencesController pc = new PreferencesController();
		PreferencesView pv = new PreferencesView(new Stage());
		MouseEvent me = new MouseEvent(null, 0, 0, 0, 0, 0, 0, false);
		//Mod.createGardenPref("Name",pv.getSun(),pv.getBloom(),pv.getSoil(),pv.getColor(),null,null,null);
		
		Mod.setStageName(StageName.PREFERENCES);
		pc.gethandleOnNextButton();
		assertEquals(StageName.DESIGN, Mod.getStageName());
		GardenPref test = new GardenPref("Name",pv.getSun().getValue(),pv.getBloom().getValue(),pv.getSoil().getValue(),pv.getColor().getValue(),null,null,null));
		assertEquals(test,Mod.getGardenPref());
		
	}
	@Test
	public void backButtonTest() {
		Model Mod = new Model();
		PreferencesController pc = new PreferencesController();
		PreferencesView pv = new PreferencesView(new Stage());
		MouseEvent me = new MouseEvent(null, 0, 0, 0, 0, 0, 0, false);
		
		
		Mod.setStageName(StageName.PREFERENCES);
		pc.gethandleOnBackButton();
		assertEquals(StageName.DRAW, Mod.getStageName());
		
		
	}

}
