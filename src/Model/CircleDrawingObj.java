package Model;

import javafx.scene.shape.Ellipse;

public class CircleDrawingObj extends DrawingObj {
	
	public CircleDrawingObj(Ellipse circle) {
		x = circle.getCenterX();
		y = circle.getCenterY();
		width = circle.getRadiusX();
		height = circle.getRadiusY();
		trans = circle.getTranslateX();
		scale = circle.getScaleX();
	}
}
