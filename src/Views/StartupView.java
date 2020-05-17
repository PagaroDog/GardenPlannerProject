package Views;

import Controllers.StartupController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
	private Button creditsButton;
	private ImageView iv;
	private Pane root;
	private TilePane buttons;
	private FileChooser fileChooser;
	private ScrollPane credits;
	private int numButtons = 3;
	private double titleFontSize = 125;
	private double buttonFont = 35;
	private double titleXPos = canvasWidth / 2;
	private double titleYPos = canvasHeight / 3;
	private double buttonXPos = canvasWidth / 2;
	private double buttonYPos = canvasHeight * 2 / 3;
	private double buttonGap = 50;
	private double creditsPadding = 5;
	private Images imgs;
	private double creditWidth = 300;
	private double creditHeight = 400;

	public StartupView(Stage stage, Images imgs) {
		this.stage = stage;
		this.imgs = imgs;
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

		creditsButton = new Button("Show Credits");
		creditsButton.setOnMouseClicked(control.handleOnCreditsButton());

		Label creditText = new Label(control.generateCredits());

		credits = new ScrollPane(creditText);
		credits.setMaxWidth(creditWidth);
		credits.setMaxHeight(creditHeight);

		root = new Pane();
		root.getChildren().addAll(iv, title, buttons, creditsButton, credits);

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

		creditsButton.setLayoutX(canvasWidth - creditsButton.getWidth() - creditsPadding);
		creditsButton.setLayoutY(canvasHeight - creditsButton.getHeight() - creditsPadding);

		creditsButton.setPrefWidth(creditsButton.getWidth());
		creditsButton.setPrefHeight(creditsButton.getHeight());

		credits.setLayoutX(canvasWidth - credits.getWidth() - creditsPadding);
		credits.setLayoutY(creditsButton.getLayoutY() - credits.getHeight() - creditsPadding);

		root.getChildren().remove(credits);
	}

	/**
	 * Shows or hides the credits based on their current state.
	 */
	public void credits() {
		if (root.getChildren().contains(credits)) {
			root.getChildren().remove(credits);
			creditsButton.setText("Show Credits");
		} else {
			root.getChildren().add(credits);
			creditsButton.setText("Hide Credits");
		}
	}

	public FileChooser getFileChooser() {
		return fileChooser;
	}

	public Images getImgs() {
		return imgs;
	}
}
