package Controllers;

import java.util.Iterator;
import java.util.LinkedList;

import Views.GardenView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GardenAction {
	
	private ImageView plant; 
	private double x;
	private double y; 
	private String undoActionstring; 
	private LinkedList<GardenAction> undoActions; 
	private LinkedList<GardenAction> redoActions; 
	
	
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


	public String getundoActionstring() {
		return undoActionstring;
	}


	public void setundoActionstring(String action) {
		this.undoActionstring = action;
	}


	public LinkedList<GardenAction> getundoActions() {
		return undoActions;
	}


	public void setundoActions(LinkedList<GardenAction> undoActions) {
		this.undoActions = undoActions;
	}


	public GardenAction(){
		undoActions = new LinkedList<GardenAction>(); 
		redoActions = new LinkedList<GardenAction>(); 
	}
	
	public String toString() {
		return undoActionstring;  
	}
	
	public GardenAction(ImageView IV, double x, double y, String s) {
		plant = IV; 
		this.x = x; 
		this.y = y; 
		undoActionstring = s; 
	}
	
	public void addAction(GardenAction ga) {
		undoActions.add(ga); 
		redoActions.clear(); 
		//equalityChecker(); 
		System.out.println(undoActions); 
	}
	
	public void undo(GardenView gv) {
		if(undoActions.size() == 0) {
			return; 
		}
		 
		redoActions.push(undoActions.removeLast()); 
		
		System.out.println("Redo Actions:" + redoActions); 
		System.out.println("Undo Actions:" + undoActions); 
		
		if(undoActions.size() == 0) {
			gv.getGarden().getChildren().clear();
			return; 
		}
		else {
			gv.getGarden().getChildren().clear();  
			for(GardenAction ga: undoActions) {
				if(ga.getundoActionstring().equals("AddIV")) {
					gv.addIVToFlow(ga.getPlant(), ga.getX(), ga.getY());
				}
				else if(ga.getundoActionstring().equals("MoveImage")) {
					gv.movePlant(ga.getPlant(), ga.getX(), ga.getY());
				}
			}
		}
		
	}
	
	public void redo(GardenView gv) {
		if(redoActions.size() == 0) {
			return; 
		}
		else {
			undoActions.add(redoActions.pop());
			gv.getGarden().getChildren().clear(); 
			for(GardenAction ga: undoActions) {
				if(ga.getundoActionstring().equals("AddIV")) {
					gv.addIVToFlow(ga.getPlant(), ga.getX(), ga.getY());
				}
				else if(ga.getundoActionstring().equals("MoveImage")) {
					gv.movePlant(ga.getPlant(), ga.getX(), ga.getY());
				}
			}
		}
	}
	
	public void equalityChecker() {
		Iterator<GardenAction> i = undoActions.iterator();  
		while(i.hasNext()) {
			GardenAction j = i.next(); 
			if(i.hasNext()) {
				System.out.println(j.getPlant().equals(i.next().getPlant()));
			}
		}
	}
	
	public void correctIV() {
		GardenAction first = undoActions.pop(); 
	
		
		Iterator<GardenAction> i = undoActions.iterator(); 
		while(i.hasNext()) {
			GardenAction next = i.next(); 
			next.setPlant(first.getPlant());
		}
		undoActions.push(first);
		
	}
}
