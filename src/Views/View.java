package Views;
import Controllers.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;
public abstract class View {
	int canvasWidth = 2000 ;
	int canvasHeight = 1000;
	public abstract Scene getScene();
	public abstract void setController(Controller controller);
	public abstract Stage getStage();
}
