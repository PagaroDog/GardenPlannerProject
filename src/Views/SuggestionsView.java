package Views;

import java.util.ArrayList;

import Controllers.Controller;
import Controllers.SuggestionsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class SuggestionsView extends View{
	private Button nextButton;
	private Button backButton;
	private Stage stage;
	private SuggestionsController control;
	private HBox stats;
	
	public SuggestionsView(Stage stage) {
		this.stage = stage;
	}
	

	public void setup() {
		
		BorderPane border = new BorderPane();
		HBox nav = top();
		GridPane cen = center();
		border.setTop(nav);
		border.setCenter(cen);
		scene = new Scene(border,canvasHeight,canvasWidth);
	}
	/**
	 * Creates the grid of ImageViews of plants that most closely relates to the users conditions and 
	 * preferences.
	 * @return GridPane
	 */
	public GridPane center() {
		int thumbnailWidth = 200;
		int thumbnailHeight = 100;
		int rows = canvasHeight/(thumbnailHeight*2);
		int cols = canvasWidth/thumbnailWidth;
		GridPane pane = new GridPane();
	
		pane.setPrefWidth(canvasWidth);
		pane.setStyle("-fx-background-color: rgba(255, 130, 203,0.5);");
		ArrayList<ImageView>imgs = new ArrayList<ImageView>();
		for(int i = 0; i<rows;i++) {
			for(int j = 0; j<cols;j++) {
			imgs.add(new ImageView(new Image(getClass().getResourceAsStream("/imgs/commonMilkweed.png"),thumbnailHeight,thumbnailWidth,true,false)));
			GridPane.setConstraints(imgs.get(imgs.size()-1),j,i);
			imgs.get(imgs.size()-1).setOnMouseEntered(control.gethandleOnMouseEnter());
			imgs.get(imgs.size()-1).setOnMouseExited(control.gethandleOnMouseExit());
			
			}
			pane.getRowConstraints().add(new RowConstraints(thumbnailHeight));
		}
		this.stats = stats();
		GridPane.setConstraints(stats, 0, rows, cols, 3);
		//Creates three rows of height 100 for the stats HBox
		for(int i = 0; i<3;i++) {
			pane.getRowConstraints().add(new RowConstraints(thumbnailHeight));
		}
		
		
		
		pane.setGridLinesVisible(true);
		pane.getChildren().addAll(imgs);
		pane.getChildren().add(stats);
		return pane;
	}
	/**
	 * Creates an HBox used to display enlarged plant images and statistics
	 * @return HBox
	 */
	public HBox stats() {
		HBox stats = new HBox();
		stats.setStyle("-fx-background-color: rgba(255, 182, 130,1);");
		return stats;
	}

	public void inputStats(Object event) {
	
		ImageView copy = new ImageView(((ImageView) event).getImage());
		stats.getChildren().add(copy);
		stats.setStyle("-fx-background-color: rgba(255,255,255,1);");
		
	}
	public void removeStats(Object event) {
		stats.getChildren().remove(0);
		stats.setStyle("-fx-background-color: rgba(255,255,0,1);");
	}
	/**
	 * Creates the navigation portion of BoarderPane. Assigned to the Top of the BoarderPane.
	 * @return HBox
	 */
	public HBox top() {
		HBox nav = new HBox();
		nav.setPadding(new Insets(15,300,15,300));
		
		Label txt = new Label("SUGGS");
		backButton = new Button("Back");
		backButton.setOnMouseClicked(control.gethandleOnBackButton());
		nextButton = new Button("Next");
		nextButton.setOnMouseClicked(control.gethandleOnNextButton());
	
		nav.getChildren().addAll(backButton,txt,nextButton);
		nav.setStyle("-fx-background-color: rgba(255, 222, 133,1);");
		nav.setSpacing(15);
		nav.setPrefHeight(20);
		nav.setPrefWidth(canvasWidth);
		nav.setAlignment(Pos.CENTER);
		return nav;
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
