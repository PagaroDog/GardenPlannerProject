package Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import Controllers.SaveController;
import Model.Model;
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
            
            model.saveGarden();
            
            sc.fileButton(emptyMouseEvent);
            assertEquals(model.StageName, StageName.PREFERENCES);
            
            sc.PNGButton(emptyMouseEvent);
            assertEquals(model.StageName, StageName.DRAW);
    }


}
