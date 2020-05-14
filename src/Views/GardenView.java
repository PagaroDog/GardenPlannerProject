package Views;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Controllers.GardenController;
import Model.PlantType;
import Model.Season;
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
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The view class for the Garden Design screen. Holds graphical data and defines
 * graphical logic.
 * 
 * @author Matt Cohen
 *
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

	private double buttonFontSize = Math.min(12, 18 * canvasWidth / expectedWidth);
	private final double labelFontSize = Math.min(16, 21 * canvasWidth / expectedWidth);
	private final double treeStrokeWidth = 10;
	private final double shrubStrokeWidth = 10;
	private final double herbStrokeWidth = 5;
	private final double vineStrokeWidth = 5;

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
		System.out.println("Number of plants: " + control.getNumPlants());
		
		tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		plants.clear();
		int numberPlants = control.getNumPlants();
		for (PlantType type : PlantType.values()) {
			Tab tab = new Tab(type.toString());
			// tab.setText(type.toString());
			// Label label = new Label(type.toString());
			VBox tile = new VBox(10);
			tile.setAlignment(Pos.CENTER);
			tile.setPadding(new Insets(5, 0, 5, 0));
			tile.setStyle("-fx-background-color: DAE6F3;");
			tile.setPrefWidth(SIZE);
			for (int i = 0; i < numberPlants; i++) {
				
				if (control.getPlantType(control.getPlantNameAt(i)) == type) {
					Image img = imgs.getPlantImages().get(control.getPlantNameAt(i))[0].getImg();
					ImageView imageview = new ImageView(img);
					imageview.setPreserveRatio(true);
					imageview.setFitHeight(SIZE);
					imageview.setFitWidth(SIZE);
					imageview.setOnDragDetected(control.getHandlerForDragDetected());
					imageview.setOnMouseEntered(control.handleOnMouseEnteredImage());
					imageview.setOnMouseExited(control.handleOnMouseExitedImage());
					imageview.setUserData(control.getPlantNameAt(i));
					tile.getChildren().add(imageview);
				}

			}
			scrollPane = new ScrollPane();
			scrollPane.setFitToWidth(true);
			scrollPane.setContent(tile);
			// tab.setContent(label);
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
		save = createButton("Save", control.handleOnSaveButton());

		Region empty1 = new Region();
		HBox.setHgrow(empty1, Priority.ALWAYS);
		Region empty2 = new Region();
		HBox.setHgrow(empty2, Priority.ALWAYS);
		Region empty3 = new Region();
		HBox.setHgrow(empty3, Priority.ALWAYS);

		Label seasonLabel = new Label("Select Season");
		seasonLabel.setFont(new Font(labelFontSize));
		summer = createButton("Summer", control.handleOnSummerButton());
		fall = createButton("Fall", control.handleOnFallButton());
		winter = createButton("Winter", control.handleOnWinterButton());
		spring = createButton("Spring", control.handleOnSpringButton());

		Label yearLabel = new Label("Select Estimated Size");
		yearLabel.setFont(new Font(labelFontSize));
		year1 = createButton("Small", control.handleOnYear1Button());
		year2 = createButton("Medium", control.handleOnYear2Button());
		year3 = createButton("Large", control.handleOnYear3Button());

		Label toolsLabel = new Label("Tools");
		toolsLabel.setFont(new Font(labelFontSize));
		delete = createButton("Delete", control.handleOnDeleteButton());
		copy = createButton("Copy", control.handleOnCopyButton());
		undo = createButton("Undo", control.handleOnUndoButton());
		redo = createButton("Redo", control.handleOnRedoButton());

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
		System.out.println(dir.isDirectory());
		File[] directoryListing = dir.listFiles();
		for (File child : directoryListing) {
			String name = child.getName();
			Image img = new Image("myDirectoryPath" + name);
			plantImages.put(name, img);
		}
	}

	/**
	 * Adds a Circle to the garden Pane when the user releases a drag over the
	 * pane. Uses the SIZE to center images around mouse.
	 * 
	 * @param plant ImageView of plant from ScrollPane that was dragged by user
	 * @param x     x coordinates of mouse
	 * @param y     y coordinates of mouse
	 */
	public Circle addCircleToFlow(double x, double y, double radius, String name, Paint paint) {
		Circle plant = new Circle();
		System.out.println("Dropping image " + name);
		plant.setUserData(name);
		plant.setRadius(radius);
		plant.setCenterX(x);
		plant.setCenterY(y);
		plant.setOnMouseDragged(control.getHandlerForDrag());
		plant.setOnMouseReleased(control.handleOnMouseReleased());
		plant.setOnMouseEntered(control.handleOnMouseEntered());
		plant.setOnMouseExited(control.handleOnMouseExited());
		Image img = imgs.getPlantImages().get(name)[0].getImg();
		plant.setFill(new ImagePattern(img));
		plant.setStrokeType(StrokeType.INSIDE);
		plant.setStroke(paint);
		((Circle) plant).setStroke(control.findCircleColor(name));
		PlantType pT = control.getPlantType(name);
		switch(pT) {
			case HERB:
				plant.setStrokeWidth(herbStrokeWidth);
				break;
			case VINE: 
				plant.setStrokeWidth(vineStrokeWidth);
				break;
			case TREE:
				plant.setStrokeWidth(treeStrokeWidth);
				break;
			case SHRUB:
				plant.setStrokeWidth(shrubStrokeWidth);
				break;	
		}
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
		System.out.println("Moving plant at x:" + x + ", y:" + y);
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
		garden.getChildren().get(index).setTranslateX(((Circle) garden.getChildren().get(index)).getCenterX() + x);
	}

	/**
	 * Sets a new y value for any of the ImageViews in garden
	 * 
	 * @param index the index of the ImageView in garden to be modified
	 * @param y     the new y value of the specified ImageView in garden
	 */
	public void setYs(int index, double y) {
		garden.getChildren().get(index).setTranslateY(((Circle) garden.getChildren().get(index)).getCenterY() + y);
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
				double minSize = control.getMinSize(plantName);
				double maxSize = control.getMaxSize(plantName);
				
				double minRad = control.getRad(minSize, control.getPropertyWidthInches(), control.getPropertyHeightInches());
				double maxRad = control.getRad(maxSize, control.getPropertyWidthInches(), control.getPropertyHeightInches());
	
				if (plantName != null) {
					if (year == 1) {
						((Circle) plant).setRadius(minRad);
					}
	
					else if (year == 2) {
						((Circle) plant).setRadius((maxRad + minRad) / 2);
					}
	
					else if (year == 3) {
						((Circle) plant).setRadius(maxRad);
					}
				}
			}
		}
			
	}

	public void changeSeason(Season season) {
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

	public void addShape(Circle circle) {
		garden.getChildren().add(circle);
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
	
	public void displayInfoForScrollPane(String plantString) {
		VBox vb = new VBox();

		vb.setStyle("-fx-background-color: DAE6F3;");

		Label plantLabel = new Label(plantString);
		plantLabel.setFont(new Font(14));

		vb.getChildren().addAll(plantLabel);

		info = vb;

		this.garden.getChildren().add(vb);
	}

	public void displayInfo(Circle e, double mouseX, double mouseY, String plantMatch) {
		ImageWithSourceInfo[] img = imgs.getPlantImages().get(e.getUserData());
		Image i = img[0].getImg();

		ImageView plantImage = new ImageView(i);
		plantImage.setPreserveRatio(true);
		plantImage.setFitHeight(SIZE);
		plantImage.setFitWidth(SIZE);
		VBox vb = new VBox();

		vb.setStyle("-fx-background-color: DAE6F3;");

		Label topLabel = new Label(e.getUserData().toString());
		topLabel.setFont(new Font(20));

		Label match = new Label(plantMatch);
		match.setFont(new Font(20));

		vb.getChildren().addAll(topLabel, plantImage, match);

		info = vb;

		this.garden.getChildren().add(vb);

		if (mouseY < garden.getHeight() / 2) {
			vb.setLayoutY(garden.getHeight() / 2);
		}

	}

	public void removeInfo() {
		this.garden.getChildren().remove(info);
	}

	public void refresh() {
		stage.hide();
		stage.show();
	}

}
