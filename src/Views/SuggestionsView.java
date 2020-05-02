package Views;

import java.util.ArrayList;

import Controllers.Controller;
import Controllers.SuggestionsController;
import Model.PlantType;
import Model.Sun;
import Model.Water;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * The SuggestionsView is used to display the screen where the user 
 * will select their top picks of plants that fit their conditions
 * @author Brandon Wu
 *
 */
public class SuggestionsView extends View{
	private Button nextButton;
	private Button backButton;
	private Stage stage;
	private SuggestionsController control;
	private GridPane stats;
	private int thumbnailWidth = 100;
	private int thumbnailHeight = 100;
	private final int spaceBetweenLabelsPerRow = 17;
	private ImageView plantCopy;
	BorderPane border;
	ArrayList<Pane> imgs;
	Images images;
	
	private final double bottomVPadding = 10;
	private final double bottomHPadding = 10;
	private final double bottomLabelFontSize = 25;
	
	public SuggestionsView(Stage stage, Images imgs) {
		this.stage = stage;
		images = imgs;
	}
	
/**
 * Creates the scene to be displayed. Calls separate methods to build each segment of the border pane.
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
	 * Creates the grid of ImageViews of plants that most closely relates to the users conditions and 
	 * preferences.
	 * @return GridPane
	 */
	public GridPane center() {
		
		int rows = canvasHeight/(thumbnailHeight*2);
		int cols = canvasWidth/thumbnailWidth;
		GridPane pane = new GridPane();
	
		pane.setPrefWidth(canvasWidth);
		pane.setStyle("-fx-background-color: rgba(255, 130, 203,0.5);");
		
		//This lines are here only for testing the GridPane Layout
		imgs = new ArrayList<Pane>();
		for(int i = 0; i<rows;i++) {
			for(int j = 0; j<cols;j++) {
				Pane p = new Pane();
				p.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream("/imgs/commonMilkweed.png"),thumbnailHeight,thumbnailWidth,true,false)));
				imgs.add(p);
				GridPane.setConstraints(imgs.get(imgs.size()-1),j,i);
				imgs.get(imgs.size()-1).setOnMouseEntered(control.gethandleOnMouseEnter());
				imgs.get(imgs.size()-1).setOnMouseExited(control.gethandleOnMouseExit());
				imgs.get(imgs.size()-1).setOnMouseClicked(control.gethandleOnMouseClick());
				
			}
			pane.getRowConstraints().add(new RowConstraints(thumbnailHeight));
		}
		
		
		Pane test = new Pane();
		test.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream("/imgs/whiteAsh.png"),thumbnailHeight,thumbnailWidth,true,false)));
		imgs.add(test);
		GridPane.setConstraints(test,1,1);
		imgs.get(imgs.size()-1).setOnMouseEntered(control.gethandleOnMouseEnter());
		imgs.get(imgs.size()-1).setOnMouseExited(control.gethandleOnMouseExit());
		imgs.get(imgs.size()-1).setOnMouseClicked(control.gethandleOnMouseClick());
		
		
		
		
		
		this.stats = stats(rows);
		GridPane.setConstraints(stats, 0, rows, cols, rows);
		//Creates three rows of height 100 for the stats gridpane
		for(int i = 0; i<rows;i++) {
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
		if(n.getStyle().equals("-fx-background-color: BLACK;")) {
			n.setStyle("-fx-background-color: transparent;");
		}
		else {
			n.setStyle("-fx-background-color: BLACK;");
		}
		
	}
	/**
	 * Creates an GridPane used to display enlarged plant images and statistics
	 * @return GridPane
	 */
	public GridPane stats(int rows) {
		GridPane stats = new GridPane();
		stats.setStyle("-fx-background-color: rgba(255, 182, 130,1);");
		//stats.setGridLinesVisible(true);
		
		
		String[] labels = {"Common Name", "Scientific Name","Soil type","Moisture","Sun"};
		
	
		stats.getColumnConstraints().add(new ColumnConstraints(thumbnailHeight*2));
		for(int i = 0; i<5; i++) {
			stats.getRowConstraints().add(new RowConstraints(rows * spaceBetweenLabelsPerRow));
			Label dud = new Label(labels[i]+": ");
			GridPane.setConstraints(dud,1,i);
			stats.getChildren().add(dud);
			
		}
		
		
		
		return stats;
	}
/**
 * Takes an event source and copies the image and displays it in the stats view. Fills in stats based on object.
 * @param event
 */
	public void inputStats(Object event, String name, String[] cNames, PlantType type, Water[] moisture, Sun[] sun) {
		Object[] info = {name,cNames,type,moisture,sun};
		int cnt = 0;
		for(Object s : info) {
			Label val = new Label(s.toString());
			GridPane.setConstraints(val,3,cnt);
			cnt++;
			stats.getChildren().add(val);
		}
		Node n = (Node)event;
		ImageView copy = new ImageView(((ImageView) ((Pane)n).getChildren().get(0)).getImage());
		copy.setFitHeight(thumbnailHeight*2);
		copy.setFitWidth(thumbnailWidth*2);
		copy.setPreserveRatio(true);
		GridPane.setConstraints(copy,0,0,1,5);
		stats.getChildren().add(copy);
		plantCopy = copy;
		
	}
	/**
	 * Clears the stats section from plant image and data
	 */
	public void removeStats() {
		stats.getChildren().remove(plantCopy);
		for(int i =0;i<5;i++) {
			stats.getChildren().remove(stats.getChildren().size()-1);
		}
	}
	/**
	 * Creates the navigation portion of BoarderPane. Assigned to the Top of the BoarderPane.
	 * @return HBox
	 */
	public BorderPane bottom() {
		BorderPane nav = new BorderPane();
		nav.setPadding(new Insets(bottomVPadding, bottomHPadding, bottomVPadding, bottomHPadding));
		
		Label txt = new Label("Pick Some of Your Favorites From Our Suggestions");
		txt.setFont(new Font(bottomLabelFontSize));
		backButton = new Button("Edit Preferences");
		backButton.setOnMouseClicked(control.gethandleOnBackButton());
		nextButton = new Button("Design Garden");
		nextButton.setOnMouseClicked(control.gethandleOnNextButton());
	
		nav.setLeft(backButton);
		nav.setRight(nextButton);
		nav.setCenter(txt);
		nav.setStyle("-fx-background-color: rgba(168,158,255,1);");
		return nav;
	}

	public GridPane getGrid(){
		return (GridPane) border.getCenter();
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
		control = (SuggestionsController) controller;
		
	}

	@Override
	public Stage getStage() {
		return stage;
	}







}
