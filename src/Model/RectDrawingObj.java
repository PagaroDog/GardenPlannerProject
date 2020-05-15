package Model;

import javafx.scene.shape.Rectangle;

public class RectDrawingObj extends DrawingObj {
	private StageNameEnum userData;
	
	public RectDrawingObj(Rectangle rect) {
		x = rect.getX();
		y = rect.getY();
		width = rect.getWidth();
		height = rect.getHeight();
		trans = rect.getTranslateX();
		scale = rect.getScaleX();
		userData = (StageNameEnum) rect.getUserData();
	}

	public StageNameEnum getUserData() {
		return userData;
	}

	public void setUserData(StageNameEnum userData) {
		this.userData = userData;
	}

}
