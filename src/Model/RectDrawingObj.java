package Model;

import javafx.scene.shape.Rectangle;

public class RectDrawingObj extends DrawingObj {
	
	public RectDrawingObj(Rectangle rect) {
		x = rect.getX();
		y = rect.getY();
		width = rect.getWidth();
		height = rect.getHeight();
		trans = rect.getTranslateX();
		scale = rect.getScaleX();
	}

}
