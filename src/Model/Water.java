package Model;

import java.io.Serializable;

/**
 * An Enum for the different water levels that plants prefer
 * 
 * @author Tommy White
 *
 */
public enum Water implements Serializable {
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
