package Controllers;

import Model.Model;
import Model.StageName;
import Views.PreferencesView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Controller for the PreferencesView. Contains EventHandlers for button presses.
 * @author Brandon Wu
 *
 */
public class PreferencesController extends Controller<PreferencesView> {

	public PreferencesController(Model model, PreferencesView view, Main main) {
		super(model, view, main);
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
		view.getStage().setScene(Main.getScenes().get(StageName.DRAW));
		model.setStageName(StageName.CONDITIONS);
		main.getDyControl().setDrawing(view.getDrawing());
	}
	
/**
 * Returns an EventHandler for when the next button is pressed on the Preferences scene.
 * @param event
 * @return EventHandler
 */
	public EventHandler gethandleOnNextButton() {
		return event -> NextButton((MouseEvent)event);
	}
	
	public EventHandler getHandleOnZoneButton(Rectangle rect) {
		return event -> zoneButton((MouseEvent)event, rect);
	}
/**
 * This will send the user to the GardenView stage. Sets the StageName to StageName.SUGGESTIONS. 
 * Saves the users preferences in the Model's userPreferences collection. Creates the collection
 * of suggested plants from preferences selected. 
 * @param event
 */
	public void NextButton(MouseEvent event) {

		view.getStage().setScene(Main.getScenes().get(StageName.SUGGESTIONS));

		model.setStageName(StageName.SUGGESTIONS);
	}
	
	/**
	 * Modifies drawing passed in from DrawYard to fit in PreferencesView.
	 * Counts the number of separate conditions areas. Passes this number
	 * and the transformed drawing to PreferencesView
	 * @param drawing The drawing Pane from DrawYard 
	 */
	public void setDrawing(Pane drawing) {
		view.getBorder().setLeft(drawing);
		double oldWidth = drawing.getWidth();
		double newWidth = view.getBorder().getWidth() - view.getVBox().getWidth();
		double ratio = newWidth/oldWidth;
		drawing.setMaxWidth(newWidth);
		model.setNumAreas(0);
		for (Node child : drawing.getChildren()) {
			if (child.getUserData() == StageName.CONDITIONS) {
				model.setNumAreas(model.getNumAreas() + 1);
			}
			double oldX = child.getBoundsInParent().getMinX();
			child.setScaleX(ratio);
			double newX = child.getBoundsInParent().getMinX();
			child.setTranslateX(oldX * ratio - newX);
		}
		view.setDrawing(drawing);
		view.setupZoneFlips(model.getNumAreas());
	}
	
	/**
	 * Invoked when user presses any of the zone buttons.
	 * Sets and highlights the currently selected area.
	 * @param event The MouseEvent generated when the
	 * 		button was pressed  
	 * @param rect The rectangle area associated with
	 * 		the button
	 */
	public void zoneButton(MouseEvent event, Rectangle rect) {
		if (view.getCurrArea() != null) {
			view.getCurrArea().setStroke(Color.TRANSPARENT);
		}
		view.setCurrArea(rect);
		rect.setStroke(Color.RED);
	}
	
}
