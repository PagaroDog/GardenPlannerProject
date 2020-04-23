package Views;
import Controllers.Controller;
import Controllers.PreferencesController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
	private ComboBox<String> color;
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
		Label txt = new Label("Pref");
		
		//tp.getChildren().addAll(backButton,nextButton);
		
		//tp.getChildren().addAll(txt,backButton,nextButton);
		scene = new Scene(border,canvasHeight,canvasWidth);
	}
	public VBox addVBox() {
		VBox vbox = new VBox();
		//Not yet implemented
		HBox zoneButtons = new HBox();
		Label label = new Label("Zone Flips");
		label.setPrefHeight(50);
		zoneButtons.getChildren().add(label);
		zoneButtons.setAlignment(Pos.CENTER);
		zoneButtons.setStyle("-fx-background-color: #987654;");
		vbox.setPadding(new Insets(15, 12, 15, 12));
	    vbox.setSpacing(25);
		vbox.setStyle("-fx-background-color: #336699;");
		Label labbloom = new Label("When do you want the plant to bloom?");
		bloom = new ComboBox<String>();
		Label labsoil = new Label("What type of soil?");
		soil = new ComboBox<String>();
		Label labwater = new Label("What is your soil's moisture?");

		water = new ComboBox<String>();
		Label labsun = new Label("What degree of sunlight?");

		sun = new ComboBox<String>();
		Label labcolor = new Label("What color of the bloom?");

		color = new ComboBox<String>();
		bloom.setPrefWidth(250);soil.setPrefWidth(250);water.setPrefWidth(250);
		sun.setPrefWidth(250);color.setPrefWidth(250);
		vbox.getChildren().addAll(zoneButtons,labwater,water,labsoil,soil,labsun,sun,labcolor,color,labbloom,bloom);
		vbox.setPrefWidth(500);
		return vbox;
	}
	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #123456;");

	    nextButton = new Button("Next");
		nextButton.setOnMouseClicked(control.gethandleOnNextButton());
		
		backButton = new Button("Back");
		backButton.setOnMouseClicked(control.gethandleOnBackButton());
	    hbox.getChildren().addAll(backButton, nextButton);
	    hbox.setPrefHeight(20);
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

	public ComboBox<String> getColor() {
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
