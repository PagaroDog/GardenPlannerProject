package Controllers;
import Model.Model;
import Model.StageName;
import Views.SaveView;
import Views.View;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Josh Stone
* this class is the controller for the Save screen
*/
public class SaveController extends Controller{
	

		private Stage stage;
		
		public SaveController(Model model, View Sview) {
			super(model, Sview);
			this.stage = Sview.getStage();
			
		}
		
		/**
		 * Handles event when user presses File button,
		 * invoking fileButton()
		 * @return EventHandler object for this action
		 */
		public EventHandler getHandleOnFileButton() {
			return null; 
		}
		
		/**
		 * Handles event when user presses PNG button,
		 * invoking PNGButton()
		 * @return EventHandler object for this action
		 */
		public EventHandler getHandleOnPNGButton() {
			return null;
		}
		/**
		 * saves the garden as a .garden file
		 */
		public void fileButton(MouseEvent event) {
			 
		}
		
		/**
		 * saves the garden as a .png file
		 */
		public void PNGButton(MouseEvent event) {
			
		}

		public EventHandler getHandleOnPrevButton() {
			
			return event -> prevButton((MouseEvent)event);
		}

		public void prevButton(MouseEvent event) {
			stage.setScene(Main.getScenes().get(StageName.DESIGN));
			model.setStageName(StageName.DESIGN);
		}
	
}
