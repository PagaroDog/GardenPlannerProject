import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartupView extends View{
	private Label label;
	
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
