package Views;
import Controllers.Controller;
import Controllers.PreferencesController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Creates the scene for the preferences view. 
 * @author Brandon Wu
 *
 */
public class PreferencesView extends View{
	private Button nextButton;
	private Button backButton;
	private ComboBox<String> soil;
	private ComboBox<String> water;
	private ComboBox<String> sun;
	private TilePane color;
	private ComboBox<String> bloom;
	private PreferencesController control;
	private Stage stage;
	
	
	public PreferencesView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		BorderPane border = new BorderPane();
		HBox hbox = addHBox();
		VBox vbox = addVBox();
		border.setTop(hbox);
		border.setRight(vbox);
		ImageView iv = new ImageView(new Image(getClass().getResourceAsStream("/imgs/emptygarden.jpg"),800,400,true,false));
		iv.setPreserveRatio(true);
		iv.setFitHeight(800);
		border.setLeft(iv);
		TilePane tp = new TilePane();
		
		scene = new Scene(border,canvasHeight,canvasWidth);
	}
	public VBox addVBox() {
		VBox vbox = new VBox();
		//Not yet implemented
		HBox zoneButtons = new HBox();
		Label label = new Label("Zone Flips (not yet implemented)");
		label.setPrefHeight(50);
		zoneButtons.getChildren().add(label);
		zoneButtons.setAlignment(Pos.CENTER);
		zoneButtons.setStyle("-fx-background-color: rgba(255,252,158,1);");
		vbox.setPadding(new Insets(15, 12, 15, 12));
	    vbox.setSpacing(25);
		vbox.setStyle("-fx-background-color: rgba(158,255,174,1);");
		
		String[] seasons = {"Any","Winter","Spring","Summer","Fall"};
		Label labbloom = new Label("When do you want the plant to bloom?");
		bloom = new ComboBox<String>();
		bloom.getItems().addAll(seasons);
		
		String[] soils = {"Any","Clay","Sand","Loam"};
		Label labsoil = new Label("What type of soil?");
		soil = new ComboBox<String>();
		soil.getItems().addAll(soils);
		
		String[] moistures = {"Any","Dry","Medium","Wet"};
		Label labwater = new Label("What is your soil's moisture?");
		water = new ComboBox<String>();
		water.getItems().addAll(moistures);
		
		String[] sunlevels = {"Any","Full Sun","Partial Sun","Parial Shade", "Shade"};
		Label labsun = new Label("What degree of sunlight?");
		sun = new ComboBox<String>();
		sun.getItems().addAll(sunlevels);
		
		String[] colors = {"Red","Blue","Violet","Pink","White","Yellow","Black"};
		color = new TilePane();
		color.setPadding(new Insets(10,10,10,10));
		Label labcolor = new Label("What color of the bloom?");
		for(String c : colors) {
			color.getChildren().add(new RadioButton(c));;
		}
		
		bloom.setPrefWidth(250);soil.setPrefWidth(250);water.setPrefWidth(250);
		sun.setPrefWidth(250);color.setPrefWidth(500);
		vbox.getChildren().addAll(zoneButtons,labwater,water,labsoil,soil,labsun,sun,labcolor,color,labbloom,bloom);
		vbox.setPrefWidth(500);
		return vbox;
	}
	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 300, 15, 300));
	    hbox.setSpacing(15);
	    hbox.setStyle("-fx-background-color: rgba(168,158,255,1);");
	    hbox.setAlignment(Pos.CENTER);
	    nextButton = new Button("Next");
		nextButton.setOnMouseClicked(control.gethandleOnNextButton());
		
		
		backButton = new Button("Back");
		backButton.setOnMouseClicked(control.gethandleOnBackButton());
	    hbox.getChildren().addAll(backButton, new Label("Choose Your Preferences"),nextButton);
	    hbox.setPrefHeight(20);
	    hbox.setPrefWidth(canvasWidth);
	    return hbox;
	}

	@Override
	/**
	 * Creates the PreferencesView scene the user will see
	 */
	public Scene getScene() {
		return scene;
	}



	public ComboBox<String> getSoil() {
		return soil;
	}

	public ComboBox<String> getWater() {
		return water;
	}

	public ComboBox<String> getSun() {
		return sun;
	}

	public TilePane getColor() {
		return color;
	}

	public ComboBox<String> getBloom() {
		return bloom;
	}

	@Override
	public void setController(Controller c) {
		control =  (PreferencesController)c;
	}

	@Override
	public Stage getStage() {
		return this.stage;
	}
}
