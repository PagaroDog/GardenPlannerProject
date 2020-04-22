package Controllers;
import Model.Model;
import Views.SaveView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
		 * Handles event when user presses select button,
		 * invoking selectButton()
		 * @return EventHandler object for this action
		 */
		public EventHandler getHandleOnFileButton() {
			return null; 
		}
		
		/**
		 * Handles event when user presses rectangle button,
		 * invoking rectButton()
		 * @return EventHandler object for this action
		 */
		public EventHandler getHandleOnPNGButton() {
			return null;
		}
		/**
		 * Sets condition area editing mode to select
		 */
		public void fileButton(MouseEvent event) {
			 
		}
		
		/**
		 * Sets condition area editing mode to label
		 */
		public void PNGButton(MouseEvent event) {
			
		}
	
}
