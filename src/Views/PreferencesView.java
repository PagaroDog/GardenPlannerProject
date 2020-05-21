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
import javafx.scene.control.ScrollPane;
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

	private final double ZONE_BUTTONS_FONT_SIZE = 16;

	private final double VBOX_SPACING = 15;
	private final double VBOX_V_PADDING = 15;
	private final double VBOX_H_PADDING = 12;
	private final double VBOX_RED = 158;
	private final double VBOX_GREEN = 255;
	private final double VBOX_BLUE = 174;
	private final double VBOX_OPACITY = 12;

	private final double COLOR_INSETS = 10;

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
		ScrollPane scroll = new ScrollPane(vbox);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		border.setRight(scroll);
		drawing = new Pane();
		border.setLeft(drawing);

		zoneButtons = createToolbar();
		zoneButtonsLabel = new Label("Garden Area: ");
		zoneButtonsLabel.setFont(new Font(ZONE_BUTTONS_FONT_SIZE));
		zoneButtons.getChildren().add(zoneButtonsLabel);

		border.setTop(zoneButtons);

		scene = new Scene(border, canvasWidth, canvasHeight);
		styleScene();
	}

	/**
	 * Generates the VBox that allows users to choose preferences
	 * 
	 * @return The generated VBox
	 */
	public VBox addVBox() {
		vbox = new VBox();
		vbox.setPadding(new Insets(VBOX_V_PADDING, VBOX_H_PADDING, VBOX_V_PADDING, VBOX_H_PADDING));
		vbox.setSpacing(VBOX_SPACING);
		vbox.setStyle(String.format("-fx-background-color: rgba(%f, %f, %f, %f);", VBOX_RED, VBOX_GREEN, VBOX_BLUE,
				VBOX_OPACITY));

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

		String[] colors = { "Red", "Blue", "Purple", "Pink", "White", "Yellow", "Brown", "Green", "Orange" };

		color = new TilePane();
		color.setPadding(new Insets(COLOR_INSETS, COLOR_INSETS, COLOR_INSETS, COLOR_INSETS));
		Label labcolor = new Label("What color of the bloom?");
		for (String c : colors) {
			color.getChildren().add(new RadioButton(c));
		}

		vbox.getChildren().addAll(name, labwater, water, labsun, sun, labcolor, color, labbloom, bloom);
		return vbox;
	}

	/**
	 * Generates the bottom navigation bar.
	 * 
	 * @return The BorderPane representing the bar.
	 */
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
		name.setText("");
		bloom.setValue(null);
		water.setValue(null);
		sun.setValue(null);
		for (Node c : color.getChildren()) {
			((RadioButton) c).setSelected(false);
		}
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
