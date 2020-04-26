package Views;
import Controllers.Controller;
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
import javafx.stage.Stage;
/**
 * @author Matt Cohen
 * @author Brandon Wu
 * 
 */
public class StartupView extends View{
	private Label title;
	private Button newButton;
	private Button loadButton;
	private Button tutorialButton;
	private ImageView iv;
	private Pane root;
	private TilePane buttons;
	private Stage stage;
	private StartupController suc;
	private double titleXPos = canvasWidth / 2;
	private double titleYPos = canvasHeight / 3;
	private double buttonXPos = canvasWidth / 2;
	private double buttonYPos = canvasHeight * 2/3;
	private double buttonGap = 50;
	public StartupView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		title = new Label("MyNativeGarden");
		title.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Dandelion.ttf"), 125));
		iv = new ImageView(new Image(getClass().getResourceAsStream("/imgs/StartupBackground.jpg"),canvasWidth,canvasHeight,false,false));
		buttons = new TilePane();
		tutorialButton = new Button("Tutorial");
		tutorialButton.setOnMouseClicked(suc.handleOnTutorialButton());
		
		newButton = new Button("New");
		newButton.setOnMouseClicked(suc.handleOnNewButton());
		
		loadButton = new Button("Load");
		loadButton.setOnMouseClicked(suc.handleOnLoadButton());
		buttons.getChildren().addAll(newButton,loadButton,tutorialButton);
		
		root = new Pane();
		root.getChildren().addAll(iv, title, buttons);
		
		for (Node button : buttons.getChildren()) {
			((Button) button).setFont(new Font(35));
		}
		
		scene = new Scene(root, canvasWidth, canvasHeight);
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}
	public Stage getStage() {
		return stage;
	}
	@Override
	public void setController(Controller controller) {
		this.suc = (StartupController)controller;
	}

	public void start() {
		buttons.setHgap(buttonGap);
		double buttonWidth = tutorialButton.getWidth();
		newButton.setPrefWidth(buttonWidth);
		loadButton.setPrefWidth(buttonWidth);
		double tileWidth = buttonWidth * 3 + buttonGap * 2;
		buttons.setLayoutX(buttonXPos - tileWidth/2);
		buttons.setLayoutY(buttonYPos);

		title.setLayoutX(titleXPos - title.getWidth()/2);
		title.setLayoutY(titleYPos);
	}
}
