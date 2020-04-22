package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.StatisticsController;
import Model.Model;
import Model.StageName;

public class StatisticsControllerTests {

	@Test
	public void test() {
		Model model = new Model();
		StatisticsController sc = new StatisticsController();
		
		sc.handleOnBackButton();
		assertEquals(StageName.DESIGN, model.getStageName());
	}

}
