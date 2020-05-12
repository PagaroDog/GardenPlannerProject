package Views;

import javafx.scene.control.Button;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Controllers.DrawYardController;
import Model.DrawMode;
import Model.StageName;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This is the View class for the Draw Yard screen. Holds graphical data and
 * defines graphical logic.
 * 
 * @author Tommy White
 *
 */
public class DrawYardView extends View<DrawYardController> {
	private BorderPane root;
	private Pane drawing;
	private HBox toolbar;
	private BorderPane navigationDraw;
	private BorderPane navigationCond;

	private Label labelSizetxt;
	private Label widthTxt;
	private Label heightTxt;
	private Label heightUnit;

	private Button selectButton;
	private Button deleteButton;
	private Button rectButton;
	private Button circleButton;
	private Button labelButton;
	private Button minusButton;
	private Button plusButton;
	private Button importButton;
	private Button removeImportButton;
	private Button newAreaButton;

	private Region emptyCenter;

	private TextField labeltxt;
	private TextField widthField;
	private TextField heightField;


	private FileChooser fileChooser;

	private ImageView background;

	private double labelSize = 12;
	private double buttonFontSize = Math.min(12, 14 * canvasWidth / expectedWidth);
	private double fieldWidth = Math.min(139, 139 * canvasWidth / expectedWidth);
	private double fieldHeight = Math.min(25, 25 * canvasHeight / expectedHeight);
	private final double labelFontSize = Math.min(16, 18 * canvasWidth / expectedWidth);
	private final double initShapeSize = 10;
	private final int minRGB = 30;
	private final double randRGB = 255 - minRGB;
	private final double opacity = 0.3;
	private final double minLabelLength = 0;

	private ArrayList<Node> areas = new ArrayList<Node>();

	public DrawYardView(Stage stage) {
		this.stage = stage;
		fileChooser = new FileChooser();
		
	}

	/**
	 * Initial setup of this class that could not be completed in the constructor
	 * since the controller had not yet been set
	 */
	@Override
	public void setup() {

		selectButton = createButton("Select", control.getHandleOnSelectButton());
		deleteButton = createButton("Delete", control.getHandleOnDeleteButton());
		rectButton = createButton("Rectangle", control.getHandleOnRectButton());
		circleButton = createButton("Circle", control.getHandleOnCircleButton());
		labelButton = createButton("Label", control.getHandleOnLabelButton());
		labeltxt = createField();
		labeltxt.setPromptText("ex. House, Shed");
		minusButton = createButton("-", control.getHandleOnMinusButton());
		plusButton = createButton("+", control.getHandleOnPlusButton());
		labelSizetxt = new Label("Label Size: " + (int) labelSize);
		labelSizetxt.setFont(new Font(labelFontSize));
		importButton = createButton("Import Drawing", control.getHandleOnImportButton());
		removeImportButton = createButton("Remove Imported Drawing", control.getHandleOnRemoveImportButton());
		emptyCenter = new Region();
		HBox.setHgrow(emptyCenter, Priority.ALWAYS);
		widthTxt = new Label("Width: ");
		widthTxt.setFont(new Font(labelFontSize));
		widthField = createField();
		widthField.setPromptText("v Left to Right v");
		heightTxt = new Label("ft.   Height: ");
		heightTxt.setFont(new Font(labelFontSize));
		heightField = createField();
		heightField.setPromptText("v Top to Bottom v");
		heightUnit = new Label("ft.");
		heightUnit.setFont(new Font(labelFontSize));

		newAreaButton = createButton("New Conditions Area", control.getHandleOnNewAreaButton());

		toolbar = createToolbar();
		toolbar.getChildren().addAll(selectButton, deleteButton, rectButton, circleButton, labelButton, labeltxt,
				minusButton, plusButton, labelSizetxt, importButton, removeImportButton, emptyCenter, widthTxt,
				widthField, heightTxt, heightField, heightUnit);

		navigationDraw = createNavigationBar("Main Menu", "Create Areas", "Draw An Outline of Your Property",
				control.getHandlePrevButton(), control.getHandleNextButton());
		navigationCond = createNavigationBar("Draw Yard", "Set Preferences",
				"Mark Different Areas with Different Conditions", control.getHandlePrevButton(),
				control.getHandleNextButton());

		drawing = new Pane();
		drawing.setOnMousePressed(control.getHandleOnPressPane());
		drawing.setOnMouseDragged(control.getHandleOnDragPane());

		root = new BorderPane();
		root.setTop(toolbar);
		root.setCenter(drawing);
		root.setBottom(navigationDraw);

		scene = new Scene(root, canvasWidth, canvasHeight);
		scene.setOnKeyPressed(control.getHandleOnKeyPressed());
//		scene.setOnMousePressed(control.getHandleOnScenePressed());
		styleScene();
	}

	public double getLabelSize() {
		return labelSize;
	}

	public void setLabelSize(double labelSize) {
		this.labelSize = labelSize;
		labelSizetxt.setText("Label Size: " + (int) labelSize);
	}

	public Pane getDrawing() {
		return drawing;
	}

	public void setDrawing(Pane drawing) {
		this.drawing = drawing;
	}

	public BorderPane getRoot() {
		return root;
	}

	public FileChooser getFileChooser() {
		return fileChooser;
	}

	public TextField getWidthField() {
		return widthField;
	}

	public TextField getHeightField() {
		return heightField;
	}

	/**
	 * Called when user clicks on the drawing Pane in RECTANGLE mode. Creates a new
	 * Rectangle object and adds it to the drawing Pane
	 * 
	 * @param x The x coordinate of the initial mouse press
	 * @param y The y coordinate of the initial mouse press
	 * @return The newly created rectangle
	 */
	public Node addRectangle(StageName mode, double x, double y) {
		Rectangle rect = new Rectangle(x, y, initShapeSize, initShapeSize);
		if (mode == StageName.DRAW) {
			rect.setFill(Color.TRANSPARENT);
			rect.setStroke(Color.BLACK);
			rect.setOnMouseClicked(control.getHandleOnPressShape());
		} else {
			rect.setFill(Color.rgb((int) (Math.random() * randRGB) + minRGB, (int) (Math.random() * randRGB) + minRGB,
					(int) (Math.random() * randRGB) + minRGB, opacity));
			rect.setStroke(Color.TRANSPARENT);
			rect.setOnMouseClicked(control.getHandleOnPressArea());
		}
		rect.setUserData(mode);
		rect.setOnMouseDragged(control.getHandleOnDragRectangle());
		drawing.getChildren().add(rect);
		return rect;
	}

	/**
	 * Called when user drags on the drawing Pane while creating a new rectangle,
	 * showing the current state of the rectangle.
	 * 
	 * @param The Rectangle to be updated
	 * @param x0  The x coordinate of the initial mouse press
	 * @param y0  The y coordinate of the initial mouse press
	 * @param x1  The x coordinate of the current mouse position
	 * @param y1  The y coordinate of the current mouse position
	 */
	public void updateRect(Rectangle rect, double topLeftX, double topLeftY, double width, double height) {
		rect.setX(topLeftX);
		rect.setY(topLeftY);
		rect.setWidth(width);
		rect.setHeight(height);
	}

	/**
	 * Called when user drags on an already created rectangle, moving the position
	 * of the rectangle.
	 * 
	 * @param rect The Rectangle to be moved.
	 * @param x    The new x coordinate of the rectangle
	 * @param y    The new y coordinate of the rectangle
	 */
	public void moveRectangle(Rectangle rect, double x, double y) {
		rect.setX(x);
		rect.setY(y);
	}

	/**
	 * Called when user clicks on the drawing Pane in CIRLCE mode. Creates a new
	 * Circle object and adds it to the drawing Pane
	 * 
	 * @param x The x coordinate of the initial mouse press
	 * @param y The y coordinate of the initial mouse press
	 * @return The newly created circle
	 */
	public Node addCircle(double x, double y) {
		Ellipse circle = new Ellipse(x, y, initShapeSize, initShapeSize);
		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.BLACK);
		circle.setOnMouseClicked(control.getHandleOnPressShape());
		circle.setOnMouseDragged(control.getHandleOnDragCircle());
		drawing.getChildren().add(circle);
		return circle;
	}

	/**
	 * Called when user drags on the drawing Pane while creating a new circle,
	 * showing the current state of the circle.
	 * 
	 * @param circle The circle to be updated
	 * @param x      The x coordinate of the current mouse position
	 * @param y      The y coordinate of the current mouse position
	 */
	public void updateCircle(Ellipse circle, double radiusX, double radiusY) {
		circle.setRadiusX(radiusX);
		circle.setRadiusY(radiusY);
	}

	/**
	 * Called when user drags on an already created circle, moving the position of
	 * the circle.
	 * 
	 * @param rect The Circle to be moved.
	 * @param x    The new x coordinate of the center of the circle
	 * @param y    The new y coordinate of the center of the circle
	 */
	public void moveCircle(Ellipse circle, double x, double y) {
		circle.setCenterX(x);
		circle.setCenterY(y);
	}

	public Node addLabel(double x, double y) {
		if (labeltxt.getText().length() > minLabelLength) {
			Label txt = new Label(labeltxt.getText());
			drawing.getChildren().add(txt);
			txt.setFont(new Font(labelSize));
			txt.setOnMousePressed(control.getHandleOnPressShape());
			txt.setOnMouseDragged(control.getHandleOnDragLabel());
			txt.setLayoutX(x);
			txt.setLayoutY(y);
			labeltxt.setText("");
			return txt;
		} else {
			return null;
		}
	}

	/**
	 * Called when user drags on an already created label, moving the position of
	 * the label.
	 * 
	 * @param label The Label to be moved
	 * @param x     The new x coordinate of the center of the label
	 * @param y     The new y coordinate of the center of the label
	 */
	public void moveLabel(Label label, double x, double y) {
		label.setLayoutX(x);
		label.setLayoutY(y);
	}

	/**
	 * Called when user presses the delete button, deleting the currently selected
	 * object.
	 * 
	 * @param node The shape to be deleted.
	 */
	public void deleteShape(Node node) {
		drawing.getChildren().remove(node);
	}

	/**
	 * Changes the view to show that a certain object has been selected
	 * 
	 * @param node The selected node
	 */
	public void select(Node node) {
		if (node instanceof Shape)
			((Shape) node).setStroke(Color.RED);
		else if (node instanceof Label)
			((Label) node).setTextFill(Color.RED);
		// nothing if null
	}

	/**
	 * Changes the view to show that a certain object has been deselected
	 * 
	 * @param node The deselected node
	 */
	public void deselect(Node node) {
		if (node instanceof Shape) {
			Shape shape = (Shape) node;
			if (shape.getUserData() == StageName.CONDITIONS)
				shape.setStroke(Color.TRANSPARENT);
			else
				shape.setStroke(Color.BLACK);
		} else if (node instanceof Label)
			((Label) node).setTextFill(Color.BLACK);
		// nothing if null
	}

	/**
	 * Makes changes to the view when entering draw mode from conditions mode
	 */
	public void drawMode() {
		toolbar.getChildren().remove(0, toolbar.getChildren().size());
		toolbar.getChildren().addAll(selectButton, deleteButton, rectButton, circleButton, labelButton, labeltxt,
				minusButton, plusButton, labelSizetxt, importButton, removeImportButton, emptyCenter, widthTxt,
				widthField, heightTxt, heightField, heightUnit);
		ObservableList<Node> drawObjs = drawing.getChildren();
		for (int i = drawObjs.size() - 1; i >= 0 && drawObjs.get(i).getUserData() == StageName.CONDITIONS; i--) {
			areas.add(drawObjs.get(i));
			drawObjs.remove(i);
		}
		root.setBottom(navigationDraw);
	}

	/**
	 * Makes changes to the view when entering conditions mode from draw mode
	 */
	public void condMode() {
		toolbar.getChildren().remove(0, toolbar.getChildren().size());
		toolbar.getChildren().addAll(selectButton, deleteButton, newAreaButton);
		drawing.getChildren().addAll(areas);
		areas.clear();
		root.setBottom(navigationCond);
	}

	/**
	 * @return The height of the toolbar
	 */
	public double getToolbarHeight() {
		return toolbar.getHeight();
	}

	/**
	 * Set the background ImageView to the image found at path
	 * 
	 * @param path The file system path to the image
	 */
	public void setBackground(String path) {
		try {
			background = new ImageView(
					new Image(new FileInputStream(path), drawing.getWidth(), drawing.getHeight(), false, false));
			if (!drawing.getChildren().contains(background)) {
				drawing.getChildren().add(background);
				background.toBack();
			}
		} catch (FileNotFoundException e) {
		}
	}

	public void removeBackground() {
		drawing.getChildren().remove(background);
	}

	/**
	 * Changes the id of the button of the current drawing mode, so that the button
	 * changes appearance.
	 * 
	 * @param newMode The newly update drawing mode
	 */
	public void updateMode(DrawMode newMode) {
		selectButton.setId("");
		rectButton.setId("");
		circleButton.setId("");
		labelButton.setId("");
		newAreaButton.setId("");
		if (newMode != null) {
			switch (newMode) {
			case CIRCLE:
				circleButton.setId("selected-button");
				break;
			case LABEL:
				labelButton.setId("selected-button");
				break;
			case RECTANGLE:
				rectButton.setId("selected-button");
				newAreaButton.setId("selected-button");
				break;
			case SELECT:
				selectButton.setId("selected-button");
				break;
			}
		}
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
	 * Creates a text field to be used in the toolbar.
	 * 
	 * @return A TextField object with the width, height, and font size defined at
	 *         the top of this file
	 */
	public TextField createField() {
		TextField field = new TextField();
		field.setPrefWidth(fieldWidth);
		field.setPrefHeight(fieldHeight);
		field.setFont(new Font(buttonFontSize));
		return field;
	}


}
