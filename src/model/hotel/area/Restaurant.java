package model.hotel.area;

public class Restaurant extends Area{

	private int capacity;

	public Restaurant(int x, int y, int width, int height) {
		super(x, y, width, height, AreaType.RESTAURANT);
	}

	public Restaurant(int x, int y, int width, int height, int capacity) {
		super(x, y, width, height, AreaType.RESTAURANT);
		setCapacity(capacity);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int a) {
		this.capacity = a;
	}

}
