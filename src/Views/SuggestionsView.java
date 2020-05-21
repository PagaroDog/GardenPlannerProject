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
	private final int THUMBNAIL_WIDTH = 100;
	private final int THUMBANIL_HEIGHT = 100;
	private final double SPACE_BETWEEN_LABELS = Math.min(17, 17 * canvasHeight / expectedHeight);
	private final int STACK_PANE_PADDING = 10;
	private int rows = canvasHeight / (THUMBANIL_HEIGHT * 2);
	private int cols = (int) (canvasWidth / (THUMBNAIL_WIDTH * 1.75));
	private ImageView plantCopy;
	BorderPane border;
	ArrayList<Pane> imgs;
	Images images;
	private final String DESELECTED_BG = "-fx-background-color: transparent;";
	private final String SELECTED_BG = "-fx-background-color: BLACK;";
	private final int FIRST_IMG_IND = 0;

	private final int GRID_RED = 255;
	private final int GRID_GREEN = 130;
	private final int GRID_BLUE = 203;
	private final double GRID_OPACITY = 0.5;

	private final int STATS_RED = 255;
	private final int STATS_GREEN = 182;
	private final int STATS_BLUE = 130;
	private final int STATS_OPACITY = 1;

	private final double FULL_PIC_MULTIPLIER = 0.8;

	private final int LABEL_STARTING_COL = 3;
	private final int LABEL_STARTING_ROW = 0;

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
		pane.setStyle(String.format("-fx-background-color: rgba(%d, %d, %d, %f);", GRID_RED, GRID_GREEN, GRID_BLUE,
				GRID_OPACITY));

		imgs = new ArrayList<Pane>();
		int count = 0;
		String plantName;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				StackPane p = new StackPane();
				plantName = control.getPlantNameAt(count);
				ImageView plant = new ImageView(images.getPlantImages().get(plantName)[FIRST_IMG_IND].getImg());
				plant.setPreserveRatio(true);
				if (plant.getImage().getWidth() > plant.getImage().getHeight())
					plant.setFitWidth(THUMBNAIL_WIDTH);
				else
					plant.setFitHeight(THUMBANIL_HEIGHT);
				p.setUserData(plantName);
				p.setPadding(new Insets(STACK_PANE_PADDING, STACK_PANE_PADDING, STACK_PANE_PADDING, STACK_PANE_PADDING));

				p.getChildren().add(plant);
				p.setAlignment(Pos.CENTER);

				imgs.add(p);
				GridPane.setConstraints(p, j, i);
				p.setOnMouseEntered(control.gethandleOnMouseEnter());
				p.setOnMouseExited(control.gethandleOnMouseExit());
				p.setOnMouseClicked(control.gethandleOnMouseClick());
				count++;
			}
			pane.getRowConstraints().add(new RowConstraints(THUMBANIL_HEIGHT + (STACK_PANE_PADDING * 2)));
		}

		this.stats = stats(rows);
		GridPane.setConstraints(stats, 0, rows, cols, rows);
		// Creates (rows - 1) rows of height thumbnailHeight for the stats gridpane
		for (int i = 0; i < rows - 1; i++) {
			pane.getRowConstraints().add(new RowConstraints(THUMBANIL_HEIGHT));
		}

		pane.setGridLinesVisible(true);
		pane.getChildren().addAll(imgs);
		pane.getChildren().add(stats);
		pane.setAlignment(Pos.CENTER);
		return pane;
	}

	/**
	 * Updates the pane containing a plant image to show that it has been selected
	 * or deselected.
	 * 
	 * @param event The event that caused this method to be called.
	 */
	public void selectImage(MouseEvent event) {
		Node n = (Node) event.getSource();
		if (n.getStyle().equals(SELECTED_BG)) {
			n.setStyle(DESELECTED_BG);
		} else {
			n.setStyle(SELECTED_BG);
		}

	}

	/**
	 * Creates an GridPane used to display enlarged plant images and statistics
	 * 
	 * @return GridPane
	 */
	public GridPane stats(int rows) {
		GridPane stats = new GridPane();
		stats.setStyle(String.format("-fx-background-color: rgba(%d, %d, %d, %d);", STATS_RED, STATS_GREEN, STATS_BLUE,
				STATS_OPACITY));

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
		GridPane.setConstraints(val, LABEL_STARTING_COL, LABEL_STARTING_ROW);
		val.setLineSpacing(SPACE_BETWEEN_LABELS);
		val.setMaxHeight(stats.getHeight());
		stats.getChildren().add(val);

		ImageView copy = new ImageView(((ImageView) ((Pane) n).getChildren().get(0)).getImage());
		copy.setPreserveRatio(true);
		if (copy.getImage().getWidth() > copy.getImage().getHeight())
			copy.setFitWidth((rows - 1) * THUMBNAIL_WIDTH * FULL_PIC_MULTIPLIER);
		else
			copy.setFitHeight((rows - 1) * THUMBANIL_HEIGHT * FULL_PIC_MULTIPLIER);
		stats.getChildren().add(copy);
		plantCopy = copy;

	}

	/**
	 * Clears the stats section from plant image and data
	 */
	public void removeStats() {
		stats.getChildren().remove(plantCopy);
		stats.getChildren().remove(stats.getChildren().size() - 1);
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
		return SELECTED_BG;
	}

	public GridPane getGrid() {
		return (GridPane) border.getCenter();
	}

}
