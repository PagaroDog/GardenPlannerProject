package Tests;

import static org.junit.Assert.*;

import org.junit.Test;


import Controllers.TutorialController;
import Model.Model;

import Views.TutorialView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TutorialControllerTests {
	MouseEvent emptyMouseEvent = new MouseEvent(null, null, null, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);

	@Test
	public void test() {
		Model model = new Model();
	    TutorialView tv = new TutorialView(new Stage());
	    TutorialController tc = new TutorialController(model, tv);
	    
	    
	    tc.nextButton(emptyMouseEvent);
	    
	    assertEquals(model.StageName, StageName.PREFERENCES);
	    
	    tc.prevButton(emptyMouseEvent);
	    assertEquals(model.StageName, StageName.DRAW);
	}


}
