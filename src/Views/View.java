package Views;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import Controllers.Controller;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The abstract class that all View classes extend.
 * 
 * @author Tommy White
 *
 */
public class View<T extends Controller> {
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	protected int canvasWidth = gd.getDisplayMode().getWidth() - 150;
	protected int canvasHeight = gd.getDisplayMode().getHeight() - 150;
	protected Scene scene;
	protected Stage stage;
	protected T control;
	private double toolbarVPadding = 15;
	private double toolbarHPadding = 15;
	private double toolbarHGap = 15;
	private double centerFontSize = 25;
	
	public HBox createToolbar() {
		HBox toolbar = new HBox();
		toolbar.setStyle("-fx-background-color: rgba(25,100,255,1);");
		toolbar.setPadding(new Insets(toolbarVPadding, toolbarHPadding, toolbarVPadding, toolbarHPadding));
		toolbar.setSpacing(toolbarHGap);
		
		return toolbar;
	}
	
	public BorderPane createNavigationBar(String prevButtonText, String CenterText, EventHandler prevHandler) {
		Button prevButton = new Button(prevButtonText);
		prevButton.setOnMouseClicked(prevHandler);
		
		Label center = new Label(CenterText);
		center.setFont(new Font(centerFontSize));

		BorderPane navigation = new BorderPane();
		navigation.setStyle("-fx-background-color: rgba(168,158,255,1);");
		navigation.setPadding(new Insets(toolbarVPadding, toolbarHPadding, toolbarVPadding, toolbarHPadding));
		navigation.setLeft(prevButton);
		navigation.setCenter(center);
		
		return navigation;
	}
	
	public BorderPane createNavigationBar(String prevButtonText, String nextButtonText, String CenterText, EventHandler prevHandler, EventHandler nextHandler) {
		Button nextButton = new Button(nextButtonText);
		nextButton.setOnMouseClicked(nextHandler);

		BorderPane navigation = createNavigationBar(prevButtonText, CenterText, prevHandler);
		navigation.setRight(nextButton);
		
		return navigation;
	}

	public Scene getScene() {return scene;}

	public void setController(T controller) {control = controller;}

	public Stage getStage() {return stage;}

	public void setup() {}
}
