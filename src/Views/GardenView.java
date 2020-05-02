package Views;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import Controllers.Controller;
import Controllers.GardenController;
import Model.GardenObj;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * The view class for the Garden Design screen. Holds graphical data and defines
 * graphical logic.
 * 
 * @author Matt Cohen
 *
 */
public class GardenView extends View {
	private HashMap<String, Image> plantImages = new HashMap<String, Image>();
	private FlowPane suggestedFlowPane;
	private TilePane seasonTilePane;
	private double bottomHeight = 35;
	private TilePane yearTilePane;
	private TilePane statsTilePane;
	private TilePane toolbarTilePane;
	private Pane garden;
	private Image backyardImage;
	private GardenController control;
	private Stage stage;
	private Button stats;
	private Button pref;
	private Button save;
	private Button year1;
	private Button year2;
	private Button year3;
	private Button spring;
	private Button summer;
	private Button fall;
	private Button winter;
	private Pane drawing;
	private ScrollPane scrollPane;
	private BorderPane border;
	private TilePane navigation;
	private FlowPane bottom;
	int SIZE = 200;
	int TILE_PANE_WIDTH = 1000;
	Images imgs;

	public GardenView(Stage stage, Images imgs) {
		this.stage = stage;
		this.imgs = imgs;
	}
	
	/**
	 * Initial setup of this class that could not be completed in the
	 * constructor since the controller had not yet been set
	 */
	@Override
	public void setup() {

		border = new BorderPane();
		bottom();
		border.setBottom(bottom);

		top();
		border.setTop(navigation);

		left();
		border.setLeft(scrollPane);

		gard();
		border.setCenter(garden);

		scene = new Scene(border, canvasWidth, canvasHeight);

	}

	/**
	 * Creates a ScrollPane of suggested plants for the left part of the BorderPane
	 */
	public void left() {
		TilePane tile = new TilePane();
		tile.setPadding(new Insets(5, 0, 5, 0));
		tile.setVgap(4);
		tile.setHgap(4);
		tile.setPrefColumns(1);
		tile.setStyle("-fx-background-color: DAE6F3;");
		tile.setPrefWidth(SIZE);

		plantImages.put("whiteAsh", new Image("/imgs/whiteAsh.png"));
		plantImages.put("commonMilkweed.png", new Image("/imgs/commonMilkweed.png"));
		plantImages.put("american-elm.jpg", new Image("/imgs/american-elm.jpg"));
		plantImages.put("american-plum.jpg", new Image("/imgs/american-plum.jpg"));
		plantImages.put("goldenrod.jpg", new Image("/imgs/goldenrod.jpg"));

		// addImageFromFile("/imgs/");

		for (Image img : plantImages.values()) {
			ImageView imageview = new ImageView(img);
			imageview.setPreserveRatio(true);
			imageview.setFitHeight(SIZE);
			imageview.setFitWidth(SIZE);
			imageview.setOnDragDetected(control.getHandlerForDragDetected());
			// imageview.setOnMouseDragged(control.getHandlerForDrag());
			// imageview.setOnMousePressed(control.getHandlerForPress());
			// imageview.setOnMouseReleased(control.getHandlerForDragReleased());
			tile.getChildren().add(imageview);
		}
		scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(tile);
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
		navigation = new TilePane();
		Label txt = new Label("Design");
		stats = new Button("Stats");
		stats.setOnMouseClicked(control.handleOnStatsButton());

		save = new Button("Save");
		save.setOnMouseClicked(control.handleOnSaveButton());

		pref = new Button("Pref");
		pref.setOnMouseClicked(control.handleOnPrefButton());

		navigation.getChildren().addAll(txt, pref, stats, save);

	}

	/**
	 * Creates a FlowPane for the bottom part of the border pane. Contains Year and
	 * Season TilePanes
	 */
	public void bottom() {
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
		seasonTilePane.setPrefHeight(bottomHeight);
		;

		yearTilePane = new TilePane();
		Label yearLabel = new Label("Select Year");
		year1 = new Button("Year: 1");
		year1.setOnMouseClicked(control.handleOnYear1Button());
		year2 = new Button("Year: 2");
		year2.setOnMouseClicked(control.handleOnYear2Button());
		year3 = new Button("Year: 3");
		year3.setOnMouseClicked(control.handleOnYear3Button());
		yearTilePane.getChildren().addAll(yearLabel, year1, year2, year3);

		bottom = new FlowPane();
		bottom.getChildren().add(seasonTilePane);
		bottom.getChildren().add(yearTilePane);

	}

	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public void setController(Controller controller) {
		control = (GardenController) controller;

	}

	@Override
	public Stage getStage() {
		return stage;
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
		this.garden.getChildren().add(plant); // TODO: creates error duplicate children added.
		List<Node> imageArr = garden.getChildren();
		int i = imageArr.size() - 1;
		((ImageView) imageArr.get(i)).setPreserveRatio(true);
		((ImageView) imageArr.get(i)).setFitHeight(SIZE);
		imageArr.get(i).setOnMouseDragged(control.getHandlerForDrag());
		imageArr.get(i).setUserData(new GardenObj(i, x, y, 0, null));
		// imageArr.get(i).setOnMouseReleased(control.getHandlerForDragReleased());
		garden.getChildren().get(i).setLayoutX(x);
		garden.getChildren().get(i).setLayoutY(y);

		// ((ImageView) imageArr.get(i)).setX(x);
		// ((ImageView) imageArr.get(i)).setY(y);

	}

	/**
	 * Called to move an already placed plant. Places the plant at the given (x,y)
	 * coordinates and changes the plants xLoc and yLoc values carried by the
	 * ImageView's User Data
	 * 
	 * @param plant
	 * @param x
	 * @param y
	 */
	public void movePlant(ImageView plant, double x, double y) {
		int i = ((GardenObj) plant.getUserData()).getID();
		garden.getChildren().get(i).setLayoutX(x);
		((GardenObj) garden.getChildren().get(i).getUserData()).setxLoc(x);
		garden.getChildren().get(i).setLayoutY(y);
		((GardenObj) garden.getChildren().get(i).getUserData()).setyLoc(y);
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
		garden.getChildren().get(index).setTranslateX(garden.getChildren().get(index).getLayoutX() + x);
	}

	/**
	 * Sets a new y value for any of the ImageViews in garden
	 * 
	 * @param index the index of the ImageView in garden to be modified
	 * @param y     the new y value of the specified ImageView in garden
	 */
	public void setYs(int index, double y) {
		garden.getChildren().get(index).setTranslateY(garden.getChildren().get(index).getLayoutY() + y);
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
		return bottomHeight;
	}

}
