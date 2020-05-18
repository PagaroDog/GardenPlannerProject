package Controllers;

import java.io.File;

import Model.DrawModeEnum;

import Model.Model;
import Model.StageNameEnum;
import Views.DrawYardView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

	private final double originalTranslate = 0;
	private final double originalScale = 1;
	private final int xInd = 0;
	private final int yInd = 1;
	private final int widthInd = 2;
	private final int heightInd = 3;
	private boolean dismissed = false;

	public DrawYardController(Model model, DrawYardView dyv, Main main) {
		super(model, dyv, main);
	}

	/**
	 * Handles event when user presses select button, invoking selectButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnSelectButton() {
		return event -> selectButton();
	}

	/**
	 * Handles event when user presses rectangle button, invoking rectButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnRectButton() {
		return event -> rectButton();
	}

	/**
	 * Handles event when user presses ellipse button, invoking ellipseButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnEllipseButton() {
		return event -> ellipseButton();
	}

	/**
	 * Handles event when user presses label button, invoking ellipseButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnLabelButton() {
		return event -> labelButton();
	}

	/**
	 * Handles event when user presses plus button, invoking plusButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnMinusButton() {
		return event -> minusButton();
	}

	/**
	 * Handles event when user presses minus button, invoking minusButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPlusButton() {
		return event -> plusButton();
	}

	/**
	 * Handles event when user presses delete button, invoking deleteButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDeleteButton() {
		return event -> deleteButton();
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
	 * Handles event when user presses remove import button, invoking
	 * removeImportButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnRemoveImportButton() {
		return event -> removeImportButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses new area button, invoking newAreaButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnNewAreaButton() {
		return event -> rectButton();
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
		return event -> nextButton();
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
	public EventHandler getHandleOnPressShape(StageNameEnum stageName) {
		return event -> pressShape((MouseEvent) event, stageName);
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
	 * Handles event when user drags on a ellipse, invoking dragEllipse()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnDragEllipse() {
		return event -> dragEllipse((MouseEvent) event);
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
	 * Handles event when user presses a key, changing the drawing mode or the label
	 * size
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnKeyPressed(TextField label, TextField width, TextField height) {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (!(label.isFocused() || width.isFocused() || height.isFocused())) {
					switch (e.getCode()) {
					case S:
						selectButton();
						break;
					case DELETE:
						deleteButton();
						break;
					case R:
						rectButton();
						break;
					case E:
						ellipseButton();
						break;
					case EQUALS:
						plusButton();
						break;
					case MINUS:
						minusButton();
						break;
					}
				}
			}
		};
	}

	/**
	 * Handles event when the user presses a key while in the label text field. If
	 * Enter is pressed, it is the same as pressing the add label button.
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnEnterPressed() {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case ENTER:
					labelButton();
					break;
				}
			}
		};
	}

	/**
	 * Sets drawing mode to select
	 */
	public void selectButton() {
		view.updateMode(DrawModeEnum.SELECT);
		model.setDrawMode(DrawModeEnum.SELECT);
	}

	/**
	 * Sets drawing mode to rectangle
	 */
	public void rectButton() {
		view.updateMode(DrawModeEnum.RECTANGLE);
		model.setDrawMode(DrawModeEnum.RECTANGLE);
	}

	/**
	 * Sets drawing mode to ellipse
	 */
	public void ellipseButton() {
		view.updateMode(DrawModeEnum.ELLIPSE);
		model.setDrawMode(DrawModeEnum.ELLIPSE);
	}

	/**
	 * Sets drawing mode to label
	 */
	public void labelButton() {
		view.removePrompts(model.getStageName());
		Node newLabel = view.addLabel();
		if (newLabel != null) {
			view.deselect(model.getCurrDrawObj());
			model.setCurrDrawObj(newLabel);
			view.select(model.getCurrDrawObj());
			selectButton();
		}
	}

	/**
	 * Decreases the label font by 1, with a minimum of 4
	 */
	public void minusButton() {
		view.setLabelSize(Math.max(view.getMinFont(), view.getLabelSize() - view.getFontDecrement()));
	}

	/**
	 * Increased the label font by 1, with a maximum of 50
	 */
	public void plusButton() {
		view.setLabelSize(Math.min(view.getMaxFont(), view.getLabelSize() + view.getFontIncrement()));
	}

	/**
	 * Deletes currently selected object, if any
	 */
	public void deleteButton() {
		view.deleteShape(model.getCurrDrawObj());
		model.setCurrDrawObj(null);
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
			view.removePrompts(model.getStageName());
		}
	}

	/**
	 * Allows user to import picture file of lawn layout
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	private void removeImportButton(MouseEvent event) {
		view.removeBackground();
	}

	/**
	 * Shows current state of objects being drawn, if in the correct mode
	 * 
	 * @param event The MouseEvent generated when the Pane was dragged
	 */
	public void dragPane(MouseEvent event) {
		if (view.removePrompts(model.getStageName())) {
			if (model.getStageName() == StageNameEnum.CONDITIONS) {
				dismissed = true;
			}
		}
		if (model.getDrawMode() != null) {
			switch (model.getDrawMode()) {
			case RECTANGLE:
				double[] newCoords = model.updateRectCoordinates(model.getDrawPressX(), model.getDrawPressY(),
						event.getX(), event.getY(), view.getDrawing().getWidth(), view.getDrawing().getHeight());
				view.updateRect((Rectangle) model.getCurrDrawObj(), newCoords[xInd], newCoords[yInd],
						newCoords[widthInd], newCoords[heightInd]);
				break;
			case ELLIPSE:
				Ellipse ellipse = (Ellipse) model.getCurrDrawObj();
				double[] newRadii = model.updateEllipseCoordinates(event.getX(), event.getY(), ellipse.getCenterX(),
						ellipse.getCenterY(), view.getDrawing().getWidth(), view.getDrawing().getHeight());
				view.updateEllipse(ellipse, newRadii[xInd], newRadii[yInd]);
			}
		}
	}

	/**
	 * Adds a rectangle, ellipse or label to drawing or does nothing based on the
	 * DrawMode. Also stores initial coordinates.
	 * 
	 * @param event The MouseEvent generated when the Pane was pressed
	 */
	public void pressPane(MouseEvent event) {
		
			view.getDrawing().requestFocus();
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
				case ELLIPSE:
					model.setCurrDrawObj(view.addEllipse(event.getX(), event.getY()));
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
	public void nextButton() {
		view.removePrompts(model.getStageName());
		view.deselect(model.getCurrDrawObj());
		model.setCurrDrawObj(null);
		model.setDrawMode(null);
		view.updateMode(null);
		if (model.getStageName() == StageNameEnum.DRAW) {
			model.setStageName(StageNameEnum.CONDITIONS);
			view.condMode();
			String widthStr = view.getWidthField().getText();
			if (widthStr.matches("[0-9]+")) {
				model.setPropertyWidthInches(Integer.valueOf(widthStr) * model.getInchesPerFoot());
			}
			String heightStr = view.getHeightField().getText();
			if (heightStr.matches("[0-9]+")) {
				model.setPropertyHeightInches(Integer.valueOf(heightStr) * model.getInchesPerFoot());
			}
		} else {
			dismissed = true;
			view.getStage().setScene(Main.getScenes().get(StageNameEnum.PREFERENCES));
			model.setStageName(StageNameEnum.PREFERENCES);
			model.setDrawMode(null);
			main.getPrefControl().setDrawing(view.getDrawing());
			main.getPrefControl().setupPrefs(view.getRectangles());
		}
		if (!dismissed) {
			view.showCondPrompt();
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
		model.setDrawMode(null);
		view.updateMode(null);
		view.deselect(model.getCurrDrawObj());
		model.setCurrDrawObj(null);
		if (model.getStageName() == StageNameEnum.DRAW) {
			view.getStage().setScene(Main.getScenes().get(StageNameEnum.WELCOME));
			model.setStageName(StageNameEnum.WELCOME);
		} else {
			view.removePrompts(model.getStageName());
			model.setStageName(StageNameEnum.DRAW);
			view.drawMode();
		}
	}

	/**
	 * In select mode and the correct stage, sets model's currDrawObj to the pressed
	 * shape
	 * 
	 * @param event The MouseEvent generated when the shape was pressed
	 */
	public void pressShape(MouseEvent event, StageNameEnum stageName) {
		model.setShapeX(((Node) event.getSource()).getBoundsInParent().getMinX());
		model.setShapeY(((Node) event.getSource()).getBoundsInParent().getMinY());
		if (model.getDrawMode() == DrawModeEnum.SELECT && model.getStageName() == stageName) {
			view.deselect(model.getCurrDrawObj());
			model.setCurrDrawObj((Node) event.getSource());
			view.select(model.getCurrDrawObj());
		}
	}

	/**
	 * In select mode, moves a dragged rectangle
	 * 
	 * @param event The MouseEvent generated when the rectangle was dragged
	 */
	public void dragRectangle(MouseEvent event) {
		if (model.getDrawMode() == DrawModeEnum.SELECT) {
			Rectangle rect = (Rectangle) event.getSource();
			double[] newCoords = model.moveRectCoordinates(event.getX(), event.getY(), rect.getWidth(),
					rect.getHeight(), view.getDrawing().getWidth(), view.getDrawing().getHeight());
			if (rect.getUserData() == model.getStageName())
				view.moveRectangle(rect, newCoords[xInd], newCoords[yInd]);
		}
	}

	/**
	 * In select mode, moves a dragged ellipse
	 * 
	 * @param event The MouseEvent generated when the ellipse was dragged
	 */
	public void dragEllipse(MouseEvent event) {
		if (model.getDrawMode() == DrawModeEnum.SELECT) {
			if (model.getStageName() == StageNameEnum.DRAW) {
				Ellipse ellipse = (Ellipse) event.getSource();
				double[] newCenters = model.moveEllipseCoordinates(event.getX(), event.getY(), ellipse.getRadiusX(),
						ellipse.getRadiusY(), view.getDrawing().getWidth(), view.getDrawing().getHeight());
				view.moveEllipse(ellipse, newCenters[xInd], newCenters[yInd]);
			}
		}
	}

	/**
	 * In select mode, moves a dragged label
	 * 
	 * @param event The MouseEvent generated when the button was pressed
	 */
	public void dragLabel(MouseEvent event) {
		if (model.getDrawMode() == DrawModeEnum.SELECT) {
			Label label = (Label) event.getSource();
			double[] newCoords = model.moveRectCoordinates(event.getSceneX(),
					event.getSceneY() - view.getToolbarHeight(), label.getWidth(), label.getHeight(),
					view.getDrawing().getWidth(), view.getDrawing().getHeight());
			if (model.getStageName() == StageNameEnum.DRAW) {
				view.moveLabel(label, newCoords[xInd], newCoords[yInd]);
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
		view.setDrawing(drawing);
		view.getRoot().setCenter(drawing);
		drawing.setPrefWidth(view.getRoot().getWidth());
		for (Node child : drawing.getChildren()) {
			child.setTranslateX(originalTranslate);
			child.setScaleX(originalScale);
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
