package Controllers;

import Model.Model;
import Model.Plant;
import Model.StageName;
import Views.SuggestionsView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
/**
 * SuggestionsController is used to handle all events form the SuggestionsView
 * @author Brandon Wu
 *
 */
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
			return event -> nextButton((MouseEvent)event);
		}
	/**
	 * This will send the user to the GardenView stage. Sets the StageName to StageName.DESIGN. 
	 * @param event
	 */
		public void nextButton(MouseEvent event) {

			view.getStage().setScene(Main.getScenes().get(StageName.DESIGN));

			model.setStageName(StageName.DESIGN);
		}
	/**
	 * Used to handle when the user hovers over a plant in the grid. Calls MouseEnter method with a MouseEvent
	 * @return EventHandler
	 */
	public EventHandler gethandleOnMouseEnter() {
		return event -> MouseEnter((MouseEvent)event);
	}

	/**
	 * Called by gethandleOnMouseEnter. Calls the SuggestionsView inputStats() method.
	 * @param MouseEvent event
	 */
	public void MouseEnter(MouseEvent event) {
		String[] colors = {"Blue","Green"};
		Plant dummy = new Plant("Milkweed", "ScineceMilkWeed", "Shade", 3, "Clay", "Arid", colors, 2, 5);
		model.addPlant(dummy);
		Plant dummy2 = new Plant("WhiteAsh", "ScienceWhiteAsh", "Direct Sun", 3, "Loam", "Dry", colors, 2, 5);
		model.addPlant(dummy2);
		if(view.getGrid().getColumnIndex((Pane)event.getSource())==1 &&
				view.getGrid().getRowIndex((Pane)event.getSource())==1){
			view.inputStats(event.getSource(),( model).getPlants().get(1).getName(), 
					model.getPlants().get(1).getScience(), 
					model.getPlants().get(1).getSoilType(),
					model.getPlants().get(1).getWaterlevel(), 
					model.getPlants().get(1).getLight());
		}
		else {
		view.inputStats(event.getSource(),( model).getPlants().get(0).getName(), 
				model.getPlants().get(0).getScience(), 
				model.getPlants().get(0).getSoilType(),
				model.getPlants().get(0).getWaterlevel(), 
				model.getPlants().get(0).getLight());
		}
	}
	/**
	 * Used to handle when the user leaves from hovering over a plant in the grid. Calls MouseExit method with a MouseEvent
	 * @return EventHandler
	 */
	public EventHandler gethandleOnMouseExit() {
		return event -> MouseExit((MouseEvent)event);
	}
	/**
	 * Called by gethandleOnMouseExit. Calls the SuggestionsView removeStats() method.
	 * @param MouseEvent event
	 */
	public void MouseExit(MouseEvent event) {
		view.removeStats();
	}

	public EventHandler gethandleOnMouseClick() {
		return event -> imgMouseClick((MouseEvent)event);
	}

	public void imgMouseClick(MouseEvent event) {
		view.selectImage(event);
		
	}

}
