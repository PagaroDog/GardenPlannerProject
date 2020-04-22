package Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import Controllers.SaveController;
import Model.Model;
import Model.StageName;
import Views.SaveView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SaveControllerTests {
	MouseEvent emptyMouseEvent = new MouseEvent(null, null, null, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);

    @Test
    public void test() {

            Model model = new Model();
            
            SaveView sv = new SaveView(new Stage());
            SaveController sc = new SaveController(model, sv);
            
            sc.fileButton(emptyMouseEvent);
            assertEquals(StageName.PREFERENCES, model.getStageName());
            
            sc.PNGButton(emptyMouseEvent);
            assertEquals(StageName.DRAW, model.getStageName());
    }
}
