package Views;

import Controllers.Controller;
import Controllers.StatisticsController;
import Controllers.SuggestionsController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class SuggestionsView extends View{
	private Button nextButton;
	private Button backButton;
	private Stage stage;
	private SuggestionsController control;
	
	public SuggestionsView(Stage stage) {
		this.stage = stage;
	}
	

	public void setup() {
		TilePane tp = new TilePane();
		Label txt = new Label("SUGGS");
		backButton = new Button("Back");
		backButton.setOnMouseClicked(control.gethandleOnBackButton());
		nextButton = new Button("Next");
		nextButton.setOnMouseClicked(control.gethandleOnNextButton());
		tp.getChildren().addAll(txt,backButton,nextButton);
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
		control = (SuggestionsController) controller;
		
	}

	@Override
	public Stage getStage() {
		return stage;
	}



}
