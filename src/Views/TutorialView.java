package Views;
import Controllers.Controller;
import Controllers.TutorialController;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
/**
 * @author Josh Stone
* this class is the view for the Tutorial screen
*/
public class TutorialView extends View{
	private Stage stage;
	private Button prevButton;
	private Button nextButton;
	private TutorialController control;
	private TilePane Buttons;
	public Button continueButton;
	private Button backButton;
	
	
	
	
	public TutorialView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		Buttons = new TilePane();
		Label txt = new Label("Tutorial");
		nextButton = new Button("Next");
		nextButton.setOnMouseClicked(control.getHandleOnNextButton());
		
		prevButton = new Button("Prev");
		prevButton.setOnMouseClicked(control.getHandleOnPrevButton());
		
		backButton = new Button("Back");
		backButton.setOnMouseClicked(control.getHandleOnBackButton());
		
		continueButton = new Button("Continue");
		continueButton.setOnMouseClicked(control.getHandleOnContinueButton());
		//continueButton.setStyle("-fx-background-image: url('orc_animation/orc_forward_north.jpg')");
		/*
		BackgroundImage myBI= new BackgroundImage(new Image("ClassDiagram.png",32,32,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		Buttons.setBackground(new Background(myBI));
		*/
		Buttons.getChildren().addAll(txt,nextButton,prevButton,continueButton,backButton);
		
		scene = new Scene(Buttons,canvasHeight,canvasWidth);
	}
	
	/**
	 * @return Scene object for the Tutorial screen
	 */
	@Override
	public Scene getScene() {
		return scene;
	}
	
	/**
	 * Sets control to tc
	 */

	@Override
	public void setController(Controller controller) {
		control = (TutorialController)controller;
		
	}
	@Override
	public Stage getStage() {
		return stage;
	}

}
