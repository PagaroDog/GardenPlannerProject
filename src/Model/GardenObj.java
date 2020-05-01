
package Model;
/**
 * 
 * @author IanMcCabe
 *
 */
public class GardenObj {
	private int ID;
	private double xLoc;
	private double yLoc; 
	private double area;
	private String name; 
	//Should there be an image associated with each object?
	
	public GardenObj(int ID, double xLoc, double yLoc, double area, String Name) {
		this.ID = ID;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.area = area;
		this.name = Name;
	}
	
	
	
	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public double getxLoc() {
		return xLoc;
	}



	public void setxLoc(double xLoc) {
		this.xLoc = xLoc;
	}



	public double getyLoc() {
		return yLoc;
	}



	public void setyLoc(double yLoc) {
		this.yLoc = yLoc;
	}



	public double getArea() {
		return area;
	}



	public void setArea(double area) {
		this.area = area;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void drawImage() {
		
	}
	
}

