
package Model;
/**
 * 
 * @author IanMcCabe
 *
 */
public class GardenObj {
	private int ID;
	private int xLoc;
	private int yLoc; 
	private int area;
	private String name; 
	//Should there be an image associated with each object?
	
	public GardenObj() {
		
	}
	
	
	
	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public int getxLoc() {
		return xLoc;
	}



	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}



	public int getyLoc() {
		return yLoc;
	}



	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}



	public int getArea() {
		return area;
	}



	public void setArea(int area) {
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

