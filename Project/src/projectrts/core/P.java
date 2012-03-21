package projectrts.core;

public enum P {
	
	INSTANCE;
	
	public int getWorldWidth() {
		return 100;
	}
	
	public int getWorldHeight() {
		return 100;
	}
	
	public float getTileSideLength() {
		return 0.05f;
	}
}
