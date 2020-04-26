package Views;
import javafx.scene.control.Button;

import java.util.ArrayList;

import Controllers.Controller;
import Controllers.DrawYardController;
import Model.StageName;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * @author Tommy White
 *
 */
public class DrawYardView extends View{
	private Stage stage;
	
	private BorderPane root;
	private Pane drawing;
	private TilePane toolbar;
	
	private Label drawtxt;
	private Label condtxt;
	private Label labelSizetxt;
	
	private Button nextButton;
	private Button prevButton;
	private Button selectButton;
	private Button deleteButton;
	private Button rectButton;
	private Button circleButton;
	private Button labelButton;
	private Button minusButton;
	private Button plusButton;
	private Button newAreaButton;
	
	private TextField labeltxt;
	
	private DrawYardController control;
	
	private double labelSize;
	private final double initShapeSize = 10;
	private final double toolbarVGap = 15;
	private final double toolbarVPadding = 10;
	private final double toolbarHPadding = 10;
	private final int minRGB = 30;
	private final double randRGB = 255 - minRGB;
	private final double opacity = 0.3;
	
	private ArrayList<Node> areas = new ArrayList<Node>();
	
	public DrawYardView(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * Initial setup of this class that could not be completed in the
	 * constructor since the controller had not yet been set
	 */
	public void setup() {
		labelSize = 12;
		drawtxt = new Label("DrawPhase");
		condtxt = new Label("Conditions");
		labelSizetxt = new Label("Label Size: " + (int) labelSize);
		nextButton = new Button("Next");
		nextButton.setOnMouseClicked(control.getHandleNextButton());
		prevButton = new Button("Prev");
		prevButton.setOnMouseClicked(control.getHandlePrevButton());
		selectButton = new Button("Select");
		selectButton.setOnMouseClicked(control.getHandleOnSelectButton());
		deleteButton = new Button("Delete");
		deleteButton.setOnMousePressed(control.getHandleOnDeleteButton());
		rectButton = new Button("Rectangle");
		rectButton.setOnMouseClicked(control.getHandleOnRectButton());
		circleButton = new Button("Circle");
		circleButton.setOnMouseClicked(control.getHandleOnCircleButton());
		labelButton = new Button("Label");
		labelButton.setOnMouseClicked(control.getHandleOnLabelButton());
		minusButton = new Button("-");
		minusButton.setOnMouseClicked(control.getHandleOnMinusButton());
		plusButton = new Button("+");
		plusButton.setOnMouseClicked(control.getHandleOnPlusButton());
		newAreaButton = new Button("New Conditions Area");
		newAreaButton.setOnMousePressed(control.getHandleOnNewAreaButton());
		labeltxt = new TextField();

		toolbar  = new TilePane(drawtxt, prevButton, nextButton, selectButton, deleteButton, rectButton, circleButton, labelButton, labeltxt, minusButton, plusButton, labelSizetxt);
		toolbar.setStyle("-fx-background-color: rgba(168,158,255,1);");
		toolbar.setPadding(new Insets(toolbarVPadding, toolbarHPadding, toolbarVPadding, toolbarHPadding));
		toolbar.setVgap(toolbarVGap);
		
		drawing = new Pane();
		drawing.setOnMousePressed(control.getHandleOnPressPane());
		drawing.setOnMouseDragged(control.getHandleOnDragPane());
		
		root = new BorderPane();
		root.setTop(toolbar);
		root.setCenter(drawing);
		
		scene = new Scene(root,canvasHeight,canvasWidth);
	}
	
	/**
	 * @return Scene object for the Draw Yard screen
	 */
	@Override
	public Scene getScene() {
		return scene;
	}
	
	/**
	 * Sets control to c
	 */
	public void setController(Controller c) {
		control = (DrawYardController) c;
	}

	@Override
	public Stage getStage() {
		return stage;
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

	/**
	 * Called when user clicks on the drawing Pane in RECTANGLE mode.
	 * Creates a new Rectangle object and adds it to the drawing Pane 
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
			rect.setFill(Color.rgb((int) (Math.random()*randRGB) + minRGB, (int) (Math.random()*randRGB) + minRGB, (int) (Math.random()*randRGB) + minRGB, opacity));
			rect.setStroke(Color.TRANSPARENT);
			rect.setOnMouseClicked(control.getHandleOnPressArea());
		}
		rect.setUserData(mode);
		rect.setOnMouseDragged(control.getHandleOnDragRectangle());
		drawing.getChildren().add(rect);
		return rect;
	}

	/**
	 * Called when user drags on the drawing Pane while creating a 
	 * new rectangle, showing the current state of the rectangle.
	 * @param The Rectangle to be updated
	 * @param x0 The x coordinate of the initial mouse press
	 * @param y0 The y coordinate of the initial mouse press
	 * @param x1 The x coordinate of the current mouse position
	 * @param y1 The y coordinate of the current mouse position
	 */
	public void updateRect(Rectangle rect, double x0, double y0, double x1, double y1) {
		double topLeftX = Math.min(x0, x1);
		double topLeftY = Math.min(y0, y1);
		double width = Math.max(x0, x1) - topLeftX;
		double height = Math.max(y0, y1) - topLeftY;
		rect.setX(topLeftX);
		rect.setY(topLeftY);
		rect.setWidth(width);
		rect.setHeight(height);
	}
	
	/**
	 * Called when user drags on an already created rectangle,
	 * moving the position of the rectangle.
	 * @param rect The Rectangle to be moved.
	 * @param x The new x coordinate of the rectangle
	 * @param y The new y coordinate of the rectangle
	 */
	public void moveRectangle(Rectangle rect, double x, double y) {
		rect.setX(x);
		rect.setY(y);
	}
	
	/**
	 * Called when user clicks on the drawing Pane in CIRLCE mode.
	 * Creates a new Circle object and adds it to the drawing Pane 
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
	 * Called when user drags on the drawing Pane while creating a 
	 * new circle, showing the current state of the circle.
	 * @param circle The circle to be updated
	 * @param x The x coordinate of the current mouse position
	 * @param y The y coordinate of the current mouse position
	 */
	public void updateCircle(Ellipse circle, double x, double y) {
		double radiusX = Math.abs(circle.getCenterX() - x);
		double radiusY = Math.abs(circle.getCenterY() - y);
		circle.setRadiusX(radiusX);
		circle.setRadiusY(radiusY);
	}
	
	/**
	 * Called when user drags on an already created circle,
	 * moving the position of the circle.
	 * @param rect The Circle to be moved.
	 * @param x The new x coordinate of the center of the circle
	 * @param y The new y coordinate of the center of the circle
	 */
	public void moveCircle(Ellipse circle, double x, double y) {
		circle.setCenterX(x);
		circle.setCenterY(y);
	}
	
	public Node addLabel(double x, double y) {
		if (labeltxt.getText().length() > 0) {
			Label txt = new Label(labeltxt.getText());
			txt.setLayoutX(x);
			txt.setLayoutY(y);
			txt.setFont(new Font(labelSize));
			txt.setOnMousePressed(control.getHandleOnPressShape());
			txt.setOnMouseDragged(control.getHandleOnDragLabel());
			drawing.getChildren().add(txt);
			labeltxt.setText("");
			return txt;
		} else {
			return null;
		}
	}
	
	/**
	 * Called when user drags on an already created label,
	 * moving the position of the label.
	 * @param label The Label to be moved
	 * @param x The new x coordinate of the center of the label
	 * @param y The new y coordinate of the center of the label
	 */
	public void moveLabel (Label label, double x, double y) {
		label.setLayoutX(x);
		label.setLayoutY(y);
	}
	
	/**
	 * Called when user presses the delete button,
	 * deleting the currently selected object.
	 * @param node The shape to be deleted.
	 */
	public void deleteShape(Node node) {
		drawing.getChildren().remove(node);
	}
	
	/**
	 * Changes the view to show that a certain object has been selected
	 * @param node The selected node
	 */
	public void select(Node node) {
		if (node instanceof Shape)
			((Shape) node).setStroke(Color.RED);
		else if (node instanceof Label)
			((Label) node).setTextFill(Color.RED);
		//nothing if null
	}
	
	/**
	 * Changes the view to show that a certain object has been deselected
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
		//nothing if null
	}
	
	/**
	 * Makes changes to the view when entering draw mode from conditions mode
	 */
	public void drawMode() {
		toolbar.getChildren().remove(0, toolbar.getChildren().size());
		toolbar.getChildren().addAll(drawtxt, prevButton, nextButton, selectButton, deleteButton, rectButton, circleButton, labelButton, labeltxt, minusButton, plusButton, labelSizetxt);
		ObservableList<Node> drawObjs = drawing.getChildren();
		for (int i = drawObjs.size()-1; i >= 0 && drawObjs.get(i).getUserData() == StageName.CONDITIONS; i--) {
			areas.add(drawObjs.get(i));
			drawObjs.remove(i);
		}
	}
	
	/**
	 * Makes changes to the view when entering conditions mode from draw mode
	 */
	public void condMode() {
		toolbar.getChildren().remove(0, toolbar.getChildren().size());
		toolbar.getChildren().addAll(condtxt, prevButton, nextButton, selectButton, deleteButton, newAreaButton);
		drawing.getChildren().addAll(areas);
		areas.clear();
	}
	
	/**
	 * @return The height of the toolbar
	 */
	public double getToolbarHeight() {
		return toolbar.getHeight();
	}

	public BorderPane getRoot() {
		return root;
	}
}
