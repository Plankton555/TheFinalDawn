package projectrts.global.constants;

public enum Constants {INSTANCE;

	private float modelToViewModifier = 0.05f;
	
	
	public float getCameraSpeed() {
		return 1f;
	}
	
	public float getModifier() {
		return modelToViewModifier;
	}
	
	public float getCameraMoveMargin() {
		return 5;
	}
}
