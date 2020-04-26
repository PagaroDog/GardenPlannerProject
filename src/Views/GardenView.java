package Views;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Controllers.Controller;
import Controllers.GardenController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class GardenView extends View{
	private HashMap<String,Image> plantImages = new HashMap<String,Image>();	
	private FlowPane suggestedFlowPane;
	private TilePane seasonTilePane;
	private TilePane yearTilePane;
	private TilePane statsTilePane;
	private TilePane toolbarTilePane;
	private Image backyardImage;
	private GardenController control;
	private Stage stage;
	private Button stats;
	private Button pref;
	private Button save;
	private Button year1;
	private Button year2;
	private Button year3;
	private Button spring;
	private Button summer;
	private Button fall;
	private Button winter;
	private ArrayList<ImageView> ivs;
	
	public GardenView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup () {
		int size = 200;
		ivs = new ArrayList<ImageView>();
		
		TilePane tp = new TilePane();
		Label txt = new Label("Design");
		stats = new Button("Stats");
		stats.setOnMouseClicked(control.handleOnStatsButton());
		
		save = new Button("Save");
		save.setOnMouseClicked(control.handleOnSaveButton());
		
		pref = new Button("Pref");
		pref.setOnMouseClicked(control.handleOnPrefButton());
		
		tp.getChildren().addAll(txt,pref,stats,save);
		
		seasonTilePane = new TilePane();
		Label seasonLabel = new Label("Select Season");
		summer = new Button("Summer");
		summer.setOnMouseClicked(control.handleOnSummerButton());
		fall = new Button("Fall");
		fall.setOnMouseClicked(control.handleOnFallButton());
		winter = new Button("Winter");
		winter.setOnMouseClicked(control.handleOnWinterButton());
		spring = new Button("Spring");
		spring.setOnMouseClicked(control.handleOnSpringButton());
		seasonTilePane.getChildren().addAll(seasonLabel,summer,fall,winter,spring);
		
		
		
		
		
		yearTilePane = new TilePane();
		Label yearLabel = new Label("Select Year");
		year1 = new Button("Year: 1");
		year1.setOnMouseClicked(control.handleOnYear1Button());
		year2 = new Button("Year: 2");
		year2.setOnMouseClicked(control.handleOnYear2Button());
		year3 = new Button("Year: 3");
		year3.setOnMouseClicked(control.handleOnYear3Button());
		yearTilePane.getChildren().addAll(yearLabel,year1,year2,year3);
		
		FlowPane bottom = new FlowPane();
		bottom.getChildren().add(seasonTilePane);
		bottom.getChildren().add(yearTilePane);

		
		
		
		BorderPane border = new BorderPane();
		border.setTop(tp);
		border.setBottom(bottom);
		
		TilePane tile = new TilePane();
		tile.setPadding(new Insets(5, 0, 5, 0));
		tile.setVgap(4);
		tile.setHgap(4);
		tile.setPrefColumns(1);
		tile.setStyle("-fx-background-color: DAE6F3;");
		tile.setPrefWidth(size);
		
		plantImages.put("whiteAsh", new Image("/imgs/whiteAsh.png"));
		plantImages.put("commonMilkweed.png", new Image("/imgs/commonMilkweed.png"));
		plantImages.put("american-elm.jpg", new Image("/imgs/american-elm.jpg"));
		plantImages.put("american-plum.jpg", new Image("/imgs/american-plum.jpg"));
		plantImages.put("goldenrod.jpg", new Image("/imgs/goldenrod.jpg"));
		
		
		
		//addImageFromFile("/imgs/");

		for (Image img: plantImages.values()) {
			ImageView imageview = new ImageView(img);
			imageview.setPreserveRatio(true);
		    imageview.setFitHeight(size);
		    imageview.setFitWidth(size);
		    tile.getChildren().add(imageview);
		}
		ScrollPane scrollPane = new ScrollPane();
	    scrollPane.setFitToWidth(true);
	    scrollPane.setContent(tile);
		border.setLeft(scrollPane);
		
		Pane garden = new Pane();
		ImageView background = new ImageView(new Image("/imgs/lawn.jpg"));
		background.fitWidthProperty().bind(garden.widthProperty()); 
		background.fitHeightProperty().bind(garden.heightProperty());
		garden.getChildren().add(background);
		border.setCenter(garden);
		
		scene = new Scene(border, canvasWidth, canvasHeight);
		
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public void setController(Controller controller) {
		control = (GardenController) controller;
		
	}

	@Override
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * go through directory and create file objects for everything in it, then load into plantImages
	 * @param myDirectoryPath
	 */
	public void addImageFromFile(String myDirectoryPath) {
        File dir = new File(myDirectoryPath);
		System.out.println(dir.isDirectory());
        File[] directoryListing = dir.listFiles();
        for (File child : directoryListing) {
        	String name = child.getName();
        	Image img = new Image("myDirectoryPath" + name);
        	plantImages.put(name, img);
        }
    }
	
	public int addIVToFlow() {
    	ivs.add(new ImageView());
    	int i = ivs.size()-1;
    	ivs.get(i).setImage(im1);	//im1 is the image we want to create, how to figure this out?
    	ivs.get(i).setPreserveRatio(true);
    	ivs.get(i).setFitHeight(PIC_SIZE);
    	ivs.get(i).setOnMouseDragged(imc.getHandlerForDrag(i));
    	garden.getChildren().add(ivs.get(i));
    	return i;
    }

}
