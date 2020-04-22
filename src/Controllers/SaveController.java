package Controllers;
import Model.Model;
import Views.SaveView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * @author Josh Stone
* this class is the controller for the Save screen
*/
public class SaveController {
		
		Model model;
		SaveView view;
		
		public SaveController(Model model, SaveView sv) {
//			this.model = model;
//			view = sv;
//			
//			view.setController(this);
//			model.setSC(this);
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
	
}
