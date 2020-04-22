package Controllers;

import Model.Model;
import Views.StatisticsView;
import Views.View;
import javafx.event.EventHandler;

/**
 * @author Tommy White
 */
public class StatisticsController extends Controller{
	
	StatisticsView view;
	
	public StatisticsController(Model model, View view) {
		super(model, view);
	}

	/**
	 * Handles event when user presses rectangle button,
	 * bringing the user back to the design stage.
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnBackButton() {
		return null;
	}
}
