package Model;

/**
 * An Enum for the different water levels that plants prefer
 * 
 * @author Tommy White
 *
 */
public enum Water {
	WET("Wet"), WETMES("Wet Mesic"), MESIC("Mesic"), DRYMES("Dry Mesic"), DRY("Dry");

	private String water = null;

	private Water(String s) {
		water = s;
	}

	@Override
	public String toString() {
		return water;
	}
}
