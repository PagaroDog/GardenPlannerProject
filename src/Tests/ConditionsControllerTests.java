package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controllers.ConditionsController;
import Model.Model;

public class ConditionsControllerTests {

	@Test
	public void testSelectButton() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		model.setCondMode(CondMode.LABEL);
		cc.handleOnSelectButton();
		assertEquals(CondMode.SELECT, model.getCondMode());
		model.setCondMode(CondMode.RECTANGLE);
		cc.handleOnSelectButton();
		assertEquals(CondMode.SELECT, model.getCondMode());
		model.setCondMode(CondMode.CIRCLE);
		cc.handleOnSelectButton();
		assertEquals(CondMode.SELECT, model.getCondMode());
	}

	@Test
	public void testLabelButton() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		model.setCondMode(CondMode.SELECT);
		cc.handleOnLabelButton();
		assertEquals(CondMode.LABEL, model.getCondMode());
		model.setCondMode(CondMode.RECTANGLE);
		cc.handleOnLabelButton();
		assertEquals(CondMode.LABEL, model.getCondMode());
		model.setCondMode(CondMode.CIRCLE);
		cc.handleOnLabelButton();
		assertEquals(CondMode.LABEL, model.getCondMode());
	}
	
	@Test
	public void testRectButton() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		model.setCondMode(CondMode.SELECT);
		cc.handleOnRectButton();
		assertEquals(CondMode.RECTANGLE, model.getCondMode());
		model.setCondMode(CondMode.LABEL);
		cc.handleOnRectButton();
		assertEquals(CondMode.RECTANGLE, model.getCondMode());
		model.setCondMode(CondMode.CIRCLE);
		cc.handleOnRectButton();
		assertEquals(CondMode.RECTANGLE, model.getCondMode());
	}
	
	@Test
	public void testCircleButton() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		model.setCondMode(CondMode.SELECT);
		cc.handleOnCircleButton();
		assertEquals(CondMode.CIRCLE, model.getCondMode());
		model.setCondMode(CondMode.LABEL);
		cc.handleOnCircleButton();
		assertEquals(CondMode.CIRCLE, model.getCondMode());
		model.setCondMode(CondMode.RECTANGLE);
		cc.handleOnCircleButton();
		assertEquals(CondMode.CIRCLE, model.getCondMode());
	}
	
	@Test
	public void testDragPressAndDelete() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		cc.handleOnRectButton();
		cc.handleOnDragPane();
		assertEquals(1, model.getGardenObjArr().size());
		cc.handleOnDragPane();
		assertEquals(2, model.getGardenObjArr().size());
		
		cc.handleOnSelectButton();
		cc.handleOnPressPane();
		cc.handleOnDeleteButton();
		assertEquals(1, model.getGardenObjArr().size());
		cc.handleOnPressPane();
		cc.handleOnDeleteButton();
		assertEquals(0, model.getGardenObjArr().size());
	}
	
	@Test
	public void testDeleteWithNoSelection() {
		Model model = new Model();
		ConditionsController cc = new ConditionsController();
		
		cc.handleOnRectButton();
		cc.handleOnDragPane();
		assertEquals(1, model.getGardenObjArr().size());
		cc.handleOnDragPane();
		assertEquals(2, model.getGardenObjArr().size());
		
		cc.handleOnSelectButton();
		cc.handleOnDeleteButton();
		assertEquals(2, model.getGardenObjArr().size());
		cc.handleOnDeleteButton();
		assertEquals(2, model.getGardenObjArr().size());
	}
}
