package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import Controllers.Controller;
import Controllers.DrawYardController;
import Controllers.GardenController;
import Controllers.PreferencesController;
import Controllers.SaveController;
import Controllers.StartupController;
import Controllers.StatisticsController;
import Controllers.TutorialController;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * This class stores the data for this software, as well as some methods that
 * define logic.
 * 
 * @author IanMcCabe
 * @author Tommy White
 *
 */

public class Model {
	private Season season;
	private StageName stageName = StageName.WELCOME;

	private DrawMode drawMode;
	private double drawPressX;
	private double drawPressY;
	private Node currDrawObj;

	// make a constructor to do this
	private HashMap<String, Plant> plants = new HashMap<String, Plant>();
	private ArrayList<GardenPref> gardenPreferences = new ArrayList<GardenPref>();
	private ArrayList<Plant> suggestedPlants = new ArrayList<Plant>();

	private HashMap<Integer, ArrayList<Plant>> plantsFromPref = new HashMap<Integer, ArrayList<Plant>>();

	private GardenPref currPref;
	private ArrayList<Actions> undoActions;
	private ArrayList<Actions> redoActions;
	private int year;
	private int prefCategoriesCnt = 4;

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

	private char nameInd = 0;
	private char commonNamesInd = 1;
	private char durationInd = 2;
	private char typeInd = 3;
	private char heightInd = 5;
	private char colorInd = 6;
	private char bloomtimeInd = 7;
	private char waterInd = 8;
	private char lightInd = 9;
	private char spreadInd = 10;
	
	private char minHeightInd = 0;
	private char maxHeightInd = 1;
	
	private char minSpreadInd = 0;
	private char maxSpreadInd = 1;
	private char failedSpreadNum = 0;

	private int inchesPerFoot = 12;
	private int heightArrLen = 2;
	private int spreadArrLen = 2;

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
	 * 
	 * @param j the garden object being copied
	 * @param i the integer key value of the garden object
	 */
	public void copy(int i) {

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
	 * Calculate the x position of where the user is dropping the image from the
	 * scroll pane
	 * 
	 * @param x    x coordinates of the users mouse
	 * @param size the size of the image being dropped
	 * @param left the size of the ScrollPane of images
	 * @return ret
	 */
	public double calcX(double x, int size, double left, double initX) {
		double rightBorder = canvasWidth - size - left;

		double ret = (x + initX) - size / 2;

		if (ret < 0) {
			System.out.println("Returning 0");
			return 0;
		}
		if (ret > rightBorder) {
			System.out.println("Returning rightBorder");
			return rightBorder;
		}

		return ret;
	}

	/**
	 * Calculate the y position of where the user is dropping the image from the
	 * scroll pane
	 * 
	 * @param y      y coordinates of the users mouse
	 * @param size   the size of the image being dropped
	 * @param bottom the size of the bottom border of the BorderPane
	 * @return ret
	 */
	public double calcY(double y, int size, double bottom, double initY) {
		double bottomBorder = canvasHeight - size - bottom * 1.5;
		double ret = (y + initY) - size / 2;
		if (ret < 0) {
			return 0;
		} else if (ret > bottomBorder) {
			return bottomBorder;
		}
		return ret;
	}

	/**
	 * Reads the plantInfo.csv file and adds a Plant object to the plants HashMap
	 * for every plant in the csv.
	 */
	public void importPlantsFromCSV() {
		String csvFile = "plantInfo.csv";
		String line = "";
		List<String> parsedLine;

		String name;
		String[] commonNames;
		String duration;
		String typeStr;
		PlantType type;
		String[] heightStr = new String[heightArrLen];
		int[] height = new int[heightArrLen];
		String[] color;
		HashSet<String> colors = new HashSet<String>();
		String[] bloomtimeStr;
		HashSet<Season> bloomSet = new HashSet<Season>();
		Season[] bloomtime;
		String[] waterStr;
		HashSet<Water> waterSet = new HashSet<Water>();
		Water[] waterLevel;
		String[] lightStr;
		HashSet<Sun> lightSet = new HashSet<Sun>();
		Sun[] light;
		String[] spreadStr = new String[spreadArrLen];
		int[] spread = new int[spreadArrLen];

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			line = br.readLine(); // ignore header
			while ((line = br.readLine()) != null) {

				parsedLine = parseLine(line);
//
//				System.out.println("Science Name: " + parsedLine.get(0) + ", common names: " + parsedLine.get(1)
//						+ ", duration: " + parsedLine.get(2) + ", type: " + parsedLine.get(3) + ", fruit: "
//						+ parsedLine.get(4) + ", size: " + parsedLine.get(5) + ", color: " + parsedLine.get(6)
//						+ ", time: " + parsedLine.get(7) + ", water: " + parsedLine.get(8) + ", light: "
//						+ parsedLine.get(9) + ", spread: " + parsedLine.get(10));

				name = parsedLine.get(nameInd);
				commonNames = parsedLine.get(commonNamesInd).split(",");
				duration = parsedLine.get(durationInd);
				typeStr = parsedLine.get(typeInd).replaceAll(" ", "");
				if (typeStr.contentEquals("Herb")) {
					type = PlantType.HERB;
				} else if (typeStr.contentEquals("Shrub")) {
					type = PlantType.SHRUB;
				} else if (typeStr.contentEquals("Tree")) {
					type = PlantType.TREE;
				} else {
					type = PlantType.VINE;
				}
				heightStr = parsedLine.get(heightInd).replaceAll(" ", "").split("-");
				if (heightStr.length == 1) {
					height[minHeightInd] = height[maxHeightInd] = Integer.valueOf(heightStr[minHeightInd].replaceAll("[^0-9]", "")) * inchesPerFoot;
				} else {
					height[minHeightInd] = Integer.valueOf(heightStr[minHeightInd]);
					height[maxHeightInd] = Integer.valueOf(heightStr[maxHeightInd].replaceAll("[^0-9]", ""));
				}
				if (parsedLine.get(heightInd).contains("ft")) {
					height[minHeightInd] *= inchesPerFoot;
					height[maxHeightInd] *= inchesPerFoot;
				}
				color = parsedLine.get(colorInd).replaceAll(" ", "").split(",");

				for (String col : color) {
					colors.add(col);
				}

				bloomtimeStr = parsedLine.get(bloomtimeInd).replaceAll(" ", "")
						.replaceAll("Dec|Jan|Feb", String.format("%d", Season.WINTER.ordinal()))
						.replaceAll("Mar|Apr|May", String.format("%d", Season.SPRING.ordinal()))
						.replaceAll("Jun|Jul|Aug|Sep", String.format("%d", Season.SUMMER.ordinal()))
						.replaceAll("Oct|Nov", String.format("%d", Season.FALL.ordinal())).split(",");

				for (String num : bloomtimeStr) {

					bloomSet.add(Season.values()[Integer.valueOf(num)]);
				}

				bloomtime = bloomSet.toArray(new Season[0]);

				waterStr = parsedLine.get(waterInd).replaceAll(" ", "")
						.replace("WetMesic", String.format("%d", Water.WETMES.ordinal()))
						.replace("DryMesic", String.format("%d", Water.DRYMES.ordinal()))
						.replace("Wet", String.format("%d", Water.WET.ordinal()))
						.replace("Mesic", String.format("%d", Water.MESIC.ordinal()))
						.replace("Dry", String.format("%d", Water.DRY.ordinal())).split(",");
				for (String num : waterStr) {
					waterSet.add(Water.values()[Integer.valueOf(num)]);
				}
				waterLevel = waterSet.toArray(new Water[0]);
				lightStr = parsedLine.get(lightInd).replaceAll(" ", "")
						.replace("FullSuntoPartialShade", String.format("%d", Sun.FULL_PARTIAL.ordinal()))
						.replace("PartialShadetoFullShade", String.format("%d", Sun.PARTIAL_NONE.ordinal()))
						.replace("FullSun", String.format("%d", Sun.FULL.ordinal()))
						.replace("PartialorDappledShade", String.format("%d", Sun.PARTIAL.ordinal()))
						.replace("FullShade", String.format("%d", Sun.NONE.ordinal())).split(",");
				for (String num : lightStr) {
					lightSet.add(Sun.values()[Integer.valueOf(num)]);
				}
				light = lightSet.toArray(new Sun[0]);
				if (parsedLine.get(spreadInd).equals("fail")) {
					spread[minSpreadInd] = failedSpreadNum;
					spread[maxSpreadInd] = failedSpreadNum;
				} else {
					spreadStr = parsedLine.get(spreadInd).replaceAll(" ", "").split("-");
					if (spreadStr.length == 1) {
						spread[minSpreadInd] = spread[maxSpreadInd] = Integer.valueOf(spreadStr[minSpreadInd].replaceAll("[^0-9]", "")) * inchesPerFoot;
					} else {
						spread[minSpreadInd] = Integer.valueOf(spreadStr[minSpreadInd]);
						spread[maxSpreadInd] = Integer.valueOf(spreadStr[maxSpreadInd].replaceAll("[^0-9]", ""));
					}
					if (parsedLine.get(spreadInd).contains("feet")) {
						spread[minSpreadInd] *= inchesPerFoot;
						spread[maxSpreadInd] *= inchesPerFoot;
					}
					System.out.println(spread[minSpreadInd]);
					System.out.println(spread[maxSpreadInd]);
				}

				plants.put(name,
						new Plant(name, commonNames, duration, type, height.clone(), (HashSet<String>) colors.clone(),
								bloomtime.clone(), waterLevel.clone(), light.clone(), spread.clone()));

				bloomSet.clear();
				waterSet.clear();
				lightSet.clear();
				colors.clear();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		createSuggestions(true);
	}

	/**
	 * Reads csv lines in a custom way that allows for commas inside of a value
	 * 
	 * @param csvLine The line to be parsed
	 * @return A List of Strings containing all the values of the line
	 */
	public static List<String> parseLine(String csvLine) {

		List<String> result = new ArrayList<>();

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
					// ignore LF characters
					continue;
				} else if (ch == '\n') {
					// the end, break!
					break;
				} else {
					curVal.append(ch);
				}
			}

		}

		result.add(curVal.toString());

		return result;
	}

	/**
	 * Takes a boolean. If startup is true, suggestedPlants if filled with plants in
	 * a default order. If startup is false, call generateRelevantPlants() to use
	 * user preferences to order plants most in common.
	 * 
	 * @param startup
	 */
	public void createSuggestions(boolean startup) {
		if (startup) {
			Iterator<Entry<String, Plant>> it = plants.entrySet().iterator();
			while (it.hasNext()) {

				// System.out.println(it.next().getValue().getName());
				suggestedPlants.add(it.next().getValue());

			}
		} else {
			generateRelevantPlants();
		}

	}

	public ArrayList<Plant> getSuggestedPlants() {
		return suggestedPlants;
	}

	/**
	 * Checks each plant against every garden preference. The more attributes in
	 * common the lower the score. Plant is then placed in an ArrayList of plants
	 * with the same number of attributes in common. The ArrayList are then appended
	 * together in the order of most relevant to least.
	 */
	public void generateRelevantPlants() {
		plantsFromPref.clear();
		int score = prefCategoriesCnt;
		int minGardenPrefScore = score;
		int cnt = 1;
		int plantNum = 1;
		for (int i = 0; i <= score; i++) {
			plantsFromPref.put(i, new ArrayList<Plant>());
		}

		for (Plant p : plants.values()) {

			Object[][] plantData = { p.getBloomtime(), p.getLight(), p.getWaterLevel() };

			for (GardenPref gp : gardenPreferences) {
				String[] userData = { gp.getUserBloom(), gp.getUserLight(), gp.getUserWater() };
				for (int i = 0; i < plantData.length; i++) {
					if (userCheck(plantData[i], userData[i])) {
						score--;
					}
				}
				HashSet<String> copy = new HashSet<String>(p.getColor());

				Iterator<String> it = copy.iterator();
				while (it.hasNext()) {
					if (gp.getUserColor().contains(it.next())) {

						score--;
						break;
					}
				}

				if (minGardenPrefScore > score) {
					minGardenPrefScore = score;
				}

				cnt++;
				score = prefCategoriesCnt;
			}

			plantsFromPref.get(minGardenPrefScore).add(p);
			cnt = 1;
			minGardenPrefScore = Integer.MAX_VALUE;
			plantNum++;
		}

		suggestedPlants.clear();
		for (int i = 0; i < score; i++) {

			suggestedPlants.addAll(plantsFromPref.get(i));
		}

	}

	/**
	 * Called by generateRelevantPlants. Returns true if the userPref is "Any".
	 * Otherwise searches the array to see if any toStrings equal the userPref,
	 * returns true.
	 * 
	 * @param array    Object[]
	 * @param userPref String
	 * @return true if "Any" or userPref is in array, false otherwise
	 */
	public boolean userCheck(Object[] array, String userPref) {
		if (userPref == null || userPref.equals("Any")) {
			return true;
		}
		for (Object o : array) {
			if (o.toString().equals(userPref)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Takes a grid, number of rows and columns, and a string that represents a
	 * background style. Searches for cells of the grid that have the style equal to
	 * the given string. If the cell has the same style, the plant is copied and
	 * added to a local arraylist. Then all plants that are added are removed from
	 * the suggestedPlants, than added back at the front.
	 * 
	 * @param grid Grid of ImageViews
	 * @param rows number of rows
	 * @param cols number of columns
	 * @param bg   Background Style
	 */
	public void getUserPicks(GridPane grid, int rows, int cols, String bg) {
		int index = 1;
		ArrayList<Plant> selected = new ArrayList<Plant>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				// System.out.println(index +" has style of " +
				// grid.getChildren().get(index).getStyle());
				if (grid.getChildren().get(index).getStyle().equals(bg)) {
					// System.out.println(grid.getChildren().get(index).getStyle());
					System.out.println(suggestedPlants.get((index - 1)).getName() + " selected at index " + (index));
					Plant copy = suggestedPlants.get(index - 1);
					selected.add(copy);

				}
				index++;
			}
		}
		suggestedPlants.removeAll(selected);
		suggestedPlants.addAll(0, selected);

	}

}
