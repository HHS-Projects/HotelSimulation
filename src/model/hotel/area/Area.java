package model.hotel.area;

import javafx.scene.shape.Rectangle;

public abstract class Area extends Rectangle {

	private int x;
	private int y;
	private int width;
	private int height;
	private AreaType areaType;

	public Area (int x, int y, int width, int height, AreaType areaType) {
		super(x,y,width,height);
		this.areaType = areaType;
	}

	public AreaType getAreaType() {
		return areaType;
	}

	public boolean areaType(AreaType areaType) {
		this.areaType = areaType;
		return true;
	}

	public void setPosition(int x, int y){
		setX(x);
		setY(y);
	}

	public String getPositionToString() {
		return "["+ getX() +","+ getY() +"]";
	}

	public void setDimension(int w, int h){
		setWidth(w);
		setHeight(h);
	}

	public String getDimensionToString() {
		return "["+ getWidth() +","+ getHeight() +"]";
	}

}