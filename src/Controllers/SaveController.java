package Controllers;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import Model.Model;
import Model.StageName;
import Views.SaveView;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is the controller for the Save screen.
 * Handles user input.
 * @author Josh Stone
 * 
 */
public class SaveController extends Controller<SaveView>{
		
		public SaveController(Model model, SaveView view, Main main) {
			super(model, view, main);
			
		}
		
		/**
		 * Handles event when user presses File button,
		 * invoking fileButton()
		 * @return EventHandler object for this action
		 */
		public EventHandler getHandleOnFileButton() {
			return event -> fileButton((MouseEvent)event); 
		}
		
		/**
		 * Handles event when user presses PNG button,
		 * invoking PNGButton()
		 * @return EventHandler object for this action
		 */
		public EventHandler getHandleOnPNGButton() {
			return event -> PNGButton((MouseEvent)event);
		}
		
		/**
		 * saves the garden as a .garden file
		 * @param event The MouseEvent generated when
		 * 		the user pressed the file button.
		 */
		public void fileButton(MouseEvent event) {//not sure how to work with load
			 saveGarden(view.getScene());
		}
		
		/**
		 * saves the garden as a .png file
		 * @param event The MouseEvent generated when
		 * 		the user pressed the png button.
		 */
		public void PNGButton(MouseEvent event) {
			takeSnapShot(view.getScene());
		}

		/**
		 * takes a snapshot of the user's window
		 * @param scene
		 */
		private void takeSnapShot(Scene scene){
			WritableImage writableImage = new WritableImage((int)scene.getWidth(), (int)scene.getHeight());
	        scene.snapshot(writableImage);
	        
	        File file = new File("snapshot.png");
	        try {
	        	ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
	            System.out.println("snapshot saved: " + file.getAbsolutePath());
	        } catch (IOException ex) {
	            Logger.getLogger(SaveController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
		
		private void saveGarden(Scene scene){//needs to work for load
			WritableImage writableImage = new WritableImage((int)scene.getWidth(), (int)scene.getHeight());
	        scene.snapshot(writableImage);
	        
	        File file = new File("Garden.garden");
	        try {
	        	ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "garden", file);
	            System.out.println("snapshot saved: " + file.getAbsolutePath());
	        } catch (IOException ex) {
	            Logger.getLogger(SaveController.class.getName()).log(Level.SEVERE, null, ex);
	        }
		}
		
		/**
		 * Handles event when user presses prev button,
		 * invoking prevButton()
		 * @return EventHandler object for this action
		 */
		public EventHandler getHandleOnPrevButton() {
			return event -> prevButton((MouseEvent)event);
		}

		/**
		 * Returns the user to the garden design screen
		 * @param event The MouseEvent generated when
		 * 		the user pressed the prev button.
		 */
		public void prevButton(MouseEvent event) {
			view.getStage().setScene(Main.getScenes().get(StageName.DESIGN));
			model.setStageName(StageName.DESIGN);
		}
	
}
