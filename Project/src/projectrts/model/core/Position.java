package projectrts.model.core;

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
	 * Creates a new position from another position.
	 * @param otherPos Other position
	 */
	public Position(Position otherPos)
	{
		this(otherPos.getX(), otherPos.getY());
	}
	
	/**
	 * @return Returns a clone of this position.
	 */
	public Position clone()
	{
		return new Position(this.x, this.y);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31; // Default generated hashcode
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
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
