package Controllers;

import Model.DrawMode;
/**
 * @author Tommy White
 */
import Model.Model;
import Model.StageName;
import Views.DrawYardView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DrawYardController extends Controller<DrawYardView>{
	
	public DrawYardController(Model model, DrawYardView dyv) {
		super(model, dyv);
	}
	
	/**
	 * Handles event when user presses select button,
	 * invoking selectButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnSelectButton() {
		return event -> selectButton((MouseEvent) event); 
	}
	
	/**
	 * Handles event when user presses rectangle button,
	 * invoking rectButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnRectButton() {
		return event -> rectButton((MouseEvent) event);
	}
	
	/**
	 * Handles event when user presses circle button,
	 * invoking circleButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnCircleButton() {
		return event -> circleButton((MouseEvent) event);
	}
	
	/**
	 * Handles event when user presses label button,
	 * invoking circleButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnLabelButton() {
		return event -> labelButton((MouseEvent) event);
	}
	
	/**
	 * Handles event when user presses delete button,
	 * invoking deleteButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDeleteButton(){
		return event -> deleteButton((MouseEvent) event); 
	}
	
	/**
	 * Handles event when user presses import button,
	 * invoking importButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnImportButton() {
		return event -> importButton((MouseEvent) event);
	}
	
	/**
	 * Handles event when user drags across the main pane,
	 * invoking dragPane();
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDragPane() {
		return event -> dragPane((MouseEvent)event);
	}
	
	/**
	 * Handles event when user presses on Pane,
	 * invoking pressPane()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPressPane() {
		return event -> pressPane((MouseEvent)event);
	}
	
	/**
	 * Handles event when user presses on Pane,
	 * invoking releasePane()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnReleasePane() {
		return event -> releasePane((MouseEvent)event);
	}
	
	/**
	 * Handles event when the user presses the next button.
	 * Invokes nextButtion()
	 * @return
	 */
	public EventHandler getHandleNextButton() {
		return event -> nextButton((MouseEvent)event);
	}
	
	/**
	 * 
	 */
	public EventHandler getHandlePrevButton() {
		return event -> prevButton((MouseEvent)event);
	}
	/**
	 * Sets drawing mode to select
	 */
	public void selectButton(MouseEvent event) {
		 model.setDrawMode(DrawMode.SELECT);
	}
	
	/**
	 * Sets drawing mode to rectangle
	 */
	public void rectButton(MouseEvent event) {
		model.setDrawMode(DrawMode.RECTANGLE);
	}
	
	/**
	 * Sets drawing mode to circle
	 */
	public void circleButton(MouseEvent event) {
		model.setDrawMode(DrawMode.CIRCLE);
	}
	
	/**
	 * Sets drawing mode to label
	 */
	public void labelButton(MouseEvent event) {
		model.setDrawMode(DrawMode.LABEL);
	}
	
	/**
	 * Deletes currently selected object, if any
	 */
	public void deleteButton(MouseEvent event){
		 
	}
	
	public void importButton(MouseEvent event){
		 
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
		System.out.println("press");
		model.setDrawPressX(event.getX());
		model.setDrawPressY(event.getY());
	}
	
	/**
	 * In either shape mode, stores final coordinates,
	 * creating new shape.
	 */
	public void releasePane(MouseEvent event) {
		if (model.getDrawMode() == DrawMode.RECTANGLE) {
//			view.addRectangle(model.getDrawPressX(), model.getDrawPressY(), event.getX(), event.getY());
			view.addRectangle();
		}
	}
	/**
	 * Sets the scene to ConditionsView, and model StageName to StageName.CONDITIONS
	 * @param event
	 */
	public void nextButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.CONDITIONS));
		model.setStageName(StageName.CONDITIONS);
	}
	
	public void prevButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.WELCOME));
		model.setStageName(StageName.WELCOME);
	}
	
}
