package Model;

/**
 * 
 * @author IanMcCabe
 *
 */

public enum ActionEnum {
	ADDPLANT("AddPlant"), MOVEPLANT("MovePlant"), DELETE("Delete"), COPY("Copy");
	
	private String action = null; 
	
	private ActionEnum(String s) {
		this.action = s; 
	}
	
	public String toString() {
		return action; 
	}
}
