package Controllers;

import Model.Model;
import Model.StageName;
import Views.GardenView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author matt cohen
* this class is the controller for the Garden screen
*/
public class GardenController extends Controller<GardenView>{
	
	public GardenController(Model model, GardenView view, Main main) {
		super(model, view, main);
	}


	/**
	* code is triggered by a press of SpringButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnSpringButton() {
		return null;
	}
	
	/**
	* code is triggered by a press of SummerButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnSummerButton() {
		return null;
	}
	
	/**
	* code is triggered by a press of fallButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnFallButton() {
		return null;
	}
	
	/**
	* code is triggered by a press of WinterButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnWinterButton() {
		return null;
	}
	
	/**
	* code is triggered by a press of StatsButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnStatsButton() {
		return event -> statsButton((MouseEvent)event);
	}
	/**
	 * Sets the scene to StatisticsView and model StageName to StageName.STATS
	 * @param event
	 */
	public void  statsButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.STATS));
		model.setStageName(StageName.STATS);
	}


	/**
	* code is triggered by a press of Year1Button
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnYear1Button() {
		return null;
	}
	
	/**
	* code is triggered by a press of Year2Button
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnYear2Button() {
		return null;
	}
	
	/**
	* code is triggered by a press of Year3Button
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnYear3Button() {
		return null;
	}
	
	/**
	* code is triggered by dragging an image
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnImgDrag() {
		return null;		
	}
	
	/**
	* code is triggered by a press of PrefButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnPrefButton() {
		return event -> prefButton((MouseEvent)event);
	}
	/**
	 * Sets the scene to PreferencesView and model StageName to StageName.PREFERENCES
	 * @param event
	 */
	public void prefButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.PREFERENCES));
		model.setStageName(StageName.PREFERENCES);
	}


	public void ImgDrag(MouseEvent e) {
		
	}

	/**
	* code is triggered by a press of SaveButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnSaveButton() {
		return event -> saveButton();
	}

	/**
	 * Sets the scene to SaveView and model StageName to StageName.SAVE
	 * @param event
	 */
	public void saveButton() {
		view.getStage().setScene(Main.getScenes().get(StageName.SAVE));
		model.setStageName(StageName.SAVE);
	}

}
