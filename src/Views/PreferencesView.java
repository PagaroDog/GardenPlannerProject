package Views;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class PreferencesView extends View{
	private Button nextButton;
	private Button backButton;
	private ComboBox<String> soil;
	private ComboBox<String> water;
	private ComboBox<String> sun;
	private ComboBox<String> color;
	private ComboBox<String> bloom;
	
	
	public PreferencesView(Stage stage) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
	}

	public Button getNextButton() {
		return nextButton;
	}
	public Button getBackButton() {
		return backButton;
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
}
