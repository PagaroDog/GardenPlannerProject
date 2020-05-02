package Views;

import javafx.scene.image.Image;

public class ImageWithSourceInfo {
	private Image img;
	private String[] sourceInfo;
	
	public ImageWithSourceInfo (Image img, String[] sourceInfo) {
		this.img = img;
		this.sourceInfo = sourceInfo;
	}
}
