package projectrts.model.constants;

/**
 * A class containing constants for the model.
 * @author Markus Ekström
 *
 */
public class P {
	/**
	 * A getter for the width of the world.
	 * @return An int representing the width of the world.
	 */
	public static int getWorldWidth() {
		return 100;
	}
	
	/**
	 * A getter for the height of the world. 
	 * @return An integer representing the height of the world.
	 */
	public static int getWorldHeight() {
		return 100;
	}
	
	/**
	 * A getter for the base length of the game.
	 * @return A float representing the base length.
	 */
	public static float getUnitLength() {
		return 1;
	}
	
	/**
	 * A getter for the worker carry amount.
	 * @return An int representing the max carry amount of workers. 
	 */
	public static int getWorkerCarryAmount() {
		return 12;
	}
}
