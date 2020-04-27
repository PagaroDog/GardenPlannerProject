package Views;
import javafx.scene.control.Button;

import Controllers.Controller;
import Controllers.StatisticsController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author Tommy White
 *
 */
public class StatisticsView extends View{
	private Button backButton;
	private Stage stage;
	private StatisticsController control;
	public StatisticsView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		
		
		TilePane tp = new TilePane();
		Label txt = new Label("Stats");
		backButton = new Button("Back");
		backButton.setOnMouseClicked(control.handleOnBackButton());
		tp.getChildren().addAll(txt,backButton);
		//scene = new Scene(tp, canvasWidth, canvasHeight);
		
		BorderPane border = new BorderPane(); 
		border.setTop(tp); 
		
		VBox vBox = new VBox(); 
		
		Label pollinators = new Label(); 
		pollinators.setText("Total Plants: , Trees: , Flowers: , Shrubs: ");
		pollinators.setPadding(new Insets(10,10,10,10));
		pollinators.setStyle("-fx-background-color: rgba(158,255,174,1);");
		
		Label totalPlants = new Label("Total Keystone Plants:"); 
		totalPlants.setPadding(new Insets(10,10,10,10));
		totalPlants.setStyle("-fx-background-color: rgba(158,255,174,1);"); 
		
		Label totalTrees = new Label("Estimated Animals Fed:"); 
		totalTrees.setPadding(new Insets(10,10,10,10));
		totalTrees.setStyle("-fx-background-color: rgba(158,255,174,1);");
		
		
		Label waterSaved = new Label("Types of Animals Fed:"); 
		waterSaved.setPadding(new Insets(10,10,10,10));
		waterSaved.setStyle("-fx-background-color: rgba(158,255,174,1);");
		
		
		
		
		vBox.getChildren().addAll(pollinators, totalPlants, totalTrees, waterSaved); 
		
		
		border.setLeft(vBox);
		
		scene = new Scene(border, canvasWidth, canvasHeight); 
		
		
	}

	/**
	 * @return Scene object for the Draw Yard screen
	 */
	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public void setController(Controller controller) {
		control = (StatisticsController) controller;
		
	}

	@Override
	public Stage getStage() {
		return stage;
	}

}
