package projectrts.model.constants;

/**
 * A class containing constants for the model.
 * @author Markus Ekström
 *
 */
public class P {
	// TODO Anyone: Remove this class when it's empty.
	/**
	 * A getter for the base length of the game.
	 * @return A float representing the base length.
	 */
	public static float getUnitLength() {
		return 1;
		// TODO Anyone: Will we ever use another value than 1 here?..
	}
	
	/**
	 * A getter for the worker carry amount.
	 * @return An int representing the max carry amount of workers. 
	 */
	public static int getWorkerCarryAmount() {
		return 12;
		// TODO Jakob: Move this parameter.
	}
}
