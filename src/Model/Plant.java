
package Model;
/**
 * 
 * @author IanMcCabe
 *
 */
public class Plant {
	private String commonName; 
	private String light; 
	private int bloomtime; 
	private String soilType; 
	private String waterlevel; 
	private String[] color; 
	private int animalsFed; 
	private int pollinator; //is this whether or not the plant pollinates or how many things it pollinates 
	private String science;
		
	public Plant(String name, String science,String light, int bloomtime,String soilType,String waterlevel, String[] color, int animalsFed, int pollinator) {
		this.commonName = name;
		this.science = science;
		this.light = light;
		this.bloomtime = bloomtime;
		this.soilType = soilType;
		this.waterlevel = waterlevel;
		this.color = color;
		this.animalsFed = animalsFed;
		
		
	}
	
	public String getName() {
		return commonName;
	}
	public void setName(String name) {
		this.commonName = name;
	}
	public String getLight() {
		return light;
	}
	public void setLight(String light) {
		this.light = light;
	}
	public int getBloomtime() {
		return bloomtime;
	}
	public void setBloomtime(int bloomtime) {
		this.bloomtime = bloomtime;
	}
	public String getSoilType() {
		return soilType;
	}
	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}
	public String getWaterlevel() {
		return waterlevel;
	}
	public void setWaterlevel(String waterlevel) {
		this.waterlevel = waterlevel;
	}
	public String[] getColor() {
		return color;
	}
	public void setColor(String[] color) {
		this.color = color;
	}
	public int getAnimalsFed() {
		return animalsFed;
	}
	public void setAnimalsFed(int animalsFed) {
		this.animalsFed = animalsFed;
	}
	public int getPollinator() {
		return pollinator;
	}
	public void setPollinator(int pollinator) {
		this.pollinator = pollinator;
	}

	public String getScience() {
		return science;
	}

	public void setScience(String science) {
		this.science = science;
	}
	
}

