package Model;

import java.io.Serializable;

/**
 * An Enum for the different levels of sunlight that plants prefer
 * 
 * @author Tommy White
 *
 */
public enum SunEnum implements Serializable {
	FULL("Full Sun"), FULL_PARTIAL("Full Sun to Partial Shade"), PARTIAL("Partial Shade"),
	PARTIAL_NONE("Partial Shade to Full Shade"), NONE("Full Shade");

	private String sun = null;

	private SunEnum(String s) {
		sun = s;
	}

	@Override
	public String toString() {
		return sun;
	}
}
