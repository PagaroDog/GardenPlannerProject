package Controllers;
import Model.Model;
import Views.StartupView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**@author matt cohen
* this class is the controller for the startup screen.
*/
public class StartupController extends Controller{
	
	private StartupView StartUpView;
	
	public StartupController(Model model, View view) {
		super(model, view);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	* code is triggered by a press of NewButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnNewButton() {
		return null;
	}
	
	/**
	* code is triggered by a press of LoadButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnLoadButton() {
		return null;
	}
	
	/**
	* code is triggered by a press of TutorialButton
	* @return EventHandler object for this action
	*/
	public EventHandler handleOnTutorialButton() {
		return null;
	}
	
	
	
}
