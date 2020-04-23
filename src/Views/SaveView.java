package Views;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import Controllers.Controller;
import Controllers.SaveController;

/**
 * @author Josh Stone
* this class is the view for the Save screen
*/
public class SaveView extends View{

	private Stage stage;
	private Button prevButton;
	private Button nextButton;
	private SaveController control;
	
	public SaveView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		TilePane tp = new TilePane();
		Label txt = new Label("Save");
		prevButton = new Button("prev");
		prevButton.setOnMouseClicked(control.getHandleOnPrevButton());
		tp.getChildren().addAll(txt,prevButton);
		scene = new Scene(tp,canvasHeight,canvasWidth);
	}
	/**
	 * @return Scene object for the Save screen
	 */
	@Override
	public Scene getScene() {
		return scene;
	}
	
	/**
	 * Sets control to sc
	 */

	@Override
	public void setController(Controller controller) {
		control = (SaveController)controller;
		
	}
	@Override
	public Stage getStage() {
		return stage;
	}

}
