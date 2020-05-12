package Model;

import java.util.HashSet;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

/**
 * GardenPref is used to organize user preferences with types of soil, bloom
 * times, light strength, the location, and how large the zone is.
 * 
 * @author Brandon Wu
 *
 */
public class GardenPref {
	private String name;
	private String userLight;
	private String userBloom;
	private String userSoil;
	private HashSet<String> userColor = new HashSet<String>();
	private String userWater;
	private Rectangle area;

	public GardenPref() {
	}

	/**
	 * Creates a GardenPref if all attributes are known
	 * 
	 * @param name
	 * @param userLight
	 * @param userBloom
	 * @param userSoil
	 * @param userColor
	 * @param xLoc
	 * @param yLoc
	 * @param height
	 * @param width
	 */
	public GardenPref(String name, String userLight, String userBloom, String userSoil, HashSet<String> userColor, Rectangle area) {
		this.name = name;
		this.userLight = userLight;
		this.userBloom = userBloom;
		this.userSoil = userSoil;
		this.userColor = userColor;
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public String getUserLight() {
		return userLight;
	}

	public String getUserBloom() {
		return userBloom;
	}

	public String getUserSoil() {
		return userSoil;
	}

	public HashSet<String> getUserColor() {
		return userColor;
	}

	public String getUserWater() {
		return userWater;
	}
	
	public Rectangle getArea() {
		return area;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserLight(String userLight) {
		this.userLight = userLight;
	}

	public void setUserBloom(String userBloom) {
		this.userBloom = userBloom;
	}

	public void setUserSoil(String userSoil) {
		this.userSoil = userSoil;
	}

	public void setUserColor(HashSet<String> userColor) {
		this.userColor = userColor;
	}

	public void setUserWater(String userWater) {
		this.userWater = userWater;
	}

	public void setArea(Rectangle area) {
		this.area = area;
	}
}
