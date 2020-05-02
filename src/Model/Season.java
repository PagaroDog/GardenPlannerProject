package Model;

/**
 * An Enum for the 4 seasons
 * 
 * @author Tommy White
 * @author Brandon Wu
 *
 */
public enum Season {
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