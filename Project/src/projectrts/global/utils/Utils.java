package projectrts.global.utils;

import projectrts.global.constants.Constants;
import projectrts.model.utils.Position;

import com.jme3.math.Vector3f;

/**
 * A class containing utility methods for use by the controllers and the world.
 * @author Markus Ekström
 *
 */
public class Utils {

	/**
	 * Converts a Vector3f position from the world into a Position position in model.
	 * @param worldLoc The world position.
	 * @return The model position in the form of a Position.
	 */
	public static Position convertWorldToModel(Vector3f worldLoc) {
		float x = worldLoc.x / Constants.getModelToWorld();
		float y = -worldLoc.y / Constants.getModelToWorld();
		return new Position(x, y);
	}
	
	/**
	 * Converts a Position position from the model into a Vector3f position in the world.
	 * @param modelLoc The model position.
	 * @return The world position in the form of a Vector3f.
	 */
	public static Vector3f convertModelToWorld(Position modelLoc) {
		float x = (float) (modelLoc.getX() * Constants.getModelToWorld());
		float y = (float) (-modelLoc.getY() * Constants.getModelToWorld());
		return new Vector3f(x, y, 0);
	}
}
