package Model;

public enum ActionEnum {
	ADDPLANT("AddPlant"), MOVEPLANT("MovePlant") ;
	
	private String action = null; 
	
	private ActionEnum(String s) {
		this.action = s; 
	}
	
	public String toString() {
		return action; 
	}
}
