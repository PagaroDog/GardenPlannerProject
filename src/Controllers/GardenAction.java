package Controllers;

import java.util.LinkedList;

import Views.GardenView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GardenAction {
	
	private ImageView plant; 
	private double x;
	private double y; 
	private String actionString; 
	private LinkedList<GardenAction> actions; 
	
	
	
	public ImageView getPlant() {
		return plant;
	}


	public void setPlant(ImageView plant) {
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


	public String getActionString() {
		return actionString;
	}


	public void setActionString(String action) {
		this.actionString = action;
	}


	public LinkedList<GardenAction> getActions() {
		return actions;
	}


	public void setActions(LinkedList<GardenAction> actions) {
		this.actions = actions;
	}


	public GardenAction(){
		actions = new LinkedList<GardenAction>(); 
	}
	
	
	public GardenAction(ImageView IV, double x, double y, String s) {
		plant = IV; 
		this.x = x; 
		this.y = y; 
		actionString = s; 
	}
	
	public void addAction(GardenAction ga) {
		actions.add(ga); 
		System.out.println(actions); 
	}
	
	public void undo(GardenView gv) {
		actions.removeLast(); 
		if(actions.size() == 0) {
			gv.getGarden().getChildren().clear(); 
		}
		gv.getGarden().getChildren().clear(); 
		for(GardenAction ga: actions) {
			if(ga.getActionString().equals("AddIV")) {
				gv.addIVToFlow(ga.getPlant(), ga.getX(), ga.getY());
			}
		}
		
	}
}
