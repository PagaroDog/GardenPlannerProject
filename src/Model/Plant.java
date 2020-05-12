
package Model;

import java.util.HashSet;

/**
 * 
 * @author IanMcCabe
 * @author Tommy White
 *
 */
public class Plant {
	private String name;
	private String[] commonNames;
	private String duration;
	private PlantType type;
	private int[] height;
	private HashSet<String> color = new HashSet<String>();
	private Season[] bloomtime;
	private Water[] waterLevel;
	private Sun[] light;
	private int[] spread;
	

	public Plant(String name, String[] commonNames, String duration, PlantType type, int[] height, String[] color,
			Season[] bloomtime, Water[] waterLevel, Sun[] light, int[] spread) {
		this.name = name;
		this.commonNames = commonNames;
		this.duration = duration;
		this.type = type;
		this.height = height;
		this.color = new HashSet<String>();
		for (String col : color) {
			this.color.add(col);
		}
		this.bloomtime = bloomtime;
		this.waterLevel = waterLevel;
		this.light = light;
		this.spread = spread;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sun[] getLight() {
		return light;
	}

	public void setLight(Sun[] light) {
		this.light = light;
	}

	public Season[] getBloomtime() {
		return bloomtime;
	}

	public void setBloomtime(Season[] bloomtime) {
		this.bloomtime = bloomtime;
	}

	public Water[] getWaterLevel() {
		return waterLevel;
	}

	public void setWaterLevel(Water[] waterLevel) {
		this.waterLevel = waterLevel;
	}

	public HashSet<String> getColor() {
		return color;
	}

	public void setColor(HashSet<String> color) {
		this.color = color;
	}

	public String[] getCommonNames() {
		return commonNames;
	}

	public void setCommonNames(String[] commonNames) {
		this.commonNames = commonNames;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public PlantType getType() {
		return type;
	}

	public void setType(PlantType type) {
		this.type = type;
	}

	public int[] getHeight() {
		return height;
	}

	public void setHeight(int[] height) {
		this.height = height;
	}

	public int[] getSpread() {
		return spread;
	}

	public void setSpread(int[] spread) {
		this.spread = spread;
	}
	
	@Override
	public String toString() {
		String commonString = Model.toCommaString(commonNames);
		String colorString = Model.toCommaString(color);
		String seasonString = Model.toCommaString(bloomtime);
		String waterString = Model.toCommaString(waterLevel);
		String lightString = Model.toCommaString(light);
		return name +
				"\nCommon names: " + commonString +
				"\nDuration: " + duration +
				"\nBloom colors: " + colorString +
				"\nBloom seasons: " + seasonString +
				"\nWater levels: " + waterString +
				"\nLight levels: " + lightString;
	}
}
