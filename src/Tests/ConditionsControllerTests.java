package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.ConditionsController;
import Model.Model;
import javafx.scene.input.MouseEvent;

public class ConditionsControllerTests {
	MouseEvent emptyMouseEvent = new MouseEvent(null, null, null, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);

	public void create50x50rect(ConditionsController cc) {
		cc.pressPane(new MouseEvent(null, null, null, 10, 10, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
		cc.dragPane(emptyMouseEvent);
		cc.releasePane(new MouseEvent(null, null, null, 60, 60, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
	}
	
	public void create100x90rect(ConditionsController cc) {
		cc.pressPane(new MouseEvent(null, null, null, 70, 40, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
		cc.dragPane(emptyMouseEvent);
		cc.releasePane(new MouseEvent(null, null, null, 170, 130, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
	}
	
	@Test
	public void testSelectButton() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		model.setCondMode(CondMode.LABEL);
		cc.selectButton(emptyMouseEvent);
		assertEquals(CondMode.SELECT, model.getCondMode());
		model.setCondMode(CondMode.RECTANGLE);
		cc.selectButton(emptyMouseEvent);
		assertEquals(CondMode.SELECT, model.getCondMode());
		model.setCondMode(CondMode.CIRCLE);
		cc.selectButton(emptyMouseEvent);
		assertEquals(CondMode.SELECT, model.getCondMode());
	}

	@Test
	public void testLabelButton() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		model.setCondMode(CondMode.SELECT);
		cc.labelButton(emptyMouseEvent);
		assertEquals(CondMode.LABEL, model.getCondMode());
		model.setCondMode(CondMode.RECTANGLE);
		cc.labelButton(emptyMouseEvent);
		assertEquals(CondMode.LABEL, model.getCondMode());
		model.setCondMode(CondMode.CIRCLE);
		cc.labelButton(emptyMouseEvent);
		assertEquals(CondMode.LABEL, model.getCondMode());
	}
	
	@Test
	public void testRectButton() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		model.setCondMode(CondMode.SELECT);
		cc.rectButton(emptyMouseEvent);
		assertEquals(CondMode.RECTANGLE, model.getCondMode());
		model.setCondMode(CondMode.LABEL);
		cc.rectButton(emptyMouseEvent);
		assertEquals(CondMode.RECTANGLE, model.getCondMode());
		model.setCondMode(CondMode.CIRCLE);
		cc.rectButton(emptyMouseEvent);
		assertEquals(CondMode.RECTANGLE, model.getCondMode());
	}
	
	@Test
	public void testCircleButton() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		model.setCondMode(CondMode.SELECT);
		cc.circleButton(emptyMouseEvent);
		assertEquals(CondMode.CIRCLE, model.getCondMode());
		model.setCondMode(CondMode.LABEL);
		cc.circleButton(emptyMouseEvent);
		assertEquals(CondMode.CIRCLE, model.getCondMode());
		model.setCondMode(CondMode.RECTANGLE);
		cc.circleButton(emptyMouseEvent);
		assertEquals(CondMode.CIRCLE, model.getCondMode());
	}
	
	@Test
	public void testDragPressAndDelete() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		cc.rectButton(emptyMouseEvent);
		create50x50rect(cc);
		assertEquals(1, model.getGardenObjArr().size());
		create100x90rect(cc);
		assertEquals(2, model.getGardenObjArr().size());
		
		//Select and delete 50x50 rectangle
		cc.selectButton(emptyMouseEvent);
		cc.pressPane(new MouseEvent(null, null, null, 35, 35, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
		cc.deleteButton(emptyMouseEvent);
		assertEquals(1, model.getGardenObjArr().size());
		//Select and delete 100x90 rectangle
		cc.pressPane(new MouseEvent(null, null, null, 120, 85, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
		cc.deleteButton(emptyMouseEvent);
		assertEquals(0, model.getGardenObjArr().size());
	}
	
	@Test
	public void testDeleteWithNoSelection() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		cc.rectButton(emptyMouseEvent);
		create50x50rect(cc);
		assertEquals(1, model.getGardenObjArr().size());
		create100x90rect(cc);
		assertEquals(2, model.getGardenObjArr().size());
		
		//Select nothing and confirm delete has no effect
		cc.selectButton(emptyMouseEvent);
		cc.deleteButton(emptyMouseEvent);
		assertEquals(2, model.getGardenObjArr().size());
		cc.deleteButton(emptyMouseEvent);
		assertEquals(2, model.getGardenObjArr().size());
	}
}
