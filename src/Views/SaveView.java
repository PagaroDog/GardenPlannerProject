package Views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import Controllers.Controller;
import Controllers.SaveController;

/**
 * 
 * The view for the Save screen.
 * 
 * @author Josh Stone
 */
public class SaveView extends View {

	private Stage stage;
	private Button prevButton;
	private Button nextButton;
	private Button fileButton;
	private Button PNGButton;
	private SaveController saveC;
	private TilePane Buttons;

	public SaveView(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Initial setup of this class that could not be completed in the constructor
	 * since the controller had not yet been set
	 */
	@Override
	public void setup() {
		TilePane tp = new TilePane();
		Label txt = new Label("Save");
		prevButton = new Button("prev");

		prevButton.setOnMouseClicked(saveC.getHandleOnPrevButton());

		fileButton = new Button("Save as .garden");
		fileButton.setOnMouseClicked(saveC.getHandleOnFileButton());

		PNGButton = new Button("Save as .png");
		PNGButton.setOnMouseClicked(saveC.getHandleOnPNGButton());

		tp.getChildren().addAll(txt, prevButton, fileButton, PNGButton);
		scene = new Scene(tp, canvasWidth, canvasHeight);

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
		saveC = (SaveController) controller;

	}

	@Override
	public Stage getStage() {
		return stage;
	}

}
