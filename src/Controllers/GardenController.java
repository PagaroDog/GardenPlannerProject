package Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import javax.imageio.ImageIO;

import Model.ActionEnum;
import Model.EllipseDrawingObj;
import Model.GardenAction;
import Model.GardenObj;
import Model.LabelDrawingObj;
import Model.Model;
import Model.Plant;
import Model.PlantTypeEnum;
import Model.RectDrawingObj;
import Model.StageNameEnum;
import Model.SeasonEnum;
import Views.GardenView;
import Views.View;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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
 * @author Ian McCabe
 */
public class GardenController extends Controller<GardenView> implements Serializable {
	private final double originalTranslate = 0;
	private final double originalScale = 1;
	private final double newLayoutY = 0;
	private String plantName = "";
	private int copyOffset = 10;
	private int minSpreadInd = 0;
	private int maxSpreadInd = 1;
	private int firstShapeOffset = 1;
	private boolean dragHappened = false;

	private transient GardenAction GA = new GardenAction();

	public GardenController(Model model, GardenView view, Main main) {
		super(model, view, main);
	}

	/**
	 * code is triggered by a press of a SeasonButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnSeasonButton(SeasonEnum season) {
		return event -> seasonButton(season);
	}

	/**
	 * sets model season to specified season
	 * 
	 * @param event
	 */
	public void seasonButton(SeasonEnum season) {
		model.setSeason(season);
		view.changeSeason(season);
		view.updateMode(season, model.getYear());
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
		view.getStage().setScene(Main.getScenes().get(StageNameEnum.STATS));
		model.setStageName(StageNameEnum.STATS);
		model.generateStats(view.getGarden().getChildren());
		main.getStatControl().updateStats();
	}

	/**
	 * code is triggered by a press of a YearButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnYearButton(int yearInt) {
		return event -> yearButton(yearInt);
	}

	/**
	 * sets model year to specified year
	 * 
	 * @param event
	 */
	public void yearButton(int yearInt) {
		model.setYear(yearInt);
		view.setYear(yearInt);
		view.updateMode(model.getSeason(), yearInt);
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
		return event -> prefButton();
	}

	/**
	 * Sets the scene to PreferencesView and model StageName to
	 * StageName.PREFERENCES
	 * 
	 * @param event
	 */
	public void prefButton() {
		model.setCurrDrawObj(null);
		view.getStage().setScene(Main.getScenes().get(StageNameEnum.PREFERENCES));
		model.setStageName(StageNameEnum.PREFERENCES);
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
		File file = view.getFileChooser().showSaveDialog(view.getStage());
		try {
			if (file.getName().endsWith(".garden")) {
				FileOutputStream fileOut = new FileOutputStream(file.getPath());
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				main.getGardenControl().savePlants();
				out.writeObject(model);
				out.close();
				fileOut.close();
			} else if (file.getName().endsWith(".png")) {
				WritableImage writableImage = new WritableImage((int) view.getGarden().getWidth(),
						(int) view.getGarden().getHeight());
				view.getGarden().snapshot(null, writableImage);
				ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
				System.out.println("snapshot saved: " + file.getAbsolutePath());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("No file chosen.");
		}
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
	 * Registers a mouse drag released event.
	 * 
	 * @param event
	 */
	public void dragReleased(MouseEvent event) {
		ImageView dragPlant = (ImageView) event.getSource();
		System.out.println(dragPlant.getX());
	}

	/**
	 * Gets the drag event handler for circles in the Garden
	 * 
	 * @param i the milkweed image that is being dragged
	 * @return the mouse event handler for the specific milkweed image that was
	 *         created
	 */
	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}

	/**
	 * Handles the dragging logic when an circle is dragged within the garden pane.
	 * 
	 * @param event the drag event that occurred
	 */
	public void drag(MouseEvent event) {

		Circle dragPlant = (Circle) event.getSource();
		model.setCurrDrawObj(dragPlant);

		double calcX = model.calcX(event.getX(), dragPlant.getRadius(), view.getSize(), View.getCanvasWidth());

		double calcY = model.calcY(event.getY(), dragPlant.getRadius(), view.getBottomHeight(), View.getCanvasHeight());
		view.movePlant(dragPlant, calcX, calcY);

		dragHappened = true;
	}

	/**
	 * code is triggered by a press of mouse on a circle
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandlerForCirclePressed() {
		return event -> circlePressed((MouseEvent) event);
	}

	/**
	 * called when a circle is clicked in the garden pane. this is used to update
	 * the CurrDrawObj in model which is used in copy, paste, and delete.
	 * 
	 * @param event
	 */
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
		for (Node child : drawing.getChildren()) {
			child.setTranslateX(originalTranslate);
			child.setScaleX(originalScale);
		}
		double oldWidth = main.getDyControl().getViewWidth();

		double newWidth = view.getGarden().getWidth();
		drawing.setPrefWidth(view.getGarden().getWidth());
		double ratio = newWidth / oldWidth;
		for (Node child : drawing.getChildren()) {
			System.out.println(child);
			double oldX = child.getBoundsInParent().getMinX();
			child.setScaleX(ratio);
			double newX = child.getBoundsInParent().getMinX();
			child.setTranslateX(oldX * ratio - newX);
		}
		view.setDrawing(drawing);
		view.getGarden().getChildren().add(drawing);
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

	/**
	 * called when an image in the left pane is started to be dragged
	 * 
	 * @param event
	 */
	public void imageDrag(MouseEvent event) {
		System.out.println("Started To Drag");
		Node n = (Node) event.getSource();
		Circle circle = new Circle();
		Pane p = new Pane();
		plantName = (String) ((Node) event.getSource()).getUserData();

		Dragboard db = n.startDragAndDrop(TransferMode.ANY);
		p.getChildren().add(circle);

		ClipboardContent content = new ClipboardContent();

		content.putImage(((ImageView) event.getSource()).getImage());
		db.setContent(content);
		event.consume();
	}

	/**
	 * calls handler and passes in event for gardenDragOver
	 * 
	 * @return event
	 */
	public EventHandler getHandlerForDragOver() {
		return event -> gardenDragOver((DragEvent) event);
	}

	/**
	 * called when the garden Pane is dragged over
	 * 
	 * @param event
	 */
	public void gardenDragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.MOVE);
		event.consume();
	}

	/**
	 * calls gardenDragDropped handler
	 * 
	 * @return event
	 */
	public EventHandler getHandlerForDragDropped() {
		return event -> gardenDragDropped((DragEvent) event);
	}

	/**
	 * activated when a plant is dragged from the left pane, this creates the plant
	 * and drops it in the garden pane.
	 * 
	 * @param event
	 */
	public void gardenDragDropped(DragEvent event) {

		Dragboard db = event.getDragboard();
		boolean success = false;
		double calcX = 0;
		double calcY = 0;

		if (db.hasImage()) {
			double rad = getRadiusFromYear(plantName);

			calcX = model.calcX(event.getX(), rad, view.getSize(), View.getCanvasWidth());
			calcY = model.calcY(event.getY(), rad, view.getBottomHeight(), View.getCanvasHeight());

			Color color = findCircleColor(plantName);

			Circle circle = new Circle();

			view.addCircleToGarden(circle, calcX, calcY, rad, plantName, color);
			model.setCurrDrawObj(circle);
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

	/**
	 * 
	 * @return number of plants on screen
	 */
	public int getNumPlants() {
		return model.getPlants().size();
	}

	/**
	 * Called by SuggestionsController when the next button is hit. Calls the view
	 * to update Plants in the event the suggestedPlants in the Model changed.
	 */
	public void update() {
		view.updatePlants();
	}

	/**
	 * returns min or max size used to get the size of the plant in changing size,
	 * and for initial plant creation
	 * 
	 * @param plantName
	 * @param minOrMax
	 * @return min or max size of plant based on size of plant and yard
	 */
	public double getSize(String plantName, int minOrMax) {
		double minSize = model.getPlants().get(plantName).getSpread()[minOrMax] / 2;
		if (minSize == 0) {
			minSize = (model.getPlants().get(plantName).getHeight()[minOrMax]) / 6;
		}
		return minSize;
	}

	/**
	 * this function is used to get the size of the circle based on the property
	 * size. called in dragReleased
	 * 
	 * @param size
	 * @param propertyWidth
	 * @param propertyHeight
	 * @return rad. circle size based on yard dimensions
	 */
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

	/**
	 * called by handleOnDeleteButton deletes object in CurrDrawObj
	 * 
	 * @param event
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

	/**
	 * triggered by handleOnCopyButton creates new circle object with
	 * addCircleToGarden and adds to GA
	 * 
	 * @param event
	 */
	public void copyButton(MouseEvent event) {
		System.out.println("creating copy");
		Circle oldCircle = (Circle) model.getCurrDrawObj();
		if (oldCircle != null) {
			Circle newCircle = new Circle();
			view.addCircleToGarden(newCircle, oldCircle.getCenterX() + copyOffset, oldCircle.getCenterY() + copyOffset,
					oldCircle.getRadius(), (String) oldCircle.getUserData(), (Color) oldCircle.getStroke());
			System.out.println(newCircle.getCenterX());
			GA.addAction(
					new GardenAction(newCircle, newCircle.getCenterX(), newCircle.getCenterY(), newCircle.getRadius(),
							newCircle.getUserData().toString(), (Color) newCircle.getStroke(), ActionEnum.COPY));
		}
	}

	/**
	 * returns the width of the property
	 * 
	 * @return int PropertyWidthInches
	 */
	public int getPropertyWidthInches() {
		return model.getPropertyWidthInches();
	}

	/**
	 * returns the height of the property
	 * 
	 * @return int PropertyHeightInches
	 */
	public int getPropertyHeightInches() {
		return model.getPropertyHeightInches();
	}

	public SeasonEnum[] getBloomTime(String plantName) {
		return model.getPlants().get(plantName).getBloomtime();
	}

	/**
	 * returns the color of the plant for the season specified in model called in
	 * changeSeason, and placing of plants
	 * 
	 * @param plantName
	 * @return
	 */
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

	/**
	 * used in categories for the left pane
	 * 
	 * @param name
	 * @return PlantTypeEnum of current plant
	 */
	public PlantTypeEnum getPlantType(String name) {
		return model.getPlants().get(name).getType();
	}

	/**
	 * calls mouseReleased, triggered when mouse is released
	 * 
	 * @return event handler
	 */
	public EventHandler handleOnMouseReleased() {
		return event -> mouseReleased((MouseEvent) event);
	}

	/**
	 * triggered by handleOnMouseReleased, adds garden Action based on modification,
	 * sets CurrDrawObj for copy paste delete
	 * 
	 * @param event
	 */
	public void mouseReleased(MouseEvent event) {
		Circle dragPlant = (Circle) event.getSource();
		model.setCurrDrawObj(dragPlant);

		double calcX = model.calcX(event.getX(), dragPlant.getRadius(), view.getSize(), View.getCanvasWidth());

		double calcY = model.calcY(event.getY(), dragPlant.getRadius(), view.getBottomHeight(), View.getCanvasHeight());
		System.out.println("Drag released at x:" + calcX + ", y:" + calcY);

		if (dragHappened) {
			GA.addAction(new GardenAction(dragPlant, calcX, calcY, dragPlant.getRadius(),
					dragPlant.getUserData().toString(), (Color) dragPlant.getStroke(), ActionEnum.MOVEPLANT));
			dragHappened = false;
		}

	}

	/**
	 * Handles when the Undo button is pressed in GardenView
	 * 
	 * @return EventHandler that calls the undo method
	 */
	public EventHandler handleOnUndoButton() {
		return event -> undo((MouseEvent) event);
	}

	/**
	 * <<<<<<< HEAD Calls GardenAction undo method to remove an action from the
	 * action stack and then calls iterateActions in gardenView to show the change
	 * made to the action stack
	 * 
	 * @param event Mouse event that triggered this event ======= calls undo logic
	 *              in GardenAction, triggered by handleOnUndoButton
	 * @param event >>>>>>> javadoc
	 */
	public void undo(MouseEvent event) {
		GA.undo();
		view.iterateGardenActions(GA);

	}

	/**
	 * <<<<<<< HEAD Handles when the redo button is pressed in gardenView
	 * 
	 * @return EventHandler that calls the redo method ======= calls redo logic,
	 *         triggered by redo button pressed.
	 * @return event handler >>>>>>> javadoc
	 */
	public EventHandler handleOnRedoButton() {
		return event -> redo((MouseEvent) event);
	}

	/**
	 * <<<<<<< HEAD Calls GardenAction redo method to add an undone action to the
	 * action stack. Calls iterateAction so the gardenView reflects the changes made
	 * in the garden action stack. ======= calls the redo function in GardenAction
	 * >>>>>>> javadoc
	 * 
	 * @param event
	 */
	public void redo(MouseEvent event) {
		GA.redo();
		view.iterateGardenActions(GA);
	}

	/**
	 * Handles when a mouse enters a circle in the gardenView
	 * 
	 * @return EventHandler that calls the displayInfo method in gardenView
	 */
	public EventHandler handleOnMouseEntered() {
		return event -> displayInfo((MouseEvent) event);
	}

	/**
	 * calls displayInfo in the gardenView to show the data of the circle the mouse
	 * is currently above
	 * 
	 * @param event Event when the mouse is above a circle in the gardenView
	 */
	public void displayInfo(MouseEvent event) {
		Circle plant = (Circle) event.getSource();
		view.displayInfo(plant, event.getX(), event.getY(),
				model.isPlantMatch(plant.getUserData().toString(), event.getX(), event.getY()));
	}

	/**
	 * Handles when a mouse leaves a circle in the gardenView
	 * 
	 * @return EventHandler that calls the removeInfo method in gardenView
	 */
	public EventHandler handleOnMouseExited() {
		return event -> removeInfo((MouseEvent) event);
	}

	/**
	 * Removes the info of the circle that the users mouse was just in
	 * 
	 * @param event Event when the user leaves a circle in gardenView
	 */
	public void removeInfo(MouseEvent event) {
		view.removeInfo();
	}

	/**
	 * triggered when mouse is in an image.
	 * 
	 * @return event handler
	 */
	public EventHandler handleOnMouseEnteredImage() {
		return event -> displayInfoForScrollPane((MouseEvent) event);
	}

	/**
	 * displays the info of plant that is moused over. triggered by
	 * handleOnMouseEnteredImage
	 * 
	 * @param event
	 */
	public void displayInfoForScrollPane(MouseEvent event) {
		ImageView plantImage = (ImageView) event.getSource();
		String plantName = (String) plantImage.getUserData();
		Plant plant = model.getPlants().get(plantName);
		view.displayInfoForScrollPane(plant.toString());
	}

	/**
	 * saves plants currently in garden frame and creates file
	 */
	public void savePlants() {
		model.getRectangles().clear();
		model.getLabels().clear();
		model.getEllipses().clear();
		model.getGardenObjs().clear();
		ObservableList<Node> rectangles = main.getDyControl().getView().getRectangles().getChildren();
		for (int i = firstShapeOffset; i < rectangles.size(); i++) {
			model.getRectangles().add(new RectDrawingObj((Rectangle) rectangles.get(i)));
		}
		ObservableList<Node> ellipses = main.getDyControl().getView().getEllipses().getChildren();
		for (int i = firstShapeOffset; i < ellipses.size(); i++) {
			model.getEllipses().add(new EllipseDrawingObj((Ellipse) ellipses.get(i)));
		}
		ObservableList<Node> labels = main.getDyControl().getView().getLabels().getChildren();
		for (int i = firstShapeOffset; i < labels.size(); i++) {
			model.getLabels().add(new LabelDrawingObj((Label) labels.get(i)));
		}
		ObservableList<Node> back = main.getDyControl().getView().getBack().getChildren();
		if (back.size() > 0) {
			Image img = ((ImageView) back.get(0)).getImage();
			String imgPath = "" + img.hashCode() + ".png";
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", new File(imgPath));
				model.setBackgroundPath(imgPath);
			} catch (IOException e) {
				System.out.println("Imported image not saved.");
			}
		}
		ObservableList<Node> plants = view.getGarden().getChildren();
		for (int i = firstShapeOffset; i < plants.size(); i++) {
			model.getGardenObjs().add(new GardenObj((Circle) plants.get(i)));
		}
		model.setWidthOnSave(View.getCanvasWidth());
		model.setHeightOnSave(View.getCanvasHeight());
	}

	/**
	 * loads plants into the garden frame based on a previous save file.
	 */
	public void loadPlants() {
		for (GardenObj plant : model.getGardenObjs()) {
			Circle circle = new Circle();
			Color color = findCircleColor(plant.getPlantName());
			view.addCircleToGarden(circle, plant.getX(), plant.getY(), plant.getRadius(), plant.getPlantName(), color);
			GA.addAction(new GardenAction(circle, plant.getX(), plant.getY(), plant.getRadius(), plant.getPlantName(),
					color, ActionEnum.ADDPLANT));
		}
		for (RectDrawingObj rectObj : model.getRectangles()) {
			if (rectObj.getUserData() == StageNameEnum.DRAW) {
				view.addRectangle(rectObj, Color.TRANSPARENT, Color.BLACK,
						main.getDyControl().getHandleOnPressShape(StageNameEnum.DRAW),
						main.getDyControl().getHandleOnDragRectangle(), main.getDyControl().getView().getRectangles());
			} else {
				view.addRectangle(rectObj, main.getDyControl().getView().getRandomColor(), Color.TRANSPARENT,
						main.getDyControl().getHandleOnPressShape(StageNameEnum.CONDITIONS),
						main.getDyControl().getHandleOnDragRectangle(), main.getDyControl().getView().getRectangles());
			}
		}
		for (EllipseDrawingObj ellipseObj : model.getEllipses()) {
			view.addEllipse(ellipseObj, main.getDyControl().getHandleOnPressShape(StageNameEnum.DRAW),
					main.getDyControl().getHandleOnDragEllipse(), main.getDyControl().getView().getEllipses());
		}
		for (LabelDrawingObj labelObj : model.getLabels()) {
			view.addLabel(labelObj, main.getDyControl().getHandleOnPressShape(StageNameEnum.DRAW),
					main.getDyControl().getHandleOnDragLabel(), main.getDyControl().getView().getLabels());
		}
		if (model.getBackgroundPath() != null) {
			System.out.println("Loading img");
			try {
				Image img = SwingFXUtils.toFXImage(ImageIO.read(new File(model.getBackgroundPath())), null);
				main.getDyControl().getView().setBackground(new ImageView(img));
				main.getDyControl().getView().getBack().getChildren()
						.add(main.getDyControl().getView().getBackground());
			} catch (IOException e) {
				System.out.println("Unable to load previously imported image.");
			}
		}
		Platform.runLater(() -> {
			prefButton();
			main.getPrefControl().nextButton();
			main.getSuggestionsControl().nextButton();
		});
	}

	/**
	 * Returns the color a circle should be based on plant name and current season
	 * 
	 * @param name The name of the plant
	 * @return A Color object
	 */
	public Color findCircleColor(String name) {
		ArrayList<SeasonEnum> seasonList = new ArrayList<SeasonEnum>(Arrays.asList(getBloomTime(name)));
		SeasonEnum season = model.getSeason();
		switch (season) {
		case WINTER:
			if (seasonList.contains(season)) {
				return getBloomColor(name);
			} else {
				return Color.GRAY;
			}
		case SPRING:
			if (seasonList.contains(season)) {
				return getBloomColor(name);
			}
			break;
		case SUMMER:
			if (seasonList.contains(season)) {
				return getBloomColor(name);
			}
			break;
		case FALL:
			if (seasonList.contains(season)) {
				return getBloomColor(name);
			}
		}
		return Color.GREEN;
	}

	/**
	 * returns the radius of the plant based on season. Used for resizing based on
	 * size buttons and initial circle creation
	 * 
	 * @param name
	 * @return rad
	 */
	public double getRadiusFromYear(String name) {
		double minSize = getSize(name, minSpreadInd);
		double maxSize = getSize(name, maxSpreadInd);
		double minRad = getRad(minSize, model.getPropertyWidthInches(), model.getPropertyHeightInches());
		double maxRad = getRad(maxSize, model.getPropertyWidthInches(), model.getPropertyHeightInches());
		double rad = 0;

		switch (model.getYear()) {
		case 3:
			rad = maxRad;
			break;
		case 2:
			rad = (maxRad + minRad) / 2;
			break;
		default:
			rad = minRad;
			break;
		}

		return rad;
	}
}