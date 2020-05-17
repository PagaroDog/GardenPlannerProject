package Controllers;

import Model.Model;
import Model.StageNameEnum;
import Views.TutorialView;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * @author Josh Stone this class is the controller for the Tutorial screen
 */

public class TutorialController extends Controller<TutorialView> {
	public TutorialController(Model model, TutorialView view, Main main) {
		super(model, view, main);
	}

	/**
	 * Handles event when user presses Next button, invoking nextButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnNextButton() {
		return event -> nextButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses Prev button, invoking prevButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnPrevButton() {
		return event -> prevButton((MouseEvent) event);
	}

	/**
	 * Goes to the next Stage
	 */
	public void nextButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageNameEnum.DRAW));
		model.setStageName(StageNameEnum.DRAW);
	}

	/**
	 * Goes to the previous stage
	 */
	public void prevButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageNameEnum.WELCOME));
		model.setStageName(StageNameEnum.WELCOME);
	}

	/**
	 * Handles event when user presses Continue button, invoking contiueButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnContinueButton() {
		return event -> continueButton((MouseEvent) event);
	}

	/**
	 * Handles event when user presses Back button, invoking backButton()
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler getHandleOnBackButton() {
		return event -> backButton((MouseEvent) event);
	}

	/**
	 * Goes to the next slide
	 */
	public void continueButton(MouseEvent event) {

		if (view.getCurrentSlide() + 1 < view.getTutorialSlides().size()) {
			ImageView iv = new ImageView(view.getTutorialSlides().get(view.getCurrentSlide() + 1));
			iv.setPreserveRatio(true);
			iv.setFitHeight(((StackPane) view.getBorder().getCenter()).getHeight());
			view.setBackground(iv);
			// view.setBackground(new
			// ImageView(view.getTutorialSlides().get(view.getCurrentSlide()+1)));
			view.getSlide().getChildren().add(view.getBackground());

			view.setCurrentSlide(view.getCurrentSlide() + 1);
			System.out.println(view.getCurrentSlide());
		}

	}

	// need to make this make sense. Either fix the numbers of
	// currentSlide/TutorialSlide or change order of background display
	// not sure why the last image goes back to w/ backButton, but not for the
	// middle images. added a null image to fix.

	/**
	 * Goes to the previous slide
	 */
	public void backButton(MouseEvent event) {

		if (view.getCurrentSlide() - 1 >= 0) {
			ImageView iv = new ImageView(view.getTutorialSlides().get(view.getCurrentSlide() - 1));
			iv.setPreserveRatio(true);
			iv.setFitHeight(((StackPane) view.getBorder().getCenter()).getHeight());
			view.setBackground(iv);
			// view.setBackground(new
			// ImageView(view.getTutorialSlides().get(view.getCurrentSlide() - 1)));
			view.getSlide().getChildren().add(view.getBackground());
			view.setCurrentSlide(view.getCurrentSlide() - 1);
			System.out.println(view.getCurrentSlide());
		}

	}

}
