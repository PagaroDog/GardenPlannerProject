package Tests;

import static org.junit.Assert.*;
/**
 * @author matt cohen
 */
import org.junit.Test;

import Controllers.Main;
import Controllers.StartupController;
import Model.Model;
import Model.StageName;
import Views.StartupView;
import javafx.stage.Stage;

public class StartupControllerTests {

	@Test
	public void test() {
		Model model = new Model();
		StartupView suv = new StartupView(new Stage());
		Main main = new Main();
		StartupController suc = new StartupController(model, suv,main);
		
		
		suc.handleOnNewButton();
		assertEquals(model.getStageName(), StageName.PREFERENCES);
		
		suc.handleOnLoadButton();
		assertEquals(model.getStageName(), StageName.DESIGN);
		
		suc.handleOnTutorialButton();
		assertEquals(model.getStageName(), StageName.TUTORIAL);
		
		
	}

}
