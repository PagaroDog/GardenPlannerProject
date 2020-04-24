package Controllers;

import Model.Model;
import Model.StageName;
import Views.PreferencesView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * Controller for the PreferencesView. Contains EventHandlers for button presses.
 * @author Brandon Wu
 *
 */
public class PreferencesController extends Controller<PreferencesView> {

	public PreferencesController(Model model, PreferencesView view) {
		super(model, view);
	}


/**
 * Returns an EventHandler for when the Back Button is pressed on the Preferences scene.
 * 
 * @param event MouseEvent
 * @return EventHandler
 */
	
	public EventHandler gethandleOnBackButton() {
		return event -> BackButton((MouseEvent)event);
	}
/**
 * This will send the user back to the ConditionsView stage where they can continue to label
 * the conditions of ranges in the garden. Sets the StageName to StageName.CONDITIONS.
 * @param event
 */
	public void BackButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.CONDITIONS));
		model.setStageName(StageName.CONDITIONS);
	}
	
/**
 * Returns an EventHandler for when the next button is pressed on the Preferences scene.
 * @param event
 * @return EventHandler
 */
	public EventHandler gethandleOnNextButton() {
		return event -> NextButton((MouseEvent)event);
	}
/**
 * This will send the user to the GardenView stage. Sets the StageName to StageName.Design. 
 * Saves the users preferences in the Model's userPreferences collection. Creates the collection
 * of suggested plants from preferences selected. 
 * @param event
 */
	public void NextButton(MouseEvent event) {

		view.getStage().setScene(Main.getScenes().get(StageName.DESIGN));

		model.setStageName(StageName.DESIGN);
	}
	
	
}
