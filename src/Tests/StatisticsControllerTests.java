package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.StatisticsController;
import Model.Model;
import Model.StageName;
import Views.StatisticsView;
import javafx.stage.Stage;

/**
 * 
 * @author Tommy White
 *
 */
public class StatisticsControllerTests {

	@Test
	public void test() {
		Model model = new Model();
		StatisticsView sv = new StatisticsView(new Stage());
		StatisticsController sc = new StatisticsController(model,sv);
		
		sc.handleOnBackButton();
		assertEquals(StageName.DESIGN, model.getStageName());
	}

}
