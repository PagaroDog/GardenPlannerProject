package Model;

public enum Water {
	WET("Wet"), WETMES("Wet Mesic"), MESIC("Mesic"), DRYMES("Dry Mesic"), DRY("Dry");
	
	private String water = null;
	
	private Water(String s){
		water = s;
	}
	
	@Override
	public String toString() {
		return water;
	}
}
