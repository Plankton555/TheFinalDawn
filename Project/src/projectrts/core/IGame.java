package projectrts.core;

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
	 * @return The tile matrix
	 */
	public ITile[][] getTileMap();
}
