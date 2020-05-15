package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import Model.Model;
import Model.StageName;
import Views.GardenView;
import Views.StartupView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * This class is the controller for the startup screen.
 * @author matt cohen
 * @author Tommy White
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
		return event -> newButton();
	}

	public void newButton() {
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
		File file = view.getFileChooser().showOpenDialog(view.getStage());
		try {
			FileInputStream fileIn = new FileInputStream(file.getPath());
			ObjectInputStream input = new ObjectInputStream(fileIn);
			main.setModel((Model) input.readObject());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("No file chosen.");
		}
		main.updateModel();
		newButton();
		main.getDyControl().nextButton();
		main.getDyControl().nextButton();
		main.getPrefControl().nextButton();
		main.getSuggestionsControl().nextButton();
		main.getGardenControl().loadPlants();
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
		main.getTutControl().getView().initSlide();
	}

}
