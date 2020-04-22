package Model;


import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;



public class Model {
    private Season season = new Season(); 
    private Season season; 

    private StageName stageName; 
    private HashMap<GardenPref, Integer> gardenPreferences; 
    private HashMap<GardenObj, Integer> gardenObjects;  
    private ArrayList<Actions> undoActions; 
    private ArrayList<Actions> redoActions; 
    private int year; 
 
    
    private int canvasWidth; 
    private int canvasHeight; 
    
    
    
    
 
    
    
    
    public Season getSeason() {
		return season;
	}


	public void setSeason(Season season) {
		this.season = season;
	}


	public StageName getStageName() {
		return stageName;
	}


	public void setStageName(StageName stageName) {
		this.stageName = stageName;
	}


	public HashMap<GardenPref, Integer> getGardenPreferences() {
		return gardenPreferences;
	}


	public void setGardenPreferences(HashMap<GardenPref, Integer> gardenPreferences) {
		this.gardenPreferences = gardenPreferences;
	}


	public HashMap<GardenObj, Integer> getGardenObjects() {
		return gardenObjects;
	}


	public void setGardenObjects(HashMap<GardenObj, Integer> gardenObjects) {
		this.gardenObjects = gardenObjects;
	}


	public ArrayList<Actions> getUndoActions() {
		return undoActions;
	}


	public void setUndoActions(ArrayList<Actions> undoActions) {
		this.undoActions = undoActions;
	}


	public ArrayList<Actions> getRedoActions() {
		return redoActions;
	}


	public void setRedoActions(ArrayList<Actions> redoActions) {
		this.redoActions = redoActions;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getCanvasWidth() {
		return canvasWidth;
	}


	public void setCanvasWidth(int canvasWidth) {
		this.canvasWidth = canvasWidth;
	}


	public int getCanvasHeight() {
		return canvasHeight;
	}


	public void setCanvasHeight(int canvasHeight) {
		this.canvasHeight = canvasHeight;
	}

	
	
	/**
	 * Copies a garden object
	 * @param j the garden object being copied
	 * @param i the integer key value of the garden object 
	 */
	public void copy(GardenObj j, int i) {
    	
    }
    
    /**
     * Undoes an action, such as removing an object after it's been created 
     */
    public void undo() {
    	
    }
    
    /**
     * Redoes an undone action
     */
    public void redo() {
    	
    }
    
   
	
	
}
