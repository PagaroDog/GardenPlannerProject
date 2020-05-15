package Controllers;

import Model.Model;
import Model.StageNameEnum;
import Views.StatisticsView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * @author Tommy White
 */
public class StatisticsController extends Controller<StatisticsView> {

	public StatisticsController(Model model, StatisticsView view, Main main) {
		super(model, view, main);
	}

	/**
	 * Handles event when user presses Back button, bringing the user back to the
	 * design stage.
	 * 
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnBackButton() {
		return event -> backButton((MouseEvent) event);
	}

	public void backButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageNameEnum.DESIGN));
		model.setStageName(StageNameEnum.DESIGN);
	}

	/**
	 * Updates the statistics view based on the stats stored in model
	 */
	public void updateStats() {
		view.updateStats(model.getNumTrees(), model.getNumShrubs(), model.getNumHerbs(), model.getNumVines(), model.getAllColors(),
				model.getAllSeasons(), model.getAllNames(), model.getGardenCoveredPercent(),model.getUniqueTrees(),model.getUniqueShrubs(),
				model.getUniqueHerbs(),model.getUniqueVines());
	}

}
