package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.Main;
import Controllers.TutorialController;
import Model.Model;
import Model.StageName;
import Views.TutorialView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Josh Stone this class is the test for the TutorialController
 */
public class TutorialControllerTests {
	MouseEvent emptyMouseEvent = new MouseEvent(null, null, null, 0, 0, 0, 0, null, 0, false, false, false, false,
			false, false, false, false, false, false, null);

	@Test
	public void test() {
		Model model = new Model();
		TutorialView tv = new TutorialView(new Stage());
		Main main = new Main();
		TutorialController tc = new TutorialController(model, tv, main);

		tc.nextButton(emptyMouseEvent);

		assertEquals(StageName.PREFERENCES, model.getStageName());

		tc.prevButton(emptyMouseEvent);
		assertEquals(StageName.DRAW, model.getStageName());
	}

}
