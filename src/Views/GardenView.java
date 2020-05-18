package Views;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Controllers.GardenController;
import Model.EllipseDrawingObj;
import Model.GardenAction;
import Model.LabelDrawingObj;
import Model.PlantTypeEnum;
import Model.RectDrawingObj;
import Model.SeasonEnum;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The view class for the Garden Design screen. Holds graphical data and defines
 * graphical logic.
 * 
 * @author Matt Cohen
 * @author IanMcCabe
 */
public class GardenView extends View<GardenController> implements Serializable {
	private HashMap<String, Image> plantImages = new HashMap<String, Image>();
	private ArrayList<Image> plants = new ArrayList<Image>();

	private HBox toolbar;
	private Pane garden;
	private Button save;
	private Button year1;
	private Button year2;
	private Button year3;
	private Button spring;
	private Button summer;
	private Button fall;
	private Button winter;
	private Button delete;
	private Pane drawing;
	private Button copy;

	private Button undo;
	private Button redo;
	private VBox info;

	private ScrollPane scrollPane;
	private BorderPane border;
	private BorderPane navigation;
	private TabPane tabPane;
	private int SIZE = 200;
	private transient Images imgs;

	private double buttonFontSize = Math.min(12, 15 * canvasWidth / expectedWidth); //Modified from 18
	private final double labelFontSize = Math.min(16, 18 * canvasWidth / expectedWidth); //Modified from 21
	private FileChooser fileChooser;

	private final int year1Int = 1;
	private final int year2Int = 2;
	private final int year3Int = 3;

	private final double vBoxSpacing = 10;
	private final double vBoxVPadding = 5;
	private final double vBoxHPadding = 0;

	private int overCircleFont = 20;
	private int overIVFont = 14;

	private int infoBoxRed = 218;
	private int infoBoxGreen = 230;
	private int infoBoxBlue = 243;
	private int infoBoxOpacity = 1;

	private int firstImgInd = 0;

	private String infoBoxStyle = String.format("-fx-background-color: rgba(%d, %d, %d, %d);", infoBoxRed, infoBoxGreen,
			infoBoxBlue, infoBoxOpacity);

	public GardenView(Stage stage, Images imgs) {
		this.stage = stage;
		this.imgs = imgs;
		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter gardenFilter = new FileChooser.ExtensionFilter("Garden Files (*.garden)",
				"*.garden");
		FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG Files (*.png)", "*.png");
		fileChooser.getExtensionFilters().addAll(gardenFilter, pngFilter);
	}

	/**
	 * Initial setup of this class that could not be completed in the constructor
	 * since the controller had not yet been set
	 */
	@Override
	public void setup() {

		border = new BorderPane();
		bottom();
		border.setBottom(navigation);

		top();
		border.setTop(toolbar);

		left();
		border.setLeft(tabPane);

		gard();
		border.setCenter(garden);

		scene = new Scene(border, canvasWidth, canvasHeight);
		styleScene();
	}

	/**
	 * Creates a ScrollPane of suggested plants for the left part of the BorderPane
	 */
	public void left() {
		tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		plants.clear();
		int numberPlants = control.getNumPlants();
		for (PlantTypeEnum type : PlantTypeEnum.values()) {
			Tab tab = new Tab(type.toString());
			VBox tile = new VBox(vBoxSpacing);
			tile.setAlignment(Pos.CENTER);
			tile.setPadding(new Insets(vBoxVPadding, vBoxHPadding, vBoxVPadding, vBoxHPadding));
			tile.setStyle(infoBoxStyle);
			tile.setPrefWidth(SIZE);
			for (int i = 0; i < numberPlants; i++) {

				if (control.getPlantType(control.getPlantNameAt(i)) == type) {
					Image img = imgs.getPlantImages().get(control.getPlantNameAt(i))[firstImgInd].getImg();
					ImageView imageview = new ImageView(img);
					imageview.setPreserveRatio(true);
					imageview.setFitHeight(SIZE);
					imageview.setFitWidth(SIZE);
					imageview.setOnDragDetected(control.getHandlerForDragDetected());
					imageview.setOnMouseEntered(control.handleOnMouseEnteredImage());
					imageview.setOnMouseExited(control.handleOnMouseExited());
					imageview.setOnMouseDragged(control.handleOnMouseExited());
					imageview.setUserData(control.getPlantNameAt(i));
					tile.getChildren().add(imageview);
				}

			}
			scrollPane = new ScrollPane();
			scrollPane.setFitToWidth(true);
			scrollPane.setContent(tile);
			tab.setContent(scrollPane);
			tabPane.getTabs().add(tab);
		}
	}

	/**
	 * Creates the garden pane. Placed in the center of the BorderPane
	 */
	public void gard() {
		garden = new Pane();
		garden.setOnDragOver(control.getHandlerForDragOver());
		garden.setOnDragDropped(control.getHandlerForDragDropped());
	}

	/**
	 * Creates the top TilePane. Contains navigation buttons to stats, save and
	 * preferences.
	 */
	public void top() {
		save = createButton("Save/Export as Image", control.handleOnSaveButton());

		Region empty1 = new Region();
		HBox.setHgrow(empty1, Priority.ALWAYS);

		Label toolsLabel = new Label("Tools");
		toolsLabel.setFont(new Font(labelFontSize));
		delete = createButton("Delete", control.handleOnDeleteButton());
		copy = createButton("Copy", control.handleOnCopyButton());
		undo = createButton("Undo", control.handleOnUndoButton());
		redo = createButton("Redo", control.handleOnRedoButton());

		Region empty2 = new Region();
		HBox.setHgrow(empty2, Priority.ALWAYS);

		Label seasonLabel = new Label("Select Season");
		seasonLabel.setFont(new Font(labelFontSize));
		summer = createButton("Summer", control.handleOnSeasonButton(SeasonEnum.SUMMER));
		fall = createButton("Fall", control.handleOnSeasonButton(SeasonEnum.FALL));
		winter = createButton("Winter", control.handleOnSeasonButton(SeasonEnum.WINTER));
		spring = createButton("Spring", control.handleOnSeasonButton(SeasonEnum.SPRING));

		Region empty3 = new Region();
		HBox.setHgrow(empty3, Priority.ALWAYS);

		Label yearLabel = new Label("Select Estimated Size");
		yearLabel.setFont(new Font(labelFontSize));
		year1 = createButton("Small", control.handleOnYearButton(year1Int));
		year2 = createButton("Medium", control.handleOnYearButton(year2Int));
		year3 = createButton("Large", control.handleOnYearButton(year3Int));

		toolbar = createToolbar();

		toolbar.getChildren().addAll(save, empty1, toolsLabel, delete, copy, undo, redo, empty2, seasonLabel, summer,
				fall, winter, spring, empty3, yearLabel, year1, year2, year3);

	}

	/**
	 * Creates a FlowPane for the bottom part of the border pane. Contains Year and
	 * Season TilePanes
	 */
	public void bottom() {
		navigation = createNavigationBar("Edit Preferences", "View Statistics", "Design Your Garden",
				control.handleOnPrefButton(), control.handleOnStatsButton());
	}

	/**
	 * go through directory and create file objects for everything in it, then load
	 * into plantImages
	 * 
	 * @param myDirectoryPath
	 */
	public void addImageFromFile(String myDirectoryPath) {
		File dir = new File(myDirectoryPath);
		File[] directoryListing = dir.listFiles();
		for (File child : directoryListing) {
			String name = child.getName();
			Image img = new Image("myDirectoryPath" + name);
			plantImages.put(name, img);
		}
	}

	/**
	 * Adds a Circle to the garden Pane when the user releases a drag over the pane.
	 * Uses the SIZE to center images around mouse.
	 * 
	 * @param plant Circle object representing plant from ScrollPane that was
	 *              dragged by user
	 * @param x     x coordinates of mouse
	 * @param y     y coordinates of mouse
	 */
	public Circle addCircleToGarden(Circle plant, double x, double y, double radius, String name, Color color) {
		plant.setUserData(name);
		plant.setRadius(radius);
		plant.setCenterX(x);
		plant.setCenterY(y);
		plant.setOnMouseDragged(control.getHandlerForDrag());
		plant.setOnMouseReleased(control.handleOnMouseReleased());
		plant.setOnMouseEntered(control.handleOnMouseEntered());
		plant.setOnMouseExited(control.handleOnMouseExited());
		Image img = imgs.getPlantImages().get(name)[firstImgInd].getImg();
		plant.setFill(new ImagePattern(img));
		plant.setStrokeType(StrokeType.INSIDE);
		plant.setStroke(color);
		PlantTypeEnum pT = control.getPlantType(name);
		plant.setStrokeWidth(pT.getStrokeSize());

		garden.getChildren().add(plant);
		return plant;
	}

	/**
	 * Called to move an already placed plant. Places the plant at the given (x,y)
	 * coordinates and changes the plants xLoc and yLoc values carried by the
	 * ImageView's User Data
	 * 
	 * @param dragPlant
	 * @param x
	 * @param y
	 */
	public void movePlant(Circle dragPlant, double x, double y) {
		dragPlant.setCenterX(x);
		dragPlant.setCenterY(y);
	}

	/**
	 * Called by GardenController. Updates plant images to reflect the change in
	 * Model's plantSuggestions ArrayList
	 */
	public void updatePlants() {
		left();
		border.setLeft(tabPane);
	}

	/*
	 * @param int year
	 * 
	 * when a year button is pressed, this updates the size of all the plants in the
	 * garden.
	 */
	public void setYear(int year) {
		List<Node> gardenList = garden.getChildren();
		for (Node plant : gardenList) {
			String plantName = (String) plant.getUserData();
			if (plantName != null) {
				double rad = control.getRadiusFromYear(plantName);
				((Circle) plant).setRadius(rad);
			}
		}

	}

	/**
	 * called by controller when season buttons are pressed. updates color of plants
	 * based on season
	 * 
	 * @param season
	 */
	public void changeSeason(SeasonEnum season) {
		List<Node> gardenList = garden.getChildren();
		for (Node plant : gardenList) {

			String plantName = (String) plant.getUserData();

			if (plantName != null) {
				((Circle) plant).setStroke(control.findCircleColor(plantName));
			}
		}
	}

	/**
	 * Called when user presses the delete button, deleting the currently selected
	 * object.
	 * 
	 * @param node The shape to be deleted.
	 */
	public void deleteShape(Node node) {
		garden.getChildren().remove(node);
	}

	/**
	 * Creates a button to be used in the toolbar.
	 * 
	 * @param text The text shown on the button
	 * @param eh   The EventHandler for when the button is pressed
	 * @return A Button object with the Font size defined by buttonFontSize
	 */
	public Button createButton(String text, EventHandler eh) {
		Button newButton = new Button(text);
		newButton.setOnMouseClicked(eh);
		newButton.setFont(new Font(buttonFontSize));
		return newButton;
	}

	/**
	 * displays info of plant moused over in the scroll pane.
	 * 
	 * @param plantString
	 */
	public void displayInfoForScrollPane(String plantString) {
		info = new VBox();

		info.setStyle(infoBoxStyle);

		Label plantLabel = new Label(plantString);
		plantLabel.setFont(new Font(overIVFont));

		info.getChildren().addAll(plantLabel);

		this.garden.getChildren().add(info);
	}

	/**
	 * This method displays the info of the current plant that the mouse is over
	 * 
	 * 
	 * @param e          The circle the user is currently over
	 * @param mouseX     The X coordinate of the mouse
	 * @param mouseY     The Y coordinate of the mouse
	 * @param plantMatch The name of the plant being displayed
	 */
	public void displayInfo(Circle e, double mouseX, double mouseY, String plantMatch) {
		Image i = imgs.getPlantImages().get(e.getUserData())[firstImgInd].getImg();

		ImageView plantImage = new ImageView(i);
		plantImage.setPreserveRatio(true);
		plantImage.setFitHeight(SIZE);
		plantImage.setFitWidth(SIZE);
		info = new VBox();

		info.setStyle(infoBoxStyle);

		Label topLabel = new Label(e.getUserData().toString());
		topLabel.setFont(new Font(overCircleFont));

		Label match = new Label(plantMatch);
		match.setFont(new Font(overCircleFont));

		info.getChildren().addAll(topLabel, plantImage, match);
		this.garden.getChildren().add(info);

		if (mouseY < garden.getHeight() / 2) {
			info.setLayoutY(garden.getHeight() / 2);
		}

	}

	/**
	 * Removes the info of a plant when the user's mouse has left the plant object
	 */
	public void removeInfo() {
		this.garden.getChildren().remove(info);
	}

	/**
	 * Adds a rectangle to the drawing based on a previously saved RectDrawingObj.
	 * 
	 * @param rectObj The saved representation of the rectangle
	 * @param fill    The color that fills the rectangle
	 * @param stroke  The color that borders the rectangle
	 * @param onPress The EventHandler for when the rectangle is pressed
	 * @param onDrag  The EventHandler for when the rectangle is dragged
	 * @param rects   The pane to which the rectangle is added
	 */
	public void addRectangle(RectDrawingObj rectObj, Color fill, Color stroke, EventHandler onPress,
			EventHandler onDrag, Pane rects) {
		Rectangle rect = new Rectangle();
		rect.setFill(fill);
		rect.setStroke(stroke);
		rect.setX(rectObj.getX());
		rect.setY(rectObj.getY());
		rect.setWidth(rectObj.getWidth());
		rect.setHeight(rectObj.getHeight());
		rect.setOnMousePressed(onPress);
		rect.setOnMouseDragged(onDrag);
		rect.setUserData(rectObj.getUserData());
		rects.getChildren().add(rect);
	}

	/**
	 * Adds an ellipse to the drawing based on a previously saved EllipseDrawingObj.
	 * 
	 * @param ellipseObj The saved representation of the ellipse
	 * @param onPress    The EventHandler for when the ellipse is pressed
	 * @param onDrag     The EventHandler for when the ellipse is dragged
	 * @param ells       The pane to which the ellipse is added
	 */
	public void addEllipse(EllipseDrawingObj ellipseObj, EventHandler onPress, EventHandler onDrag, Pane ells) {
		Ellipse e = new Ellipse();
		e.setFill(Color.TRANSPARENT);
		e.setStroke(Color.BLACK);
		e.setCenterX(ellipseObj.getX());
		e.setCenterY(ellipseObj.getY());
		e.setRadiusX(ellipseObj.getWidth());
		e.setRadiusY(ellipseObj.getHeight());
		e.setOnMousePressed(onPress);
		e.setOnMouseDragged(onDrag);
		ells.getChildren().add(e);
	}

	/**
	 * Adds a label to the drawing based on a previously saved LabelDrawingObj.
	 * 
	 * @param labelObj The saved representation of the label
	 * @param onPress  The EventHandler for when the label is pressed
	 * @param onDrag   The EventHandler for when the label is dragged
	 * @param labs     The pane to which the label is added
	 */
	public void addLabel(LabelDrawingObj labelObj, EventHandler onPress, EventHandler onDrag, Pane labs) {
		Label lab = new Label();
		lab.setText(labelObj.getText());
		lab.setLayoutX(labelObj.getX());
		lab.setLayoutY(labelObj.getY());
		lab.setFont(new Font(labelObj.getWidth()));
		lab.setOnMousePressed(onPress);
		lab.setOnMouseDragged(onDrag);
		labs.getChildren().add(lab);
	}

	/**
	 * Changes the id of the buttons of the current season and year, so that the
	 * button changes appearance.
	 * 
	 * @param newMode The newly update drawing mode
	 */
	public void updateMode(SeasonEnum season, int year) {
		year1.setId("");
		year2.setId("");
		year3.setId("");
		winter.setId("");
		spring.setId("");
		summer.setId("");
		fall.setId("");
		if (season != null) {
			switch (season) {
			case WINTER:
				winter.setId("selected-button");
				break;
			case SPRING:
				spring.setId("selected-button");
				break;
			case SUMMER:
				summer.setId("selected-button");
				break;
			case FALL:
				fall.setId("selected-button");
				break;
			}
		}
		switch (year) {
		case 3:
			year3.setId("selected-button");
			break;
		case 2:
			year2.setId("selected-button");
			break;
		default:
			year1.setId("selected-button");
			break;
		}
	}

	/**
	 * This method iterates over a list of garden actions drawing all of the users
	 * inputs to the gardenView. The list of GardenActions is modified by the
	 * GardenAction class to remove/add actions the user has done to undo/redo
	 * objects from the screen.
	 * 
	 * @param GA The list of GardenActions the method goes through to draw the users
	 *           inputs back on the screen.
	 */
	public void iterateGardenActions(GardenAction GA) {
		System.out.println("Iterating over garden actions");
		garden.getChildren().clear();
		garden.getChildren().add(drawing);
		for (GardenAction ga : GA.getActionList()) {
			switch (ga.getAction()) {
			case ADDPLANT:
				addCircleToGarden(ga.getPlant(), ga.getX(), ga.getY(), ga.getRadius(), ga.getName(), ga.getColor());
				System.out.print("ADD");
				break;

			case MOVEPLANT:
				movePlant(ga.getPlant(), ga.getX(), ga.getY());
				System.out.print("MOVE");
				break;

			case DELETE:
				deleteShape(ga.getPlant());
				System.out.print("DELETE");
				break;

			case COPY:
				addCircleToGarden(ga.getPlant(), ga.getX(), ga.getY(), ga.getRadius(), ga.getName(), ga.getColor());
				System.out.print("COPY");
			}
			System.out.println();
		}

	}

	public Pane getDrawing() {
		return drawing;
	}

	public void setDrawing(Pane drawing) {
		this.drawing = drawing;
	}

	public Pane getGarden() {
		return garden;
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public BorderPane getBorder() {
		return border;
	}

	public int getSize() {
		return SIZE;
	}

	public double getBottomHeight() {
		return border.getBottom().getLayoutBounds().getHeight();
	}

	public FileChooser getFileChooser() {
		return fileChooser;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

}
