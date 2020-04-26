package Views;
import java.util.Collection;

import Controllers.Controller;
import Controllers.GardenController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class GardenView extends View{
	private Collection plantImages;
	private FlowPane suggestedFlowPane;
	private TilePane seasonTilePane;
	private TilePane yearTilePane;
	private TilePane statsTilePane;
	private TilePane toolbarTilePane;
	private Image backyardImage;
	private GardenController control;
	private Stage stage;
	private Button stats;
	private Button pref;
	private Button save;
	
	public GardenView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup () {
		TilePane tp = new TilePane();
		Label txt = new Label("Design");
		stats = new Button("Stats");
		stats.setOnMouseClicked(control.handleOnStatsButton());
		
		save = new Button("Save");
		save.setOnMouseClicked(control.handleOnSaveButton());
		
		pref = new Button("Pref");
		pref.setOnMouseClicked(control.handleOnPrefButton());
		
		tp.getChildren().addAll(txt,pref,stats,save);
		scene = new Scene(tp, canvasWidth, canvasHeight);
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public void setController(Controller controller) {
		control = (GardenController) controller;
		
	}

	@Override
	public Stage getStage() {
		return stage;
	}

}
