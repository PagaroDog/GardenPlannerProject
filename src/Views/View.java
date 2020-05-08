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
 * The class that all View classes extend.
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
	private int toolbarRed = 105;
	private int toolbarGreen = 197;
	private int toolbarBlue = 255;
	private double toolbarOpacity = 1;
	private int navRed = 168;
	private int navGreen = 158;
	private int navBlue = 255;
	private double navOpacity = 1;
	
	/**
	 * Creates an HBox node that can act as the toolbar at the top of a View.
	 * @return The toolbar HBox
	 */
	public HBox createToolbar() {
		HBox toolbar = new HBox();
		toolbar.setStyle(String.format("-fx-background-color: rgba(%d, %d, %d, %f);", toolbarRed, toolbarGreen, toolbarBlue, toolbarOpacity));
		toolbar.setPadding(new Insets(toolbarVPadding, toolbarHPadding, toolbarVPadding, toolbarHPadding));
		toolbar.setSpacing(toolbarHGap);
		
		return toolbar;
	}
	
	/**
	 * Creates a BorderPane that can be used as a navigation bar at the bottom of a View.
	 * Should be used when a View only has a previous View and no next View.
	 * @param prevButtonText The text to display on the Previous Button
	 * @param CenterText The text to display at the center of the bar
	 * @param prevHandler The EventHandler for when the Previous Button is pressed
	 * @return The navigation bar BorderPane
	 */
	public BorderPane createNavigationBar(String prevButtonText, String CenterText, EventHandler prevHandler) {
		Button prevButton = new Button(prevButtonText);
		prevButton.setOnMouseClicked(prevHandler);
		
		Label center = new Label(CenterText);
		center.setFont(new Font(centerFontSize));

		BorderPane navigation = new BorderPane();
		navigation.setStyle(String.format("-fx-background-color: rgba(%d, %d, %d, %f);", navRed, navGreen, navBlue, navOpacity));
		navigation.setPadding(new Insets(toolbarVPadding, toolbarHPadding, toolbarVPadding, toolbarHPadding));
		navigation.setLeft(prevButton);
		navigation.setCenter(center);
		
		return navigation;
	}
	
	/**
	 * Creates a BorderPane that can be used as a navigation bar at the bottom of a View.
	 * Should be used when a View has a both a previous View and a next next View.
	 * @param prevButtonText The text to display on the Previous Button
	 * @param nextButtonText The text to display on the Next Button
	 * @param CenterText The text to display at the center of the bar
	 * @param prevHandler The EventHandler for when the Previous Button is pressed
	 * @param nextHandler The EventHandler for when the Next Button is pressed
	 * @return The navigation bar BorderPane
	 */
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
	
	public void styleScene() {
		scene.getStylesheets().add("Views/stylesheet.css");
	}
}
