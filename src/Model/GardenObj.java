package Model;
import java.io.Serializable;

import javafx.scene.shape.Ellipse;

public class GardenObj implements Serializable {
	private double x;
	private double y;
	private double radiusX;
	private double radiusY;
	private String plantName;
	
	public GardenObj(Ellipse plant) {
		x = plant.getCenterX();
		y = plant.getCenterY();
		radiusX = plant.getRadiusY();
	}
}
