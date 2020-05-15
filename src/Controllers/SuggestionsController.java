package Controllers;

import Model.Model;
import Model.Plant;
import Model.StageName;
import Views.SuggestionsView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
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
		return event -> nextButton();
	}

	/**
	 * This will send the user to the GardenView stage. Sets the StageName to
	 * StageName.DESIGN.
	 * 
	 * @param event
	 */
	public void nextButton() {
		
		
		view.getStage().setScene(Main.getScenes().get(StageName.DESIGN));
		model.getUserPicks(view.getGrid(),view.getRows(),view.getCols(),view.getSelectedBG());
		
		main.getGardenControl().update();
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
		
		
		String name = (String) ((Pane)event.getSource()).getUserData();

		Plant roll = model.getPlants().get(name);
		
		view.inputStats(event.getSource(), roll.getCommonNames(), name, roll.getType(), roll.getWaterLevel(), roll.getLight());
		
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
	/**
	 * Gets the scientific name of the plant at position x of the suggestedPlants ArrayList in the Model
	 * @param x index
	 * @return String Scientific Name of Plant
	 */
	public String getPlantNameAt(int x) {
		return model.getSuggestedPlants().get(x).getName();
	}
	/**
	 * Called by PreferencesController when the next button is hit. Calls the view to update Plants in the event the 
	 * suggestedPlants in the Model changed.
	 */
	public void update() {
		view.refreshPlants();
	}

}
