package projectrts.global.constants;

public enum Constants {INSTANCE;

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
