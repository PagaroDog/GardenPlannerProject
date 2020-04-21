package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.DrawYardController;
import Model.Model;
import Views.DrawYardView;
import javafx.stage.Stage;

public class DrawYardControllerTests {

	@Test
	public void testRectangleButton() {
		Model model = new Model();
		DrawYardView dyv = new DrawYardView(new Stage());
		DrawYardController dyc = new DrawYardController(model, dyv);
		
		model.setDrawMode(DrawMode.CIRCLE);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.RECTANGLE, model.getDrawMode());
		model.setDrawMode(DrawMode.SELECT);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.RECTANGLE, model.getDrawMode());
		model.setDrawMode(DrawMode.RECTANGLE);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.RECTANGLE, model.getDrawMode());
	}
	
	public void testCircleButton() {
		Model model = new Model();
		DrawYardView dyv = new DrawYardView(new Stage());
		DrawYardController dyc = new DrawYardController(model, dyv);
		
		model.setDrawMode(DrawMode.RECTANGLE);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.CIRCLE, model.getDrawMode());
		model.setDrawMode(DrawMode.SELECT);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.CIRCLE, model.getDrawMode());
		model.setDrawMode(DrawMode.CIRCLE);
		dyc.handleOnRectButton();
		assertEquals(DrawMode.CIRCLE, model.getDrawMode());
	}
	
	public void testDragPane() {
		Model model = new Model();
		DrawYardView dyv = new DrawYardView(new Stage());
		DrawYardController dyc = new DrawYardController(model, dyv);
		
		model.setDrawMode(DrawMode.RECTANGLE);
		dyc.handleOnDragPane();
		assertEquals(1, model.getGardenObjectArr().size());
	}

}
