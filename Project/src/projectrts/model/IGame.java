package projectrts.model;

import projectrts.model.entities.IEntityManager;
import projectrts.model.player.IPlayer;
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
	public void update(float tpf);
	
	/**
	 * @return The human player
	 */
	public IPlayer getHumanPlayer();
	
	/**
	 * @return The entity manager.
	 */
	public IEntityManager getEntityManager();
	
	/**
	 * @return The game world.
	 */
	public IWorld getWorld();
	
	/**
	 * @return The current game time.
	 */
	public float getGameTime();

	/**
	 * @return The ai player.
	 */
	public IPlayer getAIPlayer();
}
