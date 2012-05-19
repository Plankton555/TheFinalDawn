package projectrts.model;

import java.beans.PropertyChangeListener;
import java.util.List;

import projectrts.model.abilities.IAbilityManager;
import projectrts.model.entities.IEntityManager;
import projectrts.model.entities.IPlayer;
import projectrts.model.world.INode;
import projectrts.model.world.IWorld;

/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public interface IGame {
	
	/**
	 * This method gets called every frame.
	 * @param tpf Time per frame (Time since last frame)
	 */
	void update(float tpf);
	
	/**
	 * @return The human player
	 */
	IPlayer getHumanPlayer();
	
	/**
	 * @return The entity manager.
	 */
	IEntityManager getEntityManager();
	
	/**
	 * @return The ability manager.
	 */
	IAbilityManager getAbilityManager();
	
	/**
	 * @return The game world.
	 */
	IWorld getWorld();
	
	/**
	 * @return The current game time.
	 */
	float getGameTime();

	/**
	 * @return The ai player.
	 */
	IPlayer getAIPlayer();
	
	/**
	 * Determines whether any of the provided nodes are occupied.
	 * @param nodes The nodes to be examined.
	 * @return true if any node is occupied, otherwise false.
	 */
	boolean isAnyNodeOccupied(List<INode> nodes);

	/**
	 * Adds a listener to the game model
	 * @param pcl the listener that will listen to the model
	 */
	void addListener(PropertyChangeListener pcl);
	
	/**
	 * Set the difficulty of the game.
	 * @param difficulty The difficulty you want to set the game to.
	 */
	void setDifficulty(Difficulty difficulty);
	
	/**
	 * @return The current difficulty.
	 */
	Difficulty getCurrentDifficulty();
}
