package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.event.MouseEvent;

import org.junit.Test;

import Controllers.Main;
import Controllers.PreferencesController;
import Model.GardenPref;
import Model.Model;
import Model.StageName;
import Views.PreferencesView;
import javafx.stage.Stage;

/**
 * 
 * @author Brandon Wu
 *
 */
public class PreferencesControllerTests {
/*
	@Test
	public void nextButtonTest() {
		Model Mod = new Model();

		PreferencesView pv = new PreferencesView(new Stage());
		Main main = new Main();
		PreferencesController pc = new PreferencesController(Mod, pv, main);
		// MouseEvent me = new MouseEvent(null, 0, 0, 0, 0, 0, 0, false);

		Mod.setStageName(StageName.PREFERENCES);
		pc.gethandleOnNextButton();
		assertEquals(StageName.DESIGN, Mod.getStageName());
		GardenPref test = new GardenPref(null, 0, 0, 0, 0);
		assertEquals(test, Mod.getGardenPreferences());

	}

	@Test
	public void backButtonTest() {
		Model Mod = new Model();

		PreferencesView pv = new PreferencesView(new Stage());
		Main main = new Main();
		PreferencesController pc = new PreferencesController(Mod, pv, main);
		// MouseEvent me = new MouseEvent(null, 0, 0, 0, 0, 0, 0, false);

		Mod.setStageName(StageName.PREFERENCES);
		pc.gethandleOnBackButton();
		assertEquals(StageName.DRAW, Mod.getStageName());

	}*/

}
