package Controllers;
import Model.Model;
import Model.StageName;
import Views.ConditionsView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Tommy White
 */
public class ConditionsController extends Controller<ConditionsView> {
	
	public ConditionsController(Model model, ConditionsView view) {
		super(model, view);
	}

	/**
	 * @return the model of the class
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * Sets the model of the class to parameter model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * @return the view of the class
	 */
	public ConditionsView getView() {
		return view;
	}

	/**
	 * Sets the view of the class to parameter view
	 */
	public void setView(ConditionsView view) {
		this.view = view;
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
	 * Handles event when user presses label button,
	 * invoking labelButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnLabelButton() {
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
	 * Handles event when user drags on Pane,
	 * invoking dragPane()
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
	 * Handles event when user presses next button,
	 * invoking nextButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleNextButton() {
		return event -> nextButton((MouseEvent) event);
	}
	
	/**
	 * Handles event when user presses next button,
	 * invoking prevButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandlePrevButton() {
		return event -> prevButton((MouseEvent) event);
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
	
	/**
	 * Sets the scene to PreferencesView, and model StageName to StageName.PREFERENCES
	 * @param event
	 */
	public void nextButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.PREFERENCES));
		model.setStageName(StageName.PREFERENCES);
	}
	
	/**
	 * Sets the scene to DrawYardView, and model StageName to StageName.DRAW
	 * @param event
	 */
	public void prevButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.DRAW));
		model.setStageName(StageName.DRAW);
	}
}
