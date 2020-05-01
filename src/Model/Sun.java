package Model;

public enum Sun {
	FULL("Full Sun"), FULL_PARTIAL("Full Sun to Partial Shade"), PARTIAL("Partial Shade"), PARTIAL_NONE("Partial Shade to Full Shade"), NONE("Full Shade");
	
	private String sun = null;
	
	private Sun(String s){
		sun = s;
	}
	
	@Override
	public String toString() {
		return sun;
	}
}
