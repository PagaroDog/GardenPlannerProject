package Views;

import javafx.scene.image.Image;

/**
 * This class allows for plant images to be stored with the author's name and a
 * link to the source
 * 
 * @author Tommy White
 *
 */
public class ImageWithSourceInfo {
	private Image img;
	private String[] sourceInfo;

	public ImageWithSourceInfo(Image img, String[] sourceInfo) {
		this.img = img;
		this.sourceInfo = sourceInfo;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public String[] getSourceInfo() {
		return sourceInfo;
	}

	public void setSourceInfo(String[] sourceInfo) {
		this.sourceInfo = sourceInfo;
	}

}
