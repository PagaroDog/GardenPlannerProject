package Controllers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PreferencesController {

	
	
/**
 * Returns an EventHandler for when the Back Button is pressed on the Preferences scene.
 * 
 * @param event MouseEvent
 * @return EventHandler
 */
	
	public EventHandler gethandleOnBackButton() {
		return null;
	}
/**
 * This will send the user back to the ConditionsView stage where they can continue to label
 * the conditions of ranges in the garden. Sets the StageName to StageName.CONDITIONS.
 * @param event
 */
	public void BackButton(MouseEvent event) {
		
	}
	
/**
 * Returns an EventHandler for when the next button is pressed on the Preferences scene.
 * @param event
 * @return EventHandler
 */
	public EventHandler gethandleOnNextButton() {
		return null;
	}
/**
 * This will send the user to the GardenView stage. Sets the StageName to StageName.Design. 
 * Saves the users preferences in the Model's userPreferences collection. Creates the collection
 * of suggested plants from preferences selected. 
 * @param event
 */
	public void NextButton(MouseEvent event) {
		
	}
	
	
}
