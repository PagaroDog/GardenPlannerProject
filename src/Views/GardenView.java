package Views;

import javafx.scene.paint.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Controllers.GardenController;
import Model.PlantType;
import Model.Season;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * The view class for the Garden Design screen. Holds graphical data and defines
 * graphical logic.
 * 
 * @author Matt Cohen
 *
 */
public class GardenView extends View<GardenController> {
	private HashMap<String, Image> plantImages = new HashMap<String, Image>();
	private ArrayList<Image> plants = new ArrayList<Image>();

	private FlowPane suggestedFlowPane;
	private TilePane seasonTilePane;
	private double bottomHeight;
	private TilePane yearTilePane;
	private TilePane statsTilePane;
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
	private ScrollPane scrollPane;
	private BorderPane border;
	private BorderPane navigation;
	private TabPane tabPane;
	int SIZE = 200;
	int TILE_PANE_WIDTH = 1000;
	Images imgs;
	double minxRad;
	double minyRad;
	double maxxRad;
	double maxyRad;

	public GardenView(Stage stage, Images imgs) {
		this.stage = stage;
		this.imgs = imgs;
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
		plants.clear();
		int numberPlants = 50;
		for (PlantType type : PlantType.values()) {
			Tab tab = new Tab(type.toString());
			//tab.setText(type.toString());
			//Label label = new Label(type.toString());
			VBox tile = new VBox(10);
			tile.setAlignment(Pos.CENTER);
			tile.setPadding(new Insets(5, 0, 5, 0));
//			tile.setVgap(4);
//			tile.setHgap(4);
//			tile.setPrefColumns(1);
			tile.setStyle("-fx-background-color: DAE6F3;");
			tile.setPrefWidth(SIZE);
			for (int i = 0; i < numberPlants; i++) {
				System.out.println(control.getPlantNameAt(i));
				if (control.getPlantType(control.getPlantNameAt(i)) == type) {
					Image img = imgs.getPlantImages().get(control.getPlantNameAt(i))[0].getImg();
					ImageView imageview = new ImageView(img);
					imageview.setPreserveRatio(true);
					imageview.setFitHeight(SIZE);
					imageview.setFitWidth(SIZE);
					imageview.setOnDragDetected(control.getHandlerForDragDetected());
					imageview.setUserData(control.getPlantNameAt(i));
					tile.getChildren().add(imageview);
				}
				
			}
			scrollPane = new ScrollPane();
			scrollPane.setFitToWidth(true);
			scrollPane.setContent(tile);
			//tab.setContent(label);
			tab.setContent(scrollPane);
			tabPane.getTabs().add(tab);
		}

		System.out.println("The first plant in garden View is " + control.getPlantNameAt(0));

		
	}

	/**
	 * Creates the garden pane. Placed in the center of the BorderPane
	 */
	public void gard() {
		garden = new Pane();
		garden.setOnDragOver(control.getHandlerForDragOver());
		garden.setOnDragDropped(control.getHandlerForDragDropped());
//		ImageView background = new ImageView(new Image("/imgs/lawn.jpg"));
//		background.fitWidthProperty().bind(garden.widthProperty()); 
//		background.fitHeightProperty().bind(garden.heightProperty());

//		garden.getChildren().add(background);
	}

	/**
	 * Creates the top TilePane. Contains navigation buttons to stats, save and
	 * preferences.
	 */
	public void top() {
		seasonTilePane = new TilePane();
		Label seasonLabel = new Label("Select Season");
		summer = new Button("Summer");
		summer.setOnMouseClicked(control.handleOnSummerButton());
		fall = new Button("Fall");
		fall.setOnMouseClicked(control.handleOnFallButton());
		winter = new Button("Winter");
		winter.setOnMouseClicked(control.handleOnWinterButton());
		spring = new Button("Spring");
		spring.setOnMouseClicked(control.handleOnSpringButton());
		seasonTilePane.getChildren().addAll(seasonLabel, summer, fall, winter, spring);

		TilePane toolsTilePane = new TilePane();
		Label toolsLabel = new Label("Tools");
		delete = new Button("Delete");
		delete.setOnMousePressed(control.handleOnDeleteButton());
		copy = new Button("Copy");
		copy.setOnMousePressed(control.handleOnCopyButton());
		toolsTilePane.getChildren().addAll(toolsLabel, delete, copy);

		yearTilePane = new TilePane();
		Label yearLabel = new Label("Select Year");
		year1 = new Button("Year: 1");
		year1.setOnMouseClicked(control.handleOnYear1Button());
		year2 = new Button("Year: 2");
		year2.setOnMouseClicked(control.handleOnYear2Button());
		year3 = new Button("Year: 3");
		year3.setOnMouseClicked(control.handleOnYear3Button());
		yearTilePane.getChildren().addAll(yearLabel, year1, year2, year3);

		toolbar = createToolbar();

		save = new Button("Save");
		save.setOnMouseClicked(control.handleOnSaveButton());

		toolbar.getChildren().addAll(save, seasonTilePane, yearTilePane, toolsTilePane);

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
		System.out.println(dir.isDirectory());
		File[] directoryListing = dir.listFiles();
		for (File child : directoryListing) {
			String name = child.getName();
			Image img = new Image("myDirectoryPath" + name);
			plantImages.put(name, img);
		}
	}

	/**
	 * Adds an ImageView to the garden Pane when the user releases a drag over the
	 * pane. Uses the SIZE to center images around mouse.
	 * 
	 * @param plant ImageView of plant from ScrollPane that was dragged by user
	 * @param x     x coordinates of mouse
	 * @param y     y coordinates of mouse
	 */
	public void addIVToFlow(ImageView plant, double x, double y) {
		System.out.println("Dropping image");
		this.garden.getChildren().add(plant);
		List<Node> imageArr = garden.getChildren();
		int i = imageArr.size() - 1;
		((ImageView) imageArr.get(i)).setPreserveRatio(true);
		((ImageView) imageArr.get(i)).setFitHeight(SIZE);
		imageArr.get(i).setOnMouseDragged(control.getHandlerForDrag());
		garden.getChildren().get(i).setLayoutX(x);
		garden.getChildren().get(i).setLayoutY(y);
	}

	/**
	 * Adds an ImageView to the garden Pane when the user releases a drag over the
	 * pane. Uses the SIZE to center images around mouse.
	 * 
	 * @param plant ImageView of plant from ScrollPane that was dragged by user
	 * @param x     x coordinates of mouse
	 * @param y     y coordinates of mouse
	 */
	public void addCirlceToFlow(Ellipse plant, double x, double y, String name) {
		System.out.println("Dropping image");
		this.garden.getChildren().add(plant);
		List<Node> imageArr = garden.getChildren();
		int i = imageArr.size() - 1;
		imageArr.get(i).setOnMouseDragged(control.getHandlerForDrag());
		((Ellipse) garden.getChildren().get(i)).setCenterX(x);
		((Ellipse) garden.getChildren().get(i)).setCenterY(y);
		((Ellipse) garden.getChildren().get(i)).setUserData(name);
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
	public void movePlant(Ellipse dragPlant, double x, double y) {
		dragPlant.setCenterX(x);
		dragPlant.setCenterY(y);
	}

	/**
	 * Gets the size of the picture
	 * 
	 * @return size of the picture
	 */
	public double getPicSize() {
		return SIZE;
	}

	/**
	 * Sets a new x value for any of the ImageViews in garden
	 * 
	 * @param index the index of the ImageView in garden to be modified
	 * @param x     the new x value of the specified imagView in garden
	 */
	public void setXs(int index, double x) {
		garden.getChildren().get(index).setTranslateX(((Ellipse) garden.getChildren().get(index)).getCenterX() + x);
	}

	/**
	 * Sets a new y value for any of the ImageViews in garden
	 * 
	 * @param index the index of the ImageView in garden to be modified
	 * @param y     the new y value of the specified ImageView in garden
	 */
	public void setYs(int index, double y) {
		garden.getChildren().get(index).setTranslateY(((Ellipse) garden.getChildren().get(index)).getCenterY() + y);
	}

	/**
	 * Gets the tile pane width
	 * 
	 * @return width of the TilePane
	 */
	public double getTPWidth() {
		return 1;
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

	/**
	 * Called by GardenController. Updates plant images to reflect the change in
	 * Model's plantSuggestions ArrayList
	 */
	public void updatePlants() {
		left();
		border.setLeft(scrollPane);
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
				if (year == 1) {
					double minSize = control.getSpread(plantName)[0] / 2;
					double maxSize = control.getSpread(plantName)[1] / 2;
					double propertyWidth = control.getPropertyWidthInches();
					double propertyHeight = control.getPropertyHeightInches();
					minxRad = minSize / propertyWidth * (this.getGarden().getWidth());
					minyRad = minSize / propertyHeight * (this.getGarden().getHeight());
					maxxRad = maxSize / propertyWidth * (this.getGarden().getWidth());
					maxyRad = maxSize / propertyHeight * (this.getGarden().getHeight());
					((Ellipse) plant).setRadiusX(minxRad);
					((Ellipse) plant).setRadiusY(minyRad);
				}

				else if (year == 2) {
					double minSize = control.getSpread(plantName)[0] / 2;
					double maxSize = control.getSpread(plantName)[1] / 2;
					double propertyWidth = control.getPropertyWidthInches();
					double propertyHeight = control.getPropertyHeightInches();
					minxRad = minSize / propertyWidth * (this.getGarden().getWidth());
					minyRad = minSize / propertyHeight * (this.getGarden().getHeight());
					maxxRad = maxSize / propertyWidth * (this.getGarden().getWidth());
					maxyRad = maxSize / propertyHeight * (this.getGarden().getHeight());
					((Ellipse) plant).setRadiusX((maxxRad + minxRad) / 2);
					((Ellipse) plant).setRadiusY((maxyRad + minyRad) / 2);
				}

				else if (year == 3) {

					double minSize = control.getSpread(plantName)[0] / 2;
					double maxSize = control.getSpread(plantName)[1] / 2;
					double propertyWidth = control.getPropertyWidthInches();
					double propertyHeight = control.getPropertyHeightInches();
					minxRad = minSize / propertyWidth * (this.getGarden().getWidth());
					minyRad = minSize / propertyHeight * (this.getGarden().getHeight());
					maxxRad = maxSize / propertyWidth * (this.getGarden().getWidth());
					maxyRad = maxSize / propertyHeight * (this.getGarden().getHeight());
					((Ellipse) plant).setRadiusX(maxxRad);
					((Ellipse) plant).setRadiusY(maxyRad);
				}
			}
		}
	}

	public void changeSeason(Season season) {
		List<Node> gardenList = garden.getChildren();
		for (Node plant : gardenList) {
			String plantName = (String) plant.getUserData();
			ArrayList<Season> seasonList = new ArrayList<Season>(Arrays.asList(control.getBloomTime(plantName)));
			if (plantName != null) {
				if (season == Season.FALL) {
					if (seasonList.contains(season)) {
						((Ellipse) plant).setFill(control.getBloomColor(plantName));
					} else {
						((Ellipse) plant).setFill(Color.GREEN);
					}
				}
				if (season == Season.WINTER) {
					if (seasonList.contains(season)) {
						((Shape) plant).setFill(control.getBloomColor(plantName));
					} else {
						((Ellipse) plant).setFill(Color.GRAY);
					}
				}
				if (season == Season.SPRING) {
					if (seasonList.contains(season)) {
						((Shape) plant).setFill(control.getBloomColor(plantName));
					} else {
						((Ellipse) plant).setFill(Color.GREEN);
					}
				}
				if (season == Season.SUMMER) {
					if (seasonList.contains(season)) {
						((Shape) plant).setFill(control.getBloomColor(plantName));
					} else {
						((Ellipse) plant).setFill(Color.GREEN);
					}
				}
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

	public void addShape(Ellipse ellipse) {
		garden.getChildren().add(ellipse);
	}

}
