package Model;

import java.io.Serializable;

public class DrawingObj implements Serializable {
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	protected double trans;
	protected double scale;
	
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

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getTrans() {
		return trans;
	}

	public void setTrans(double trans) {
		this.trans = trans;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
}
