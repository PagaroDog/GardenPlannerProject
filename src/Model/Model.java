package Model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

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
	private HashMap<String, Plant> plants=new HashMap<String, Plant>();
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
    
    public Model() {
    	importPlantsFromCSV();
    }
    
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


	public HashMap<String, Plant> getPlants() {
		return plants;
	}


	public void setPlants(HashMap<String, Plant> plants) {
		this.plants = plants;
	}
	public void addPlant(String name, Plant p) {
		plants.put(name, p);
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

    public void importPlantsFromCSV() {
    	String csvFile = "plantInfo.csv";
        String line = "";
        List<String> parsedLine;
        
        String name;
    	String[] commonNames;
    	String duration;
    	String type;
    	String[] heightStr = new String[2];
    	int[] height = new int[2];
    	String[] color;
    	String[] bloomtimeStr;
    	int[] bloomtime;
    	String waterLevel;
    	String light;
    	String[] spreadStr = new String[2];
    	int[] spread = new int[2];

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	line = br.readLine(); //ignore header
            while ((line = br.readLine()) != null) {
            	
            	parsedLine = parseLine(line);

            	name = parsedLine.get(0);
            	commonNames = parsedLine.get(1).split(",");
            	duration = parsedLine.get(2);
            	type = parsedLine.get(3);
        		heightStr = parsedLine.get(5).replaceAll(" ", "").split("-");
        		if (heightStr.length == 1) {
        			height[0] = height[1] = Integer.valueOf(heightStr[0].replaceAll("[^0-9]", "")) * 12;
        		} else {
        			height[0] = Integer.valueOf(heightStr[0]);
            		height[1] = Integer.valueOf(heightStr[1].replaceAll("[^0-9]", ""));
        		}
        		if (parsedLine.get(5).contains("ft")) {
	        		height[0] *= 12;
	        		height[1] *= 12;
            	}
            	color = parsedLine.get(6).replaceAll(" ", "").split(",");
            	bloomtimeStr = parsedLine.get(7).replaceAll(" ", "").replace("Jan", "1").replace("Feb", "2").replace("Mar", "3").replace("Apr", "4").replace("May", "5").replace("Jun", "6").replace("Jul", "7").replace("Aug", "8").replace("Sep", "9").replace("Oct", "10").replace("Nov", "11").replace("Dec", "12").split(",");
            	bloomtime = new int[bloomtimeStr.length];
            	for (int i = 0; i < bloomtime.length; i++) {
            		bloomtime[i] = Integer.valueOf(bloomtimeStr[i]);
            	}
            	waterLevel = parsedLine.get(8);
            	light = parsedLine.get(9);
            	if (parsedLine.get(10).equals("fail")) {
            		spread[0] = 0;
        			spread[1] = 0;
            	} else {
            		spreadStr = parsedLine.get(10).replaceAll(" ", "").split("-");
	            	if (spreadStr.length == 1) {
	            		spread[0] = height[1] = Integer.valueOf(spreadStr[0].replaceAll("[^0-9]", "")) * 12;
	        		} else {
	        			spread[0] = Integer.valueOf(spreadStr[0]);
	        			spread[1] = Integer.valueOf(spreadStr[1].replaceAll("[^0-9]", ""));
	        		}
	        		if (parsedLine.get(10).contains("feet")) {
	        			spread[0] *= 12;
	        			spread[1] *= 12;
	            	}
            	}
            	
            	
                System.out.println("Science Name: " + parsedLine.get(0) + ", common names: " + parsedLine.get(1) + ", duration: " + parsedLine.get(2) + ", type: " + parsedLine.get(3) + ", fruit: " + parsedLine.get(4) + ", size: " + parsedLine.get(5) + ", color: " + parsedLine.get(6) + ", time: " + parsedLine.get(7) + ", water: " + parsedLine.get(8) + ", light: " + parsedLine.get(9) + ", spread: " + parsedLine.get(10));

                plants.put(parsedLine.get(0), new Plant(name, commonNames, duration, type, height, color, bloomtime, waterLevel, light, spread));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public static List<String> parseLine(String csvLine) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (csvLine == null && csvLine.isEmpty()) {
            return result;
        }
        
        char customQuote = '"';

        char separators = ',';

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;

        char[] chars = csvLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                if (ch == customQuote) {
                    inQuotes = false;
                } else {
                    curVal.append(ch);
                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }

}
