package projectrts.model.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Player class for handling all of one players units.
 * 
 * @author Björn Persson Mattson, Modified by Filip Brynfors, Jakob Svensson
 */
public class Player implements IPlayer {
	public static final int RESOURCE_START_AMOUNT = 400;
	private int resources;
	private final PropertyChangeSupport pcs;

	/**
	 * Constructs a player
	 */
	public Player() {
		resources = RESOURCE_START_AMOUNT;
		pcs = new PropertyChangeSupport(this);
	}

	@Override
	public int getResources() {
		return resources;
	}

	/**
	 * Changes the amount of resources the player has
	 * 
	 * @param amount
	 *            the amount to change with
	 */
	public void modifyResource(int amount) {
		resources += amount;
		pcs.firePropertyChange("ResourceChange", resources - amount, resources);
	}

	@Override
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);

	}
}
