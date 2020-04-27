package Controllers;

import Model.Model;
import Model.StageName;
import Views.TutorialView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Josh Stone
* this class is the controller for the Tutorial screen
*/
public class TutorialController extends Controller<TutorialView>{
	
	public TutorialController(Model model, TutorialView view) {
		super(model, view);
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
	
	//int currentSlide = 0;
	/**
	 * Goes to the next slide
	 */
	public void continueButton(MouseEvent event) {
		
		//view.continueButton.setStyle("-fx-background-image: url('orc_animation/orc_forward_north.jpg')");
		//setStyle("-fx-background-image: url('/tutorial/1.jpg')");
		//model.setStageName(StageName.DRAW);
	}
	
	/**
	 * Goes to the previous slide
	 */
	public void backButton(MouseEvent event) {
		
	}


}
