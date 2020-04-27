package Controllers;

import Model.Model;
import Model.StageName;
import Views.TutorialView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Josh Stone
* this class is the controller for the Tutorial screen
*/
public class TutorialController extends Controller<TutorialView>{
	
	public TutorialController(Model model, TutorialView view, Main main) {
		super(model, view, main);
	}
	
	/**
	 * Handles event when user presses Next button,
	 * invoking nextButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnNextButton() {
		return event -> nextButton((MouseEvent) event);
	}
	
	/**
	 * Handles event when user presses Prev button,
	 * invoking prevButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPrevButton() {
		return event -> prevButton((MouseEvent)event);
	}
	
	/**
	 * Goes to the next Stage
	 */
	public void nextButton(MouseEvent event) {
		 view.getStage().setScene(Main.getScenes().get(StageName.DRAW));
		 model.setStageName(StageName.DRAW);
	}
	
	/**
	 * Goes to the previous stage
	 */
	public void prevButton(MouseEvent event) {
		 view.getStage().setScene(Main.getScenes().get(StageName.WELCOME));
		 model.setStageName(StageName.WELCOME);
	}
	
	/**
	 * Handles event when user presses Continue button,
	 * invoking contiueButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnContinueButton() {
		return event -> continueButton((MouseEvent) event);
	}
	
	/**
	 * Handles event when user presses Back button,
	 * invoking backButton()
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnBackButton() {
		return event -> backButton((MouseEvent)event);
	}
	
	
	/**
	 * Goes to the next slide
	 */
	public void continueButton(MouseEvent event) {
		view.setBackground(new ImageView(view.getTutorialSlides().get(view.getCurrentSlide())));
		view.getSlide().getChildren().add(view.getBackground());
		System.out.println(view.getCurrentSlide());
		if(!(view.getCurrentSlide() + 1 > 3)) view.setCurrentSlide(view.getCurrentSlide() + 1);
		
	}
	
	/**
	 * Goes to the previous slide
	 */
	public void backButton(MouseEvent event) {
		System.out.println(view.getCurrentSlide());
		if(!(view.getCurrentSlide() - 1<=0)) view.setCurrentSlide(view.getCurrentSlide() - 1);
		
		view.setBackground(new ImageView(view.getTutorialSlides().get(view.getCurrentSlide() - 1)));
		view.getSlide().getChildren().add(view.getBackground());
		//.getChildren().add(view.background);
		
		
	}


}
