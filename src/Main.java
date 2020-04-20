import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application{
	private static Map<StageName, Scene> scenes = new HashMap<>();
	
	@Override
	public void start(Stage stage) throws Exception {
		
		scenes.put(StageName.WELCOME, new StartupView(stage).getScene());
		scenes.put(StageName.TUTORIAL, new TutorialView(stage).getScene());
		scenes.put(StageName.DRAW, new DrawYardView(stage).getScene());
		scenes.put(StageName.CONDITIONS, new ConditionsView(stage).getScene());
		
		// Start with the main scene
		stage.setScene(scenes.get(StageName.WELCOME));
		stage.setTitle("Garden Simulator");
		stage.show();	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}

