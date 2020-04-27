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
	public Button continueButton;
	private Button backButton;
	public StackPane slide;
	
	private BorderPane border;
	public ArrayList<Image> tutorialSlides = new ArrayList<Image>();	
	public static ArrayList<String> tutorialSlidesPath = new ArrayList<String>();	
	public static ImageView background = new ImageView("/TutorialSlides/2019-11-27 (1).png");;
	public int currentSlide=1;
	
	public TutorialView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		
		tutorialSlides.add(new Image("/TutorialSlides/2019-11-27 (1).png"));//0
		tutorialSlides.add(new Image("/TutorialSlides/Earthquake+Focus+and+Epicenter.jpg"));//1
		tutorialSlides.add(new Image("/TutorialSlides/Reverse Fault.png"));//2
		tutorialSlidesPath.add("/TutorialSlides/2019-11-27 (1).png");//0
		tutorialSlidesPath.add("/TutorialSlides/Earthquake+Focus+and+Epicenter.jpg");//1
		tutorialSlidesPath.add("/TutorialSlides/Reverse Fault.png");//2
		
		
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
		
		
		
		/*
		 * ImageView bg = new ImageView(); continueButton.setOnAction(e -> { int
		 * imageIndex = -1; imageIndex++; String imageFilename =
		 * tutorialSlidesPath.get(imageIndex); imageObject = new Image(imageFilename);
		 * bg.setImage(imageObject); });
		 */
		
		Buttons.getChildren().addAll(txt,nextButton,prevButton,continueButton,backButton);
		
		border.setTop(Buttons);
		
		slide = new StackPane();
		//background = new ImageView(tutorialSlides.get(currentSlide));
		//background.fitWidthProperty().bind(slide.widthProperty()); 
		//background.fitHeightProperty().bind(slide.heightProperty());
		slide.getChildren().add(background);
		//slide.getChildren().add(background);
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
