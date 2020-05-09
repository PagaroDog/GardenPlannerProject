package Model;

import java.util.LinkedList;

import Views.GardenView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;

/**
 * 
 * @author IanMcCabe
 *
 */
public class GardenAction {
	
	private Ellipse plant; 
	private double x; 
	private double y; 
	private String name; 
	private ActionEnum action; 
	private LinkedList<GardenAction> actionList; 
	private LinkedList<GardenAction> redoList;
	public Ellipse getPlant() {
		return plant;
	}
	
	public void setPlant(Ellipse plant) {
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
	
	public String toString() {
		return action.toString(); 
	}
	
	public GardenAction() {
		actionList = new LinkedList<GardenAction>(); 
		redoList = new LinkedList<GardenAction>(); 
	}
	
	public GardenAction(Ellipse plant, double x, double y, String name, ActionEnum action){
		this.plant = plant; 
		this.x = x; 
		this.y = y; 
		this.name = name; 
		this.action = action; 
	}
	
	public void addAction(GardenAction ga) {
		actionList.add(ga); 
		redoList.clear(); 
		System.out.println(actionList); 
	}
	
	public void undo(GardenView gv) {
		if(actionList.size() == 0) {
			return; 
		}
		redoList.push(actionList.removeLast());
		
		if(actionList.size()== 0) {
			gv.getGarden().getChildren().clear(); 
		}
		else {
			gv.getGarden().getChildren().clear(); 
			for(GardenAction ga : actionList) {
				switch(ga.getAction()) {
					case ADDPLANT:
						gv.addCirlceToFlow(ga.getPlant(), ga.getX(), ga.getY(), ga.getName());
						break; 
						
					case MOVEPLANT:
						break; 
				}
			}
		}
	}
}
