package Model;

import java.util.Iterator;
import java.util.LinkedList;

import Views.GardenView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Handles the list of actions the user enters into the program as they modify
 * the garden.
 * 
 * 
 * @author IanMcCabe
 *
 */
public class GardenAction {

	private Circle plant;
	private double x;
	private double y;
	private String name;
	private ActionEnum action;
	private LinkedList<GardenAction> actionList;
	private LinkedList<GardenAction> redoList;
	private double radius;
	private Color color;

	public Circle getPlant() {
		return plant;
	}

	public void setPlant(Circle plant) {
		this.plant = plant;
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

	public String getName() {
		return name;
	}

	public void setName(String s) {
		this.name = s;
	}

	public ActionEnum getAction() {
		return action;
	}

	public void setAction(ActionEnum action) {
		this.action = action;
	}

	public LinkedList<GardenAction> getActionList() {
		return actionList;
	}

	public void setActionList(LinkedList<GardenAction> actionList) {
		this.actionList = actionList;
	}

	public LinkedList<GardenAction> getRedoList() {
		return redoList;
	}

	public void setRedoList(LinkedList<GardenAction> redoList) {
		this.redoList = redoList;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return action.toString();
	}

	public GardenAction() {
		actionList = new LinkedList<GardenAction>();
		redoList = new LinkedList<GardenAction>();
	}

	public GardenAction(Circle plant, double x, double y, double radius, String name, Color color, ActionEnum action) {
		this.plant = plant;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
		this.name = name;
		this.action = action;
	}

	/**
	 * This method adds an action to the actionList
	 * 
	 * @param ga The garden action being added to the actionList
	 */
	public void addAction(GardenAction ga) {
		actionList.add(ga);
		redoList.clear();
	}

	/**
	 * Removes an action from the actionList by popping off the end of the
	 * actionList and adding it to the head of the redoList.
	 */
	public void undo() {
		if (actionList.size() == 0) {
			return;
		}
		redoList.push(actionList.removeLast());
	}

	/**
	 * Removes an item from the head of the redoList and adds it to the tail of the
	 * actionList.
	 */
	public void redo() {
		if (redoList.size() == 0) {
			return;
		} else {
			actionList.add(redoList.pop());
		}
	}

	public void equalityChecker() {
		Iterator<GardenAction> i = actionList.iterator();
		while (i.hasNext()) {
			GardenAction j = i.next();
			if (i.hasNext()) {
				System.out.println(j.getPlant().equals(i.next().getPlant()));
			}
		}
	}

}
