package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.StatisticsController;
import Model.Model;

public class StatisticsControllerTests {

	@Test
	public void test() {
		Model model = new Model();
		StatisticsController sc = new StatisticsController();
		
		sc.handleOnBackButton();
		ssertEquals(StageName.DESIGN, model.getStageName());
	}

}
