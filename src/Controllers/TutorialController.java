package Controllers;

import Model.Model;
import Model.StageName;
import Views.TutorialView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Josh Stone
* this class is the controller for the Tutorial screen
*/
public class TutorialController extends Controller<TutorialView>{
	
	public TutorialController(Model model, TutorialView view, Main main) {
		super(model, view, main);
	}
	
	/**
	 * Handles event when user presses Next button,
	 * invoking nextButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnNextButton() {
		return event -> nextButton((MouseEvent) event);
	}
	
	/**
	 * Handles event when user presses Prev button,
	 * invoking prevButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPrevButton() {
		return event -> prevButton((MouseEvent)event);
	}
	
	/**
	 * Goes to the next Stage
	 */
	public void nextButton(MouseEvent event) {
		 view.getStage().setScene(Main.getScenes().get(StageName.DRAW));
		 model.setStageName(StageName.DRAW);
	}
	
	/**
	 * Goes to the previous stage
	 */
	public void prevButton(MouseEvent event) {
		 view.getStage().setScene(Main.getScenes().get(StageName.WELCOME));
		 model.setStageName(StageName.WELCOME);
	}
	
	/**
	 * Handles event when user presses Continue button,
	 * invoking contiueButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnContinueButton() {
		return event -> continueButton((MouseEvent) event);
	}
	
	/**
	 * Handles event when user presses Back button,
	 * invoking backButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnBackButton() {
		return event -> backButton((MouseEvent)event);
	}
	
	
	/**
	 * Goes to the next slide
	 */
	public void continueButton(MouseEvent event) {
		view.background=new ImageView(view.tutorialSlides.get(view.currentSlide));
		view.slide.getChildren().add(view.background);
		System.out.println(view.currentSlide);
		if(!(view.currentSlide+1>3)) view.currentSlide++;
		
	}
	
	/**
	 * Goes to the previous slide
	 */
	public void backButton(MouseEvent event) {
		System.out.println(view.currentSlide);
		if(!(view.currentSlide-1<=0)) view.currentSlide--;
		
		view.background=new ImageView(view.tutorialSlides.get(view.currentSlide-1));
		view.slide.getChildren().add(view.background);
		//.getChildren().add(view.background);
		
		
	}


}
