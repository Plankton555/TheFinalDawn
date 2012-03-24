package projectrts.model.core.utils;

import projectrts.model.core.Position;

/**
 * Utility class
 * @author Filip Brynfors
 *
 */
public enum ModelUtils {
	INSTANCE;
	
	/**
	 * Gets the distance between two positions
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the distance between the points
	 */
	public float getDistance(Position p1, Position p2){
		float dx = p1.getX() - p2.getX();
		float dy = p1.getY() - p2.getY();
		
		return (float) Math.sqrt(dx*dx+dy*dy);
	}
}
