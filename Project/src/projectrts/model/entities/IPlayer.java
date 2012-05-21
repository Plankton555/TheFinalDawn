package projectrts.model.entities;

import java.beans.PropertyChangeListener;

/**
 * An interface for the Players
 * 
 * @author Filip Brynfors
 * 
 */
public interface IPlayer {

	/**
	 * Adds a listener to the player
	 * 
	 * @param pcl
	 *            the listener
	 */
	void addListener(PropertyChangeListener pcl);

	/**
	 * Returns the amount of resources the player has
	 * 
	 * @return the amount of resources
	 */
	int getResources();

	/**
	 * @return Player color.
	 */
	PlayerColor getColor();
}