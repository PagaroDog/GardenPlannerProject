package Views;
import java.util.ArrayList;
import java.util.function.Predicate;

import Controllers.Controller;
import Controllers.PreferencesController;
import Model.GardenPref;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * Creates the scene for the preferences view. 
 * @author Brandon Wu
 *
 */
public class PreferencesView extends View{
	private Button nextButton;
	private Button backButton;
	private TextField name;
	private ComboBox<String> soil;
	private ComboBox<String> water;
	private ComboBox<String> sun;
	private TilePane color;
	private ComboBox<String> bloom;
	private PreferencesController control;
	private Stage stage;
	private Pane drawing;
	private BorderPane border;
	private VBox vbox;
	private HBox zoneButtons;
	private Rectangle currArea;
	private double bottomLabelFontSize = 25;
	private double bottomHeight = 60;
	private Label label;

	private final double bottomVPadding = 10;
	private final double bottomHPadding = 10;
	
	
	public PreferencesView(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * Initial setup of this class that could not be completed in the
	 * constructor since the controller had not yet been set
	 */
	@Override
	public void setup() {
		border = new BorderPane();
		BorderPane bottom = addBottom();
		vbox = addVBox();
		border.setBottom(bottom);
		border.setRight(vbox);
		drawing = new Pane();
		border.setLeft(drawing);
		TilePane tp = new TilePane();
		
		scene = new Scene(border, canvasWidth, canvasHeight);
	}
	
	public VBox addVBox() {
		vbox = new VBox();
		//Not yet implemented
		zoneButtons = new HBox();
		Label label = new Label("Garden Area:");
		label.setPrefHeight(50);
		zoneButtons.getChildren().add(label);
		zoneButtons.setAlignment(Pos.CENTER);
		zoneButtons.setStyle("-fx-background-color: rgba(255,252,158,1);");
		vbox.setPadding(new Insets(15, 12, 15, 12));
	    vbox.setSpacing(25);
		vbox.setStyle("-fx-background-color: rgba(158,255,174,1);");
		
		name = new TextField();
		
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
		
		String[] sunlevels = {"Any","Full Sun","Partial Sun","Partial Shade", "Shade"};
		Label labsun = new Label("What degree of sunlight?");
		sun = new ComboBox<String>();
		sun.getItems().addAll(sunlevels);
		
		String[] colors = {"Red","Blue","Violet","Pink","White","Yellow","Black"};
		color = new TilePane();
		color.setPadding(new Insets(10,10,10,10));
		Label labcolor = new Label("What color of the bloom?");
		for(String c : colors) {
			color.getChildren().add(new RadioButton(c));
		}
		
		bloom.setPrefWidth(250);soil.setPrefWidth(250);water.setPrefWidth(250);
		sun.setPrefWidth(250);color.setPrefWidth(500);
		vbox.getChildren().addAll(zoneButtons,name,labwater,water,labsoil,soil,labsun,sun,labcolor,color,labbloom,bloom);
		vbox.setPrefWidth(500);
		return vbox;
	}
	
	public BorderPane addBottom() {
	    BorderPane bottom = new BorderPane();
	    bottom.setPadding(new Insets(bottomVPadding, bottomHPadding, bottomVPadding, bottomHPadding));
	    bottom.setStyle("-fx-background-color: rgba(168,158,255,1);");
	    nextButton = new Button("See Suggestions");
		nextButton.setOnMouseClicked(control.gethandleOnNextButton());
		
		
		backButton = new Button("Edit Areas");
		backButton.setOnMouseClicked(control.gethandleOnBackButton());
		
		
		label = new Label("Choose Your Preferences");
		label.setFont(new Font(bottomLabelFontSize));
		
		bottom.setLeft(backButton);
		bottom.setRight(nextButton);
		bottom.setCenter(label);
		
	    return bottom;
	}
	
	public void setupZoneFlips(ArrayList<GardenPref> gardenPrefs) {
		for (int i = 0; i < gardenPrefs.size(); i++) {
			Button button = new Button("" + (i+1));
			button.setOnMousePressed(control.getHandleOnZoneButton((Rectangle) drawing.getChildren().get(drawing.getChildren().size() - gardenPrefs.size() + i), gardenPrefs.get(i)));
			zoneButtons.getChildren().add(button);
		}
	}

	@Override
	/**
	 * Creates the PreferencesView scene the user will see
	 */
	public Scene getScene() {
		return scene;
	}

	public TextField getName() {
		return name;
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
	
	public BorderPane getBorder() {
		return border;
	}
	
	public VBox getVBox() {
		return vbox;
	}

	@Override
	public void setController(Controller c) {
		control =  (PreferencesController)c;
	}
	
	public void setDrawing(Pane drawing) {
		this.drawing = drawing;
	}

	@Override
	public Stage getStage() {
		return this.stage;
	}

	public Shape getCurrArea() {
		return currArea;
	}

	public void setCurrArea(Rectangle rect) {
		currArea = rect;
	}

	public Pane getDrawing() {
		return drawing;
	}

	public String[] getUserColor() {
		Object[] buttons = color.getChildren().filtered(new Predicate<Node>() {
			@Override
			public boolean test(Node node) {
				if (node instanceof RadioButton) {
					return ((RadioButton) node).isSelected();
				}
				return false;
			}
			
		}).toArray();
		String[] strings = new String[buttons.length];
		for (int i = 0; i < buttons.length; i++) {
			strings[i] = buttons[i].toString();
		}
		System.out.println(strings);
		return strings;
	}
}
