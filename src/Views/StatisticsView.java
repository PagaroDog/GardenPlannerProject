package Views;

import java.util.HashSet;

import Controllers.StatisticsController;
import Model.SeasonEnum;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
 *
 */
public class StatisticsView extends View<StatisticsController> {
	private double hboxSpacing = 70;
	private double vboxSpacing = 50;
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
	public StatisticsView(Stage stage) {
		this.stage = stage;
		if(canvasHeight < 700 || canvasWidth < 1200) {
			defaultFontSize = 15;
			smallScreen = true;
		}else {
			defaultFontSize = 20;
		}
		smallFontSize = defaultFontSize-5;	
		evenSmallerFontSize = smallFontSize-3;
		smallestFontSize =  evenSmallerFontSize - 3;
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

		HBox plants = new HBox(totPlants, trees, flowers, shrubs,vines);
		
		HBox uPlants = new HBox(uTotPlants,uTrees,uFlowers,uShrubs,uVines);
		plants.setSpacing(hboxSpacing);
		uPlants.setSpacing(hboxSpacing);

		for (int i = 0; i < plants.getChildren().size(); i++) {
			((Label) plants.getChildren().get(i)).setFont(new Font(defaultFontSize));
			((Label) plants.getChildren().get(i)).setWrapText(true);
			((Label) uPlants.getChildren().get(i)).setFont(new Font(defaultFontSize));
			((Label) uPlants.getChildren().get(i)).setWrapText(true);
		}

		vBox.getChildren().addAll(plants,uPlants, gardenCover, colors, bloomingSeasons, plantList,tips);

		for (int i = 2; i < vBox.getChildren().size(); i++) {
			((Label) vBox.getChildren().get(i)).setFont(new Font(defaultFontSize));
			((Label) vBox.getChildren().get(i)).setWrapText(true);
		}

		vBox.setPadding(new Insets(vboxSpacing, vboxSpacing, vboxSpacing, vboxSpacing));
		vBox.setSpacing(vboxSpacing);
		vBox.setStyle("-fx-background-color: rgba(158,255,174,1);");

		border.setCenter(scroll);

		scene = new Scene(border, canvasWidth, canvasHeight);
		styleScene();
	}

/**
 * Modifies the labels to show statistics. 
 * @param numTrees number of trees in garden
 * @param numShrubs number of shrubs in garden
 * @param numHerbs number of herbs in garden
 * @param numVines number of vine plants in garden
 * @param colorSet set of colors of plant blooms from the plants in the garden
 * @param seasons set of the seasons the plants bloom in the garden
 * @param allNames set of all scientific names of the plants in the garden
 * @param gardenCoveredPercent percent of garden covered by plants in percent form (EX. 1.23 = 1.23%)
 * @param uTrees number of unique trees in garden
 * @param uShrubs number of unique shrubs in garden
 * @param uHerbs number of unique herbs in garden
 * @param uVines number of unique vine plants in garden
 */
	public void updateStats(int numTrees, int numShrubs, int numHerbs, int numVines, HashSet<String> colorSet,
		HashSet<SeasonEnum> seasons, HashSet<String> allNames, double gardenCoveredPercent,int uTrees, int uShrubs, int uHerbs, int uVines) {
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
		
		
		colors.setText("Potential Bloom Colors: ");
		for (String color : colorSet) {
			colors.setText(colors.getText() + color + ", ");
		}
		colors.setText(colors.getText().substring(0, colors.getText().length() - 2));

		bloomingSeasons.setText("Blooming seasons: ");
		for (SeasonEnum season : seasons) {
			bloomingSeasons.setText(bloomingSeasons.getText() + season + ", ");
		}
		bloomingSeasons.setText(bloomingSeasons.getText().substring(0, bloomingSeasons.getText().length() - 2));
	
		String percent = String.format("%2.2f", gardenCoveredPercent)+ "%";
		gardenCover.setText("Garden Covered: " + percent);

		plantList.setText("List of Plants in your Garden: ");
		for (String name : allNames) {
			plantList.setText(plantList.getText() + name + ", ");
		}
		plantList.setText(plantList.getText().substring(0, plantList.getText().length() - 2));
		
		if(uTotal == 0) {
			tips.setText("Add some plants! Bring your yard to life!");
		}
		else if(uTotal < 3) {
			tips.setText("We all have our favorite plants, but a greater variety brings more life!");
		}
		else {
			tips.setText("Nice work! You and nature will be pleased!");
		}
		

		if(smallScreen) {
			if(uTotal < 20) {
				plantList.setFont(new Font(defaultFontSize));
			}
			else if(uTotal<50) {
				plantList.setFont(new Font(smallFontSize));
			}
			else {
				plantList.setFont(new Font(evenSmallerFontSize));
			}
		}
		else {
			if(uTotal < 30) {
				plantList.setFont(new Font(defaultFontSize));
			}
			else if(uTotal < 60) {
				plantList.setFont(new Font(smallFontSize));
			}
			else if(uTotal < 100){
				plantList.setFont(new Font(evenSmallerFontSize));
			}
			else {
				plantList.setFont(new Font(smallestFontSize));
			}
		}
	}

}
