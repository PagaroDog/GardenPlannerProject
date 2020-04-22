package Controllers;

import Model.Model;
import Views.TutorialView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * @author Josh Stone
* this class is the controller for the Tutorial screen
*/
public class TutorialController extends Controller{
	
	TutorialView view;
	
	public TutorialController(Model model, View view) {
		super(model, view);
	}
	
	/**
	 * Handles event when user presses Next button,
	 * invoking nextButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnNextButton() {
		return null; 
	}
	
	/**
	 * Handles event when user presses Prev button,
	 * invoking prevButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPrevButton() {
		return null;
	}
	/**
	 * Goes to the next screen
	 */
	public void nextButton(MouseEvent event) {
		 
	}
	
	/**
	 * Sets condition area editing mode to label
	 */
	public void prevButton(MouseEvent event) {
		
	}
}
