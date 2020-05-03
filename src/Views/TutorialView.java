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
	private ImageView background = new ImageView("/TutorialSlides/Slide1.PNG");;
	private int currentSlide = 1;

	public TutorialView(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Initial setup of this class that could not be completed in the constructor
	 * since the controller had not yet been set
	 */
	@Override
	public void setup() {

		tutorialSlides.add(new Image("/TutorialSlides/Slide1.PNG"));// 0
		tutorialSlides.add(new Image("/TutorialSlides/Slide2.PNG"));// 1
		tutorialSlides.add(new Image("/TutorialSlides/Slide3.PNG"));// 2
		tutorialSlides.add(null);// 3

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

		slide.getChildren().add(background);// sets up initial slide

		BorderPane bottom = createNavigationBar("Main Menu", "Draw Yard", "Tutorial", control.getHandleOnPrevButton(), control.getHandleOnNextButton());

		border.setBottom(bottom);

		border.setCenter(slide);

		scene = new Scene(border, canvasWidth, canvasHeight);

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
