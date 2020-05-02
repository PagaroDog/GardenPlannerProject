package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.Main;
import Controllers.StartupController;
import Controllers.SuggestionsController;
import Model.Model;
import Model.StageName;
import Views.StartupView;
import Views.SuggestionsView;
import javafx.stage.Stage;

public class SuggestionsControllerTest {

	@Test
	public void test() {
		Model model = new Model();
		SuggestionsView sv = new SuggestionsView(new Stage());
		Main main = new Main();
		SuggestionsController sc = new SuggestionsController(model, sv, main);

		sc.gethandleOnNextButton();
		assertEquals(model.getStageName(), StageName.PREFERENCES);

		sc.gethandleOnBackButton();
		assertEquals(model.getStageName(), StageName.DESIGN);

	}

}
