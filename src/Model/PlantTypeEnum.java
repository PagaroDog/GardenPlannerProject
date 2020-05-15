package Model;

import java.io.Serializable;

/**
 * An Enum for the different types of plants
 * 
 * @author Tommy White
 *
 */
public enum PlantTypeEnum implements Serializable {
	HERB("Herb"), SHRUB("Shrub"), TREE("Tree"), VINE("Vine");

	private String plantType = null;

	private PlantTypeEnum(String s) {
		plantType = s;
	}

	@Override
	public String toString() {
		return plantType;
	}
	
	public static String[] getVals() {
		String[] arr =  {"Herb", "Shrub", "Tree", "Vine"};
		return arr;
	}
}
