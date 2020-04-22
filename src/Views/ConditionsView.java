package Views;
import Controllers.ConditionsController;
import Controllers.Controller;
import Controllers.DrawYardController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Tommy White
 *
 */
public class ConditionsView extends View{
	private Stage stage;
	private Button selectButton;
	private Button deleteButton;
	private Button labelButton;
	private Button rectButton;
	private Button circleButton;
	private Pane drawing;
	private ConditionsController control;
	
	public ConditionsView(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * @return Scene object for the Conditions Areas screen
	 */
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Sets control to cc
	 */
	public void setController(Controller c) {
		control = (ConditionsController) c;
	}

	@Override
	public Stage getStage() {
		// TODO Auto-generated method stub
		return null;
	}
}
