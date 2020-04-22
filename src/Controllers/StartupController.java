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


/**@author matt cohen
* this class is the controller for the startup screen.
*/
public class StartupController extends Controller{
	private Model model;
	private View StartUpView;
	private Button newButton;
	private Button loadButton;
	private Button tutorialButton;
	
	private Stage stage;
	

	
	public StartupController(Model model, View startView) {
		super(model,startView);
	}

	/**
	* code is triggered by a press of NewButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnNewButton() {
		return event->newButton((MouseEvent)event);
	}
	
	public void newButton(MouseEvent event) {
		stage.setScene(Main.getScenes().get(StageName.DRAW));
	}

	/**
	* code is triggered by a press of LoadButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnLoadButton() {
		return event->loadButton((MouseEvent)event);
	}
	
	public void loadButton(MouseEvent event) {
		stage.setScene(Main.getScenes().get(StageName.DESIGN));
	}

	/**
	* code is triggered by a press of TutorialButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnTutorialButton() {
		return event->tutorialButton((MouseEvent)event);
	}

	public void tutorialButton(MouseEvent event) {
		stage.setScene(Main.getScenes().get(StageName.TUTORIAL));
	}
	
	
	
}
