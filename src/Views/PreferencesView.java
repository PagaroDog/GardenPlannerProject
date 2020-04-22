package Views;
import Controllers.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	private PreferencesControl control;
	
	
	public PreferencesView(Stage stage) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/**
	 * Creates the PreferencesView scene the user will see
	 */
	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
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
		control = c;
	}
}
