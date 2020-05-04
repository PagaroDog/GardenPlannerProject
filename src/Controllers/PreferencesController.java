package Controllers;


import java.util.ArrayList;
import java.util.HashSet;

import Model.GardenPref;
import Model.Model;
import Model.StageName;
import Views.PreferencesView;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Controller for the PreferencesView. Contains EventHandlers for button
 * presses.
 * 
 * @author Brandon Wu
 *
 */
public class PreferencesController extends Controller<PreferencesView> {

	public PreferencesController(Model model, PreferencesView view, Main main) {
		super(model, view, main);
	}

	/**
	 * Returns an EventHandler for when the Back Button is pressed on the
	 * Preferences scene.
	 * 
	 * @param event MouseEvent
	 * @return EventHandler
	 */

	public EventHandler gethandleOnBackButton() {
		return event -> BackButton((MouseEvent) event);
	}

	/**
	 * This will send the user back to the ConditionsView stage where they can
	 * continue to label the conditions of ranges in the garden. Sets the StageName
	 * to StageName.CONDITIONS.
	 * 
	 * @param event
	 */
	public void BackButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.DRAW));
		model.setStageName(StageName.CONDITIONS);
		main.getDyControl().setDrawing(view.getDrawing());
	}

	/**
	 * Returns an EventHandler for when the next button is pressed on the
	 * Preferences scene.
	 * 
	 * @param event
	 * @return EventHandler
	 */
	public EventHandler gethandleOnNextButton() {
		return event -> NextButton((MouseEvent) event);
	}

	public EventHandler getHandleOnZoneButton(Rectangle rect, GardenPref gardenPref) {
		return event -> zoneButton((MouseEvent) event, rect, gardenPref);
	}

	/**
	 * This will send the user to the GardenView stage. Sets the StageName to
	 * StageName.SUGGESTIONS. Saves the users preferences in the Model's
	 * userPreferences collection. Creates the collection of suggested plants from
	 * preferences selected.
	 * 
	 * @param event
	 */
	public void NextButton(MouseEvent event) {

		view.getStage().setScene(Main.getScenes().get(StageName.SUGGESTIONS));
		
		model.createSuggestions();
		model.setStageName(StageName.SUGGESTIONS);
		System.out.println(model.getGardenPreferences().get(0).getUserBloom());
	}

	/**
	 * Modifies drawing passed in from DrawYard to fit in PreferencesView. Counts
	 * the number of separate conditions areas. Passes this number and the
	 * transformed drawing to PreferencesView
	 * 
	 * @param drawing The drawing Pane from DrawYard
	 */
	public void setDrawing(Pane drawing) {
		view.getBorder().setLeft(drawing);
		for (Node child : drawing.getChildren()) {
			child.setTranslateX(0);
			child.setScaleX(1);
		}
		double oldWidth = main.getDyControl().getViewWidth();
		System.out.println(oldWidth);
		double newWidth = view.getBorder().getWidth() - view.getVBox().getWidth();
		System.out.println(newWidth);
		drawing.setPrefWidth(newWidth);
		double ratio = newWidth / oldWidth;
		model.getGardenPreferences().clear();
		for (Node child : drawing.getChildren()) {
			if (child.getUserData() == StageName.CONDITIONS) {
				model.getGardenPreferences().add(new GardenPref());
			}
			double oldX = child.getBoundsInParent().getMinX();
			System.out.println(oldX);
			child.setScaleX(ratio);
			double newX = child.getBoundsInParent().getMinX();
			System.out.println(newX);
			child.setTranslateX(oldX * ratio - newX);
		}
		view.setDrawing(drawing);
		view.setupZoneFlips(model.getGardenPreferences());
		model.setCurrPref(model.getGardenPreferences().get(0));
	}

	
	/**
	 * Returns the drawing from the PreferencesView so that it can be transferred to other controllers.
	 * @return The drawing from view
	 */
	public Pane getDrawing() {
		return view.getDrawing();
	}

	/**
	 * Invoked when user presses any of the zone buttons. Sets and highlights the
	 * currently selected area.
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 * @param rect  The rectangle area associated with the button
	 */
	public void zoneButton(MouseEvent event, Rectangle rect, GardenPref gardenPref) {
		ObservableList<Node> colorButtons = view.getColor().getChildren();
		ArrayList<String> colors = new ArrayList<String>();
		if (model.getCurrPref() != null) {
			System.out.println("Saving Prefs");
			model.getCurrPref().setName(view.getName().getText());
			model.getCurrPref().setUserLight(view.getSun().getValue());
			model.getCurrPref().setUserBloom(view.getBloom().getValue());
			//model.getCurrPref().setUserSoil(view.getSoil().getValue());
			model.getCurrPref().setUserWater(view.getWater().getValue());

			for (int i = 0; i < colorButtons.size(); i++) {
				RadioButton button = (RadioButton) (colorButtons.get(i));
				if (button.isSelected()) {
					colors.add(button.getText());
					button.setSelected(false);
				}
			}
			HashSet<String> strings = new HashSet<String>();
			for(String col:colors) {
				strings.add(col);
			}
			model.getCurrPref().setUserColor(strings);
		}
		if (view.getCurrArea() != null) {
			view.getCurrArea().setStroke(Color.TRANSPARENT);
		}
		
		rect.setStroke(Color.RED);
		model.setCurrPref(gardenPref);
		view.setCurrArea(rect);
		view.getName().setText(gardenPref.getName());
		view.getSun().setValue(gardenPref.getUserLight());
		view.getBloom().setValue(gardenPref.getUserBloom());
		//view.getSoil().setValue(gardenPref.getUserSoil());
		view.getWater().setValue(gardenPref.getUserWater());
		if (gardenPref.getUserColor() != null) {
			for (String str : gardenPref.getUserColor()) {
				for (int i = 0; i < colorButtons.size(); i++) {
					RadioButton button = (RadioButton) (colorButtons.get(i));
					if (!button.isSelected()) {
						button.setSelected(str.equals(button.getText()));
					}
				}
			}
		}

	}
	
	


	

}
