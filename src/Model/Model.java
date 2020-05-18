package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;

import Controllers.Controller;
import Controllers.DrawYardController;
import Controllers.GardenController;
import Controllers.PreferencesController;
import Controllers.StartupController;
import Controllers.StatisticsController;
import Controllers.TutorialController;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

/**
 * This class stores the data for this software, as well as some methods that
 * define logic.
 * 
 * @author IanMcCabe
 * @author Tommy White
 * @author Brandon Wu
 * @author Matt Cohen
 *
 */

public class Model implements Serializable {
	private SeasonEnum season = SeasonEnum.SUMMER;
	private transient StageNameEnum stageName = StageNameEnum.WELCOME;

	private transient DrawModeEnum drawMode;
	private transient double drawPressX;
	private transient double drawPressY;
	private transient double shapeX;
	private transient double shapeY;
	private transient Node currDrawObj;

	private int propertyHeightInches = 1200;
	private int propertyWidthInches = 2400;

	private HashMap<String, Plant> plants = new HashMap<String, Plant>();
	private ArrayList<GardenPref> gardenPreferences = new ArrayList<GardenPref>();
	private ArrayList<Plant> suggestedPlants = new ArrayList<Plant>();

	private HashMap<Integer, ArrayList<Plant>> plantsFromPref = new HashMap<Integer, ArrayList<Plant>>();

	private GardenPref currPref;
	private int year;
	private int prefCategoriesCnt = 4;

	private transient StartupController startControl;
	private transient TutorialController tutControl;
	private transient DrawYardController dyControl;
	private transient StatisticsController statControl;
	private transient GardenController gardenControl;
	private transient PreferencesController prefControl;

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

	private int numTrees = 0;
	private int numShrubs = 0;
	private int numHerbs = 0;

	private int numVine = 0;
	private double gardenCovered = 0;

	private int uniqueTrees = 0;
	private int uniqueShrubs = 0;
	private int uniqueHerbs = 0;
	private int uniqueVines = 0;

	private HashSet<String> allColors = new HashSet<String>();
	private HashSet<SeasonEnum> allSeasons = new HashSet<SeasonEnum>();
	private HashSet<String> allNames = new HashSet<String>();
	private HashSet<String> uniquePlant = new HashSet<String>();

	private final double rectMinX = 0;
	private final double rectMinY = 0;

	private ArrayList<GardenObj> gardenObjs = new ArrayList<GardenObj>();
	private ArrayList<RectDrawingObj> rectangles = new ArrayList<RectDrawingObj>();
	private ArrayList<LabelDrawingObj> labels = new ArrayList<LabelDrawingObj>();
	private ArrayList<EllipseDrawingObj> ellipses = new ArrayList<EllipseDrawingObj>();
	private String backgroundPath;

	private int decToPercent = 100;
	private int radiusExp = 2;

	private double widthOnSave;
	private double heightOnSave;

	public SeasonEnum getSeason() {
		return season;
	}

	public void setSeason(SeasonEnum season) {
		this.season = season;
	}

	public StageNameEnum getStageName() {
		return stageName;
	}

	public void setStageName(StageNameEnum stageName) {
		this.stageName = stageName;
	}

	public ArrayList<GardenPref> getGardenPreferences() {
		return gardenPreferences;
	}

	public void setGardenPreferences(ArrayList<GardenPref> gardenPreferences) {
		this.gardenPreferences = gardenPreferences;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public DrawModeEnum getDrawMode() {
		return drawMode;
	}

	public void setDrawMode(DrawModeEnum drawMode) {
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

	public PreferencesController getPrefControl() {
		return prefControl;
	}

	public void setPrefControl(PreferencesController prefControl) {
		this.prefControl = prefControl;
	}

	public int getPropertyHeightInches() {
		return propertyHeightInches;
	}

	public void setPropertyHeightInches(int propertyHeightInches) {
		this.propertyHeightInches = propertyHeightInches;
	}

	public int getPropertyWidthInches() {
		return propertyWidthInches;
	}

	public void setPropertyWidthInches(int propertyWidthInches) {
		this.propertyWidthInches = propertyWidthInches;
	}

	public int getInchesPerFoot() {
		return inchesPerFoot;
	}

	public int getNumTrees() {
		return numTrees;
	}

	public void setNumTrees(int numTrees) {
		this.numTrees = numTrees;
	}

	public int getNumShrubs() {
		return numShrubs;
	}

	public void setNumShrubs(int numShrubs) {
		this.numShrubs = numShrubs;
	}

	public int getNumHerbs() {
		return numHerbs;
	}

	public void setNumHerbs(int numHerbs) {
		this.numHerbs = numHerbs;
	}

	public HashSet<String> getAllColors() {
		return allColors;
	}

	public HashSet<SeasonEnum> getAllSeasons() {
		return allSeasons;
	}

	public HashSet<String> getAllNames() {
		return allNames;
	}

	public int getUniqueShrubs() {
		return uniqueShrubs;
	}

	public int getUniqueHerbs() {
		return uniqueHerbs;
	}

	public int getUniqueVines() {
		return uniqueVines;
	}

	public ArrayList<GardenObj> getGardenObjs() {
		return gardenObjs;
	}

	public ArrayList<RectDrawingObj> getRectangles() {
		return rectangles;
	}

	public ArrayList<LabelDrawingObj> getLabels() {
		return labels;
	}

	public ArrayList<EllipseDrawingObj> getEllipses() {
		return ellipses;
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}

	public double getShapeX() {
		return shapeX;
	}

	public void setShapeX(double shapeX) {
		this.shapeX = shapeX;
	}

	public double getShapeY() {
		return shapeY;
	}

	public void setShapeY(double shapeY) {
		this.shapeY = shapeY;
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

	public double getGardenCoveredPercent() {

		return gardenCovered;
	}

	public int getNumVines() {

		return numVine;
	}

	public int getUniqueTrees() {
		return uniqueTrees;
	}

	public double getWidthOnSave() {
		return widthOnSave;
	}

	public void setWidthOnSave(double widthOnSave) {
		this.widthOnSave = widthOnSave;
	}

	public double getHeightOnSave() {
		return heightOnSave;
	}

	public void setHeightOnSave(double heightOnSave) {
		this.heightOnSave = heightOnSave;
	}

	public ArrayList<Plant> getSuggestedPlants() {
		return suggestedPlants;
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
	public double calcX(double x, double size, double left, double canvasWidth) {
		double rightBorder = canvasWidth - size - left;
		double leftBorder = size;
		double ret = x;

		if (ret < leftBorder) {
			return leftBorder;
		}
		if (ret > rightBorder) {
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
	public double calcY(double y, double size, double bottom, double canvasHeight) {

		double bottomBorder = canvasHeight - size - bottom * 2;
		double topBorder = size;
		double ret = y;
		if (ret < topBorder) {
			return topBorder;
		} else if (ret > bottomBorder) {
			return bottomBorder;
		}
		return ret;
	}

	/**
	 * Reads the plantInfo.csv file and adds a Plant object to the plants HashMap
	 * for every plant in the csv.
	 */
	public void importPlantsFromCSV(String csvFile) {
		String line = "";
		List<String> parsedLine;

		String name;
		String[] commonNames;
		String duration;
		String typeStr;
		PlantTypeEnum type;
		String[] heightStr = new String[heightArrLen];
		int[] height = new int[heightArrLen];
		String[] color;
		String[] bloomtimeStr;
		LinkedHashSet<SeasonEnum> bloomSet = new LinkedHashSet<SeasonEnum>();
		SeasonEnum[] bloomtime;
		String[] waterStr;
		LinkedHashSet<WaterEnum> waterSet = new LinkedHashSet<WaterEnum>();
		WaterEnum[] waterLevel;
		String[] lightStr;
		LinkedHashSet<SunEnum> lightSet = new LinkedHashSet<SunEnum>();
		SunEnum[] light;
		String[] spreadStr = new String[spreadArrLen];
		int[] spread = new int[spreadArrLen];

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			line = br.readLine(); // ignore header
			while ((line = br.readLine()) != null) {

				parsedLine = parseLine(line);

				name = parsedLine.get(nameInd);
				commonNames = parsedLine.get(commonNamesInd).split(",");
				for (int i = 0; i < commonNames.length; i++) {
					commonNames[i] = commonNames[i].trim();
				}
				duration = parsedLine.get(durationInd);
				typeStr = parsedLine.get(typeInd).trim();
				if (typeStr.equals("Herb")) {
					type = PlantTypeEnum.HERB;
				} else if (typeStr.equals("Shrub")) {
					type = PlantTypeEnum.SHRUB;
				} else if (typeStr.equals("Tree")) {
					type = PlantTypeEnum.TREE;
				} else {
					type = PlantTypeEnum.VINE;
				}
				heightStr = parsedLine.get(heightInd).replaceAll(" ", "").split("-");
				if (heightStr.length == 1) {
					height[minHeightInd] = height[maxHeightInd] = Integer
							.valueOf(heightStr[minHeightInd].replaceAll("[^0-9]", ""));
				} else {
					height[minHeightInd] = Integer.valueOf(heightStr[minHeightInd]);
					height[maxHeightInd] = Integer.valueOf(heightStr[maxHeightInd].replaceAll("[^0-9]", ""));
				}
				if (parsedLine.get(heightInd).contains("ft")) {
					height[minHeightInd] *= inchesPerFoot;
					height[maxHeightInd] *= inchesPerFoot;
				}
				color = parsedLine.get(colorInd).replaceAll(" ", "").split(",");

				bloomtimeStr = parsedLine.get(bloomtimeInd).replaceAll(" ", "")
						.replaceAll("Dec|Jan|Feb", String.format("%d", SeasonEnum.WINTER.ordinal()))
						.replaceAll("Mar|Apr|May", String.format("%d", SeasonEnum.SPRING.ordinal()))
						.replaceAll("Jun|Jul|Aug|Sep", String.format("%d", SeasonEnum.SUMMER.ordinal()))
						.replaceAll("Oct|Nov", String.format("%d", SeasonEnum.FALL.ordinal())).split(",");

				for (String num : bloomtimeStr) {

					bloomSet.add(SeasonEnum.values()[Integer.valueOf(num)]);
				}

				bloomtime = bloomSet.toArray(new SeasonEnum[0]);

				waterStr = parsedLine.get(waterInd).replaceAll(" ", "")
						.replace("WetMesic", String.format("%d", WaterEnum.WETMES.ordinal()))
						.replace("DryMesic", String.format("%d", WaterEnum.DRYMES.ordinal()))
						.replace("Wet", String.format("%d", WaterEnum.WET.ordinal()))
						.replace("Mesic", String.format("%d", WaterEnum.MESIC.ordinal()))
						.replace("Dry", String.format("%d", WaterEnum.DRY.ordinal())).split(",");
				for (String num : waterStr) {
					waterSet.add(WaterEnum.values()[Integer.valueOf(num)]);
				}
				waterLevel = waterSet.toArray(new WaterEnum[0]);
				lightStr = parsedLine.get(lightInd).replaceAll(" ", "")
						.replace("FullSuntoPartialShade", String.format("%d", SunEnum.FULL_PARTIAL.ordinal()))
						.replace("PartialShadetoFullShade", String.format("%d", SunEnum.PARTIAL_NONE.ordinal()))
						.replace("FullSun", String.format("%d", SunEnum.FULL.ordinal()))
						.replace("PartialorDappledShade", String.format("%d", SunEnum.PARTIAL.ordinal()))
						.replace("FullShade", String.format("%d", SunEnum.NONE.ordinal())).split(",");
				for (String num : lightStr) {
					lightSet.add(SunEnum.values()[Integer.valueOf(num)]);
				}
				light = lightSet.toArray(new SunEnum[0]);
				if (parsedLine.get(spreadInd).equals("fail")) {
					spread[minSpreadInd] = failedSpreadNum;
					spread[maxSpreadInd] = failedSpreadNum;
				} else {
					spreadStr = parsedLine.get(spreadInd).replaceAll(" ", "").split("-");
					if (spreadStr.length == 1) {
						spread[minSpreadInd] = spread[maxSpreadInd] = Integer
								.valueOf(spreadStr[minSpreadInd].replaceAll("[^0-9]", ""));
					} else {
						spread[minSpreadInd] = Integer.valueOf(spreadStr[minSpreadInd]);
						spread[maxSpreadInd] = Integer.valueOf(spreadStr[maxSpreadInd].replaceAll("[^0-9]", ""));
					}
					if (parsedLine.get(spreadInd).contains("feet")) {
						spread[minSpreadInd] *= inchesPerFoot;
						spread[maxSpreadInd] *= inchesPerFoot;
					}
				}

				plants.put(name, new Plant(name, commonNames, duration, type, height.clone(), color.clone(),
						bloomtime.clone(), waterLevel.clone(), light.clone(), spread.clone()));

				bloomSet.clear();
				waterSet.clear();
				lightSet.clear();

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
				suggestedPlants.add(it.next().getValue());
			}
		} else {
			generateRelevantPlants();
		}

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
				score = prefCategoriesCnt;
			}
			plantsFromPref.get(minGardenPrefScore).add(p);
			minGardenPrefScore = prefCategoriesCnt;

		}

		suggestedPlants.clear();

		for (int i = 0; i <= score; i++) {

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
				if (grid.getChildren().get(index).getStyle().equals(bg)) {
					Plant copy = suggestedPlants.get(index - 1);
					selected.add(copy);

				}
				index++;
			}
		}

		suggestedPlants.removeAll(selected);
		suggestedPlants.addAll(0, selected);

	}

	/**
	 * Takes in an observable list of nodes from the GardenController. Iterates
	 * through the nodes counting the total number of trees, herbs, vines, and
	 * shrubs and counting the number of unique plants of each plant type. Also uses
	 * the property height and width to calculate the coverage of the garden by the
	 * plants. Sets all attributes to their appropriate values.
	 * 
	 * @param garden
	 */
	public void generateStats(ObservableList<Node> garden) {
		numTrees = 0;
		numShrubs = 0;
		numHerbs = 0;
		numVine = 0;
		uniqueTrees = 0;
		uniqueShrubs = 0;
		uniqueHerbs = 0;
		uniqueVines = 0;
		double plantSurfaceArea = 0;
		allColors.clear();
		allSeasons.clear();
		uniquePlant.clear();
		allNames.clear();
		for (Node node : garden) {
			String plantName = (String) node.getUserData();
			if (plantName != null) {
				Plant plant = plants.get(plantName);
				switch (plant.getType()) {
				case HERB:
					numHerbs++;
					break;
				case SHRUB:
					numShrubs++;
					break;
				case TREE:
					numTrees++;
					break;
				case VINE:
					numVine++;
					break;
				}
				for (String color : plant.getColor()) {
					allColors.add(color);
				}
				for (SeasonEnum season : plant.getBloomtime()) {
					allSeasons.add(season);
				}
				if (uniquePlant.add(plantName)) {
					switch (plant.getType()) {
					case HERB:
						uniqueHerbs++;
						break;
					case SHRUB:
						uniqueShrubs++;
						break;
					case TREE:
						uniqueTrees++;
						break;
					case VINE:
						uniqueVines++;
						break;
					}
				}

				allNames.add(plantName);

				plantSurfaceArea += Math.PI * Math.pow(((Circle) node).getRadius(), radiusExp);
			}
		}

		gardenCovered = plantSurfaceArea / (propertyHeightInches * propertyWidthInches) * decToPercent;

	}

	/**
	 * Calculates the coordinates of a rectangle as it is being created.
	 * 
	 * @param x0            The x coordinate of one corner
	 * @param y0            The y coordinate of one corner
	 * @param x1            The x coordinate of the other corner
	 * @param y1            The y coordinate of the other corner
	 * @param drawingWidth  The width of the drawing in which the rectangle is
	 *                      contained
	 * @param drawingHeight The height of the drawing in which the rectangle is
	 *                      contained
	 * @return An array of doubles containing the top left x-coordinate, top left
	 *         y-coordinate, the width of the rectangle, and the height of the
	 *         rectangle
	 */
	public double[] updateRectCoordinates(double x0, double y0, double x1, double y1, double drawingWidth,
			double drawingHeight) {
		double topLeftX = Math.max(rectMinX, Math.min(x0, x1));
		double topLeftY = Math.max(rectMinY, Math.min(y0, y1));
		double width = Math.min(drawingWidth - topLeftX, Math.max(x0, x1) - topLeftX);
		double height = Math.min(drawingHeight - topLeftY, Math.max(y0, y1) - topLeftY);
		double[] coords = { topLeftX, topLeftY, width, height };
		return coords;
	}

	/**
	 * Calculates the coordinates of a rectangle or label as it is being dragged.
	 * 
	 * @param x             The x-coordinate of the mouse
	 * @param y             The y-coordinate of the mouse
	 * @param drawingWidth  The width of the drawing in which the rectangle is
	 *                      contained
	 * @param drawingHeight The height of the drawing in which the rectangle is
	 *                      contained
	 * @param rectWidth     The width of the rectangle
	 * @param rectHeight    The width of the rectangle
	 * @return An array of doubles containing the top left x-coordinate and top left
	 *         y-coordinate
	 */
	public double[] moveRectCoordinates(double x, double y, double rectWidth, double rectHeight, double drawingWidth,
			double drawingHeight) {
		double newX = shapeX + (x - drawPressX);
		double newY = shapeY + (y - drawPressY);
		newX = Math.max(rectMinX, Math.min(drawingWidth - rectWidth, newX));
		newY = Math.max(rectMinY, Math.min(drawingHeight - rectHeight, newY));
		double[] coords = { newX, newY };
		return coords;
	}

	/**
	 * Calculates the radii of an ellipse as it is being created.
	 * 
	 * @param x             The x-coordinate of the mouse
	 * @param y             The y-coordinate of the mouse
	 * @param centerX       The x-coordinate of the center of the ellipse
	 * @param centerY       The y-coordinate of the center of the ellipse
	 * @param drawingWidth  The width of the drawing in which the ellipse is
	 *                      contained
	 * @param drawingHeight The height of the drawing in which the ellipse is
	 *                      contained
	 * @return An array of doubles containing the radiusX and radiusY
	 */
	public double[] updateEllipseCoordinates(double x, double y, double centerX, double centerY, double drawingWidth,
			double drawingHeight) {
		double maxRadiusX = Math.min(centerX, drawingWidth - centerX);
		double maxRadiusY = Math.min(centerY, drawingHeight - centerY);
		double radiusX = Math.min(maxRadiusX, Math.abs(centerX - x));
		double radiusY = Math.min(maxRadiusY, Math.abs(centerY - y));
		double[] radii = { radiusX, radiusY };
		return radii;
	}

	/**
	 * 
	 * @param x             The x-coordinate of the mouse
	 * @param y             The y-coordinate of the mouse
	 * @param radiusX       The radiusX of the ellipse
	 * @param radiusY       The radiusY of the ellipse
	 * @param drawingWidth  The width of the drawing in which the ellipse is
	 *                      contained
	 * @param drawingHeight The height of the drawing in which the ellipse is
	 *                      contained
	 * @return An array of doubles containing the center x-coordinate and center
	 *         y-coordinate
	 */
	public double[] moveEllipseCoordinates(double x, double y, double radiusX, double radiusY, double drawingWidth,
			double drawingHeight) {
		double newCenterX = x;
		double newCenterY = y;
		newCenterX = Math.max(radiusX, Math.min(drawingWidth - radiusX, newCenterX));
		newCenterY = Math.max(radiusY, Math.min(drawingHeight - radiusY, newCenterY));
		double[] centers = { newCenterX, newCenterY };
		return centers;
	}

	/**
	 * Checks if a plant matches the preferences of the area it has been placed in
	 * 
	 * @param plantName The name of the plant
	 * @param x         The x-coordinate of the mouse
	 * @param y         The y-coordinate of the mouse
	 * @return A string that describes how well the plant matches
	 */
	public String isPlantMatch(String plantName, double x, double y, Pane garden) {
		for (GardenPref gp : gardenPreferences) {
			if (gp.getArea() != null) {
				if (isInArea(x, y, gp.getArea(), garden)) {
					String match = "";
					Plant p = plants.get(plantName);

					if (gp.getUserLight() != null) {
						if (userCheck(p.getLight(), gp.getUserLight())) {
							match = "Plant matches light requirement.";
						} else {
							match = "Plant does not match light requirement.";
						}
					} else {
						match += "No light level preference in this area.";
					}

					if (gp.getUserWater() != null) {
						if (userCheck(p.getWaterLevel(), gp.getUserWater())) {
							match += "\nPlant matches soil moisture.";
						} else {
							match += "\nPlant does not match soil moisture.";
						}
					} else {
						match += "\nNo soil moisture preference in this area.";
					}

					if (gp.getUserBloom() != null) {
						if (userCheck(p.getBloomtime(), gp.getUserBloom())) {
							match += "\nPlant blooms in desired season.";
						} else {
							match += "\nPlant does not bloom in desired season.";
						}
					} else {
						match += "\nNo season preference in this area.";
					}

					HashSet<String> copy = new HashSet<String>(p.getColor());
					Iterator<String> it = copy.iterator();

					if (gp.getUserColor() != null)
						while (it.hasNext()) {
							if (gp.getUserColor().contains(it.next())) {
								match += "\nPlant matches desired color.";
								break;
							}
						}
					return match;
				}
			}
		}
		return "No preferences in this area.";
	}

	/**
	 * Determines if the given x and y coordinates are inside the given rectangle
	 * 
	 * @param x    The x-coordinate of the mouse
	 * @param y    The y-coordinate of the mouse
	 * @param area The rectangle to check if the mouse is inside
	 * @return true if the mouse is in the rectangle, false otherwise
	 */
	public boolean isInArea(double x, double y, Rectangle area, Pane garden) {
		Bounds bounds = garden.sceneToLocal(area.localToScene(area.getBoundsInLocal()));
		boolean inX = x <= bounds.getMaxX() && x >= bounds.getMinX();
		boolean inY = y <= bounds.getMaxY() && y >= bounds.getMinY();
		return inX && inY;
	}
}
