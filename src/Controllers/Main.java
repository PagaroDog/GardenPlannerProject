package Controllers;

import java.util.HashMap;
import java.util.Map;

import Model.Model;
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


/**
 * @author Brandon Wu
 */
public class Main extends Application{
	private static Map<StageName, Scene> scenes = new HashMap<>();
	private Model model;
	private StartupView startView;
	private TutorialView tutView;
	private DrawYardView dyView;
	private ConditionsView condView;
	private StatisticsView statView;
	private GardenView gardenView;
	private SaveView saveView;
	private PreferencesView prefView;
	private StartupController startControl;
	private TutorialController tutControl;
	private DrawYardController dyControl;
	private ConditionsController condControl;
	private StatisticsController statControl;
	private GardenController gardenControl;
	private SaveController saveControl;
	private PreferencesController prefControl;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		model = new Model();
		startView = new StartupView(stage);
		tutView = new TutorialView(stage);
		dyView = new DrawYardView(stage);
		condView = new ConditionsView(stage);
		statView = new StatisticsView(stage);
		gardenView = new GardenView(stage);
		saveView = new SaveView(stage);
		prefView = new PreferencesView(stage);
		startControl = new StartupController(model, startView);
		tutControl = new TutorialController(model, tutView);
		dyControl = new DrawYardController(model, dyView);
		condControl = new ConditionsController(model, condView);
		statControl = new StatisticsController(model, statView);
		gardenControl = new GardenController(model, gardenView);
		saveControl = new SaveController(model, saveView);
		prefControl = new PreferencesController(model, prefView);
		
		scenes.put(StageName.WELCOME, startView.getScene());
		scenes.put(StageName.TUTORIAL, tutView.getScene());
		scenes.put(StageName.DRAW, dyView.getScene());
		scenes.put(StageName.CONDITIONS, condView.getScene());
		scenes.put(StageName.STATS, statView.getScene());
		scenes.put(StageName.DESIGN, gardenView.getScene());
		scenes.put(StageName.SAVE, saveView.getScene());
		scenes.put(StageName.PREFERENCES, prefView.getScene());
		
		
		// Start with the main scene
		stage.setScene(scenes.get(StageName.WELCOME));
		stage.setTitle("Garden Simulator");
		stage.show();	}
	
	public static Map<StageName,Scene> getScenes(){
		return scenes;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}


