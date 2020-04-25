package Controllers;

import Model.DrawMode;
/**
 * @author Tommy White
 */
import Model.Model;
import Model.StageName;
import Views.DrawYardView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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
	 * Handles event when the user presses the next button.
	 * Invokes nextButtion()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleNextButton() {
		return event -> nextButton((MouseEvent)event);
	}
	
	/**
	 * Handles event when user presses prevButton,
	 * invoking prevButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandlePrevButton() {
		return event -> prevButton((MouseEvent)event);
	}
	
	/**
	 * Handles event when user presses on a Shape,
	 * invoking pressShape()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPressShape() {
		return event -> pressShape((MouseEvent)event);
	}
	
	/**
	 * Handles event when user drags on a Rectangle,
	 * invoking dragRectangle()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDragRectangle() {
		return event -> dragRectangle((MouseEvent)event);
	}
	
	/**
	 * Handles event when user drags on a circle,
	 * invoking dragCircle()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDragCircle() {
		return event -> dragCircle((MouseEvent)event);
	}
	
	/**
	 * Sets drawing mode to select
	 * @param event The MouseEvent generated when the
	 * 		button was pressed
	 */
	public void selectButton(MouseEvent event) {
		 model.setDrawMode(DrawMode.SELECT);
	}
	
	/**
	 * Sets drawing mode to rectangle
	 * @param event The MouseEvent generated when the
	 * 		button was pressed
	 */
	public void rectButton(MouseEvent event) {
		model.setDrawMode(DrawMode.RECTANGLE);
	}
	
	/**
	 * Sets drawing mode to circle
	 * @param event The MouseEvent generated when the
	 * 		button was pressed
	 */
	public void circleButton(MouseEvent event) {
		model.setDrawMode(DrawMode.CIRCLE);
	}
	
	/**
	 * Sets drawing mode to label
	 * @param event The MouseEvent generated when the
	 * 		button was pressed
	 */
	public void labelButton(MouseEvent event) {
		model.setDrawMode(DrawMode.LABEL);
	}
	
	/**
	 * Deletes currently selected object, if any
	 * @param event The MouseEvent generated when the
	 * 		button was pressed
	 */
	public void deleteButton(MouseEvent event){
		 view.deleteShape(model.getCurrDrawObj());
	}
	
	/**
	 * Allows user to import picture file of lawn layout
	 * @param event The MouseEvent generated when the
	 * 		button was pressed
	 */
	public void importButton(MouseEvent event){
		 
	}
	
	/**
	 * Creates new objects, if in the correct mode
	 * @param event The MouseEvent generated when the
	 * 		Pane was dragged
	 */
	public void dragPane(MouseEvent event) {
		switch(model.getDrawMode()) {
		case RECTANGLE:
			view.updateRect((Rectangle) model.getCurrDrawObj(), model.getDrawPressX(), model.getDrawPressY(), event.getX(), event.getY());
			break;
		case CIRCLE:
			view.updateCircle((Ellipse) model.getCurrDrawObj(), event.getX(), event.getY());
		}
	}
	
	/**
	 * In select mode, selects object pressed, if any.
	 * Also stores initial coordinates.
	 * @param event The MouseEvent generated when the
	 * 		Pane was pressed
	 */
	public void pressPane(MouseEvent event) {
		model.setDrawPressX(event.getX());
		model.setDrawPressY(event.getY());
		switch(model.getDrawMode()) {
		case RECTANGLE:
			if (model.getCurrDrawObj() != null) {
				view.deselect((Shape) model.getCurrDrawObj());
			}
			model.setCurrDrawObj(view.addRectangle(event.getX(), event.getY()));
			view.select((Shape) model.getCurrDrawObj());
			break;
		case CIRCLE:
			if (model.getCurrDrawObj() != null) {
				view.deselect((Shape) model.getCurrDrawObj());
			}
			model.setCurrDrawObj(view.addCircle(event.getX(), event.getY()));
			view.select((Shape) model.getCurrDrawObj());
			break;
		}
	}
	
	/**
	 * Sets the scene to ConditionsView, and model StageName to StageName.CONDITIONS
	 * @param event The MouseEvent generated when the
	 * 		button was pressed
	 */
	public void nextButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.CONDITIONS));
		model.setStageName(StageName.CONDITIONS);
	}
	
	/**
	 * Sets the scene to StartupView, and model StageName to StageName.WELCOME
	 * @param event The MouseEvent generated when the
	 * 		button was pressed
	 */
	public void prevButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.WELCOME));
		model.setStageName(StageName.WELCOME);
	}

	/**
	 * In select mode, sets model's currDrawObj to the pressed shape
	 * @param event The MouseEvent generated when the
	 * 		shape was pressed
	 */
	public void pressShape(MouseEvent event) {
		System.out.println("rect");
		switch(model.getDrawMode()) {
		case SELECT:
			view.deselect((Shape) model.getCurrDrawObj());
			model.setCurrDrawObj((Node) event.getSource());
			view.select((Shape) model.getCurrDrawObj());
		}
	}
	
	/**
	 * In select mode, moves a dragged rectangle
	 * @param event The MouseEvent generated when the
	 * 		rectangle was dragged 
	 */
	public void dragRectangle(MouseEvent event) {
		System.out.println("drag");
		switch(model.getDrawMode()) {
		case SELECT:
			view.moveRectangle((Rectangle)event.getSource(), event.getX(), event.getY());
		}
	}
	
	/**
	 * In select mode, moves a dragged circle
	 * @param event The MouseEvent generated when the
	 * 		circle was dragged
	 */
	public void dragCircle(MouseEvent event) {
		System.out.println("drag");
		switch(model.getDrawMode()) {
		case SELECT:
			view.moveCircle((Ellipse)event.getSource(), event.getX(), event.getY());
		}
	}

	public void releasePane(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub
		
	}
	
}
