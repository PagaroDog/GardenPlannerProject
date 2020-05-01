package Model;

public enum PlantType {
	HERB("Herb"), SHRUB("Shrub"), TREE("Tree"), VINE("Vine");
	
	private String plantType = null;
	
	private PlantType(String s){
		plantType = s;
	}
	
	@Override
	public String toString() {
		return plantType;
	}
}
