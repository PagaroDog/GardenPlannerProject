package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

import Model.Model;
import Model.StageNameEnum;
import Views.StartupView;
import Views.View;
import javafx.event.EventHandler;

/**
 * This class is the controller for the startup screen.
 * 
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

	/**
	 * Brings the user to the DrawYard Screen
	 */
	public void newButton() {
		view.getStage().setScene(Main.getScenes().get(StageNameEnum.DRAW));
		model.setStageName(StageNameEnum.DRAW);
	}

	/**
	 * code is triggered by a press of LoadButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnLoadButton() {
		return event -> loadButton();
	}

	/**
	 * Loads the model from a previously saved garden and updates the program
	 * accordingly, bringing the user to the Garden screen.
	 */
	public void loadButton() {
		File file = view.getFileChooser().showOpenDialog(view.getStage());
		try {
			FileInputStream fileIn = new FileInputStream(file.getPath());
			ObjectInputStream input = new ObjectInputStream(fileIn);
			Model loadedModel = (Model) input.readObject();
			if (loadedModel.getWidthOnSave() == View.getCanvasWidth()
					&& loadedModel.getHeightOnSave() == View.getCanvasHeight()) {
				main.setModel(loadedModel);
				main.updateModel();
				newButton();
				main.getDyControl().nextButton();
				main.getDyControl().nextButton();
				main.getPrefControl().nextButton();
				main.getSuggestionsControl().nextButton();
				main.getGardenControl().loadPlants();
			} else {
				System.out.println("Cannot load garden - screen size mismatch.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (InvalidClassException e) {
			System.out.println("Software has been updated. This garden file is no longer compatible.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("No file chosen.");
		}
	}

	/**
	 * code is triggered by a press of TutorialButton
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnTutorialButton() {
		return event -> tutorialButton();
	}

	/**
	 * Brings the user to the Tutorial screen.
	 */
	public void tutorialButton() {
		view.getStage().setScene(Main.getScenes().get(StageNameEnum.TUTORIAL));
		model.setStageName(StageNameEnum.TUTORIAL);
		main.getTutControl().getView().initSlide();
	}

	/**
	 * Handles the event when the user presses the credits button.
	 * 
	 * @return An EventHandler for this action
	 */
	public EventHandler handleOnCreditsButton() {
		return event -> view.credits();
	}

	/**
	 * Generates credits for the software, including the developers' names and the
	 * authors of and links to the plants images used.
	 * 
	 * @return A String containing the credits.
	 */
	public String generateCredits() {
		String credits = "Developers:" + "\nMatt Cohen" + "\nIan McCabe" + "\nJosh Stone" + "\nTommy White"
				+ "\nBrandon Wu";
		for (String plantName : view.getImgs().getPlantImages().keySet()) {
			credits += "\n\n" + plantName + " image:";
			String[] src = view.getImgs().getPlantImages().get(plantName)[0].getSourceInfo();
			credits += "\nAuthor: " + src[0];
			credits += "\nLink: " + src[1];
		}
		return credits;
	}

}
