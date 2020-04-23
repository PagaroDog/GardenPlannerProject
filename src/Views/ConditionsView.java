package Views;
import Controllers.ConditionsController;
import Controllers.Controller;
import Controllers.DrawYardController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * 
 * @author Tommy White
 *
 */
public class ConditionsView extends View{
	private Stage stage;
	private Scene scene;
	private Button selectButton;
	private Button deleteButton;
	private Button labelButton;
	private Button rectButton;
	private Button circleButton;
	private Pane drawing;
	private Button next;
	private Button prev;
	private ConditionsController control;
	
	public ConditionsView(Stage stage) {
		TilePane tp = new TilePane();
		Label txt = new Label("DrawPhase");
		next = new Button("Next");
		next.setOnMouseClicked(control.getHandleNextButton());
		prev = new Button("Prev");
		prev.setOnMouseClicked(control.getHandlePrevButton());
		
		tp.getChildren().addAll(txt,prev,next);
		scene = new Scene(tp, canvasHeight,canvasWidth);
		this.stage = stage;
	}
	
	/**
	 * @return Scene object for the Conditions Areas screen
	 */
	@Override
	public Scene getScene() {
		
		return scene;
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
