package Controllers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class ConditionsController {
	
	/**
	 * Handles event when user presses select button,
	 * invoking selectButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnSelectButton() {
		return null; 
	}
	
	/**
	 * Handles event when user presses label button,
	 * invoking labelButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnLabelButton() {
		return null;
	}
	
	/**
	 * Handles event when user presses rectangle button,
	 * invoking rectButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnRectButton() {
		return null;
	}
	
	/**
	 * Handles event when user presses circle button,
	 * invoking circleButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnCircleButton() {
		return null;
	}
	
	/**
	 * Handles event when user presses delete button,
	 * invoking deleteButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDeleteButton(){
		return null; 
	}
	
	/**
	 * Handles event when user drags on Pane,
	 * invoking dragPane()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDragPane() {
		return null;
	}
	
	/**
	 * Handles event when user presses on Pane,
	 * invoking pressPane()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPressPane() {
		return null;
	}
	
	/**
	 * Handles event when user presses on Pane,
	 * invoking releasePane()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnReleasePane() {
		return null;
	}
	
	/**
	 * Sets condition area editing mode to select
	 * @return EventHandler object for this action
	 */
	public void selectButton(MouseEvent event) {
		 
	}
	
	/**
	 * Sets condition area editing mode to label
	 * @return EventHandler object for this action
	 */
	public void labelButton(MouseEvent event) {
		
	}
	
	/**
	 * Sets condition area editing mode to rectangle
	 * @return EventHandler object for this action
	 */
	public void rectButton(MouseEvent event) {
		
	}
	
	/**
	 * Sets condition area editing mode to circle
	 * @return EventHandler object for this action
	 */
	public void circleButton(MouseEvent event) {
		
	}
	
	/**
	 * Deletes currently selected object, if any
	 * @return EventHandler object for this action
	 */
	public void deleteButton(MouseEvent event){
		 
	}
	
	/**
	 * Creates new objects, or moves already made objects
	 * @return EventHandler object for this action
	 */
	public void dragPane(MouseEvent event) {
		
	}
	
	/**
	 * In select mode, selects object pressed, if any.
	 * In either shape mode, stores initial coordinates.
	 * @return EventHandler object for this action
	 */
	public void pressPane(MouseEvent event) {
		
	}
	
	/**
	 * In either shape mode, stores final coordinates,
	 * creating new shape.
	 * @return EventHandler object for this action
	 */
	public void releasePane(MouseEvent event) {
		
	}
}
