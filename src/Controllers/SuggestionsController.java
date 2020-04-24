package Controllers;

import Model.Model;
import Model.StageName;
import Views.SuggestionsView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SuggestionsController extends Controller<SuggestionsView>{

	public SuggestionsController(Model model, SuggestionsView view) {
		super(model, view);
	}

	public EventHandler gethandleOnBackButton() {
		return event -> backButton((MouseEvent)event);
	}
	public void backButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.PREFERENCES));
		model.setStageName(StageName.PREFERENCES);
	}
	/**
	 * Returns an EventHandler for when the next button is pressed on the Suggestions scene.
	 * @param event
	 * @return EventHandler
	 */
		public EventHandler gethandleOnNextButton() {
			return event -> NextButton((MouseEvent)event);
		}
	/**
	 * This will send the user to the GardenView stage. Sets the StageName to StageName.DESIGN. 
	 * @param event
	 */
		public void NextButton(MouseEvent event) {

			view.getStage().setScene(Main.getScenes().get(StageName.DESIGN));

			model.setStageName(StageName.DESIGN);
		}

}
