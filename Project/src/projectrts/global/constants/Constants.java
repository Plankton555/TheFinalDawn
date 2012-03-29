package projectrts.global.constants;

/**
 * A singleton containing constants for use by the controllers and the world.
 * @author Markus Ekström
 *
 */
public enum Constants {INSTANCE;

	/**
	 * Returns the camera speed.
	 * @return A float representing the camera speed.
	 */
	public float getCameraSpeed() {
		return 1f;
	}
	
	/**
	 * Returns the value needed to convert lengths between the model and the world.
	 * 
	 * Multiply with this value to go from model -> world.
	 * Divide with it to go from world -> model.
	 * @return The modifier in the form of a float.
	 */
	public float getModelToWorld() {
		return 0.05f;
	}
	
	/**
	 * Returns the margin from the sides the mouse cursor has to move the camera.
	 * @return The margin in the form of a float.
	 */
	public float getCameraMoveMargin() {
		return 5;
	}
}
