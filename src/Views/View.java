package Views;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import Controllers.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;
public abstract class View {
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int canvasWidth = gd.getDisplayMode().getWidth() - 150;
	int canvasHeight = gd.getDisplayMode().getHeight() - 150;
	Scene scene; 
	public abstract Scene getScene();
	public abstract void setController(Controller controller);
	public abstract Stage getStage();
	public abstract void setup();
}
