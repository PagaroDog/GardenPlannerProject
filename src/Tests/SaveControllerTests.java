package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Controllers.Main;
import Controllers.SaveController;
import Model.Model;
import Model.StageName;
import Views.SaveView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Josh Stone this class is the test for the SaveController
 */
public class SaveControllerTests {
	MouseEvent emptyMouseEvent = new MouseEvent(null, null, null, 0, 0, 0, 0, null, 0, false, false, false, false,
			false, false, false, false, false, false, null);

	@Test
	public void test() {

		Model model = new Model();

		SaveView sv = new SaveView(new Stage());
		Main main = new Main();
		SaveController sc = new SaveController(model, sv, main);

		sc.fileButton(emptyMouseEvent);
		assertEquals(StageName.PREFERENCES, model.getStageName());

		sc.PNGButton(emptyMouseEvent);
		assertEquals(StageName.DRAW, model.getStageName());
	}
}
