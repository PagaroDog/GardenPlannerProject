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
	 * Handles event when user presses rectangle button,
	 * setting drawing mode to rectangle
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnRectButton() {
		return null;
	}
	
	/**
	 * Handles event when user presses circle button,
	 * setting drawing mode to circle
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnCircleButton() {
		return null;
	}
	
	/**
	 * Handles event when user presses import button,
	 * allowing user to import line drawing
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnImportButton() {
		return null;
	}
	
	/**
	 * Handles event when user drags across the main pane,
	 * drawing a shape based on the current mode
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnDragPane() {
		return null;
	}
}
