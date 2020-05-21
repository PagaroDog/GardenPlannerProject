package Views;

import javafx.scene.control.Button;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Controllers.DrawYardController;
import Model.DrawModeEnum;
import Model.StageNameEnum;
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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
	private Pane rectangles;
	private Pane ellipses;
	private Pane labels;
	private Pane back;
	private HBox toolbar;
	private BorderPane navigationDraw;
	private BorderPane navigationCond;

	private Label labelSizetxt;
	private Label widthTxt;
	private Label heightTxt;
	private Label heightUnit;
	private Label helpText;
	private Label helpTextCond;

	private Button selectButton;
	private Button deleteButton;
	private Button rectButton;
	private Button ellipseButton;
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
	private final double LABEL_FONT_SIZE = Math.min(16, 18 * canvasWidth / expectedWidth);
	private final double INIT_SHAPE_SIZE = 10;
	private final int MIN_RGB = 30;
	private final int MAX_RGB = 190;
	private final double RAND_RGB = MAX_RGB - MIN_RGB;
	private final double OPACITY = 0.3;
	private final double MIN_LABEL_LENGTH = 0;
	private final double MIN_FONT = 4;
	private final double MAX_FONT = 50;
	private final double FONT_DECREMENT = 1;
	private final double FONT_INCREMENT = 1;
	private final double PROMPT_SIZE = Math.min(50, 60 * canvasWidth / expectedWidth);

	private final String SELECTED_RGB = "rgba(255, 0, 0, 1)";
	private final String DESELECTED_RGB = "rgba(0, 0, 0, 1)";
	private final String DESELECTED_AREA_RGB = "rgba(0, 0, 0, 0)";

	private ArrayList<Node> areas = new ArrayList<Node>();

	public DrawYardView(Stage stage) {
		this.stage = stage;
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
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
		ellipseButton = createButton("Ellipse", control.getHandleOnEllipseButton());
		labelButton = createButton("Add Label", control.getHandleOnLabelButton());
		labeltxt = createField();
		labeltxt.setOnKeyPressed(control.getHandleOnEnterPressed());
		labeltxt.setPromptText("ex. House, Shed");
		minusButton = createButton("-", control.getHandleOnMinusButton());
		plusButton = createButton("+", control.getHandleOnPlusButton());
		labelSizetxt = new Label("Label Size: " + (int) labelSize);
		labelSizetxt.setFont(new Font(LABEL_FONT_SIZE));
		importButton = createButton("Import Drawing", control.getHandleOnImportButton());
		removeImportButton = createButton("Remove Imported Drawing", control.getHandleOnRemoveImportButton());
		emptyCenter = new Region();
		HBox.setHgrow(emptyCenter, Priority.ALWAYS);
		widthTxt = new Label("Width: ");
		widthTxt.setFont(new Font(LABEL_FONT_SIZE));
		widthField = createField();
		widthField.setPromptText("v Left to Right v");
		heightTxt = new Label("ft.   Height: ");
		heightTxt.setFont(new Font(LABEL_FONT_SIZE));
		heightField = createField();
		heightField.setPromptText("v Top to Bottom v");
		heightUnit = new Label("ft.");
		heightUnit.setFont(new Font(LABEL_FONT_SIZE));

		newAreaButton = createButton("New Conditions Area", control.getHandleOnNewAreaButton());

		toolbar = createToolbar();
		toolbar.getChildren().addAll(selectButton, deleteButton, rectButton, ellipseButton, labelButton, labeltxt,
				minusButton, plusButton, labelSizetxt, importButton, removeImportButton, emptyCenter, widthTxt,
				widthField, heightTxt, heightField, heightUnit);

		navigationDraw = createNavigationBar("Main Menu", "Create Areas", "Draw An Outline of Your Property",
				control.getHandlePrevButton(), control.getHandleNextButton());
		navigationCond = createNavigationBar("Draw Yard", "Set Preferences",
				"Mark Different Areas with Different Conditions", control.getHandlePrevButton(),
				control.getHandleNextButton());

		helpText = new Label("Use rectangles, ellipses, and labels\n" + "to draw an outline of your yard.\n"
				+ "Then enter the dimensions of your yard above and\n"
				+ "press \"Create Areas\" below to go to the next step.");
		helpText.setFont(new Font(PROMPT_SIZE));
		helpText.setTextAlignment(TextAlignment.CENTER);

		helpTextCond = new Label(
				"Use the \"New Conditions Area\" button\n" + "to mark different areas of your garden that have\n"
						+ "different conditions (light, soil moisture, etc).\n"
						+ "Then press the \"Set Preferences\" button below.");
		helpTextCond.setFont(new Font(PROMPT_SIZE));
		helpTextCond.setTextAlignment(TextAlignment.CENTER);
		
		drawing = new Pane();
		rectangles = new Pane();
		ellipses = new Pane();
		labels = new Pane();
		back = new Pane();
		drawing.getChildren().addAll(rectangles, helpText);
		rectangles.getChildren().add(ellipses);
		ellipses.getChildren().add(labels);
		labels.getChildren().add(back);
		drawing.setOnMousePressed(control.getHandleOnPressPane());
		drawing.setOnMouseDragged(control.getHandleOnDragPane());
		drawing.setOnMouseReleased(control.getHandleOnSelectButton());

		root = new BorderPane();
		root.setTop(toolbar);
		root.setCenter(drawing);
		root.setBottom(navigationDraw);

		scene = new Scene(root, canvasWidth, canvasHeight);
		scene.setOnKeyPressed(control.getHandleOnKeyPressed(labeltxt, widthField, heightField));
		styleScene();

		helpText.layoutXProperty().bind(drawing.widthProperty().subtract(helpText.widthProperty()).divide(2));
		helpText.layoutYProperty().bind(drawing.heightProperty().subtract(helpText.heightProperty()).divide(2));

		helpTextCond.layoutXProperty().bind(drawing.widthProperty().subtract(helpTextCond.widthProperty()).divide(2));
		helpTextCond.layoutYProperty().bind(drawing.heightProperty().subtract(helpTextCond.heightProperty()).divide(2));
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

	public double getMinFont() {
		return MIN_FONT;
	}

	public double getMaxFont() {
		return MAX_FONT;
	}

	public double getFontDecrement() {
		return FONT_DECREMENT;
	}

	public double getFontIncrement() {
		return FONT_INCREMENT;
	}

	public ImageView getBackground() {
		return background;
	}

	public void setBackground(ImageView background) {
		this.background = background;
	}

	public Pane getRectangles() {
		return rectangles;
	}

	public void setRectangles(Pane rectangles) {
		this.rectangles = rectangles;
	}

	public Pane getEllipses() {
		return ellipses;
	}

	public void setEllipses(Pane ellipses) {
		this.ellipses = ellipses;
	}

	public Pane getLabels() {
		return labels;
	}

	public void setLabels(Pane labels) {
		this.labels = labels;
	}

	public Pane getBack() {
		return back;
	}

	public void setBack(Pane back) {
		this.back = back;
	}

	/**
	 * Called when user clicks on the drawing Pane in RECTANGLE mode. Creates a new
	 * Rectangle object and adds it to the drawing Pane
	 * 
	 * @param x The x coordinate of the initial mouse press
	 * @param y The y coordinate of the initial mouse press
	 * @return The newly created rectangle
	 */
	public Node addRectangle(StageNameEnum mode, double x, double y) {
		Rectangle rect = new Rectangle(x, y, INIT_SHAPE_SIZE, INIT_SHAPE_SIZE);
		if (mode == StageNameEnum.DRAW) {
			rect.setFill(Color.TRANSPARENT);
			rect.setStroke(Color.BLACK);
			rect.setOnMousePressed(control.getHandleOnPressShape(StageNameEnum.DRAW));
		} else {
			rect.setFill(getRandomColor());
			rect.setStroke(Color.TRANSPARENT);
			rect.setOnMousePressed(control.getHandleOnPressShape(StageNameEnum.CONDITIONS));
		}
		rect.setUserData(mode);
		rect.setOnMouseDragged(control.getHandleOnDragRectangle());
		rectangles.getChildren().add(rect);
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
	 * Ellipse object and adds it to the drawing Pane
	 * 
	 * @param x The x coordinate of the initial mouse press
	 * @param y The y coordinate of the initial mouse press
	 * @return The newly created ellipse
	 */
	public Node addEllipse(double x, double y) {
		Ellipse ellipse = new Ellipse(x, y, INIT_SHAPE_SIZE, INIT_SHAPE_SIZE);
		ellipse.setFill(Color.TRANSPARENT);
		ellipse.setStroke(Color.BLACK);
		ellipse.setOnMousePressed(control.getHandleOnPressShape(StageNameEnum.DRAW));
		ellipse.setOnMouseDragged(control.getHandleOnDragEllipse());
		ellipses.getChildren().add(ellipse);
		return ellipse;
	}

	/**
	 * Called when user drags on the drawing Pane while creating a new ellipse,
	 * showing the current state of the ellipse.
	 * 
	 * @param ellipse The ellipse to be updated
	 * @param x       The x coordinate of the current mouse position
	 * @param y       The y coordinate of the current mouse position
	 */
	public void updateEllipse(Ellipse ellipse, double radiusX, double radiusY) {
		ellipse.setRadiusX(radiusX);
		ellipse.setRadiusY(radiusY);
	}

	/**
	 * Called when user drags on an already created ellipse, moving the position of
	 * the ellipse.
	 * 
	 * @param rect The Ellipse to be moved.
	 * @param x    The new x coordinate of the center of the ellipse
	 * @param y    The new y coordinate of the center of the ellipse
	 */
	public void moveEllipse(Ellipse ellipse, double x, double y) {
		ellipse.setCenterX(x);
		ellipse.setCenterY(y);
	}

	/**
	 * Called when user clicks on the Add Label button. Creates a new label object
	 * and adds it to the center of the drawing Pane
	 * 
	 * @return The Node representation of the label
	 */
	public Node addLabel() {
		if (labeltxt.getText().length() > MIN_LABEL_LENGTH) {
			Label txt = new Label(labeltxt.getText());
			labels.getChildren().add(txt);
			txt.setFont(new Font(labelSize));
			txt.setOnMousePressed(control.getHandleOnPressShape(StageNameEnum.DRAW));
			txt.setOnMouseDragged(control.getHandleOnDragLabel());
			txt.setLayoutX(drawing.getWidth() / 2);
			txt.setLayoutY(drawing.getHeight() / 2);
			drawing.requestFocus();
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
		if (node != null) {
			((Pane) node.getParent()).getChildren().remove(node);
		}
	}

	/**
	 * Changes the view to show that a certain object has been selected
	 * 
	 * @param node The selected node
	 */
	public void select(Node node) {
		if (node != null) {
			node.setStyle("-fx-stroke: " + SELECTED_RGB + ";" + "-fx-text-fill: " + SELECTED_RGB + ";");
		}
	}

	/**
	 * Changes the view to show that a certain object has been deselected
	 * 
	 * @param node The deselected node
	 */
	public void deselect(Node node) {
		if (node != null) {
			if (node.getUserData() != StageNameEnum.CONDITIONS) {
				node.setStyle("-fx-stroke: " + DESELECTED_RGB + ";" + "-fx-text-fill: " + DESELECTED_RGB + ";");
			} else {
				node.setStyle("-fx-stroke: " + DESELECTED_AREA_RGB + ";");
			}
		}
	}

	/**
	 * Makes changes to the view when entering draw mode from conditions mode
	 */
	public void drawMode() {
		toolbar.getChildren().remove(0, toolbar.getChildren().size());
		toolbar.getChildren().addAll(selectButton, deleteButton, rectButton, ellipseButton, labelButton, labeltxt,
				minusButton, plusButton, labelSizetxt, importButton, removeImportButton, emptyCenter, widthTxt,
				widthField, heightTxt, heightField, heightUnit);
		ObservableList<Node> rects = rectangles.getChildren();
		for (int i = rects.size() - 1; i >= 0 && rects.get(i).getUserData() == StageNameEnum.CONDITIONS; i--) {
			areas.add(rects.get(i));
			rects.remove(i);
		}
		root.setBottom(navigationDraw);
	}

	/**
	 * Makes changes to the view when entering conditions mode from draw mode
	 */
	public void condMode() {
		toolbar.getChildren().remove(0, toolbar.getChildren().size());
		toolbar.getChildren().addAll(selectButton, deleteButton, newAreaButton);
		for (int i = areas.size() - 1; i >= 0; i--) {
			rectangles.getChildren().add(areas.get(i));
		}
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
			if (!back.getChildren().contains(background)) {
				back.getChildren().add(background);
				back.toBack();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file.");
		}
	}

	/**
	 * Removes the background image from the drawing
	 */
	public void removeBackground() {
		back.getChildren().remove(background);
	}

	/**
	 * Changes the id of the button of the current drawing mode, so that the button
	 * changes appearance.
	 * 
	 * @param newMode The newly update drawing mode
	 */
	public void updateMode(DrawModeEnum newMode) {
		selectButton.setId("");
		rectButton.setId("");
		ellipseButton.setId("");
		newAreaButton.setId("");
		if (newMode != null) {
			switch (newMode) {
			case ELLIPSE:
				ellipseButton.setId("selected-button");
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

	/**
	 * Generates a random RGB Color based on the randomRGB and minRGB values.
	 * 
	 * @return A Color object
	 */
	public Color getRandomColor() {
		return Color.rgb((int) (Math.random() * RAND_RGB) + MIN_RGB, (int) (Math.random() * RAND_RGB) + MIN_RGB,
				(int) (Math.random() * RAND_RGB) + MIN_RGB, OPACITY);
	}

	/**
	 * Removes the help prompt based on the current stageName
	 * 
	 * @param stageName The current stageName
	 * @return A boolean: true if a prompt was removed, false otherwise
	 */
	public boolean removePrompts(StageNameEnum stageName) {
		if (stageName == StageNameEnum.DRAW)
			return drawing.getChildren().remove(helpText);
		else
			return drawing.getChildren().remove(helpTextCond);
	}

	/**
	 * Adds the help prompt for the Conditions screen to the drawing
	 */
	public void showCondPrompt() {
		drawing.getChildren().add(helpTextCond);
	}

}
