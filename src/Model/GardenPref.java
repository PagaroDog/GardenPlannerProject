package Model;
/**
 * GardenPref is used to organize user preferences with types of soil, bloom times, light strength, 
 * the location, and how large the zone is.  
 * @author Brandon Wu
 *
 */
public class GardenPref {
	private String name;
	private String userLight;
	private String userBloom;
	private String userSoil;
	private String[] userColor;
	private double xLoc;
	private double yLoc;
	private double height;
	private double width;
	
	/**
	 * Creates a GardenPref that is called by the Conditions Controller when the user chooses to
	 * move on to the PreferencesView. Establishes name/Id of the zone, the location of a zone and the dimensions.
	 * The user attributes are set to null.
	 * @param name
	 * @param xLoc
	 * @param yLoc
	 * @param height
	 * @param width
	 */
	public GardenPref(String name,double xLoc, double yLoc, double height, double width) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.height = height;
		this.width = width;
		this.name = name;
		userLight = null;
		userBloom = null;
		userSoil = null;
		userColor = null;
	}
	/**
	 * Creates a GardenPref if all attributes are known
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
	public GardenPref(String name, String userLight, String userBloom, String userSoil, String[] userColor, double xLoc,
		double yLoc, double height, double width) {
		this.name = name;
		this.userLight = userLight;
		this.userBloom = userBloom;
		this.userSoil = userSoil;
		this.userColor = userColor;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.height = height;
		this.width = width;
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
	public String[] getUserColor() {
		return userColor;
	}
	public double getxLoc() {
		return xLoc;
	}
	public double getyLoc() {
		return yLoc;
	}
	public double getHeight() {
		return height;
	}
	public double getWidth() {
		return width;
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
	public void setUserColor(String[] userColor) {
		this.userColor = userColor;
	}
	public void setxLoc(double xLoc) {
		this.xLoc = xLoc;
	}
	public void setyLoc(double yLoc) {
		this.yLoc = yLoc;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public void setWidth(double width) {
		this.width = width;
	}
}
