package Views;
import java.awt.Button;

import Controllers.TutorialController;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * @author Josh Stone
* this class is the view for the Tutorial screen
*/
public class TutorialView extends View{
	private Stage stage;
	private Button prevButton;
	private Button nextButton;
	private TutorialController control;
	
	public TutorialView(Stage stage) {
		this.stage = stage;
	}
	/**
	 * @return Scene object for the Tutorial screen
	 */
	@Override
	public Scene getScene() {
		return null;
	}
	
	/**
	 * Sets control to tc
	 */
	public void setController(TutorialController tc) {
		
	}

}
