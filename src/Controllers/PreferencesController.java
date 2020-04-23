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
public class PreferencesController extends Controller {

	private Stage stage;
	public PreferencesController(Model model, View Pview) {
		super(model, Pview);
		this.stage = Pview.getStage();
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
		stage.setScene(Main.getScenes().get(StageName.DRAW));
		model.setStageName(StageName.DRAW);
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
		stage.setScene(Main.getScenes().get(StageName.DESIGN));
		model.setStageName(StageName.DESIGN);
	}
	
	
}
