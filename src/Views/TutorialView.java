package Views;
import java.util.ArrayList;
import Controllers.Controller;
import Controllers.TutorialController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
	private Button continueButton;
	private Button backButton;
	private BorderPane border;
	
	public StackPane slide;
	public ArrayList<Image> tutorialSlides = new ArrayList<Image>();	
	public ImageView background = new ImageView("/TutorialSlides/Slide1.PNG");;
	public int currentSlide=1;
	
	public TutorialView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		
		tutorialSlides.add(new Image("/TutorialSlides/Slide1.PNG"));//0
		tutorialSlides.add(new Image("/TutorialSlides/Slide2.PNG"));//1
		tutorialSlides.add(new Image("/TutorialSlides/Slide3.PNG"));//2
		tutorialSlides.add(null);//3
		
		
		border = new BorderPane();
		
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
		
		Buttons.getChildren().addAll(txt,nextButton,prevButton,continueButton,backButton);
		
		border.setTop(Buttons);
		
		slide = new StackPane();
		
		slide.getChildren().add(background);//sets up initial slide
		
		border.setCenter(slide);
		
		scene = new Scene(border,canvasWidth,canvasHeight);

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
