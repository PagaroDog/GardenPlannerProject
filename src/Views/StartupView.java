package Views;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartupView extends View{
	private Label label;
	private Button newButton;
	private Button loadButton;
	private Button tutorialButton;
	private Image backgroundImage;
	
	private Stage stage;
	public StartupView(Stage stage) {
		this.stage = stage;
	}
	@Override
	public Scene getScene() {
		label = new Label("text");
		Pane root = new Pane();
		root.getChildren().add(label);
		return new Scene(root,canvasHeight,canvasWidth);
	}
	
}
