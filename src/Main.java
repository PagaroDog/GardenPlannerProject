
import java.util.HashMap;
import java.util.Map;

import Views.ConditionsView;
import Views.DrawYardView;
import Views.GardenView;
import Views.PreferencesView;
import Views.SaveView;
import Views.StartupView;
import Views.StatisticsView;
import Views.TutorialView;
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
		scenes.put(StageName.STATS, new StatisticsView(stage).getScene());
		scenes.put(StageName.DESIGN, new GardenView(stage).getScene());
		scenes.put(StageName.SAVE, new SaveView(stage).getScene());
		scenes.put(StageName.PREFERENCES, new PreferencesView(stage).getScene());
		
		
		// Start with the main scene
		stage.setScene(scenes.get(StageName.WELCOME));
		stage.setTitle("Garden Simulator");
		stage.show();	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}

=======
import java.util.HashMap;
import java.util.Map;

import Model.StageName;
import Views.ConditionsView;
import Views.DrawYardView;
import Views.GardenView;
import Views.PreferencesView;
import Views.SaveView;
import Views.StartupView;
import Views.StatisticsView;
import Views.TutorialView;
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
		scenes.put(StageName.STATS, new StatisticsView(stage).getScene());
		scenes.put(StageName.DESIGN, new GardenView(stage).getScene());
		scenes.put(StageName.SAVE, new SaveView(stage).getScene());
		scenes.put(StageName.PREFERENCES, new PreferencesView(stage).getScene());
		
		
		// Start with the main scene
		stage.setScene(scenes.get(StageName.WELCOME));
		stage.setTitle("Garden Simulator");
		stage.show();	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}


