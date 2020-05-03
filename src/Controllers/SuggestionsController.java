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
 * 
 * @author Brandon Wu
 *
 */
public class SuggestionsController extends Controller<SuggestionsView> {

	public SuggestionsController(Model model, SuggestionsView view, Main main) {
		super(model, view, main);
	}

	public EventHandler gethandleOnBackButton() {
		return event -> backButton((MouseEvent) event);
	}

	public void backButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.PREFERENCES));
		model.setStageName(StageName.PREFERENCES);
	}

	/**
	 * Returns an EventHandler for when the next button is pressed on the
	 * Suggestions scene.
	 * 
	 * @param event
	 * @return EventHandler
	 */
	public EventHandler gethandleOnNextButton() {
		return event -> nextButton((MouseEvent) event);
	}

	/**
	 * This will send the user to the GardenView stage. Sets the StageName to
	 * StageName.DESIGN.
	 * 
	 * @param event
	 */
	public void nextButton(MouseEvent event) {

		view.getStage().setScene(Main.getScenes().get(StageName.DESIGN));

		model.setStageName(StageName.DESIGN);

		main.getGardenControl().setDrawing(main.getPrefControl().getDrawing());
	}

	/**
	 * Used to handle when the user hovers over a plant in the grid. Calls
	 * MouseEnter method with a MouseEvent
	 * 
	 * @return EventHandler
	 */
	public EventHandler gethandleOnMouseEnter() {
		return event -> MouseEnter((MouseEvent) event);
	}

	/**
	 * Called by gethandleOnMouseEnter. Calls the SuggestionsView inputStats()
	 * method.
	 * 
	 * @param MouseEvent event
	 */
	public void MouseEnter(MouseEvent event) {
		String[] colors = { "Blue", "Green" };
		if (view.getGrid().getColumnIndex((Pane) event.getSource()) == 1
				&& view.getGrid().getRowIndex((Pane) event.getSource()) == 1) {
			view.inputStats(event.getSource(), (model).getPlants().get("Asclepias tuberosa").getName(),
					model.getPlants().get("Asclepias tuberosa").getCommonNames(),
					model.getPlants().get("Asclepias tuberosa").getType(),
					model.getPlants().get("Asclepias tuberosa").getWaterLevel(),
					model.getPlants().get("Asclepias tuberosa").getLight());
		} else {
			view.inputStats(event.getSource(), (model).getPlants().get("Fraxinus americana").getName(),
					model.getPlants().get("Fraxinus americana").getCommonNames(),
					model.getPlants().get("Fraxinus americana").getType(),
					model.getPlants().get("Fraxinus americana").getWaterLevel(),
					model.getPlants().get("Fraxinus americana").getLight());
		}
	}

	/**
	 * Used to handle when the user leaves from hovering over a plant in the grid.
	 * Calls MouseExit method with a MouseEvent
	 * 
	 * @return EventHandler
	 */
	public EventHandler gethandleOnMouseExit() {
		return event -> MouseExit((MouseEvent) event);
	}

	/**
	 * Called by gethandleOnMouseExit. Calls the SuggestionsView removeStats()
	 * method.
	 * 
	 * @param MouseEvent event
	 */
	public void MouseExit(MouseEvent event) {
		view.removeStats();
	}

	/**
	 * Used to handle when the user selects the plant from the suggested grid. Calls
	 * imgMouseClick
	 * 
	 * @return EventHandler
	 */
	public EventHandler gethandleOnMouseClick() {
		return event -> imgMouseClick((MouseEvent) event);
	}

	/**
	 * Called by gethandeleOnMouseClick. Calls the SuggestionsView selectImage
	 * method
	 * 
	 * @param MouseEvent event
	 */
	public void imgMouseClick(MouseEvent event) {
		view.selectImage(event);

	}
	public String getPlantNameAt(int x) {
		return model.getSuggestedPlants().get(x).getName();
	}

}
