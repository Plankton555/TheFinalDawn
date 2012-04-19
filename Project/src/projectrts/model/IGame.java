package projectrts.model;

import java.util.List;

import projectrts.model.entities.IEntity;
import projectrts.model.entities.IEntityManager;
import projectrts.model.player.IPlayer;

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
	public IPlayer getPlayer();
	
	/**
	 * @return The entity manager.
	 */
	public IEntityManager getEntityManager();
}
