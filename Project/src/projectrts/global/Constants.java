package projectrts.global;

public enum Constants {INSTANCE;

	//TODO This class should be moved to another package
	private float modelBaseLength = 0.05f;
	
	
	public float getCameraSpeed() {
		return 1f;
	}
	
	public float getBaseLength() {
		return modelBaseLength;
	}
	
	public float getCameraMoveMargin() {
		return 5;
	}
}
