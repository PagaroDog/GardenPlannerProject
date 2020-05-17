package Model;

import java.io.Serializable;

import javafx.scene.shape.Circle;

/**
 * This class represents the plants that make up the garden and is only used if
 * the garden is saved.
 * 
 * @author Tommy White
 *
 */
public class GardenObj implements Serializable {
	private double x;
	private double y;
	private double radius;
	private String plantName;

	public GardenObj(Circle plant) {
		x = plant.getCenterX();
		y = plant.getCenterY();
		radius = plant.getRadius();
		plantName = (String) plant.getUserData();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

}
