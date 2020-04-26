package Views;
import Controllers.Controller;
import Controllers.TutorialController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
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
	private TilePane Buttons;
	
	public TutorialView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		Buttons = new TilePane();
		Label txt = new Label("Tutorial");
		nextButton = new Button("Next");
		nextButton.setOnMouseClicked(control.getHandleOnNextButton());
		
		prevButton = new Button("Prev");
		prevButton.setOnMouseClicked(control.getHandleOnPrevButton());
		
		Buttons.getChildren().addAll(txt,nextButton,prevButton);
		scene = new Scene(Buttons, canvasWidth, canvasHeight);
	}
	
	/**
	 * @return Scene object for the Tutorial screen
	 */
	@Override
	public Scene getScene() {
		return scene;
	}
	
	/**
	 * Sets control to tc
	 */

	@Override
	public void setController(Controller controller) {
		control = (TutorialController)controller;
		
	}
	@Override
	public Stage getStage() {
		return stage;
	}

}
