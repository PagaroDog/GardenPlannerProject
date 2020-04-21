package Controllers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class ConditionsController {
	
	/**
	 * Handles event when user presses select button,
	 * setting condition area editing mode to select
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnSelectButton() {
		return null; 
	}
	
	/**
	 * Handles event when user presses label button,
	 * setting condition area editing mode to label
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnLabelButton() {
		return null;
	}
	
	/**
	 * Handles event when user presses rectangle button,
	 * setting condition area editing mode to rectangle
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnRectButton() {
		return null;
	}
	
	/**
	 * Handles event when user presses circle button,
	 * setting condition area editing mode to circle
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnCircleButton() {
		return null;
	}
	
	/**
	 * Handles event when user presses delete button,
	 * deleting currently selected object, if any
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnDeleteButton(){
		return null; 
	}
	
	/**
	 * Handles event when user drags on Pane,
	 * creating new objects, or moving already made objects
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnDragPane() {
		return null;
	}
	
	/**
	 * Handles event when user presses on Pane,
	 * selecting object pressed, if any
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnPressPane() {
		return null;
	}
}
