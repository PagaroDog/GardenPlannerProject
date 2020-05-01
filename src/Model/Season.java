package Model;


public enum Season {
	WINTER("Winter"), SPRING("Spring"), SUMMER("Summer"), FALL("Fall"); 

	private String season = null;
	
	private Season(String s){
		season = s;
	}
	
	@Override
	public String toString() {
		return season;
	}
}