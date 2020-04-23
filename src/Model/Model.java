package Model;


import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import Controllers.ConditionsController;
import Controllers.Controller;
import Controllers.DrawYardController;
import Controllers.GardenController;
import Controllers.PreferencesController;
import Controllers.SaveController;
import Controllers.StartupController;
import Controllers.StatisticsController;
import Controllers.TutorialController;

/**
 * 
 * @author IanMcCabe
 *
 */

public class Model {
    private Season season;
    private StageName stageName = StageName.WELCOME;
    private CondMode condMode;

	private DrawMode drawMode;
	private double drawPressX;
	private double drawPressY;
	
    private HashMap<Integer, GardenPref> gardenPreferences; 
    private HashMap<Integer, GardenObj> gardenObjects;  
    private ArrayList<Actions> undoActions;
    private ArrayList<Actions> redoActions;
    private int year; 
 
    
    private int canvasWidth; 
    private int canvasHeight; 
    
    private StartupController startControl;
	

	private TutorialController tutControl;
	private DrawYardController dyControl;
	private ConditionsController condControl;
	private StatisticsController statControl;
	private GardenController gardenControl;
	private SaveController saveControl;
	private PreferencesController prefControl;
    
    
    
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


	public HashMap<Integer, GardenPref> getGardenPreferences() {
		return gardenPreferences;
	}


	public void setGardenPreferences(HashMap<Integer, GardenPref> gardenPreferences) {
		this.gardenPreferences = gardenPreferences;
	}


	public HashMap<Integer, GardenObj> getGardenObjects() {
		return gardenObjects;
	}


	public void setGardenObjects(HashMap<Integer, GardenObj> gardenObjects) {
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
	
	public CondMode getCondMode() {
		return condMode;
	}


	public void setCondMode(CondMode condMode) {
		this.condMode = condMode;
	}


	public DrawMode getDrawMode() {
		return drawMode;
	}

	public void setDrawMode(DrawMode drawMode) {
		this.drawMode = drawMode;
	}
	
	public double getDrawPressX() {
		return drawPressX;
	}

	public void setDrawPressX(double drawPressX) {
		this.drawPressX = drawPressX;
	}

	public double getDrawPressY() {
		return drawPressY;
	}

	public void setDrawPressY(double drawPressY) {
		this.drawPressY = drawPressY;
	}

	public StartupController getStartControl() {
		return startControl;
	}


	public void setController(Controller controller) {
		
	}
	
	public void setStartControl(StartupController startControl) {
		this.startControl = startControl;
	}


	public TutorialController getTutControl() {
		return tutControl;
	}


	public void setTutControl(TutorialController tutControl) {
		this.tutControl = tutControl;
	}


	public DrawYardController getDyControl() {
		return dyControl;
	}


	public void setDyControl(DrawYardController dyControl) {
		this.dyControl = dyControl;
	}


	public ConditionsController getCondControl() {
		return condControl;
	}


	public void setCondControl(ConditionsController condControl) {
		this.condControl = condControl;
	}


	public StatisticsController getStatControl() {
		return statControl;
	}


	public void setStatControl(StatisticsController statControl) {
		this.statControl = statControl;
	}


	public GardenController getGardenControl() {
		return gardenControl;
	}


	public void setGardenControl(GardenController gardenControl) {
		this.gardenControl = gardenControl;
	}


	public SaveController getSaveControl() {
		return saveControl;
	}


	public void setSaveControl(SaveController saveControl) {
		this.saveControl = saveControl;
	}


	public PreferencesController getPrefControl() {
		return prefControl;
	}


	public void setPrefControl(PreferencesController prefControl) {
		this.prefControl = prefControl;
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
