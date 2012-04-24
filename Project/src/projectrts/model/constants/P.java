package projectrts.model.constants;

/**
 * A class containing constants for the model.
 * @author Markus Ekström
 *
 */
public enum P {INSTANCE;
	/**
	 * A getter for the width of the world.
	 * @return An int representing the width of the world.
	 */
	public int getWorldWidth() {
		return 100;
	}
	
	/**
	 * A getter for the height of the world. 
	 * @return An integer representing the height of the world.
	 */
	public int getWorldHeight() {
		return 100;
	}
	
	/**
	 * A getter for the base length of the game.
	 * @return A float representing the base length.
	 */
	public float getUnitLength() {
		return 1;
	}
	
	/**
	 * A getter for the worker carry amount.
	 * @return An int representing the max carry amount of workers. 
	 */
	public int getWorkerCarryAmount() {
		return 12;
	}

	/**
	 * A getter for the starter amount of resources.
	 * @return An int representing starter amount of resources for players.
	 */
	public int getResourceStarterAmount() {
		return 1000;
	}
}
