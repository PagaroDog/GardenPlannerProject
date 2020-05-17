package Views;

import java.util.ArrayList;

import Controllers.SuggestionsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The SuggestionsView is used to display the screen where the user will select
 * their top picks of plants that fit their conditions
 * 
 * @author Brandon Wu
 *
 */
public class SuggestionsView extends View<SuggestionsController> {
	private GridPane stats;
	private int thumbnailWidth = 100;
	private int thumbnailHeight = 100;
	private final double spaceBetweenLabels = Math.min(17, 17 * canvasHeight/expectedHeight);
	private int stackPanePadding = 10;
	private int rows = canvasHeight / (thumbnailHeight * 2);
	private int cols = (int) (canvasWidth / (thumbnailWidth * 1.75));
	private ImageView plantCopy;
	BorderPane border;
	ArrayList<Pane> imgs;
	Images images;
	private String deselectedBG = "-fx-background-color: transparent;";
	private String selectedBG = "-fx-background-color: BLACK;";
	private int firstImgInd = 0;
	
	private int gridRed = 255;
	private int gridGreen = 130;
	private int gridBlue = 203;
	private double gridOpacity = 0.5;
	
	private int statsRed = 255;
	private int statsGreen = 182;
	private int statsBlue = 130;
	private int statsOpacity = 1;

	public SuggestionsView(Stage stage, Images imgs) {
		this.stage = stage;
		images = imgs;
	}

	/**
	 * Creates the scene to be displayed. Calls separate methods to build each
	 * segment of the border pane.
	 */
	@Override
	public void setup() {

		border = new BorderPane();
		border.setBottom(bottom());
		border.setCenter(center());
		scene = new Scene(border, canvasWidth, canvasHeight);
		styleScene();
	}

	/**
	 * Creates the grid of ImageViews of plants that most closely relates to the
	 * users conditions and preferences.
	 * 
	 * @return GridPane
	 */
	public GridPane center() {

		GridPane pane = new GridPane();

//		pane.setPrefWidth(canvasWidth);
		pane.setStyle(String.format("-fx-background-color: rgba(%d, %d, %d, %f);", gridRed, gridGreen, gridBlue, gridOpacity));

		imgs = new ArrayList<Pane>();
		int count = 0;
		String plantName;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				StackPane p = new StackPane();
				plantName = control.getPlantNameAt(count);
				ImageView plant = new ImageView(images.getPlantImages().get(plantName)[firstImgInd].getImg());
				plant.setPreserveRatio(true);
				if (plant.getImage().getWidth() > plant.getImage().getHeight())
					plant.setFitWidth(thumbnailWidth);
				else
					plant.setFitHeight(thumbnailHeight);
				p.setUserData(plantName);
				p.setPadding(new Insets(stackPanePadding, stackPanePadding, stackPanePadding, stackPanePadding));

				p.getChildren().add(plant);
				p.setAlignment(Pos.CENTER);

				imgs.add(p);
				GridPane.setConstraints(p, j, i);
				p.setOnMouseEntered(control.gethandleOnMouseEnter());
				p.setOnMouseExited(control.gethandleOnMouseExit());
				p.setOnMouseClicked(control.gethandleOnMouseClick());
				count++;
			}
			pane.getRowConstraints().add(new RowConstraints(thumbnailHeight + (stackPanePadding * 2)));
		}

		this.stats = stats(rows);
		GridPane.setConstraints(stats, 0, rows, cols, rows);
		// Creates (rows - 1) rows of height thumbnailHeight for the stats gridpane
		for (int i = 0; i < rows - 1; i++) {
			pane.getRowConstraints().add(new RowConstraints(thumbnailHeight));
		}

		pane.setGridLinesVisible(true);
		pane.getChildren().addAll(imgs);
		pane.getChildren().add(stats);
		pane.setAlignment(Pos.CENTER);
		return pane;
	}

	public void selectImage(MouseEvent event) {
		Node n = (Node) event.getSource();
		if (n.getStyle().equals(selectedBG)) {
			n.setStyle(deselectedBG);
		} else {
			n.setStyle(selectedBG);
		}

	}

	/**
	 * Creates an GridPane used to display enlarged plant images and statistics
	 * 
	 * @return GridPane
	 */
	public GridPane stats(int rows) {
//		int imageCols = 2;
		GridPane stats = new GridPane();
		stats.setStyle(String.format("-fx-background-color: rgba(%d, %d, %d, %d);", statsRed, statsGreen, statsBlue, statsOpacity));

//		stats.getColumnConstraints().add(new ColumnConstraints(thumbnailWidth * imageCols));
//		Label fill = new Label("");
//		fill.setPrefWidth(thumbnailWidth*cols-imageCols);

		return stats;
	}

	/**
	 * Takes an event source and copies the image and displays it in the stats view.
	 * Fills in stats based on object.
	 * 
	 * @param event
	 */
	public void inputStats(Node n, String plantStr) {
		Label val = new Label(plantStr);
		GridPane.setConstraints(val, 3, 0);
		val.setLineSpacing(spaceBetweenLabels);
		val.setMaxHeight(stats.getHeight());
		stats.getChildren().add(val);

		ImageView copy = new ImageView(((ImageView) ((Pane) n).getChildren().get(0)).getImage());
		copy.setPreserveRatio(true);
		if (copy.getImage().getWidth() > copy.getImage().getHeight())
			copy.setFitWidth((rows-1) * thumbnailWidth * 0.8);
		else
			copy.setFitHeight((rows-1) * thumbnailHeight * 0.8);
//		GridPane.setConstraints(copy, 0, 0, 1, 5);
		stats.getChildren().add(copy);
		plantCopy = copy;

	}

	/**
	 * Clears the stats section from plant image and data
	 */
	public void removeStats() {
		stats.getChildren().remove(plantCopy);
//		for (int i = 0; i < 5; i++) {
		stats.getChildren().remove(stats.getChildren().size() - 1);
//		}
	}

	/**
	 * Creates the navigation portion of BorderPane. Assigned to the Top of the
	 * BoarderPane.
	 * 
	 * @return HBox
	 */
	public BorderPane bottom() {
		BorderPane nav = createNavigationBar("Edit Preferences", "Design Garden",
				"Pick Some of Your Favorite Plants From Our Suggestions", control.gethandleOnBackButton(),
				control.gethandleOnNextButton());
		return nav;
	}

	public GridPane getGrid() {
		return (GridPane) border.getCenter();
	}

	/**
	 * Called by SuggestionsController. Updates plant images to reflect the change
	 * in Model's plantSuggestions ArrayList
	 */
	public void refreshPlants() {
		GridPane cen = center();

		border.setCenter(cen);
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public String getSelectedBG() {
		return selectedBG;
	}

}
