package Views;

import Controllers.Controller;
import Controllers.SuggestionsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
		
		BorderPane border = new BorderPane();
		HBox nav = top();
		border.setTop(nav);
		scene = new Scene(border,canvasHeight,canvasWidth);
	}
	/**
	 * Creates the navigation portion of BoarderPane. Assigned to the Top of the BoarderPane.
	 */
	public HBox top() {
		HBox nav = new HBox();
		nav.setPadding(new Insets(15,300,15,300));
		
		Label txt = new Label("SUGGS");
		backButton = new Button("Back");
		backButton.setOnMouseClicked(control.gethandleOnBackButton());
		nextButton = new Button("Next");
		nextButton.setOnMouseClicked(control.gethandleOnNextButton());
	
		nav.getChildren().addAll(backButton,txt,nextButton);
		nav.setStyle("-fx-background-color: rgba(255, 222, 133,1);");
		nav.setSpacing(15);
		nav.setPrefHeight(20);
		nav.setPrefWidth(canvasWidth);
		nav.setAlignment(Pos.CENTER);
		return nav;
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
