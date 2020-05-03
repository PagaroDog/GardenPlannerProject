package Controllers;

import java.io.File;

import Model.DrawMode;

import Model.Model;
import Model.StageName;
import Views.DrawYardView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 * The Controller for the DrawYard scene. Mainly deals with user input during
 * this scene.
 * 
 * @author Tommy White
 */
public class DrawYardController extends Controller<DrawYardView> {

	private final double minFont = 4;
	private final double maxFont = 50;

	public DrawYardController(Model model, DrawYardView dyv, Main main) {
		super(model, dyv, main);
	}

	/**
	 * Handles event when user presses select button, invoking selectButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnSelectButton() {
		return event -> selectButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses rectangle button, invoking rectButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnRectButton() {
		return event -> rectButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses circle button, invoking circleButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnCircleButton() {
		return event -> circleButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses label button, invoking circleButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnLabelButton() {
		return event -> labelButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses plus button, invoking plusButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnMinusButton() {
		return event -> minusButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses minus button, invoking minusButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPlusButton() {
		return event -> plusButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses delete button, invoking deleteButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDeleteButton() {
		return event -> deleteButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses import button, invoking importButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnImportButton() {
		return event -> importButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses new area button, invoking newAreaButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnNewAreaButton() {
		return event -> rectButton((MouseEvent) event);
	}

	/**
	 * Handles event when user drags across the main pane, invoking dragPane();
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDragPane() {
		return event -> dragPane((MouseEvent) event);
	}

	/**
	 * Handles event when user presses on Pane, invoking pressPane()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPressPane() {
		return event -> pressPane((MouseEvent) event);
	}

	/**
	 * Handles event when the user presses the next button. Invokes nextButtion()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleNextButton() {
		return event -> nextButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses prevButton, invoking prevButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandlePrevButton() {
		return event -> prevButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses on a Shape, invoking pressShape()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPressShape() {
		return event -> pressShape((MouseEvent) event);
	}

	/**
	 * Handles event when user presses on a conditions area, invoking pressArea()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPressArea() {
		return event -> pressArea((MouseEvent) event);
	}

	/**
	 * Handles event when user drags on a Rectangle, invoking dragRectangle()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDragRectangle() {
		return event -> dragRectangle((MouseEvent) event);
	}

	/**
	 * Handles event when user drags on a circle, invoking dragCircle()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDragCircle() {
		return event -> dragCircle((MouseEvent) event);
	}

	/**
	 * Handles event when user drags a label, invoking dragLabel()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDragLabel() {
		return event -> dragLabel((MouseEvent) event);
	}

	/**
	 * Sets drawing mode to select
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void selectButton(MouseEvent event) {
		model.setDrawMode(DrawMode.SELECT);
	}

	/**
	 * Sets drawing mode to rectangle
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void rectButton(MouseEvent event) {
		model.setDrawMode(DrawMode.RECTANGLE);
	}

	/**
	 * Sets drawing mode to circle
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void circleButton(MouseEvent event) {
		model.setDrawMode(DrawMode.CIRCLE);
	}

	/**
	 * Sets drawing mode to label
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void labelButton(MouseEvent event) {
		model.setDrawMode(DrawMode.LABEL);
	}

	/**
	 * Decreases the label font by 1, with a minimum of 4
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void minusButton(MouseEvent event) {
		view.setLabelSize(Math.max(minFont, view.getLabelSize() - 1));
	}

	/**
	 * Increased the label font by 1, with a maximum of 50
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void plusButton(MouseEvent event) {
		view.setLabelSize(Math.min(maxFont, view.getLabelSize() + 1));
	}

	/**
	 * Deletes currently selected object, if any
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void deleteButton(MouseEvent event) {
		view.deleteShape(model.getCurrDrawObj());
	}

	/**
	 * Allows user to import picture file of lawn layout
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void importButton(MouseEvent event) {
		File file = view.getFileChooser().showOpenDialog(view.getStage());
		if (file != null) {
            view.setBackground(file.getPath());
         }
	}

	/**
	 * Shows current state of objects being drawn, if in the correct mode
	 * 
	 * @param event The MouseEvent generated when the Pane was dragged
	 */
	public void dragPane(MouseEvent event) {
		if (model.getDrawMode() != null) {
			switch (model.getDrawMode()) {
			case RECTANGLE:
				view.updateRect((Rectangle) model.getCurrDrawObj(), model.getDrawPressX(), model.getDrawPressY(),
						event.getX(), event.getY());
				break;
			case CIRCLE:
				view.updateCircle((Ellipse) model.getCurrDrawObj(), event.getX(), event.getY());
			}
		}
	}

	/**
	 * Adds a rectangle, circle or label to drawing or does nothing based on the
	 * DrawMode. Also stores initial coordinates.
	 * 
	 * @param event The MouseEvent generated when the Pane was pressed
	 */
	public void pressPane(MouseEvent event) {
		model.setDrawPressX(event.getX());
		model.setDrawPressY(event.getY());
		if (model.getCurrDrawObj() != null) {
			view.deselect(model.getCurrDrawObj());
		}
		if (model.getDrawMode() != null) {
			switch (model.getDrawMode()) {
			case RECTANGLE:
				model.setCurrDrawObj(view.addRectangle(model.getStageName(), event.getX(), event.getY()));
				break;
			case CIRCLE:
				model.setCurrDrawObj(view.addCircle(event.getX(), event.getY()));
				break;
			case LABEL:
				model.setCurrDrawObj(view.addLabel(event.getX(), event.getY()));
				break;
			}
		}
		view.select(model.getCurrDrawObj());
	}

	/**
	 * If in draw mode, does not change view and changes model StageName to
	 * StageName.CONDITIONS If in conditions mode, sets scene to PreferencesView and
	 * model StageName to StageName.PREFERENCES
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void nextButton(MouseEvent event) {
		System.out.println(view.getDrawing().getHeight());
		view.deselect(model.getCurrDrawObj());
		model.setCurrDrawObj(null);
		if (model.getStageName() == StageName.DRAW) {
			model.setStageName(StageName.CONDITIONS);
			view.condMode();
		} else {
			view.getStage().setScene(Main.getScenes().get(StageName.PREFERENCES));
			model.setStageName(StageName.PREFERENCES);
			model.setDrawMode(null);
			main.getPrefControl().setDrawing(view.getDrawing());
		}
	}

	/**
	 * If in draw mode, sets the scene to StartupView and model StageName to
	 * StageName.WELCOME If in conditions mode, does not change view and changes
	 * model StageName to StageName.DRAW
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void prevButton(MouseEvent event) {
		view.deselect(model.getCurrDrawObj());
		model.setCurrDrawObj(null);
		if (model.getStageName() == StageName.DRAW) {
			view.getStage().setScene(Main.getScenes().get(StageName.WELCOME));
			model.setStageName(StageName.WELCOME);
		} else {
			model.setStageName(StageName.DRAW);
			view.drawMode();
		}
	}

	/**
	 * In select mode and draw mode, sets model's currDrawObj to the pressed shape
	 * 
	 * @param event The MouseEvent generated when the shape was pressed
	 */
	public void pressShape(MouseEvent event) {
		System.out.println("rect");
		if (model.getDrawMode() != null) {
			switch (model.getDrawMode()) {
			case SELECT:
				if (model.getStageName() == StageName.DRAW) {
					view.deselect(model.getCurrDrawObj());
					model.setCurrDrawObj((Node) event.getSource());
					view.select(model.getCurrDrawObj());
				}
			}
		}
	}

	/**
	 * In select mode and conditions mode, sets model's currDrawObj to the pressed
	 * conditions area
	 * 
	 * @param event The MouseEvent generated when the area was pressed
	 */
	public void pressArea(MouseEvent event) {
		if (model.getDrawMode() != null) {
			switch (model.getDrawMode()) {
			case SELECT:
				if (model.getStageName() == StageName.CONDITIONS) {
					view.deselect(model.getCurrDrawObj());
					model.setCurrDrawObj((Node) event.getSource());
					view.select(model.getCurrDrawObj());
				}
			}
		}
	}

	/**
	 * In select mode, moves a dragged rectangle
	 * 
	 * @param event The MouseEvent generated when the rectangle was dragged
	 */
	public void dragRectangle(MouseEvent event) {
		System.out.println("drag");
		if (model.getDrawMode() != null) {
			switch (model.getDrawMode()) {
			case SELECT:
				if (((Rectangle) event.getSource()).getUserData() == model.getStageName())
					view.moveRectangle((Rectangle) event.getSource(), event.getX(), event.getY());
			}
		}
	}

	/**
	 * In select mode, moves a dragged circle
	 * 
	 * @param event The MouseEvent generated when the circle was dragged
	 */
	public void dragCircle(MouseEvent event) {
		System.out.println("drag");
		if (model.getDrawMode() != null) {
			switch (model.getDrawMode()) {
			case SELECT:
				if (model.getStageName() == StageName.DRAW)
					view.moveCircle((Ellipse) event.getSource(), event.getX(), event.getY());
			}
		}
	}

	/**
	 * In select mode, moves a dragged label
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void dragLabel(MouseEvent event) {
		System.out.println("drag");
		if (model.getDrawMode() != null) {
			switch (model.getDrawMode()) {
			case SELECT:
				event.getX();
				if (model.getStageName() == StageName.DRAW)
					view.moveLabel((Label) event.getSource(), event.getSceneX(),
							event.getSceneY() - view.getToolbarHeight());
			}
		}
	}

	/**
	 * Called when returning to the DrawYard scene after entering the Preferences
	 * scene. Since the drawing was resized, it needs to be brought back to its
	 * original size.
	 * 
	 * @param drawing The drawing from the preferences screen
	 */
	public void setDrawing(Pane drawing) {
		// Set children nodes back to original size and position
		view.setDrawing(drawing);
		view.getRoot().setCenter(drawing);
		drawing.setPrefWidth(view.getRoot().getWidth());
		for (Node child : drawing.getChildren()) {
			child.setTranslateX(0);
			child.setScaleX(1);
		}
	}

	/**
	 * Gets the drawing from the DrawYardView so that it can be passed to other
	 * controllers
	 * 
	 * @return The drawing from DrawYardView
	 */
	public Pane getDrawing() {
		return view.getDrawing();
	}

	/**
	 * Gets the width of the BorderPane in DrawYardView so that it can be passed to
	 * other controllers
	 * 
	 * @return The width of root from DrawYardView
	 */
	public double getViewWidth() {
		return view.getRoot().getWidth();
	}
}
