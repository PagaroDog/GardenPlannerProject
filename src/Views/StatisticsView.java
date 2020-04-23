package Views;
import javafx.scene.control.Button;

import Controllers.Controller;
import Controllers.StatisticsController;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * 
 * @author Tommy White
 *
 */
public class StatisticsView extends View{
	private Button backButton;
	private Stage stage;
	private StatisticsController control;
	public StatisticsView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		TilePane tp = new TilePane();
		Label txt = new Label("Stats");
		backButton = new Button("Back");
		backButton.setOnMouseClicked(control.handleOnBackButton());
		tp.getChildren().addAll(txt,backButton);
		scene = new Scene(tp,canvasHeight,canvasWidth);
	}

	/**
	 * @return Scene object for the Draw Yard screen
	 */
	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public void setController(Controller controller) {
		control = (StatisticsController) controller;
		
	}

	@Override
	public Stage getStage() {
		return stage;
	}

}
