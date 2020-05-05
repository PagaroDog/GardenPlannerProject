package Views;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import Controllers.SuggestionsController;
import Model.PlantType;
import Model.Sun;
import Model.Water;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
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
	private final int spaceBetweenLabelsPerRow = 17;
	int rows = canvasHeight / (thumbnailHeight * 2);
	int cols = (int) (canvasWidth / (thumbnailWidth * 1.75));
	private ImageView plantCopy;
	BorderPane border;
	ArrayList<Pane> imgs;
	Images images;
	private String selectedBG = "-fx-background-color: BLACK;";

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
		BorderPane nav = bottom();
		GridPane cen = center();
		border.setBottom(nav);
		border.setCenter(cen);
		scene = new Scene(border, canvasWidth, canvasHeight);
	}

	/**
	 * Creates the grid of ImageViews of plants that most closely relates to the
	 * users conditions and preferences.
	 * 
	 * @return GridPane
	 */
	public GridPane center() {

		
		GridPane pane = new GridPane();

		pane.setPrefWidth(canvasWidth);
		pane.setStyle("-fx-background-color: rgba(255, 130, 203,0.5);");
				// This lines are here only for testing the GridPane Layout
		
		
		imgs = new ArrayList<Pane>();
		int count = 0;
		String plantName;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				StackPane p = new StackPane();
				p.setPadding(new Insets(10,10,10,10));
				plantName = control.getPlantNameAt(count);
				ImageView plant = new ImageView(images.getPlantImages().get(control.getPlantNameAt(count))[0].getImg());
				p.setUserData(plantName);
			
				p.getChildren().add(plant);
				p.setAlignment(Pos.CENTER);
				//p.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream("/imgs/commonMilkweed.png"),
			//	thumbnailHeight, thumbnailWidth, true, false)));
				//System.out.println(control.getPlantNameAt(count));
				
				imgs.add(p);
				GridPane.setConstraints(imgs.get(imgs.size() - 1), j, i);
				imgs.get(imgs.size() - 1).setOnMouseEntered(control.gethandleOnMouseEnter());
				imgs.get(imgs.size() - 1).setOnMouseExited(control.gethandleOnMouseExit());
				imgs.get(imgs.size() - 1).setOnMouseClicked(control.gethandleOnMouseClick());
				count++;
			}
			pane.getRowConstraints().add(new RowConstraints(thumbnailHeight));
		}

	/*	Pane test = new Pane();
		test.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream("/imgs/whiteAsh.png"),
				thumbnailHeight, thumbnailWidth, true, false)));
		imgs.add(test);
		GridPane.setConstraints(test, 1, 1);
		imgs.get(imgs.size() - 1).setOnMouseEntered(control.gethandleOnMouseEnter());
		imgs.get(imgs.size() - 1).setOnMouseExited(control.gethandleOnMouseExit());
		imgs.get(imgs.size() - 1).setOnMouseClicked(control.gethandleOnMouseClick());
*/
		this.stats = stats(rows);
		GridPane.setConstraints(stats, 0, rows, cols, rows);
		// Creates three rows of height 100 for the stats gridpane
		for (int i = 0; i < rows; i++) {
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
			n.setStyle("-fx-background-color: transparent;");
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
		int imageCols = 2;
		GridPane stats = new GridPane();
		stats.setStyle("-fx-background-color: rgba(255, 182, 130,1);");
		// stats.setGridLinesVisible(true);

		String[] labels = { "Common Name", "Scientific Name", "Plant type", "Moisture", "Sun" };

		stats.getColumnConstraints().add(new ColumnConstraints(thumbnailHeight * imageCols));
		for (int i = 0; i < 5; i++) {
			stats.getRowConstraints().add(new RowConstraints(rows * spaceBetweenLabelsPerRow));
			Label dud = new Label(labels[i] + ": ");
			GridPane.setConstraints(dud, 1, i);
			stats.getChildren().add(dud);

		}
		Label fill = new Label("");
		fill.setPrefWidth(thumbnailWidth*cols-imageCols);

		return stats;
	}

	/**
	 * Takes an event source and copies the image and displays it in the stats view.
	 * Fills in stats based on object.
	 * 
	 * @param event
	 */
	public void inputStats(Object event,  String[] cNames, String name,PlantType type, Water[] moisture, Sun[] sun) {
		Object[] info = { cNames, name,  moisture, type, sun };
		int cnt = 0;
		/*for (Object s : info) {
			Label val = new Label();
			if(cnt % 2 == 0) {
				val.setText(Arrays.deepToString((Object[]) s));
			}
			else {
				val.setText(s.toString());
			}
			
			
			GridPane.setConstraints(val, 3, cnt);
			cnt++;
			stats.getChildren().add(val);
			
		}*/
		
		Label val = new Label(cNames[0]);
		GridPane.setConstraints(val, 3, cnt);
		cnt++;
		stats.getChildren().add(val);
		
		Label val2 = new Label(name);
		GridPane.setConstraints(val2, 3, cnt);
		cnt++;
		stats.getChildren().add(val2);
		
		Label val3 = new Label(type.toString());
		GridPane.setConstraints(val3, 3, cnt);
		cnt++;
		stats.getChildren().add(val3);
		
		String moistures = "";
		moistures = Arrays.toString(moisture).replace('[', ' ');
		moistures = moistures.replace(']', ' ');

		
		Label val4 = new Label(moistures);
		GridPane.setConstraints(val4, 3, cnt);
		cnt++;
		stats.getChildren().add(val4);
		
		String suns = "";
		suns = Arrays.toString(sun).replace('[', ' ');
		suns =suns.replace(']', ' ');
		Label val5 = new Label(suns);
		GridPane.setConstraints(val5, 3, cnt);
		cnt++;
		stats.getChildren().add(val5);
		
		
		
		
		Node n = (Node) event;
		ImageView copy = new ImageView(((ImageView) ((Pane) n).getChildren().get(0)).getImage());
		copy.setFitHeight(thumbnailHeight * 2);
		copy.setFitWidth(thumbnailWidth * 2);
		copy.setPreserveRatio(true);
		GridPane.setConstraints(copy, 0, 0, 1, 5);
		stats.getChildren().add(copy);
		plantCopy = copy;

	}

	/**
	 * Clears the stats section from plant image and data
	 */
	public void removeStats() {
		stats.getChildren().remove(plantCopy);
		for (int i = 0; i < 5; i++) {
			stats.getChildren().remove(stats.getChildren().size() - 1);
		}
	}

	/**
	 * Creates the navigation portion of BoarderPane. Assigned to the Top of the
	 * BoarderPane.
	 * 
	 * @return HBox
	 */
	public BorderPane bottom() {
		BorderPane nav = createNavigationBar("Edit Preferences", "Design Garden", "Pick Some of Your Favorite Plants From Our Suggestions", control.gethandleOnBackButton(), control.gethandleOnNextButton());
		return nav;
	}

	public GridPane getGrid() {
		return (GridPane) border.getCenter();
	}
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
