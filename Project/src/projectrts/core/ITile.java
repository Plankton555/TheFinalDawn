package projectrts.core;

/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public interface ITile {

	/**
	 * @return The side length of the tile.
	 */
	public int getSideLength();
	
	/**
	 * @return The texture of the tile.
	 */
	public Texture getTexture();
}
