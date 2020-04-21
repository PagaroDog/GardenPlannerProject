package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.DrawYardController;
import Model.Model;
import Views.DrawYardView;
import javafx.stage.Stage;

public class DrawYardControllerTests {

	@Test
	public void test() {
		Model model = new Model();
		DrawYardView dyv = new DrawYardView(new Stage());
		DrawYardController dyc = new DrawYardController();
		
		model.setDrawMode(DrawMode.CIRCLE);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.RECTANGLE, model.getDrawMode());
		model.setDrawMode(DrawMode.SELECT);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.RECTANGLE, model.getDrawMode());
		model.setDrawMode(DrawMode.RECTANGLE);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.RECTANGLE, model.getDrawMode());
		
		model.setDrawMode(DrawMode.RECTANGLE);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.CIRCLE, model.getDrawMode());
		model.setDrawMode(DrawMode.SELECT);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.CIRCLE, model.getDrawMode());
		model.setDrawMode(DrawMode.CIRCLE);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.CIRCLE, model.getDrawMode());
		
		dyc.handleOnDragPane();
		assertEquals(1, model.getGardenObjectArr().size());
	}

}
