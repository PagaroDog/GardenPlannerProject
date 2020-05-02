package Controllers;

import Controllers.Main;
import Model.Model;
import Model.StageName;
import Views.StartupView;
import Views.View;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author matt cohen this class is the controller for the startup screen.
 */
public class StartupController extends Controller<StartupView> {

	public StartupController(Model model, StartupView view, Main main) {
		super(model, view, main);
	}

	/**
	 * code is triggered by a press of NewButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnNewButton() {
		return event -> newButton((MouseEvent) event);
	}

	public void newButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.DRAW));
		model.setStageName(StageName.DRAW);
	}

	/**
	 * code is triggered by a press of LoadButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnLoadButton() {
		return event -> loadButton((MouseEvent) event);
	}

	public void loadButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.DESIGN));
		model.setStageName(StageName.DESIGN);
	}

	/**
	 * code is triggered by a press of TutorialButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnTutorialButton() {
		return event -> tutorialButton((MouseEvent) event);
	}

	public void tutorialButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.TUTORIAL));
		model.setStageName(StageName.TUTORIAL);
	}

}
