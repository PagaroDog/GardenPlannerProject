package Views;
import javafx.scene.control.Button;

import Controllers.Controller;
import Controllers.DrawYardController;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
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
	private Pane drawing;
	private Button next;
	private Button prev;
	private DrawYardController control;
	
	public DrawYardView(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * @return Scene object for the Draw Yard screen
	 */
	@Override
	public Scene getScene() {
		TilePane tp = new TilePane();
		Label txt = new Label("DrawPhase");
		next = new Button("Next");
		next.setOnMouseClicked(control.getHandleNextButton());
		prev = new Button("Prev");
		prev.setOnMouseClicked(control.getHandlePrevButton());
		
		tp.getChildren().addAll(txt,prev,next);
		return new Scene(tp,canvasHeight,canvasWidth);
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
}
