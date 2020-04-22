package Controllers;

import Model.Model;
import Views.DrawYardView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class DrawYardController {
	
	Model model;
	DrawYardView view;
	
	public DrawYardController(Model model, DrawYardView dyv) {
//		this.model = model;
//		view = dyv;
//		
//		view.setController(this);
//		model.setDYC(this);
	}
	
	/**
	 * Handles event when user presses select button,
	 * invoking selectButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnSelectButton() {
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
	 * Handles event when user presses import button,
	 * invoking importButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnImportButton() {
		return null;
	}
	
	/**
	 * Handles event when user drags across the main pane,
	 * invoking dragPane();
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
	 */
	public void selectButton(MouseEvent event) {
		 
	}
	
	/**
	 * Sets condition area editing mode to label
	 */
	public void labelButton(MouseEvent event) {
		
	}
	
	/**
	 * Sets condition area editing mode to rectangle
	 */
	public void rectButton(MouseEvent event) {
		
	}
	
	/**
	 * Sets condition area editing mode to circle
	 */
	public void circleButton(MouseEvent event) {
		
	}
	
	/**
	 * Deletes currently selected object, if any
	 */
	public void deleteButton(MouseEvent event){
		 
	}
	
	/**
	 * Creates new objects, or moves already made objects
	 */
	public void dragPane(MouseEvent event) {
		
	}
	
	/**
	 * In select mode, selects object pressed, if any.
	 * In either shape mode, stores initial coordinates.
	 */
	public void pressPane(MouseEvent event) {
		
	}
	
	/**
	 * In either shape mode, stores final coordinates,
	 * creating new shape.
	 */
	public void releasePane(MouseEvent event) {
		
	}
}
