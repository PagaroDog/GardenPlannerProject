package Views;
import javafx.scene.control.Button;

import Controllers.Controller;
import Controllers.DrawYardController;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 
 * @author Tommy White
 *
 */
public class DrawYardView extends View{
	private Stage stage;
	private Button rectButton;
	private Button circleButton;
	private Button importButton;
	private BorderPane root;
	private Pane drawing;
	private Pane toolbar;
	private Button next;
	private Button prev;
	private Button select;
	private Button rect;
	private Button circle;
	private Button label;
	private DrawYardController control;
	
	public DrawYardView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		toolbar  = new TilePane();
		Label txt = new Label("DrawPhase");
		next = new Button("Next");
		next.setOnMouseClicked(control.getHandleNextButton());
		prev = new Button("Prev");
		prev.setOnMouseClicked(control.getHandlePrevButton());
		select = new Button("Select");
		select.setOnMouseClicked(control.getHandleOnSelectButton());
		rect = new Button("Rectangle");
		rect.setOnMouseClicked(control.getHandleOnRectButton());
		circle = new Button("Circle");
		circle.setOnMouseClicked(control.getHandleOnCircleButton());
		label = new Button("Label");
		label.setOnMouseClicked(control.getHandleOnLabelButton());
		toolbar.getChildren().addAll(txt,prev,next, select, rect, circle, label);
		
		drawing = new Pane();
		drawing.setOnMousePressed(control.getHandleOnPressPane());
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
	 * Sets control to dyc
	 */
	public void setController(Controller c) {
		control = (DrawYardController) c;
	}

	@Override
	public Stage getStage() {
		return stage;
	}
	
	public void addRectangle(double x0, double y0, double x1, double y1) {
		System.out.println("1");
		double topLeftX = Math.min(x0, x1);
		double topLeftY = Math.min(y0, y1);
		double width = topLeftX + Math.max(x0, x1);
		double height = topLeftX + Math.max(y0, y1);
		Rectangle rect = new Rectangle(topLeftX, topLeftY, width, height);
		drawing.getChildren().add(rect);
	}
}
