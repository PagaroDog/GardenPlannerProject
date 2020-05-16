package Tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import Model.ActionEnum;
import Model.GardenAction;
import Views.GardenView;
import Views.Images;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * 
 * @author IanMcCabe
 *
 */
public class GardenActionTests {
	
	@Test
	public void addActionTest() {
		GardenAction test = new GardenAction(); 
		
		Circle testPlant = new Circle(); 
		double testX = 30; 
		double testY = 100; 
		double testRadius = 50; 
		String testName = "Green Plant"; 
		Color testColor = Color.DARKGREEN;  
		
		GardenAction actionOne = new GardenAction(testPlant, testX, testY, testRadius, testName, testColor, ActionEnum.ADDPLANT); 
		test.addAction(actionOne); 
		assertEquals(test.getActionList().size(), 1); 
		
		double testMoveX = 25; 
		double testMoveY = 30;
		testX += testMoveX;
		testY += testMoveY; 
		
		GardenAction actionTwo = new GardenAction(testPlant, testX + testMoveX, testY , testRadius, testName, testColor, ActionEnum.MOVEPLANT);
		test.addAction(actionTwo);
		assertEquals(test.getActionList().size(), 2);
		
		testX += testMoveX;
		testY += testMoveY;
		
		GardenAction actionThree = new GardenAction(testPlant, testX + testMoveX, testY , testRadius, testName, testColor, ActionEnum.MOVEPLANT);
		test.addAction(actionThree);
		assertEquals(test.getActionList().size(), 3);
		 
		test.undo();
		test.undo(); 
		assertEquals(test.getRedoList().size(), 2); 
		assertEquals(test.getActionList().size(), 1); 
		
		testX += testMoveX;
		testY += testMoveY;
		GardenAction actionFour = new GardenAction(testPlant, testX + testMoveX, testY , testRadius, testName, testColor, ActionEnum.MOVEPLANT);
		test.addAction(actionFour);
		
		assertEquals(test.getRedoList().size(), 0); 
		assertEquals(test.getActionList().size(), 2); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
