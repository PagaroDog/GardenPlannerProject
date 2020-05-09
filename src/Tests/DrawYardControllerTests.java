package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.DrawYardController;
import Controllers.Main;
import Model.DrawMode;
import Model.Model;
import Views.DrawYardView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * @author Tommy White
 *
 */
public class DrawYardControllerTests {
	MouseEvent emptyMouseEvent = new MouseEvent(null, null, null, 0, 0, 0, 0, null, 0, false, false, false, false,
			false, false, false, false, false, false, null);
/*
	public void create50x50rect(DrawYardController dyc) {
		dyc.pressPane(new MouseEvent(null, null, null, 10, 10, 0, 0, null, 0, false, false, false, false, false, false,
				false, false, false, false, null));
		dyc.dragPane(emptyMouseEvent);
		// dyc.releasePane(new MouseEvent(null, null, null, 60, 60, 0, 0, null, 0,
		// false, false, false, false, false, false, false, false, false, false, null));
	}

	public void create100x90rect(DrawYardController dyc) {
		dyc.pressPane(new MouseEvent(null, null, null, 70, 40, 0, 0, null, 0, false, false, false, false, false, false,
				false, false, false, false, null));
		dyc.dragPane(emptyMouseEvent);
		// dyc.releasePane(new MouseEvent(null, null, null, 170, 130, 0, 0, null, 0,
		// false, false, false, false, false, false, false, false, false, false, null));
	}
*/
	@Test
	public void testRectangleButton() {
		/*Model model = new Model();
		DrawYardView dyv = new DrawYardView(new Stage());
		Main main = new Main();
		DrawYardController dyc = new DrawYardController(model, dyv, main);

		model.setDrawMode(DrawMode.CIRCLE);
		dyc.rectButton(emptyMouseEvent);
		assertEquals(DrawMode.RECTANGLE, model.getDrawMode());
		model.setDrawMode(DrawMode.SELECT);
		dyc.rectButton(emptyMouseEvent);
		assertEquals(DrawMode.RECTANGLE, model.getDrawMode());
		model.setDrawMode(DrawMode.RECTANGLE);
		dyc.rectButton(emptyMouseEvent);
		assertEquals(DrawMode.RECTANGLE, model.getDrawMode());
		*/
		fail("Test not available");
	}
	@Test
	public void testCircleButton() {
		/*Model model = new Model();
		DrawYardView dyv = new DrawYardView(new Stage());
		Main main = new Main();
		DrawYardController dyc = new DrawYardController(model, dyv, main);

		model.setDrawMode(DrawMode.RECTANGLE);
		dyc.circleButton(emptyMouseEvent);
		assertEquals(DrawMode.CIRCLE, model.getDrawMode());
		model.setDrawMode(DrawMode.SELECT);
		dyc.circleButton(emptyMouseEvent);
		assertEquals(DrawMode.CIRCLE, model.getDrawMode());
		model.setDrawMode(DrawMode.CIRCLE);
		dyc.circleButton(emptyMouseEvent);
		assertEquals(DrawMode.CIRCLE, model.getDrawMode());
		*/
	}
	@Test
	public void testDragPressAndDelete() {
		/*Model model = new Model();
		DrawYardView dyv = new DrawYardView(new Stage());
		Main main = new Main();
		DrawYardController dyc = new DrawYardController(model, dyv, main);

		model.setDrawMode(DrawMode.RECTANGLE);
		create50x50rect(dyc);
		assertEquals(1, model.getGardenObjects().size());

		// Select and delete 50x50 rectangle
		dyc.selectButton(emptyMouseEvent);
		dyc.pressPane(new MouseEvent(null, null, null, 35, 35, 0, 0, null, 0, false, false, false, false, false, false,
				false, false, false, false, null));
		dyc.deleteButton(emptyMouseEvent);
		assertEquals(1, model.getGardenObjects().size());
		// Select and delete 100x90 rectangle
		dyc.pressPane(new MouseEvent(null, null, null, 120, 85, 0, 0, null, 0, false, false, false, false, false, false,
				false, false, false, false, null));
		dyc.deleteButton(emptyMouseEvent);
		assertEquals(0, model.getGardenObjects().size());*/
		fail("Test not available");
	}

	@Test
	public void testDeleteWithNoSelection() {
		/*Model model = new Model();
		DrawYardView dyv = new DrawYardView(new Stage());
		Main main = new Main();
		DrawYardController dyc = new DrawYardController(model, dyv, main);

		dyc.rectButton(emptyMouseEvent);
		create50x50rect(dyc);
		assertEquals(1, model.getGardenObjects().size());
		create100x90rect(dyc);
		assertEquals(2, model.getGardenObjects().size());

		// Select nothing and confirm delete has no effect
		dyc.selectButton(emptyMouseEvent);
		dyc.deleteButton(emptyMouseEvent);
		assertEquals(2, model.getGardenObjects().size());
		dyc.deleteButton(emptyMouseEvent);
		assertEquals(2, model.getGardenObjects().size());
		*/
		fail("Test not available");
	}

}
