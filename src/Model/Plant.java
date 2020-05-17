
package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;

import Views.View;

/**
 * This class represents plants. It contains their attributes.
 * 
 * @author IanMcCabe
 * @author Tommy White
 *
 */
public class Plant implements Serializable {
	private String name;
	private String[] commonNames;
	private String duration;
	private PlantTypeEnum type;
	private int[] height;
	private HashSet<String> color = new HashSet<String>();
	private SeasonEnum[] bloomtime;
	private WaterEnum[] waterLevel;
	private SunEnum[] light;
	private int[] spread;
	private static int maxCommonNames = (int) Math.min(7, 8 * View.getCanvasWidth() / View.getExpectedWidth());

	public Plant(String name, String[] commonNames, String duration, PlantTypeEnum type, int[] height, String[] color,
			SeasonEnum[] bloomtime, WaterEnum[] waterLevel, SunEnum[] light, int[] spread) {
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

	public SunEnum[] getLight() {
		return light;
	}

	public void setLight(SunEnum[] light) {
		this.light = light;
	}

	public SeasonEnum[] getBloomtime() {
		return bloomtime;
	}

	public void setBloomtime(SeasonEnum[] bloomtime) {
		this.bloomtime = bloomtime;
	}

	public WaterEnum[] getWaterLevel() {
		return waterLevel;
	}

	public void setWaterLevel(WaterEnum[] waterLevel) {
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

	public PlantTypeEnum getType() {
		return type;
	}

	public void setType(PlantTypeEnum type) {
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

	public int getMaxCommonNames() {
		return maxCommonNames;
	}

	public void setMaxCommonNames(int maxCommonNames) {
		Plant.maxCommonNames = maxCommonNames;
	}

	@Override
	public String toString() {
		String commonString;
		if (commonNames.length > maxCommonNames) {
			String[] commonCopy = Arrays.copyOf(commonNames, maxCommonNames);
			commonString = Arrays.toString(commonCopy).replace("[", "").replace("]", "");
		} else {
			commonString = Arrays.toString(commonNames).replace("[", "").replace("]", "");
		}
		String colorString = color.toString().replace("[", "").replace("]", "");
		String seasonString = Arrays.toString(bloomtime).replace("[", "").replace("]", "");
		String waterString = Arrays.toString(waterLevel).replace("[", "").replace("]", "");
		String lightString = Arrays.toString(light).replace("[", "").replace("]", "");
		return name + "\nCommon names: " + commonString + "\nDuration: " + duration + "\nBloom colors: " + colorString
				+ "\nBloom seasons: " + seasonString + "\nWater levels: " + waterString + "\nLight levels: "
				+ lightString;
	}
}
