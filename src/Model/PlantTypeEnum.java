package Model;

import java.io.Serializable;

/**
 * An Enum for the different types of plants
 * 
 * @author Tommy White
 *
 */
public enum PlantTypeEnum implements Serializable {
	HERB("Herb",2), SHRUB("Shrub",4), TREE("Tree",8), VINE("Vine",2);

	private String plantType = null;
	private double strokeSize;

	private PlantTypeEnum(String s,double stroke) {
		plantType = s;
		strokeSize =stroke;
	}

	@Override
	public String toString() {
		return plantType;
	}
	
	public static String[] getVals() {
		String[] arr =  {"Herb", "Shrub", "Tree", "Vine"};
		return arr;
	}
	
	public double getStrokeSize() {
		return strokeSize;
	}
}
