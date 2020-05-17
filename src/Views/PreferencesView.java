package Views;

import java.util.ArrayList;
import java.util.function.Predicate;

import Controllers.PreferencesController;
import Model.GardenPref;
import javafx.geometry.Insets;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Creates the scene for the preferences view.
 * 
 * @author Brandon Wu
 *
 */
public class PreferencesView extends View<PreferencesController> {
	private TextField name;
	private ComboBox<String> soil;
	private ComboBox<String> water;
	private ComboBox<String> sun;
	private TilePane color;
	private ComboBox<String> bloom;
	private Pane drawing;
	private BorderPane border;
	private VBox vbox;
	private HBox zoneButtons;
	private Rectangle currArea;
	private Label zoneButtonsLabel;

	private double zoneButtonsFontSize = 16;

	private final double vBoxSpacing = 15;
	private final double vBoxVPadding = 15;
	private final double vBoxHPadding = 12;
	private final double vBoxRed = 158;
	private final double vBoxGreen = 255;
	private final double vBoxBlue = 174;
	private final double vBoxOpacity = 12;

	private final double colorInsets = 10;

	public PreferencesView(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Initial setup of this class that could not be completed in the constructor
	 * since the controller had not yet been set
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

		zoneButtons = createToolbar();
		zoneButtonsLabel = new Label("Garden Area: ");
		zoneButtonsLabel.setFont(new Font(zoneButtonsFontSize));
		zoneButtons.getChildren().add(zoneButtonsLabel);

		border.setTop(zoneButtons);

		scene = new Scene(border, canvasWidth, canvasHeight);
		styleScene();
	}

	public VBox addVBox() {
		vbox = new VBox();
		vbox.setPadding(new Insets(vBoxVPadding, vBoxHPadding, vBoxVPadding, vBoxHPadding));
		vbox.setSpacing(vBoxSpacing);
		vbox.setStyle(String.format("-fx-background-color: rgba(%f, %f, %f, %f);", vBoxRed, vBoxGreen, vBoxBlue,
				vBoxOpacity));

		name = new TextField();
		name.setPromptText("Name this Area");

		String[] seasons = { "Any", "Winter", "Spring", "Summer", "Fall" };
		Label labbloom = new Label("When do you want the plant to bloom?");
		bloom = new ComboBox<String>();
		bloom.getItems().addAll(seasons);

		String[] moistures = { "Any", "Wet", "Wet Mesic", "Mesic", "Dry Mesic", "Dry" };
		Label labwater = new Label("What is your soil's moisture?");
		water = new ComboBox<String>();

		water.getItems().addAll(moistures);

		String[] sunlevels = { "Any", "Full Sun", "Full Sun to Partial Shade", "Partial Shade",
				"Partial Shade to Full Shade", "Full Shade" };
		Label labsun = new Label("What degree of sunlight?");
		sun = new ComboBox<String>();
		sun.getItems().addAll(sunlevels);

		String[] colors = { "Red", "Blue", "Purple", "Pink", "White", "Yellow", "Black", "Brown", "Green", "Orange" };

		color = new TilePane();
		color.setPadding(new Insets(colorInsets, colorInsets, colorInsets, colorInsets));
		Label labcolor = new Label("What color of the bloom?");
		for (String c : colors) {
			color.getChildren().add(new RadioButton(c));
		}

		vbox.getChildren().addAll(name, labwater, water, labsun, sun, labcolor, color, labbloom, bloom);
		return vbox;
	}

	public BorderPane addBottom() {
		BorderPane bottom = createNavigationBar("Edit Areas", "See Suggestions", "Choose Your Preferences",
				control.gethandleOnBackButton(), control.gethandleOnNextButton());

		return bottom;
	}

	/**
	 * Creates the buttons that allow the user to switch between garden areas and
	 * associates each garden area with a GardenPref.
	 * 
	 * @param gardenPrefs The ArrayList of GardenPrefs
	 */
	public void setupZoneFlips(ArrayList<GardenPref> gardenPrefs) {
		zoneButtons.getChildren().clear();
		zoneButtons.getChildren().add(zoneButtonsLabel);
		Pane rectangles = (Pane) drawing.getChildren().get(0);
		for (int i = 0; i < gardenPrefs.size(); i++) {
			Button button = new Button("" + (i + 1));
			button.setUserData(i);
			button.setOnMousePressed(control.getHandleOnZoneButton(
					(Rectangle) rectangles.getChildren().get(rectangles.getChildren().size() - gardenPrefs.size() + i),
					gardenPrefs.get(i)));
			zoneButtons.getChildren().add(button);
		}
	}

	/**
	 * Searches the color radio buttons and puts the selected colors into an array
	 * and returns it
	 * 
	 * @return An array of strings of the selected colors
	 */
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

	public void setDrawing(Pane drawing) {
		this.drawing = drawing;
	}

	public Rectangle getCurrArea() {
		return currArea;
	}

	public void setCurrArea(Rectangle rect) {
		currArea = rect;
	}

	public Pane getDrawing() {
		return drawing;
	}
}
