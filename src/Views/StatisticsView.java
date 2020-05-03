package Views;

import Controllers.StatisticsController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The View class for the Statistics screen. Holds graphical data and defines
 * graphical logic.
 * 
 * @author Ian McCabe
 * @author Tommy White
 *
 */
public class StatisticsView extends View<StatisticsController> {
	private double hboxSpacing = 70;
	private double vboxSpacing = 50;
	private double fontSize = 25;

	public StatisticsView(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Initial setup of this class that could not be completed in the constructor
	 * since the controller had not yet been set
	 */
	@Override
	public void setup() {
		BorderPane bottom = createNavigationBar("Back to Garden", "Garden Statistics", control.handleOnBackButton());

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

}
