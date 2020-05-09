package Controllers;

import Model.Model;
import Model.Plant;
import Model.StageName;
import Model.Season;
import Views.GardenView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * This class is the controller for the Garden Design screen. It mostly handles
 * user input.
 * 
 * @author Matt Cohen
 * 
 */
public class GardenController extends Controller<GardenView> {
	boolean copied = false;
	private final int year1int = 1;
	private final int year2int = 2;
	private final int year3int = 3;
	private final double originalTranslate = 0;
	private final double originalScale = 1;
	private final double newLayoutY = 0;
	double plantWidthX=0;
	double plantWidthY=0;
	String plantName = "";
	double minxRad;
	double minyRad;
	double maxxRad;
	double maxyRad;

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
		model.setNumTrees(0);
		model.setNumShrubs(0);
		model.setNumHerbs(0);
		model.getAllColors().clear();
		model.getAllSeasons().clear();
		for (Node node : view.getGarden().getChildren()) {
			String plantName = (String) node.getUserData();
			if (plantName != null) {
				Plant plant = model.getPlants().get(plantName);
				switch (plant.getType()) {
				case HERB:
					model.setNumHerbs(model.getNumHerbs() + 1);
					break;
				case SHRUB:
					model.setNumShrubs(model.getNumShrubs() + 1);
					break;
				case TREE:
					model.setNumTrees(model.getNumTrees() + 1);
					break;
				case VINE:
					break;
				}
				for (String color : plant.getColor()) {
					model.getAllColors().add(color);
				}
				for (Season season : plant.getBloomtime()) {
					model.getAllSeasons().add(season);
				}
			}
		}
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

		Ellipse dragPlant = (Ellipse) event.getSource();
		model.setCurrDrawObj(dragPlant);
		
		double calcX = model.calcX(event.getX(), dragPlant.getRadiusX(), view.getSize());
		// System.out.println(view.getGarden().getLayoutX());
		// System.out.println(dragPlant.getTranslateX());

		double calcY = model.calcY(event.getY(), dragPlant.getRadiusY(), view.getBottomHeight());
		view.movePlant(dragPlant, calcX, calcY);

	}
	public EventHandler getHandlerForEllipsePressed() {
		return event -> ellipsePressed((MouseEvent) event);
	}
	public void ellipsePressed(MouseEvent event) {
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
		System.out.println(n.getUserData());
		Ellipse circle = new Ellipse();
		Pane p = new Pane();
		plantName = (String) ((Node) event.getSource()).getUserData();
		System.out.println("plantName in imageDrag " + plantName);
		
		double minSize = model.getPlants().get(plantName).getSpread()[0]/2;
		double maxSize = model.getPlants().get(plantName).getSpread()[1]/2;
		if(model.getYear() == 3) {
			circle.setRadiusX(maxSize);
			circle.setRadiusY(maxSize);
			circle.setFill(Color.BLUE);		//TODO: figure out where we should get the color. 
			circle.setStroke(Color.BLUE);
			plantWidthX=maxSize;
			plantWidthY=maxSize;
		}
		else if(model.getYear() == 2) {
			circle.setRadiusX((maxSize+minSize)/2);
			circle.setRadiusY((maxSize+minSize)/2);
			circle.setFill(Color.BLUE);		//TODO: figure out where we should get the color. 
			circle.setStroke(Color.BLUE);
			plantWidthX=(maxSize-minSize)/2;
			plantWidthY=(maxSize-minSize)/2;
		}
		else if(model.getYear() == 1) {
			circle.setRadiusX(minSize);
			circle.setRadiusY(minSize);
			circle.setFill(Color.BLUE);		//TODO: figure out where we should get the color. 
			circle.setStroke(Color.BLUE);
			plantWidthX=minSize;
			plantWidthY=minSize;
		}
		else {
			circle.setRadiusX(minSize);
			circle.setRadiusY(minSize);
			plantWidthX=10;
			plantWidthY=10;
		}

		Dragboard db = n.startDragAndDrop(TransferMode.ANY);
		p.getChildren().add(circle);
		//Image i = new Image(circle);
		
		plantWidthX=100;
		plantWidthY=100;
		
		
		ClipboardContent content = new ClipboardContent();
		
		content.putImage(((ImageView) event.getSource()).getImage());
		//content.putImage(p);
		db.setContent(content);
		event.consume();
	}


	public EventHandler getHandlerForDragOver() {
		return event -> gardenDragOver((DragEvent) event);
	}

	public void gardenDragOver(DragEvent event) {
		//System.out.println("gardenDragOver");
		event.acceptTransferModes(TransferMode.MOVE);
		event.consume();
	}

	public EventHandler getHandlerForDragDropped() {
		return event -> gardenDragDropped((DragEvent) event);
	}

	public void gardenDragDropped(DragEvent event) {
		
		Dragboard db = event.getDragboard();
		boolean success = false;
		double calcX =0;
		double calcY =0;
		System.out.println("in dragDropped");

		if (db.hasImage()) {
			System.out.println("in db.hasImage");
			
			Ellipse circle = new Ellipse();
			double minSize = model.getPlants().get(plantName).getSpread()[0]/2;
			double maxSize = model.getPlants().get(plantName).getSpread()[1]/2;		//divide by two bc radius and not diameter
			double propertyWidth = model.getPropertyWidthInches();
			double propertyHeight = model.getPropertyHeightInches();
			minxRad = minSize/propertyWidth * (view.getGarden().getWidth());
			minyRad = minSize/propertyHeight * (view.getGarden().getHeight());
			maxxRad = maxSize/propertyWidth * (view.getGarden().getWidth());
			maxyRad = maxSize/propertyHeight * (view.getGarden().getHeight());
			if(maxSize == 0) {
				minSize = (model.getPlants().get(plantName).getHeight()[0])/4;
				maxSize = (model.getPlants().get(plantName).getHeight()[1])/4;
				propertyWidth = model.getPropertyWidthInches();
				propertyHeight = model.getPropertyHeightInches();
				minxRad = minSize/propertyWidth * (view.getGarden().getWidth());
				minyRad = minSize/propertyHeight * (view.getGarden().getHeight());
				maxxRad = maxSize/propertyWidth * (view.getGarden().getWidth());
				maxyRad = maxSize/propertyHeight * (view.getGarden().getHeight());
			}
			System.out.println("Dragging " + plantName);
			System.out.print("maxSize: " + maxSize);
			if(model.getYear() == 3) {
				circle.setRadiusX(maxxRad);
				circle.setRadiusY(maxyRad);
				circle.setFill(Color.BLUE);		//TODO: figure out where we should get the color. 
				circle.setStroke(Color.BLUE);
				calcX = model.calcX(event.getX(), maxxRad, view.getSize());
				calcY = model.calcY(event.getY(), maxyRad, view.getBottomHeight());
			}
			else if(model.getYear() == 2) {
				circle.setRadiusX((maxxRad+minxRad)/2);
				circle.setRadiusY((maxyRad+minyRad)/2);
				circle.setFill(Color.BLUE);		//TODO: figure out where we should get the color. 
				circle.setStroke(Color.BLUE);
				calcX = model.calcX(event.getX(), (maxxRad+minxRad)/2, view.getSize());
				calcY = model.calcY(event.getY(), (maxyRad+minyRad)/2, view.getBottomHeight());
			}
			else if(model.getYear() == 1) {
				circle.setRadiusX(minxRad);
				circle.setRadiusY(minyRad);
				circle.setFill(Color.BLUE);		//TODO: figure out where we should get the color. 
				circle.setStroke(Color.BLUE);
				calcX = model.calcX(event.getX(), minxRad, view.getSize());
				calcY = model.calcY(event.getY(), minyRad, view.getBottomHeight());
			}
			else {
				circle.setRadiusX(minxRad);
				circle.setRadiusY(minyRad);
				calcX = model.calcX(event.getX(), minSize, view.getSize());
				calcY = model.calcY(event.getY(), minSize, view.getBottomHeight());
			}
			circle.setUserData(plantName);
			System.out.println("plantName in gardenDragDropped " + circle.getUserData());
			
			view.addCirlceToFlow(circle, calcX, calcY, plantName);
			success = true;
			model.setCurrDrawObj(circle);
		}
		event.setDropCompleted(success);
		event.consume();
	}
	/**
	 * Gets the scientific name of the plant at position x of the suggestedPlants ArrayList in the Model
	 * @param x index
	 * @return String Scientific Name of Plant
	 */
	public String getPlantNameAt(int x) {
		return model.getSuggestedPlants().get(x).getName();
	}
	/**
	 * Called by SuggestionsController when the next button is hit. Calls the view to update Plants in the event the 
	 * suggestedPlants in the Model changed.
	 */
	public void update() {
		view.updatePlants();
		//System.out.println("Updating");
	}
	/*
	 * @param String plantName
	 * returns list of spread vals from model, if spread is zero returns height list.
	 */
	public int[] getSpread(String plantName) {
		System.out.println("plantName from getSpread " + plantName);
		//System.out.println(model.getPlants().get(plantName).getSpread());
		if(model.getPlants().get(plantName).getSpread()[1] == 0) {
			return model.getPlants().get(plantName).getHeight();
		}
		else {
			return model.getPlants().get(plantName).getSpread();
		}
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
	 * @param MouseEvent event
	 * called by eventHandler
	 */
	public void deleteButton(MouseEvent event) {
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
	 * @param MouseEvent event
	 * called by eventHandler
	 */
	public void copyButton(MouseEvent event) {
		Ellipse oldEllipse = new Ellipse();
		oldEllipse = (Ellipse) model.getCurrDrawObj();
		Ellipse copy = new Ellipse(oldEllipse.getRadiusX(), oldEllipse.getRadiusY());
		copy.setUserData(oldEllipse.getUserData());
		copy.setOnMouseClicked(this.getHandlerForEllipsePressed());
		copy.setOnMouseDragged(this.getHandlerForDrag());
		((Ellipse) copy).setFill(oldEllipse.getFill());
		view.addShape(copy);
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
	
	

}
