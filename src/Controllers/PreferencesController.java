package Controllers;

import java.util.ArrayList;
import java.util.HashSet;

import Model.GardenPref;
import Model.Model;
import Model.StageNameEnum;
import Views.PreferencesView;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
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
	private final double ORIGINAL_TRANSLATE = 0;
	private final double ORIGINAL_SCALE = 1;

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
		view.getStage().setScene(Main.getScenes().get(StageNameEnum.DRAW));
		model.setStageName(StageNameEnum.CONDITIONS);
		main.getDyControl().setDrawing(view.getDrawing());
		main.getDyControl().getView().condMode();
	}

	/**
	 * Returns an EventHandler for when the next button is pressed on the
	 * Preferences scene.
	 * 
	 * @param event
	 * @return EventHandler
	 */
	public EventHandler gethandleOnNextButton() {
		return event -> nextButton();
	}

	/**
	 * Returns an EventHandler for when a zone button is pressed on the Preferences
	 * scene.
	 * 
	 * @param rect       The rectangle associated with the zone
	 * @param gardenPref The gardenPref associated with the zone
	 * @return An EventHandler object for this action.
	 */
	public EventHandler getHandleOnZoneButton(Rectangle rect, GardenPref gardenPref) {
		return event -> zoneButton(rect, gardenPref);
	}

	/**
	 * This will send the user to the GardenView stage. Sets the StageName to
	 * StageName.SUGGESTIONS. Saves the users preferences in the Model's
	 * userPreferences collection. Creates the collection of suggested plants from
	 * preferences selected.
	 * 
	 * @param event
	 */
	public void nextButton() {
		saveUserPref(false);
		view.getStage().setScene(Main.getScenes().get(StageNameEnum.SUGGESTIONS));

		model.createSuggestions(model.getGardenPreferences().size() == 0);
		main.getSuggestionsControl().update();
		if (view.getCurrArea() != null) {
			view.getCurrArea().setStroke(Color.TRANSPARENT);
		}
		view.setCurrArea(null);
		model.setCurrPref(null);
		model.setStageName(StageNameEnum.SUGGESTIONS);

	}

	/**
	 * Modifies drawing passed in from DrawYard to fit in PreferencesView. Counts
	 * the number of separate conditions areas. Passes this number and the
	 * transformed drawing to PreferencesView
	 * 
	 * @param drawing The drawing Pane from DrawYard
	 */
	public void setDrawing(Pane drawing) {
		
		if (drawing != null) {
			view.getBorder().setLeft(drawing);
			for (Node child : drawing.getChildren()) {
				child.setTranslateX(ORIGINAL_TRANSLATE);
				child.setScaleX(ORIGINAL_SCALE);
			}
			double oldWidth = main.getDyControl().getViewWidth();
			double newWidth = view.getBorder().getWidth() - view.getVBox().getWidth();
			drawing.setPrefWidth(newWidth);
			double ratio = newWidth / oldWidth;

			for (Node child : drawing.getChildren()) {
				double oldX = child.getBoundsInParent().getMinX();
				child.setScaleX(ratio);
				double newX = child.getBoundsInParent().getMinX();
				child.setTranslateX(oldX * ratio - newX);
			}

			view.setDrawing(drawing);
			
			
		}
	}
	
	/**
	 * Sets up the garden preferences based on the areas specified by the user.
	 * 
	 * @param rectangles The Pane containing the rectangle objects
	 */
	public void setupPrefs(Pane rectangles) {
		model.getGardenPreferences().clear();
		
		for (Node child : rectangles.getChildren()) {
			if (child.getUserData() == StageNameEnum.CONDITIONS) {
				model.getGardenPreferences().add(new GardenPref((Rectangle) child));
			}
		}
		
		view.setupZoneFlips(model.getGardenPreferences());
		
		if (model.getGardenPreferences().size() > 0) {
			model.setCurrPref(model.getGardenPreferences().get(0));
			
			Platform.runLater(() -> {
				zoneButton(model.getCurrPref().getArea(), model.getCurrPref());
			});
		}
	}

	/**
	 * Returns the drawing from the PreferencesView so that it can be transferred to
	 * other controllers.
	 * 
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
	public void zoneButton(Rectangle rect, GardenPref gardenPref) {
		ObservableList<Node> colorButtons = view.getColor().getChildren();

		saveUserPref(true);
		if (view.getCurrArea() != null) {
			view.getCurrArea().setStroke(Color.TRANSPARENT);
		}

		rect.setStroke(Color.RED);
		model.setCurrPref(gardenPref);
		view.setCurrArea(rect);
		view.getName().setText(gardenPref.getName());
		view.getSun().setValue(gardenPref.getUserLight());
		view.getBloom().setValue(gardenPref.getUserBloom());
		// view.getSoil().setValue(gardenPref.getUserSoil());
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

	/**
	 * Called by zoneButton and NextButton. If stay is true, then the radio buttons
	 * are reset. If stay is false, then the radio buttons remain the same.
	 * 
	 * @param stay true -> reset RadioButtons, false -> don't reset RadioButtons
	 */
	public void saveUserPref(boolean stay) {
		ObservableList<Node> colorButtons = view.getColor().getChildren();

		ArrayList<String> colors = new ArrayList<String>();
		if (model.getCurrPref() != null) {
			model.getCurrPref().setName(view.getName().getText());
			model.getCurrPref().setUserLight(view.getSun().getValue());
			model.getCurrPref().setUserBloom(view.getBloom().getValue());
			// model.getCurrPref().setUserSoil(view.getSoil().getValue());
			model.getCurrPref().setUserWater(view.getWater().getValue());
			model.getCurrPref().setArea(view.getCurrArea());

			for (int i = 0; i < colorButtons.size(); i++) {
				RadioButton button = (RadioButton) (colorButtons.get(i));
				if (button.isSelected()) {
					colors.add(button.getText());
					if (stay) {
						button.setSelected(false);
					}
				}
			}
			HashSet<String> strings = new HashSet<String>();
			for (String col : colors) {
				strings.add(col);
			}
			model.getCurrPref().setUserColor(strings);
		}

	}

}
