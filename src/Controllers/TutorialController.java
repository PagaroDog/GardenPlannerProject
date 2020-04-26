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
	 * Goes to the next screen
	 */
	public void nextButton(MouseEvent event) {
		 view.getStage().setScene(Main.getScenes().get(StageName.DRAW));
		 model.setStageName(StageName.DRAW);
	}
	
	/**
	 * Sets condition area editing mode to label
	 */
	public void prevButton(MouseEvent event) {
		 view.getStage().setScene(Main.getScenes().get(StageName.WELCOME));
		 model.setStageName(StageName.WELCOME);
	}


}
