package Views;
import java.awt.Button;

import Controllers.Controller;
import Controllers.DrawYardController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
	private DrawYardController control;
	
	public DrawYardView(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * @return Scene object for the Draw Yard screen
	 */
	@Override
	public Scene getScene() {
		return null;
	}
	
	/**
	 * Sets control to dyc
	 */
	public void setController(Controller c) {
		control = (DrawYardController) c;
	}

	@Override
	public Stage getStage() {
		// TODO Auto-generated method stub
		return null;
	}
}
