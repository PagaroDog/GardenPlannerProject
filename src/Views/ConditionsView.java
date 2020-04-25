package Views;
import Controllers.ConditionsController;
import Controllers.Controller;
import Controllers.DrawYardController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * 
 * @author Tommy White
 *
 */
public class ConditionsView extends View{
	private Stage stage;
	private Button selectButton;
	private Button deleteButton;
	private Button labelButton;
	private Button rectButton;
	//private Button circleButton;
	private Pane toolbar;
	private BorderPane root;
	private Pane stack;
	private Pane areasPane;
	private Pane drawing;
	private Button nextButton;
	private Button prevButton;
	private ConditionsController control;
	
	public ConditionsView(Stage stage) {
		
		this.stage = stage;
	}
	
	public void setup () {
		toolbar = new TilePane();
		Label txt = new Label("Conditions");
		nextButton = new Button("Next");
		nextButton.setOnMouseClicked(control.getHandleNextButton());
		prevButton = new Button("Prev");
		prevButton.setOnMouseClicked(control.getHandlePrevButton());
		selectButton = new Button("Select");
		selectButton.setOnMouseClicked(control.getHandleOnSelectButton());
		rectButton = new Button("Rectangle");
		rectButton.setOnMouseClicked(control.getHandleOnRectButton());
//		circleButton = new Button("Circle");
//		circleButton.setOnMouseClicked(control.getHandleOnCircleButton());
		labelButton = new Button("Label");
		labelButton.setOnMouseClicked(control.getHandleOnLabelButton());
		deleteButton = new Button("Delete");
		deleteButton.setOnMousePressed(control.getHandleOnDeleteButton());
		
		drawing = new Pane();
		
		areasPane = new Pane();
		
		stack = new StackPane(drawing, areasPane);

		toolbar.getChildren().addAll(txt, prevButton,nextButton, selectButton, rectButton, labelButton, deleteButton);
		root = new BorderPane();
		root.setTop(toolbar);
		root.setCenter(stack);
		scene = new Scene(root, canvasHeight,canvasWidth);
	}
	
	/**
	 * @return Scene object for the Conditions Areas screen
	 */
	@Override
	public Scene getScene() {
		return scene;
	}

	/**
	 * Sets control to c
	 */
	public void setController(Controller c) {
		control = (ConditionsController) c;
	}

	@Override
	public Stage getStage() {
		return stage;
	}
}
