package Controllers;



import Model.Model;
import Model.StageName;
import Views.StatisticsView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Tommy White
 */
public class StatisticsController extends Controller<StatisticsView>{

	public StatisticsController(Model model, StatisticsView view) {
		super(model, view);
	}

	/**
	 * Handles event when user presses rectangle button,
	 * bringing the user back to the design stage.
	 * @return EventHandler object for this action
	 */
	public EventHandler handleOnBackButton() {
		return event -> backButton((MouseEvent)event);
	}

	public void backButton(MouseEvent event) {
		view.getStage().setScene(Main.getScenes().get(StageName.DESIGN));
		model.setStageName(StageName.DESIGN);
	}
	
	
}
