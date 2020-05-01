package Model;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import java.util.HashMap;

import Controllers.Controller;
import Controllers.DrawYardController;
import Controllers.GardenController;
import Controllers.PreferencesController;
import Controllers.SaveController;
import Controllers.StartupController;
import Controllers.StatisticsController;
import Controllers.TutorialController;
import javafx.scene.Node;

/**
 * 
 * @author IanMcCabe
 *
 */

public class Model {
    private Season season;
    private StageName stageName = StageName.WELCOME;

	private DrawMode drawMode;
	private double drawPressX;
	private double drawPressY;
	private Node currDrawObj;
	
	//make a constructor to do this
	private ArrayList<Plant> plants=new ArrayList<Plant>();
    private ArrayList<GardenPref> gardenPreferences = new ArrayList<GardenPref>(); 
    private GardenPref currPref;
    private HashMap<Integer, GardenObj> gardenObjects;  
    private ArrayList<Actions> undoActions;
    private ArrayList<Actions> redoActions;
    private int year; 
 
    
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int canvasWidth = gd.getDisplayMode().getWidth() - 150;
	int canvasHeight = gd.getDisplayMode().getHeight() - 150;
    
    private StartupController startControl;
	private TutorialController tutControl;
	private DrawYardController dyControl;
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


	public ArrayList<GardenPref> getGardenPreferences() {
		return gardenPreferences;
	}


	public void setGardenPreferences(ArrayList<GardenPref> gardenPreferences) {
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


	public void setCurrDrawObj(Node node) {
		currDrawObj = node;
	}
	
	public Node getCurrDrawObj() {
		return currDrawObj;
	}


	public ArrayList<Plant> getPlants() {
		return plants;
	}


	public void setPlants(ArrayList<Plant> plants) {
		this.plants = plants;
	}
	public void addPlant(Plant p) {
		plants.add(p);
	}


	public void setCurrPref(GardenPref gardenPref) {
		currPref = gardenPref;
	}

	public GardenPref getCurrPref() {
		return currPref;
	}
	/**
	 * Calculate the x position of where the user is dropping the image from the scroll pane
	 * @param x x coordinates of the users mouse
	 * @param size the size of the image being dropped 
	 * @param left the size of the ScrollPane of images
	 * @return ret 
	 */
	public double calcX(double x,int size,double left,double initX) {
		double rightBorder = canvasWidth-size-left;

		double ret = (x+initX)-size/2;

		if(ret<0) {
			System.out.println("Returning 0");
			return 0;
		}
		if(ret>rightBorder) {
			System.out.println("Returning rightBorder");
			return rightBorder;
		}
		
		return ret;
	}
	/**
	 * Calculate the y position of where the user is dropping the image from the scroll pane
	 * @param y y coordinates of the users mouse
	 * @param size the size of the image being dropped
	 * @param bottom the size of the bottom border of the BorderPane
	 * @return ret
	 */
	public double calcY(double y, int size,double bottom,double initY) {
		double bottomBorder = canvasHeight-size-bottom*1.5;
		double ret = (y+initY)-size/2;
		if(ret<0) {
			return 0;
		}
		else if(ret>bottomBorder) {
			return bottomBorder;
		}
		return ret;
	}

    
   
	
	
}
