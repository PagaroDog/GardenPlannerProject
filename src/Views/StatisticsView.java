package Views;

import java.util.HashSet;

import Controllers.StatisticsController;
import Model.SeasonEnum;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
 * @author Brandon Wu
 */
public class StatisticsView extends View<StatisticsController> {
	private final double HBOX_SPACING = 70;
	private final double VBOX_SPACING = 50;
	private double defaultFontSize;
	private double smallFontSize;
	private double evenSmallerFontSize;
	private double smallestFontSize;
	private Label totPlants = new Label();
	private Label trees = new Label();
	private Label flowers = new Label();
	private Label shrubs = new Label();
	private Label vines = new Label();
	private Label uTotPlants = new Label();
	private Label uTrees = new Label();
	private Label uFlowers = new Label();
	private Label uShrubs = new Label();
	private Label uVines = new Label();
	private Label colors = new Label();
	private Label bloomingSeasons = new Label();
	private Label gardenCover = new Label();

	private Label plantList = new Label();
	private Label tips = new Label();

	private boolean smallScreen = false;

	private final double SMALL_HEIGHT = 700;
	private final double SMALL_WIDTH = 1200;
	private final double SMALL_DEFAULT_FONT = 15;
	private final double STANDARD_DEFAULT_FONT = 20;

	private final double SMALL_FONT_DIFFERENCE = 5;
	private final double SMALLER_FONT_DIFFERENCE = 3;
	private final double SMALLEST_FONT_DIFFERENCE = 3;

	private final double VBOX_RED = 158;
	private final double VBOX_GREEN = 255;
	private final double VBOX_BLUE = 174;
	private final double VBOX_OPACITY = 1;

	private final double SMALL_DEFAULT_MAX = 20;
	private final double SMALL_SMALL_MAX = 50;
	private final double NORM_DEFAULT_MAX = 30;
	private final double NORM_SMALL_MAX = 60;
	private final double NORM_SMALLER_MAX = 100;

	private final int ZERO = 0;
	private final int FEW_PLANTS = 3;

	public StatisticsView(Stage stage) {
		this.stage = stage;
		if (canvasHeight < SMALL_HEIGHT || canvasWidth < SMALL_WIDTH) {
			defaultFontSize = SMALL_DEFAULT_FONT;
			smallScreen = true;
		} else {
			defaultFontSize = STANDARD_DEFAULT_FONT;
		}
		smallFontSize = defaultFontSize - SMALL_FONT_DIFFERENCE;
		evenSmallerFontSize = smallFontSize - SMALLER_FONT_DIFFERENCE;
		smallestFontSize = evenSmallerFontSize - SMALLEST_FONT_DIFFERENCE;
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
		ScrollPane scroll = new ScrollPane(vBox);
		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);

		HBox plants = new HBox(totPlants, trees, flowers, shrubs, vines);

		HBox uPlants = new HBox(uTotPlants, uTrees, uFlowers, uShrubs, uVines);
		plants.setSpacing(HBOX_SPACING);
		uPlants.setSpacing(HBOX_SPACING);

		for (int i = 0; i < plants.getChildren().size(); i++) {
			((Label) plants.getChildren().get(i)).setFont(new Font(defaultFontSize));
			((Label) plants.getChildren().get(i)).setWrapText(true);
			((Label) uPlants.getChildren().get(i)).setFont(new Font(defaultFontSize));
			((Label) uPlants.getChildren().get(i)).setWrapText(true);
		}

		vBox.getChildren().addAll(plants, uPlants, gardenCover, colors, bloomingSeasons, plantList, tips);

		for (int i = 2; i < vBox.getChildren().size(); i++) {
			((Label) vBox.getChildren().get(i)).setFont(new Font(defaultFontSize));
			((Label) vBox.getChildren().get(i)).setWrapText(true);
		}

		vBox.setPadding(new Insets(VBOX_SPACING, VBOX_SPACING, VBOX_SPACING, VBOX_SPACING));
		vBox.setSpacing(VBOX_SPACING);
		vBox.setStyle(String.format("-fx-background-color: rgba( %f, %f, %f, %f);", VBOX_RED, VBOX_GREEN, VBOX_BLUE,
				VBOX_OPACITY));

		border.setCenter(scroll);

		scene = new Scene(border, canvasWidth, canvasHeight);
		styleScene();
	}

	/**
	 * Modifies the labels to show statistics.
	 * 
	 * @param numTrees             number of trees in garden
	 * @param numShrubs            number of shrubs in garden
	 * @param numHerbs             number of herbs in garden
	 * @param numVines             number of vine plants in garden
	 * @param colorSet             set of colors of plant blooms from the plants in
	 *                             the garden
	 * @param seasons              set of the seasons the plants bloom in the garden
	 * @param allNames             set of all scientific names of the plants in the
	 *                             garden
	 * @param gardenCoveredPercent percent of garden covered by plants in percent
	 *                             form (EX. 1.23 = 1.23%)
	 * @param uTrees               number of unique trees in garden
	 * @param uShrubs              number of unique shrubs in garden
	 * @param uHerbs               number of unique herbs in garden
	 * @param uVines               number of unique vine plants in garden
	 */
	public void updateStats(int numTrees, int numShrubs, int numHerbs, int numVines, HashSet<String> colorSet,
			HashSet<SeasonEnum> seasons, HashSet<String> allNames, double gardenCoveredPercent, int uTrees, int uShrubs,
			int uHerbs, int uVines) {
		int total = numTrees + numShrubs + numHerbs + numVines;
		totPlants.setText("Total Plants: " + total);
		trees.setText("Tree Count: " + numTrees);
		shrubs.setText("Shrub Count: " + numShrubs);
		flowers.setText("Herb Count: " + numHerbs);
		vines.setText("Vine Count: " + numVines);
		int uTotal = uTrees + uShrubs + uHerbs + uVines;
		this.uTotPlants.setText("Unique Plants: " + uTotal);
		this.uTrees.setText("Unique Trees: " + uTrees);
		this.uShrubs.setText("Unique Shrubs: " + uShrubs);
		this.uFlowers.setText("Unique Herbs: " + uHerbs);
		this.uVines.setText("Unique Vines: " + uVines);

		colors.setText("Potential Bloom Colors: " + colorSet.toString().replace("[", "").replace("]", ""));

		bloomingSeasons.setText("Blooming seasons: " + seasons.toString().replace("[", "").replace("]", ""));

		String percent = String.format("%2.2f", gardenCoveredPercent) + "%";
		gardenCover.setText("Garden Covered: " + percent);

		plantList.setText("List of Plants in your Garden: " + allNames.toString().replace("[", "").replace("]", ""));

		if (uTotal == ZERO) {
			tips.setText("Add some plants! Bring your yard to life!");
		} else if (uTotal < FEW_PLANTS) {
			tips.setText("We all have our favorite plants, but a greater variety brings more life!");
		} else {
			tips.setText("Nice work! You and nature will be pleased!");
		}

		if (smallScreen) {
			if (uTotal < SMALL_DEFAULT_MAX) {
				plantList.setFont(new Font(defaultFontSize));
			} else if (uTotal < SMALL_SMALL_MAX) {
				plantList.setFont(new Font(smallFontSize));
			} else {
				plantList.setFont(new Font(evenSmallerFontSize));
			}
		} else {
			if (uTotal < NORM_DEFAULT_MAX) {
				plantList.setFont(new Font(defaultFontSize));
			} else if (uTotal < NORM_SMALL_MAX) {
				plantList.setFont(new Font(smallFontSize));
			} else if (uTotal < NORM_SMALLER_MAX) {
				plantList.setFont(new Font(evenSmallerFontSize));
			} else {
				plantList.setFont(new Font(smallestFontSize));
			}
		}
	}

}
