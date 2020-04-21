package Model;

public class Plant {
	private String name; 
	private int light; 
	private int bloomtime; 
	private String soilType; 
	private double waterlevel; 
	private String color; 
	private int animalsFed; 
	private int pollinator; //is this whether or not the plant pollinates or
	//how many things it pollinates 
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLight() {
		return light;
	}
	public void setLight(int light) {
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
	public double getWaterlevel() {
		return waterlevel;
	}
	public void setWaterlevel(double waterlevel) {
		this.waterlevel = waterlevel;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
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
	
}
