package Views;
import java.util.ArrayList;
import Controllers.Controller;
import Controllers.TutorialController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
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
	private BorderPane buttons;
	private Button continueButton;
	private Button backButton;
	private BorderPane border;
	private double fontSize = 25;
	
	private final double buttonsVPadding = 10;
	private final double buttonsHPadding = 10;

	private final double bottomVPadding = 10;
	private final double bottomHPadding = 10;
	
	private StackPane slide;
	private ArrayList<Image> tutorialSlides = new ArrayList<Image>();	
	private ImageView background = new ImageView("/TutorialSlides/Slide1.PNG");;
	private int currentSlide=1;
	
	public TutorialView(Stage stage) {
		this.stage = stage;
	}
	
	public void setup() {
		
		tutorialSlides.add(new Image("/TutorialSlides/Slide1.PNG"));//0
		tutorialSlides.add(new Image("/TutorialSlides/Slide2.PNG"));//1
		tutorialSlides.add(new Image("/TutorialSlides/Slide3.PNG"));//2
		tutorialSlides.add(null);//3
		
		border = new BorderPane();
		
		buttons = new BorderPane();
		buttons.setPadding(new Insets(buttonsVPadding, buttonsHPadding, buttonsVPadding, buttonsHPadding));
		buttons.setStyle("-fx-background-color: rgba(25,100,255,1);");
		
		backButton = new Button("Previous Slide");
		backButton.setOnMouseClicked(control.getHandleOnBackButton());
		
		continueButton = new Button("Next Slide");
		continueButton.setOnMouseClicked(control.getHandleOnContinueButton());
		
		buttons.setLeft(backButton);
		buttons.setRight(continueButton);
		
		border.setTop(buttons);
		
		slide = new StackPane();
		
		slide.getChildren().add(background);//sets up initial slide
		
		prevButton = new Button("Main Menu");
		prevButton.setOnMouseClicked(control.getHandleOnPrevButton());
		
		nextButton = new Button("Draw Yard");
		nextButton.setOnMouseClicked(control.getHandleOnNextButton());

	    Label txt = new Label("Tutorial");
		txt.setFont(new Font(fontSize));
		
		BorderPane bottom = new BorderPane();
		bottom.setPadding(new Insets(bottomVPadding, bottomHPadding, bottomVPadding, bottomHPadding));
	    bottom.setStyle("-fx-background-color: rgba(168,158,255,1);");
		bottom.setLeft(prevButton);
		bottom.setRight(nextButton);
		bottom.setCenter(txt);
		
		border.setBottom(bottom);
		
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

	public StackPane getSlide() {
		return slide;
	}

	public void setSlide(StackPane slide) {
		this.slide = slide;
	}

	public ImageView getBackground() {
		return background;
	}

	public void setBackground(ImageView background) {
		this.background = background;
	}

	public int getCurrentSlide() {
		return currentSlide;
	}

	public void setCurrentSlide(int currentSlide) {
		this.currentSlide = currentSlide;
	}

	public ArrayList<Image> getTutorialSlides() {
		return tutorialSlides;
	}

	public void setTutorialSlides(ArrayList<Image> tutorialSlides) {
		this.tutorialSlides = tutorialSlides;
	}

	public BorderPane getBorder() {
		return border;
	}

	public void setBorder(BorderPane border) {
		this.border = border;
	}

}
