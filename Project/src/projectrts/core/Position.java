package projectrts.core;

/**
 * A 2D position.
 * @author Bjorn Persson Mattsson
 *
 */
public class Position {

	private float x;
	private float y;
	
	/**
	 * Creates a new position with the given components.
	 * @param x X component.
	 * @param y Y component.
	 */
	public Position(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return The x component.
	 */
	public float getX()
	{
		return x;
	}
	
	/**
	 * @return The y component.
	 */
	public float getY()
	{
		return y;
	}
}
