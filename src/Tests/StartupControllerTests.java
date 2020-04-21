package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Controllers.StartupController;
import Model.Model;
import Views.StartupView;
import javafx.stage.Stage;

public class StartupControllerTests {

	@Test
	public void test() {
		Model model = new Model();
		StartupView suv = new StartupView(new Stage());
		StartupController suc = new StartupController();
		suc.handleOnNewButton();
		assertEquals(model.stageName,
	}

}
