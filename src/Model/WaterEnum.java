package Model;

import java.io.Serializable;

/**
 * An Enum for the different water levels that plants prefer
 * 
 * @author Tommy White
 *
 */
public enum WaterEnum implements Serializable {
	WET("Wet"), WETMES("Wet Mesic"), MESIC("Mesic"), DRYMES("Dry Mesic"), DRY("Dry");

	private String water = null;

	private WaterEnum(String s) {
		water = s;
	}

	@Override
	public String toString() {
		return water;
	}
}
