
package Model;
/**
 * 
 * @author IanMcCabe
 *
 */
public class Plant {
	private String name;
	private String[] commonNames;
	private String duration;
	private String type;
	private int[] height;
	private String[] color;
	private int[] bloomtime;
	private String waterLevel;
	private String light;
	private int[] spread;
		
	public Plant(String name, String[] commonNames, String duration, String type, int[] height, String[] color, int[] bloomtime, String waterLevel, String light, int[] spread) {
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
	public String getLight() {
		return light;
	}
	public void setLight(String light) {
		this.light = light;
	}
	public int[] getBloomtime() {
		return bloomtime;
	}
	public void setBloomtime(int[] bloomtime) {
		this.bloomtime = bloomtime;
	}
	public String getWaterLevel() {
		return waterLevel;
	}
	public void setWaterLevel(String waterLevel) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

