package Views;
import Controllers.Controller;
import Controllers.PreferencesController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
/**
 * Creates the scene for the preferences view. 
 * @author Brandon Wu
 *
 */
public class PreferencesView extends View{
	private Button nextButton;
	private Button backButton;
	private ComboBox<String> soil;
	private ComboBox<String> water;
	private ComboBox<String> sun;
	private ComboBox<String> color;
	private ComboBox<String> bloom;
	private PreferencesController control;
	private Stage stage;
	
	
	public PreferencesView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		TilePane tp = new TilePane();
		Label txt = new Label("Pref");
		nextButton = new Button("Next");
		nextButton.setOnMouseClicked(control.gethandleOnNextButton());
		
		backButton = new Button("Back");
		backButton.setOnMouseClicked(control.gethandleOnBackButton());
		
		tp.getChildren().addAll(txt,backButton,nextButton);
		scene = new Scene(tp,canvasHeight,canvasWidth);
	}
	
	@Override
	/**
	 * Creates the PreferencesView scene the user will see
	 */
	public Scene getScene() {
		return scene;
	}



	public ComboBox<String> getSoil() {
		return soil;
	}

	public ComboBox<String> getWater() {
		return water;
	}

	public ComboBox<String> getSun() {
		return sun;
	}

	public ComboBox<String> getColor() {
		return color;
	}

	public ComboBox<String> getBloom() {
		return bloom;
	}

	@Override
	public void setController(Controller c) {
		control =  (PreferencesController)c;
	}

	@Override
	public Stage getStage() {
		return this.stage;
	}
}
