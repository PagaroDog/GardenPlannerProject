package Views;

import Controllers.StartupController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The View class for the Startup screen. Holds graphical data and defines
 * graphical logic.
 * 
 * @author Matt Cohen
 * @author Brandon Wu
 * @author Tommy White
 * 
 */
public class StartupView extends View<StartupController> {
	private Label title;
	private Button newButton;
	private Button loadButton;
	private Button tutorialButton;
	private ImageView iv;
	private Pane root;
	private TilePane buttons;
	private FileChooser fileChooser;
	private int numButtons = 3;
	private double titleFontSize = 125;
	private double buttonFont = 35;
	private double titleXPos = canvasWidth / 2;
	private double titleYPos = canvasHeight / 3;
	private double buttonXPos = canvasWidth / 2;
	private double buttonYPos = canvasHeight * 2 / 3;
	private double buttonGap = 50;

	public StartupView(Stage stage) {
		this.stage = stage;
		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Garden Files (*.garden)", "*.garden");
		fileChooser.getExtensionFilters().add(filter);
	}

	/**
	 * Initial setup of this class that could not be completed in the constructor
	 * since the controller had not yet been set
	 */
	@Override
	public void setup() {
		stage.setResizable(false);
		stage.sizeToScene();
		
		title = new Label("MyNativeGarden");
		title.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Dandelion.ttf"), titleFontSize));
		iv = new ImageView(new Image(getClass().getResourceAsStream("/imgs/StartupBackground.jpg"), canvasWidth,
				canvasHeight, false, false));
		buttons = new TilePane();
		tutorialButton = new Button("Tutorial");
		tutorialButton.setOnMouseClicked(control.handleOnTutorialButton());

		newButton = new Button("New");
		newButton.setOnMouseClicked(control.handleOnNewButton());

		loadButton = new Button("Load");
		loadButton.setOnMouseClicked(control.handleOnLoadButton());
		buttons.getChildren().addAll(newButton, loadButton, tutorialButton);

		root = new Pane();
		root.getChildren().addAll(iv, title, buttons);

		for (Node button : buttons.getChildren()) {
			((Button) button).setFont(new Font(buttonFont));
		}

		scene = new Scene(root, canvasWidth, canvasHeight);
		styleScene();

	}

	/**
	 * Further setup of this screen that can only occur once some variables have
	 * been set by the initial showing of this screen.
	 */
	public void start() {
		buttons.setHgap(buttonGap);
		double buttonWidth = tutorialButton.getWidth();
		newButton.setPrefWidth(buttonWidth);
		loadButton.setPrefWidth(buttonWidth);
		double tileWidth = buttonWidth * numButtons + buttonGap * (numButtons - 1);
		buttons.setLayoutX(buttonXPos - tileWidth / 2);
		buttons.setLayoutY(buttonYPos);

		title.setLayoutX(titleXPos - title.getWidth() / 2);
		title.setLayoutY(titleYPos);
	}

	public FileChooser getFileChooser() {
		return fileChooser;
	}
}
