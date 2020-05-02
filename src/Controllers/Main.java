package Controllers;

import java.util.HashMap;
import java.util.Map;

import Model.Model;
import Model.StageName;
import Views.DrawYardView;
import Views.GardenView;
import Views.Images;
import Views.PreferencesView;
import Views.SaveView;
import Views.StartupView;
import Views.StatisticsView;
import Views.SuggestionsView;
import Views.TutorialView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Brandon Wu
 */
public class Main extends Application {
	private static Map<StageName, Scene> scenes = new HashMap<>();
	private Model model;
	private StartupView startView;
	private TutorialView tutView;
	private DrawYardView dyView;
	private StatisticsView statView;
	private GardenView gardenView;
	private SaveView saveView;
	private SuggestionsView suggView;
	private PreferencesView prefView;
	private StartupController startControl;
	private TutorialController tutControl;
	private DrawYardController dyControl;
	private StatisticsController statControl;
	private GardenController gardenControl;
	private SaveController saveControl;
	private PreferencesController prefControl;
	private SuggestionsController suggControl;
	private Images imgs;

	@Override
	public void start(Stage stage) throws Exception {

		model = new Model();
		imgs = new Images("Resources/imgs/");
		startView = new StartupView(stage);
		tutView = new TutorialView(stage);
		dyView = new DrawYardView(stage);
		statView = new StatisticsView(stage);
		gardenView = new GardenView(stage, imgs);
		saveView = new SaveView(stage);
		prefView = new PreferencesView(stage);
		suggView = new SuggestionsView(stage, imgs);
		startControl = new StartupController(model, startView, this);
		tutControl = new TutorialController(model, tutView, this);
		dyControl = new DrawYardController(model, dyView, this);
		statControl = new StatisticsController(model, statView, this);
		gardenControl = new GardenController(model, gardenView, this);
		saveControl = new SaveController(model, saveView, this);
		prefControl = new PreferencesController(model, prefView, this);
		suggControl = new SuggestionsController(model, suggView, this);

		scenes.put(StageName.WELCOME, startView.getScene());
		scenes.put(StageName.TUTORIAL, tutView.getScene());
		scenes.put(StageName.DRAW, dyView.getScene());
		scenes.put(StageName.STATS, statView.getScene());
		scenes.put(StageName.DESIGN, gardenView.getScene());
		scenes.put(StageName.SAVE, saveView.getScene());
		scenes.put(StageName.PREFERENCES, prefView.getScene());
		scenes.put(StageName.SUGGESTIONS, suggView.getScene());

		// Start with the main scene
		stage.setScene(scenes.get(StageName.WELCOME));
		stage.setTitle("My Native Garden");
		stage.show();
		startView.start();
	}

	public static Map<StageName, Scene> getScenes() {
		return scenes;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public DrawYardController getDyControl() {
		return dyControl;
	}

	public PreferencesController getPrefControl() {
		return prefControl;
	}

	public GardenController getGardenControl() {
		return gardenControl;
	}

}