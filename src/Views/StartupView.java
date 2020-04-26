package Views;
import Controllers.Controller;
import Controllers.StartupController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
/**
 * @author Matt Cohen
 * @author Brandon Wu
 * 
 */
public class StartupView extends View{
	private Label label;
	private Button newButton;
	private Button loadButton;
	private Button tutorialButton;
	private Image backgroundImage;
	private TilePane Buttons;
	private Stage stage;
	private StartupController suc;
	public StartupView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		label = new Label("StartUp");

		Buttons = new TilePane();
		tutorialButton = new Button("Tutorial");
		tutorialButton.setOnMouseClicked(suc.handleOnTutorialButton());
		
		newButton = new Button("New");
		newButton.setOnMouseClicked(suc.handleOnNewButton());
		
		loadButton = new Button("Load");
		loadButton.setOnMouseClicked(suc.handleOnLoadButton());
		Buttons.getChildren().addAll(label,newButton,loadButton,tutorialButton);
		scene = new Scene(Buttons, canvasWidth, canvasHeight);
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}
	public Stage getStage() {
		return stage;
	}
	@Override
	public void setController(Controller controller) {
		this.suc = (StartupController)controller;
	}
}
