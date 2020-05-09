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
	private Label totPlants = new Label();
	private Label trees = new Label();
	private Label flowers = new Label();
	private Label shrubs = new Label();
	private Label pollinators = new Label();
	private Label numAnimal = new Label();
	private Label animalTypes = new Label();

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

		HBox plants = new HBox(totPlants, trees, flowers, shrubs);
		plants.setSpacing(hboxSpacing);

		for (int i = 0; i < plants.getChildren().size(); i++) {
			((Label) plants.getChildren().get(i)).setFont(new Font(fontSize));
		}

		vBox.getChildren().addAll(plants, pollinators, numAnimal, animalTypes);

		for (int i = 1; i < vBox.getChildren().size(); i++) {
			((Label) vBox.getChildren().get(i)).setFont(new Font(fontSize));
		}

		vBox.setPadding(new Insets(vboxSpacing, vboxSpacing, vboxSpacing, vboxSpacing));
		vBox.setSpacing(vboxSpacing);
		vBox.setStyle("-fx-background-color: rgba(158,255,174,1);");

		border.setCenter(vBox);

		scene = new Scene(border, canvasWidth, canvasHeight);
		styleScene();
	}

	public void updateStats(int numTrees, int numShrubs, int numHerbs, int pollinatorsPerTree, int pollinatorsPerShrub,
			int pollinatorsPerHerb, int animalsPerTree, int animalsPerShrub, int animalsPerHerb, int beeMin,
			int butterflyMin, int birdMin, int mammalMin) {
		int total = numTrees + numShrubs + numHerbs;
		totPlants.setText("Total Plants: " + total);
		trees.setText("Trees: " + numTrees);
		shrubs.setText("Shrubs: " + numShrubs);
		flowers.setText("Herbs: " + numHerbs);

		pollinators.setText("Estimated Pollinators Supported: "
				+ (numTrees * pollinatorsPerTree + numShrubs * pollinatorsPerShrub + numHerbs * pollinatorsPerHerb));
		numAnimal.setText("Estimated Animals Fed: "
				+ (numTrees * animalsPerTree + numShrubs * animalsPerShrub + numHerbs * animalsPerHerb));

		if (total > mammalMin) {
			animalTypes.setText("Possible Animal Types: Bees, Butterflies, Birds, Small mammals");
		} else if (total > birdMin) {
			animalTypes.setText("Possible Animal Types: Bees, Butterflies, Birds");
		} else if (total > butterflyMin) {
			animalTypes.setText("Possible Animal Types: Bees, Butterflies");
		} else if (total > beeMin) {
			animalTypes.setText("Possible Animal Types: Bees");
		} else {
			animalTypes.setText("Possible Animal Types: None (Add more plants to you garden!)");
		}
	}

}
