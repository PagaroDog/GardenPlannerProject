package Controllers;

import Model.Model;
import Views.TutorialView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class TutorialController {
	
	Model model;
	TutorialView view;
	
	public TutorialController(Model model, TutorialView tv) {
//		this.model = model;
//		view = tv;
//		
//		view.setController(this);
//		model.setTC(this);
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
