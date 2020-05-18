package Views;

import java.util.ArrayList;
import Controllers.TutorialController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * 
 * The View class for the Tutorial screen. Holds graphical data and defines
 * graphical logic.
 * 
 * @author Josh Stone
 */
public class TutorialView extends View<TutorialController> {
	private HBox buttons;
	private Button continueButton;
	private Button backButton;
	private BorderPane border;

	private StackPane slide;
	private ArrayList<Image> tutorialSlides = new ArrayList<Image>();
	private ImageView background = new ImageView("/TutorialSlides/Slide1.PNG");
	private int currentSlide = 0;

	public TutorialView(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Initial setup of this class that could not be completed in the constructor
	 * since the controller had not yet been set
	 */
	@Override
	public void setup() {

		tutorialSlides.add(new Image("/TutorialSlides/Slide1.png"));// 0
		
		tutorialSlides.add(new Image("/TutorialSlides/Slide1_Outline_1.PNG")); //1
		tutorialSlides.add(new Image("/TutorialSlides/Slide2_Outline_2.PNG")); //2
		tutorialSlides.add(new Image("/TutorialSlides/Slide3_Conditions_1.PNG")); //3
		tutorialSlides.add(new Image("/TutorialSlides/Slide4_Conditions_2.PNG")); //4
		tutorialSlides.add(new Image("/TutorialSlides/Slide5_Conditions_3.PNG")); //5
		tutorialSlides.add(new Image("/TutorialSlides/Slide6_Preferences_1.PNG")); //6
		tutorialSlides.add(new Image("/TutorialSlides/Slide7_Preferences_2.PNG")); //7
		tutorialSlides.add(new Image("/TutorialSlides/Slide8_Suggestions_2.PNG")); //8
		tutorialSlides.add(new Image("/TutorialSlides/Slide9_Design_1.PNG")); //9
		tutorialSlides.add(new Image("/TutorialSlides/Slide10_Design_2.PNG")); //10
		tutorialSlides.add(new Image("/TutorialSlides/Slide11_Design_4.PNG")); //11
		tutorialSlides.add(new Image("/TutorialSlides/Slide12_Statistics.PNG")); //12
		
		tutorialSlides.add(new Image("/TutorialSlides/Slide2.PNG"));// 1
		

		border = new BorderPane();

		buttons = createToolbar();

		backButton = new Button("Previous Slide");
		backButton.setOnMouseClicked(control.getHandleOnBackButton());

		continueButton = new Button("Next Slide");
		continueButton.setOnMouseClicked(control.getHandleOnContinueButton());

		Region emptyCenter = new Region();
		HBox.setHgrow(emptyCenter, Priority.ALWAYS);

		buttons.getChildren().addAll(backButton, emptyCenter, continueButton);

		border.setTop(buttons);

		slide = new StackPane();

		BorderPane bottom = createNavigationBar("Main Menu", "Draw Yard", "Tutorial", control.getHandleOnPrevButton(),
				control.getHandleOnNextButton());

		border.setBottom(bottom);

		border.setCenter(slide);

		scene = new Scene(border, canvasWidth, canvasHeight);
		styleScene();
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

	public void initSlide() {
		background.setFitHeight(((StackPane) border.getCenter()).getHeight());
		background.setPreserveRatio(true);
		slide.getChildren().add(background);
	}

}
