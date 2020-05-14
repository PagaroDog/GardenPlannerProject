package Controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import Model.ActionEnum;
import Model.CircleDrawingObj;
import Model.GardenAction;
import Model.GardenObj;
import Model.LabelDrawingObj;
import Model.Model;
import Model.Plant;
import Model.PlantType;
import Model.RectDrawingObj;
import Model.StageName;
import Model.Season;
import Views.GardenView;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 * This class is the controller for the Garden Design screen. It mostly handles
 * user input.
 * 
 * @author Matt Cohen
 * 
 */
public class GardenController extends Controller<GardenView> implements Serializable {
	private final int year1int = 1;
	private final int year2int = 2;
	private final int year3int = 3;
	private final double originalTranslate = 0;
	private final double originalScale = 1;
	private final double newLayoutY = 0;
	private String plantName = "";

	private transient GardenAction GA = new GardenAction();

	public GardenController(Model model, GardenView view, Main main) {
		super(model, view, main);
	}

	/**
	 * code is triggered by a press of SpringButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnSpringButton() {
		return event -> springButton((MouseEvent) event);
	}

	/**
	 * sets model season to spring
	 * 
	 * @param event
	 */
	public void springButton(MouseEvent event) {
		model.setSeason(Season.SPRING);
		view.changeSeason(Season.SPRING);
	}

	/**
	 * code is triggered by a press of SummerButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnSummerButton() {
		return event -> summerButton((MouseEvent) event);
	}

	/**
	 * sets model season to summer
	 * 
	 * @param event
	 */
	public void summerButton(MouseEvent event) {
		model.setSeason(Season.SUMMER);
		view.changeSeason(Season.SUMMER);
	}

	/**
	 * code is triggered by a press of fallButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnFallButton() {
		return event -> fallButton((MouseEvent) event);
	}

	/**
	 * sets model season to fall
	 * 
	 * @param event
	 */
	public void fallButton(MouseEvent event) {
		model.setSeason(Season.FALL);
		view.changeSeason(Season.FALL);
	}

	/**
	 * code is triggered by a press of WinterButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnWinterButton() {
		return event -> winterButton((MouseEvent) event);
	}

	/**
	 * sets model season to winter
	 * 
	 * @param event
	 */
	public void winterButton(MouseEvent event) {
		model.setSeason(Season.WINTER);
		view.changeSeason(Season.WINTER);
	}

	/**
	 * code is triggered by a press of StatsButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnStatsButton() {
		return event -> statsButton((MouseEvent) event);
	}

	/**
	 * Sets the scene to StatisticsView and model StageName to StageName.STATS
	 * 
	 * @param event
	 */
	public void statsButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.STATS));
		model.setStageName(StageName.STATS);
		model.generateStats(view.getGarden().getChildren());

		main.getStatControl().updateStats();
	}

	/**
	 * code is triggered by a press of Year1Button
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnYear1Button() {
		return event -> year1Button((MouseEvent) event);
	}

	/**
	 * sets model year to 1
	 * 
	 * @param event
	 */
	public void year1Button(MouseEvent event) {
		model.setYear(year1int);
		view.setYear(year1int);
	}

	/**
	 * code is triggered by a press of Year2Button
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnYear2Button() {
		return event -> year2Button((MouseEvent) event);
	}

	/**
	 * sets model year to 2
	 * 
	 * @param event
	 */
	public void year2Button(MouseEvent event) {
		model.setYear(year2int);
		view.setYear(year2int);
	}

	/**
	 * code is triggered by a press of Year3Button
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnYear3Button() {
		return event -> year3Button((MouseEvent) event);
	}

	/**
	 * sets model year to 3
	 * 
	 * @param event
	 */
	public void year3Button(MouseEvent event) {
		model.setYear(year3int);
		view.setYear(year3int);
	}

	/**
	 * code is triggered by dragging an image
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnImgDrag() {
		return event -> imgDrag((MouseEvent) event);
	}

	/**
	 * runs when an image is dragged.
	 * 
	 * @param event
	 */
	public void imgDrag(MouseEvent event) {

	}

	/**
	 * code is triggered by a press of PrefButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnPrefButton() {
		return event -> prefButton((MouseEvent) event);
	}

	/**
	 * Sets the scene to PreferencesView and model StageName to
	 * StageName.PREFERENCES
	 * 
	 * @param event
	 */
	public void prefButton(MouseEvent event) {
		model.setCurrDrawObj(null);
		view.getStage().setScene(Main.getScenes().get(StageName.PREFERENCES));
		model.setStageName(StageName.PREFERENCES);
		main.getPrefControl().setDrawing(view.getDrawing());
	}

	/**
	 * code is triggered by a press of SaveButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnSaveButton() {
		return event -> saveButton();
	}

	/**
	 * Sets the scene to SaveView and model StageName to StageName.SAVE
	 * 
	 * @param event
	 */
	public void saveButton() {
		view.getStage().setScene(Main.getScenes().get(StageName.SAVE));
		model.setStageName(StageName.SAVE);
	}

	/**
	 * Gets event handler for when a mouse drag is released
	 * 
	 * @return drag released mouse event
	 */
	public EventHandler getHandlerForDragReleased() {
		return event -> dragReleased((MouseEvent) event);
	}

	/**
	 * Registers a mouse drag released event, sets copied to true so a new image
	 * will not be generated accidentally
	 * 
	 * @param event
	 */
	public void dragReleased(MouseEvent event) {
		ImageView dragPlant = (ImageView) event.getSource();
		System.out.println(dragPlant.getX());
	}

	/**
	 * Gets the drag event handler for images in the FlowPane
	 * 
	 * @param i the milkweed image that is being dragged
	 * @return the mouse event handler for the specific milkweed image that was
	 *         created
	 */
	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}

	/**
	 * Handles the dragging logic of the static milkweed image in the TilePane
	 * 
	 * @param event the drag event that occurred
	 */
	public void drag(MouseEvent event) {

		Circle dragPlant = (Circle) event.getSource();
		model.setCurrDrawObj(dragPlant);

		double calcX = model.calcX(event.getX(), dragPlant.getRadius(), view.getSize(), view.getCanvasWidth());

		double calcY = model.calcY(event.getY(), dragPlant.getRadius(), view.getBottomHeight(), view.getCanvasHeight());
		view.movePlant(dragPlant, calcX, calcY);

	}

	public EventHandler getHandlerForCirclePressed() {
		return event -> circlePressed((MouseEvent) event);
	}

	public void circlePressed(MouseEvent event) {
		model.setCurrDrawObj((Node) event.getSource());
	}

	/**
	 * code is triggered by a press of mouse
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandlerForPress() {
		return event -> press((MouseEvent) event);
	}

	/**
	 * Registers a mouse press on the milkweed image, sets copied to false so that a
	 * new milkweed image may be generated
	 * 
	 * @param event the mouse pressing event
	 */
	public void press(MouseEvent event) {
		Object click = event.getSource();
		model.setCurrDrawObj((Node) event.getSource());
		// int index = view.addIVToFlow(new ImageView(((ImageView) click).getImage()));
	}

	/**
	 * Allows the drawing from DrawYardView to be displayed in the GardenView.
	 * Resizes the drawing accordingly.
	 * 
	 * @param drawing The drawing to be resized and set.
	 */
	public void setDrawing(Pane drawing) {
		view.setDrawing(drawing);
		view.getGarden().getChildren().add(drawing);
		for (Node child : drawing.getChildren()) {
			child.setTranslateX(originalTranslate);
			child.setScaleX(originalScale);
		}
		double oldWidth = main.getDyControl().getViewWidth();
		drawing.setPrefWidth(view.getGarden().getWidth());
		double newWidth = view.getGarden().getWidth();
		double ratio = newWidth / oldWidth;
		for (Node child : drawing.getChildren()) {
			double oldX = child.getBoundsInParent().getMinX();
			child.setScaleX(ratio);
			double newX = child.getBoundsInParent().getMinX();
			child.setTranslateX(oldX * ratio - newX);
		}
		drawing.setLayoutY(newLayoutY);
		drawing.toBack();
	}

	/**
	 * Handles event when user drags an image, invoking imageDrag()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandlerForDragDetected() {
		return event -> imageDrag((MouseEvent) event);
	}

	public void imageDrag(MouseEvent event) {
		System.out.println("Started To Drag");
		Node n = (Node) event.getSource();
		Circle circle = new Circle();
		Pane p = new Pane();
		plantName = (String) ((Node) event.getSource()).getUserData();

		Dragboard db = n.startDragAndDrop(TransferMode.ANY);
		p.getChildren().add(circle);
		// Image i = new Image(circle);

		ClipboardContent content = new ClipboardContent();

		content.putImage(((ImageView) event.getSource()).getImage());
		// content.putImage(p);
		db.setContent(content);
		event.consume();
	}

	public EventHandler getHandlerForDragOver() {
		return event -> gardenDragOver((DragEvent) event);
	}

	public void gardenDragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.MOVE);
		event.consume();
	}

	public EventHandler getHandlerForDragDropped() {
		return event -> gardenDragDropped((DragEvent) event);
	}

	public void gardenDragDropped(DragEvent event) {

		Dragboard db = event.getDragboard();
		boolean success = false;
		double calcX = 0;
		double calcY = 0;
		// System.out.println("in dragDropped");

		if (db.hasImage()) {
			double minSize = getMinSize(plantName);
			double maxSize = getMaxSize(plantName);
			double minRad = getRad(minSize, model.getPropertyWidthInches(), model.getPropertyHeightInches());
			double maxRad = getRad(maxSize, model.getPropertyWidthInches(), model.getPropertyHeightInches());
			double rad;
			// System.out.println("Dragging " + plantName);
			// System.out.print("maxSize: " + maxSize);
			if (model.getYear() == 3) {
				rad = maxRad;
			} else if (model.getYear() == 2) {
				rad = (maxRad + minRad) / 2;
			} else {
				rad = minRad;
			}

			calcX = model.calcX(event.getX(), rad, view.getSize(), view.getCanvasWidth());
			calcY = model.calcY(event.getY(), rad, view.getBottomHeight(), view.getCanvasHeight());

			Color color = findCircleColor(plantName);

			model.setCurrDrawObj(view.addCircleToFlow(calcX, calcY, rad, plantName, color));
			GA.addAction(new GardenAction((Circle) model.getCurrDrawObj(), calcX, calcY, rad, plantName, color,
					ActionEnum.ADDPLANT));
			success = true;
		}
		event.setDropCompleted(success);
		event.consume();
	}

	/**
	 * Gets the scientific name of the plant at position x of the suggestedPlants
	 * ArrayList in the Model
	 * 
	 * @param x index
	 * @return String Scientific Name of Plant
	 */
	public String getPlantNameAt(int x) {
		return model.getSuggestedPlants().get(x).getName();
	}

	public int getNumPlants() {
		return model.getPlants().size();
	}

	/**
	 * Called by SuggestionsController when the next button is hit. Calls the view
	 * to update Plants in the event the suggestedPlants in the Model changed.
	 */
	public void update() {
		view.updatePlants();
		// System.out.println("Updating");
	}

	public double getMinSize(String plantName) {
		double minSize = model.getPlants().get(plantName).getSpread()[0] / 2;
		if (minSize == 0) {
			minSize = (model.getPlants().get(plantName).getHeight()[0]) / 6;
		}
		return minSize;
	}

	public double getMaxSize(String plantName) {
		double maxSize = model.getPlants().get(plantName).getSpread()[1] / 2;
		if (maxSize == 0) {
			maxSize = (model.getPlants().get(plantName).getHeight()[1]) / 6;
		}
		return maxSize;
	}

	public double getRad(double size, double propertyWidth, double propertyHeight) {
		double xRad = size / propertyWidth * (view.getGarden().getWidth());
		double yRad = size / propertyHeight * (view.getGarden().getHeight());
		double rad = (xRad + yRad) / 2;
		return rad;
	}

	/**
	 * Handles event when user presses delete button, invoking deleteButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnDeleteButton() {
		return event -> deleteButton((MouseEvent) event);
	}

	/*
	 * @param MouseEvent event called by eventHandler
	 */
	public void deleteButton(MouseEvent event) {
		Circle e = (Circle) model.getCurrDrawObj();

		GA.addAction(new GardenAction(e, 0, 0, 0, e.getUserData().toString(), null, ActionEnum.DELETE));

		view.deleteShape(model.getCurrDrawObj());
	}

	/**
	 * Handles event when user presses delete button, invoking deleteButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnCopyButton() {
		return event -> copyButton((MouseEvent) event);
	}

	/*
	 * @param MouseEvent event called by eventHandler
	 */
	public void copyButton(MouseEvent event) {
		Circle oldCircle = new Circle();
		oldCircle = (Circle) model.getCurrDrawObj();
//		Circle copy = new Circle(oldCircle.getRadius());
//		copy.setUserData(oldCircle.getUserData());
//		copy.setOnMouseClicked(this.getHandlerForCirclePressed());
//		copy.setOnMouseDragged(this.getHandlerForDrag());
//		copy.setOnMouseReleased(this.handleOnMouseReleased());
//		((Circle) copy).setFill(oldCircle.getFill());
//		System.out.println("In copyButton");
		

		//view.addShape(copy);
		Circle copy = view.addCircleToFlow(oldCircle.getCenterX(), oldCircle.getCenterY(), oldCircle.getRadius(),	(String) oldCircle.getUserData(), oldCircle.getFill());
		GA.addAction(new GardenAction(copy, 0, 0, 0, copy.getUserData().toString(), null, ActionEnum.COPY));
	}

	/*
	 * returns the width of the property.
	 */
	public int getPropertyWidthInches() {
		return model.getPropertyWidthInches();
	}

	/*
	 * returns the width of the property
	 */
	public int getPropertyHeightInches() {
		return model.getPropertyHeightInches();
	}

	public Season[] getBloomTime(String plantName) {
		return model.getPlants().get(plantName).getBloomtime();
	}

	public Color getBloomColor(String plantName) {
		HashSet<String> hashset = model.getPlants().get(plantName).getColor();
		int size = hashset.size();
		int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
		int i = 0;
		for (String colorName : hashset) {
			if (i == item)
				return Color.web(colorName);
			i++;
		}
		System.out.println("setting as BLACK");
		return Color.BLACK;

	}

	public PlantType getPlantType(String name) {
		return model.getPlants().get(name).getType();
	}

	public EventHandler handleOnMouseReleased() {
		return event -> mouseReleased((MouseEvent) event);
	}

	public void mouseReleased(MouseEvent event) {
		Circle dragPlant = (Circle) event.getSource();
		model.setCurrDrawObj(dragPlant);

		double calcX = model.calcX(event.getX(), dragPlant.getRadius(), view.getSize(), view.getCanvasWidth());

		double calcY = model.calcY(event.getY(), dragPlant.getRadius(), view.getBottomHeight(), view.getCanvasHeight());
		System.out.println("Drag released at x:" + calcX + ", y:" + calcY);

		GA.addAction(new GardenAction(dragPlant, calcX, calcY, dragPlant.getRadius(),
				dragPlant.getUserData().toString(), (Color) dragPlant.getStroke(), ActionEnum.MOVEPLANT));

	}

	public EventHandler handleOnUndoButton() {
		return event -> undo((MouseEvent) event);
	}

	public void undo(MouseEvent event) {
		GA.undo(view);
	}

	public EventHandler handleOnRedoButton() {
		return event -> redo((MouseEvent) event);
	}

	public void redo(MouseEvent event) {
		GA.redo(view);
	}

	public EventHandler handleOnMouseEntered() {
		return event -> displayInfo((MouseEvent) event);
	}

	public void displayInfo(MouseEvent event) {
		Circle plant = (Circle) event.getSource();
		view.displayInfo(plant, event.getX(), event.getY(),
				model.isPlantMatch(plant.getUserData().toString(), event.getX(), event.getY()));
	}

	public EventHandler handleOnMouseExited() {
		return event -> removeInfo((MouseEvent) event);
	}

	public void removeInfo(MouseEvent event) {
		view.removeInfo();
	}

	public EventHandler handleOnMouseEnteredImage() {
		return event -> displayInfoForScrollPane((MouseEvent) event);
	}

	public void displayInfoForScrollPane(MouseEvent event) {
		ImageView plantImage = (ImageView) event.getSource();
		String plantName = (String) plantImage.getUserData();
		Plant plant = model.getPlants().get(plantName);
		view.displayInfoForScrollPane(plant.toString());
	}

	public EventHandler handleOnMouseExitedImage() {
		return event -> removeInfo((MouseEvent) event);
	}

	public void setView(GardenView view) {
		this.view = view;
	}

	public void savePlants() {
		ObservableList<Node> children = view.getGarden().getChildren();
		model.getRectangles().clear();
		model.getLabels().clear();
		model.getCircles().clear();
		model.getGardenObjs().clear();
		ObservableList<Node> rectangles = ((Pane) view.getDrawing().getChildren().get(0)).getChildren();
		for (int i = 1; i < rectangles.size(); i++) {
			model.getRectangles().add(new RectDrawingObj((Rectangle) rectangles.get(i)));
		}
		ObservableList<Node> circles = ((Pane) rectangles.get(0)).getChildren();
		for (int i = 1; i < circles.size(); i++) {
			model.getCircles().add(new CircleDrawingObj((Ellipse) circles.get(i)));
		}
		ObservableList<Node> labels = ((Pane) circles.get(0)).getChildren();
		for (int i = 1; i < labels.size(); i++) {
			model.getLabels().add(new LabelDrawingObj((Label) labels.get(i)));
		}
		// TODO save background image
		for (int i = 1; i < children.size(); i++) {
			model.getGardenObjs().add(new GardenObj((Circle) children.get(i)));
		}
	}

	public void loadPlants() {
		for (GardenObj plant : model.getGardenObjs()) {
			view.addCircleToFlow(plant.getX(), plant.getY(), plant.getRadius(), plant.getPlantName(),
					findCircleColor(plant.getPlantName()));
		}
		view.setDrawing(main.getDyControl().getDrawing());
		for (RectDrawingObj rectObj : model.getRectangles()) {
			Rectangle rect = new Rectangle();
			rect.setFill(Color.TRANSPARENT);
			rect.setStroke(Color.BLACK);
			rect.setScaleX(rectObj.getScale());
			rect.setTranslateX(rectObj.getTrans());
			rect.setX(rectObj.getX());
			rect.setY(rectObj.getY());
			rect.setWidth(rectObj.getWidth());
			rect.setHeight(rectObj.getHeight());
			System.out.println(rectObj.getWidth());
			System.out.println(rectObj.getX());
			((Pane) view.getDrawing().getChildren().get(0)).getChildren().add(rect);
		}
	}

	public Color findCircleColor(String name) {
		ArrayList<Season> seasonList = new ArrayList<Season>(Arrays.asList(getBloomTime(name)));
		Season season = model.getSeason();
		if (season == Season.FALL) {
			if (seasonList.contains(season)) {
				return getBloomColor(name);
			}
		} else if (season == Season.WINTER) {
			if (seasonList.contains(season)) {
				return getBloomColor(name);
			} else {
				return Color.GRAY;
			}
		} else if (season == Season.SPRING) {
			if (seasonList.contains(season)) {
				return getBloomColor(name);
			}
		} else if (season == Season.SUMMER) {
			if (seasonList.contains(season)) {
				return getBloomColor(name);
			}
		}
		return Color.GREEN;
	}
}