package Views;
import javafx.scene.control.Button;

import Controllers.Controller;
import Controllers.StatisticsController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The View class for the Statistics screen.
 * Holds graphical data and defines graphical logic.
 * @author Ian McCabe
 * @author Tommy White
 *
 */
public class StatisticsView extends View{
	
	private Button backButton;
	private Stage stage;
	private StatisticsController control;
	private double hboxSpacing = 70;
	private double vboxSpacing = 50;
	private double fontSize = 25;
	private final double bottomVPadding = 10;
	private final double bottomHPadding = 10;
	
	public StatisticsView(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * Initial setup of this class that could not be completed in the
	 * constructor since the controller had not yet been set
	 */
	@Override
	public void setup() {
		BorderPane bottom = new BorderPane();
		Label txt = new Label("Garden Statistics");
		txt.setFont(new Font(fontSize));
		backButton = new Button("Back");
		backButton.setOnMouseClicked(control.handleOnBackButton());
		bottom.setPadding(new Insets(bottomVPadding, bottomHPadding, bottomVPadding, bottomHPadding));
	    bottom.setStyle("-fx-background-color: rgba(168,158,255,1);");
		bottom.setLeft(backButton);
		bottom.setCenter(txt);
		
		BorderPane border = new BorderPane(); 
		border.setBottom(bottom); 
		
		VBox vBox = new VBox(); 
		
		Label totPlants = new Label("Total Plants: ");
		
		Label trees = new Label("Trees: ");
		
		Label flowers = new Label("Flowers: ");
		
		Label shrubs = new Label("Shrubs: "); 
		
		HBox plants = new HBox(totPlants, trees, flowers, shrubs);
		plants.setSpacing(hboxSpacing);
		
		for (int i = 0; i < plants.getChildren().size(); i++) {
			((Label) plants.getChildren().get(i)).setFont(new Font(fontSize));
		}
		
		Label keyPlants = new Label("Keystone Plants: "); 
		
		Label pollinators = new Label("Estimated Pollinators Supported: "); 
		
		Label numAnimal = new Label("Estimated Animals Fed: ");
		
		Label animalTypes = new Label("Types of Animals Fed: ");
		
		vBox.getChildren().addAll(plants, keyPlants, pollinators, numAnimal, animalTypes);
		
		for (int i = 1; i < vBox.getChildren().size(); i++) {
			((Label) vBox.getChildren().get(i)).setFont(new Font(fontSize));
		}
		
		vBox.setPadding(new Insets(vboxSpacing, vboxSpacing, vboxSpacing, vboxSpacing));
		vBox.setSpacing(vboxSpacing);
		vBox.setStyle("-fx-background-color: rgba(158,255,174,1);");
		
		border.setCenter(vBox);
		
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
