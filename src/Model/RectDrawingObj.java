package Model;

import javafx.scene.shape.Rectangle;

public class RectDrawingObj extends DrawingObj {
	private StageName userData;
	
	public RectDrawingObj(Rectangle rect) {
		x = rect.getX();
		y = rect.getY();
		width = rect.getWidth();
		height = rect.getHeight();
		trans = rect.getTranslateX();
		scale = rect.getScaleX();
		userData = (StageName) rect.getUserData();
	}

	public StageName getUserData() {
		return userData;
	}

	public void setUserData(StageName userData) {
		this.userData = userData;
	}

}
