
package Model;

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
	private String[] color;
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
		this.color = color;
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

	public String[] getColor() {
		return color;
	}

	public void setColor(String[] color) {
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
}
