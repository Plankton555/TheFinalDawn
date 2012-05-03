package projectrts.global.constants;

/**
 * A class containing constants for use by the controllers and the world.
 * @author Markus Ekström
 *
 */
public class Constants {

	/**
	 * @return true if the nodes should be rendered for debugging, otherwise false.
	 */
	public static boolean isDebugNodes()
	{
		return false;
	}
	
	/**
	 * Returns the camera speed.
	 * @return A float representing the camera speed.
	 */
	public static float getCameraSpeed() {
		return 1f;
	}
	
	/**
	 * Returns the value needed to convert lengths between the model and the world.
	 * 
	 * Multiply with this value to go from model --> world.
	 * Divide with it to go from world --> model.
	 * @return The modifier in the form of a float.
	 */
	public static float getModelToWorld() {
		return 0.05f;
	}
	
	/**
	 * Returns the margin from the sides the mouse cursor has to move the camera.
	 * @return The margin in the form of a float.
	 */
	public static float getCameraMoveMargin() {
		return 5;
	}
}
