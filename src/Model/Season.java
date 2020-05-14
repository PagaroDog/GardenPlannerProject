package Model;

import java.io.Serializable;

/**
 * An Enum for the 4 seasons
 * 
 * @author Tommy White
 * @author Brandon Wu
 *
 */
public enum Season implements Serializable {
	WINTER("Winter"), SPRING("Spring"), SUMMER("Summer"), FALL("Fall");

	private String season = null;

	private Season(String s) {
		season = s;
	}

	@Override
	public String toString() {
		return season;
	}
}