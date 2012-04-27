package projectrts.model.player;

import java.beans.PropertyChangeListener;


/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public interface IPlayer {
	/**
	 * Returns the players current amount of resources
	 * @return The amount of resources
	 */
	public int getResources();
	
	/**
	 * Adds a listener for the player
	 * @param pcl the listener
	 */
	public void addListener(PropertyChangeListener pcl);
}
