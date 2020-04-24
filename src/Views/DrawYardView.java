package Views;
import javafx.scene.control.Button;

import java.util.ArrayList;

import Controllers.Controller;
import Controllers.DrawYardController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
	private Pane toolbar;
	private Button nextButton;
	private Button prevButton;
	private Button selectButton;
	private Button rectButton;
	private Button circleButton;
	private Button labelButton;
	private Button deleteButton;
	private DrawYardController control;
	
	public DrawYardView(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * Initial setup of this class that could not be completed in the constructor since the
	 * controller had not yet been set
	 */
	public void setup() {
		toolbar  = new TilePane();
		Label txt = new Label("DrawPhase");
		nextButton = new Button("Next");
		nextButton.setOnMouseClicked(control.getHandleNextButton());
		prevButton = new Button("Prev");
		prevButton.setOnMouseClicked(control.getHandlePrevButton());
		selectButton = new Button("Select");
		selectButton.setOnMouseClicked(control.getHandleOnSelectButton());
		rectButton = new Button("Rectangle");
		rectButton.setOnMouseClicked(control.getHandleOnRectButton());
		circleButton = new Button("Circle");
		circleButton.setOnMouseClicked(control.getHandleOnCircleButton());
		labelButton = new Button("Label");
		labelButton.setOnMouseClicked(control.getHandleOnLabelButton());
		deleteButton = new Button("Delete");
		deleteButton.setOnMousePressed(control.getHandleOnDeleteButton());
		toolbar.getChildren().addAll(txt,prevButton,nextButton, selectButton, rectButton, circleButton, labelButton, deleteButton);
		
		drawing = new Pane();
		drawing.setOnMousePressed(control.getHandleOnPressPane());
		drawing.setOnMouseDragged(control.getHandleOnDragPane());
		drawing.setOnMouseReleased(control.getHandleOnReleasePane());
		
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

	/**
	 * Called when user clicks on the drawing Pane.
	 * Creates a new Ractangle object and adds it to the
	 * shapes ArrayList, as well as the 
	 * @param x
	 * @param y
	 */
	public Node addRectangle(double x, double y) {
		Rectangle rect = new Rectangle(x, y, 10, 10);
		rect.setFill(Color.TRANSPARENT);
		rect.setStroke(Color.BLACK);
		rect.setOnMouseClicked(control.getHandleOnPressShape());
		rect.setOnMouseDragged(control.getHandleOnDragRectangle());
		drawing.getChildren().add(rect);
		return rect;
	}

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
	
	public void moveRectangle(Rectangle rect, double x, double y) {
		rect.setX(x);
		rect.setY(y);
	}
	
	public Node addCircle(double x, double y) {
		Ellipse circle = new Ellipse(x, y, 10, 10);
		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.BLACK);
		circle.setOnMouseClicked(control.getHandleOnPressShape());
		circle.setOnMouseDragged(control.getHandleOnDragCircle());
		drawing.getChildren().add(circle);
		return circle;
	}

	public void updateCircle(Ellipse circle, double x, double y) {
		double radiusX = Math.abs(circle.getCenterX() - x);
		double radiusY = Math.abs(circle.getCenterY() - y);
		circle.setRadiusX(radiusX);
		circle.setRadiusY(radiusY);
	}
	
	public void moveCircle(Ellipse circle, double x, double y) {
		circle.setCenterX(x);
		circle.setCenterY(y);
	}
	
	public void deleteShape(Node node) {
		drawing.getChildren().remove(node);
//		shapes.remove(index);
//		for (int i = index; i < shapes.size(); i++) {
//			shapes.get(i).setUserData(i);
//		}
	}
}
