package Model;

import javafx.scene.control.Label;

/**
 * This DrawingObj class represents labels in particular.
 * 
 * @author Tommy White
 *
 */
public class LabelDrawingObj extends DrawingObj {

	String text;

	public LabelDrawingObj(Label label) {
		x = label.getLayoutX();
		y = label.getLayoutY();
		width = label.getFont().getSize();
		trans = label.getTranslateX();
		scale = label.getScaleX();
		text = label.getText();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
