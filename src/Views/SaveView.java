package Views;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import Controllers.SaveController;

public class SaveView extends View{

	private Stage stage;
	private Button prevButton;
	private Button nextButton;
	private SaveController control;
	
	public SaveView(Stage stage) {
		this.stage = stage;
	}
	/**
	 * @return Scene object for the Save screen
	 */
	@Override
	public Scene getScene() {
		return null;
	}
	
	/**
	 * Sets control to sc
	 */
	public void setController(SaveController sc) {
		
	}

}
