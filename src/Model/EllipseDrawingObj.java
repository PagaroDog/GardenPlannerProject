package Model;

import javafx.scene.shape.Ellipse;

public class EllipseDrawingObj extends DrawingObj {

	public EllipseDrawingObj(Ellipse ellipse) {
		x = ellipse.getCenterX();
		y = ellipse.getCenterY();
		width = ellipse.getRadiusX();
		height = ellipse.getRadiusY();
		trans = ellipse.getTranslateX();
		scale = ellipse.getScaleX();
	}
}
