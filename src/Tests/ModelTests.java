package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.GardenObj;
import Model.GardenPref;
import Model.Model;

public class ModelTests {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void copyTest() {
		Model test = new Model(); 
		GardenObj j = new GardenObj(); 
		
		test.getGardenObjects().put(1, j); 
		
		test.copy(j, j.getID()); 
		
		assertEquals(test.getGardenObjects().size(), 2); 
	}
	
	
	@Test
	public void undoTest() {
		Model test = new Model(); 
		GardenObj j = new GardenObj(); 
		
		test.getGardenObjects().put(1, j); 
		
		test.undo();
		
		assertEquals(test.getGardenObjects().size(), 0); 
	}
	
	public void redoTest() {
		Model test = new Model(); 
		GardenObj j = new GardenObj(); 
		
		test.getGardenObjects().put(1, j); 
		
		test.undo();
		test.redo(); 
		
		assertEquals(test.getGardenObjects().size(), 1);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
