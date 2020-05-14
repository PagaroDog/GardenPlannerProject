package Model;

import javafx.scene.control.Label;

public class LabelDrawingObj extends DrawingObj {
	
	String text;
	
	public LabelDrawingObj(Label label) {
		x = label.getLayoutX();
		y = label.getLayoutY();
		width = label.getWidth();
		height = label.getHeight();
		trans = label.getTranslateX();
		scale = label.getScaleX();
		text = label.getText();
	}
}
