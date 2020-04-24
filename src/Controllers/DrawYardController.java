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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
	 * 
	 */
	public EventHandler getHandleOnPressShape() {
		return event -> pressShape((MouseEvent)event);
	}
	
	/**
	 * 
	 */
	public EventHandler getHandleOnDragRectangle() {
		return event -> dragRectangle((MouseEvent)event);
	}
	
	/**
	 * 
	 */
	public EventHandler getHandleOnDragCircle() {
		return event -> dragCircle((MouseEvent)event);
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
		 view.deleteShape(model.getCurrDrawObj());
	}
	
	public void importButton(MouseEvent event){
		 
	}
	
	/**
	 * Creates new objects, or moves already made objects
	 */
	public void dragPane(MouseEvent event) {
		switch(model.getDrawMode()) {
		case RECTANGLE:
			view.updateRect(model.getDrawPressX(), model.getDrawPressY(), event.getX(), event.getY());
			break;
		case CIRCLE:
			view.updateCircle(event.getX(), event.getY());
		}
	}
	
	/**
	 * In select mode, selects object pressed, if any.
	 * In either shape mode, stores initial coordinates.
	 */
	public void pressPane(MouseEvent event) {
		System.out.println("press");
		model.setDrawPressX(event.getX());
		model.setDrawPressY(event.getY());
		switch(model.getDrawMode()) {
		case RECTANGLE:
			view.addRectangle(event.getX(), event.getY());
			break;
		case CIRCLE:
			view.addCircle(event.getX(), event.getY());
			break;
		}
	}
	
	/**
	 * In either shape mode, stores final coordinates,
	 * creating new shape.
	 */
	public void releasePane(MouseEvent event) {
		
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

	public void pressShape(MouseEvent event) {
		System.out.println("rect");
		switch(model.getDrawMode()) {
		case SELECT:
			model.setCurrDrawObj((int) ((Shape)event.getSource()).getUserData());
		}
	}
	
	public void dragRectangle(MouseEvent event) {
		System.out.println("drag");
		switch(model.getDrawMode()) {
		case SELECT:
			view.moveRectangle((int) ((Shape)event.getSource()).getUserData(), event.getX(), event.getY());
		}
	}
	
	public void dragCircle(MouseEvent event) {
		System.out.println("drag");
		switch(model.getDrawMode()) {
		case SELECT:
			view.moveCircle((int) ((Shape)event.getSource()).getUserData(), event.getX(), event.getY());
		}
	}
	
}
