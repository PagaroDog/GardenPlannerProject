package Controllers;

import Model.Model;
import Model.StageName;
import Model.Season;
import Views.GardenView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author matt cohen
* this class is the controller for the Garden screen
*/
public class GardenController extends Controller<GardenView>{
	boolean copied = false;
	public GardenController(Model model, GardenView view, Main main) {
		super(model, view, main);
	}


	/**
	* code is triggered by a press of SpringButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnSpringButton() {
		return event -> springButton((MouseEvent)event);
	}
	/**
	 * sets model season to spring
	 * @param event
	 */
	public void springButton(MouseEvent event) {
		model.setSeason(Season.SPRING);
	}
	
	/**
	* code is triggered by a press of SummerButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnSummerButton() {
		return event -> summerButton((MouseEvent)event);
	}
	/**
	 * sets model season to summer
	 * @param event
	 */
	public void summerButton(MouseEvent event) {
		model.setSeason(Season.SUMMER);
		
	}
	
	/**
	* code is triggered by a press of fallButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnFallButton() {
		return event -> fallButton((MouseEvent)event);
	}
	/**
	 * sets model season to fall
	 * @param event
	 */
	public void fallButton(MouseEvent event) {
		model.setSeason(Season.FALL);
	}
	
	/**
	* code is triggered by a press of WinterButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnWinterButton() {
		return event -> winterButton((MouseEvent)event);
	}
	/**
	 * sets model season to winter
	 * @param event
	 */
	public void winterButton(MouseEvent event) {
		model.setSeason(Season.WINTER);
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
		return event -> year1Button((MouseEvent)event);
	}
	/**
	 * sets model year to 1
	 * @param event
	 */
	public void year1Button(MouseEvent event) {
		model.setYear(1);
	}
	
	/**
	* code is triggered by a press of Year2Button
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnYear2Button() {
		return event -> year2Button((MouseEvent)event);
	}
	/**
	 * sets model year to 2
	 * @param event
	 */
	public void year2Button(MouseEvent event) {
		model.setYear(2);
	}
	
	/**
	* code is triggered by a press of Year3Button
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnYear3Button() {
		return event -> year3Button((MouseEvent)event);
	}
	/**
	 * sets model year to 3
	 * @param event
	 */
	public void year3Button(MouseEvent event) {
		model.setYear(3);
	}
	
	/**
	* code is triggered by dragging an image
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnImgDrag() {
		return event -> imgDrag((MouseEvent)event);
	}
	/**
	 * runs when an image is dragged.
	 * @param event
	 */
	public void imgDrag(MouseEvent event) {
		
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
	
	/**
	 * Gets event handler for when a mouse drag is released 
	 * @return drag released mouse event 
	 */
	public EventHandler getHandlerForDragReleased() {
		return event -> dragReleased((MouseEvent) event);
	}
	/**
	 * Registers a mouse drag released event, sets copied to true so a new image will not be generated accidentally 
	 * @param event
	 */
	public void dragReleased(MouseEvent event) {
		copied = true;
	}
	
	/**
	 * Gets the drag event handler for images in the FlowPane 
	 * @param i the milkweed image that is being dragged 
	 * @return the mouse event handler for the specific milkweed image that was created 
	 */
	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}
	/**
	 * Handles the dragging logic of the static milkweed image in the TilePane 
	 * @param event the drag event that occurred
	 */
	public void drag(MouseEvent event) {
		//TODO: figure out how to add plant objects in the model
		System.out.println("in drag");
		if (event.getX() > view.getTPWidth() && !copied) {
			System.out.println("in if");
			Object plant = event.getSource();
			int index = view.addIVToFlow((ImageView) plant);	//TODO: creates the multiple children error. figure out way to clone imageview.
//			model.addX(0);
//			model.addY(event.getY());
			view.setXs(index, 0 - view.getPicSize()*index);
			view.setYs(index, event.getY());
			copied = true;
		}
	}
	
	/**
	 * Gets the event handler for when the mouse presses on the TilePane milkweed 
	 * @return mouse press event 
	 */
	public EventHandler getHandlerForPress() {
		return event -> press((MouseEvent) event);
	}
	
	/**
	 * Registers a mouse press on the milkweed image, sets copied to false so that a new milkweed image may be generated
	 * @param event the mouse pressing event 
	 */
	public void press(MouseEvent event) {
		copied = false;
	}
	
	
	

}
