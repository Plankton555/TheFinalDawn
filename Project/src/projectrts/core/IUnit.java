package projectrts.core;

/**
 * 
 * @author Bjorn Persson Mattsson
 *
 */
public interface IUnit {

	/**
	 * @return The position of the unit.
	 */
	public Position getPosition();
	
	/**
	 * @return The texture of the unit.
	 */
	public Texture getTexture();
}
