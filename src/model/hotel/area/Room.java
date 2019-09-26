package model.hotel.area;

public class Room extends Area {
	
	private String classification;

	public Room(int x, int y, int width, int height) {
		super(x, y, width, height,AreaType.ROOM);
	}

	public Room(int x, int y, int width, int height, String classification) {
		super(x, y, width, height,AreaType.ROOM);
		setClassification(classification);
	}

	public boolean setAreaType(AreaType areaType){
		return areaType.equals(AreaType.ROOM);
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

}
