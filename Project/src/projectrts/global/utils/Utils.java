package projectrts.global.utils;

import com.jme3.math.Vector3f;

import projectrts.global.constants.Constants;
import projectrts.model.core.Position;

/**
 * A singleton containing utility methods for use by the controllers and the world.
 * @author Markus Ekström
 *
 */
public enum Utils {INSTANCE;

	/**
	 * Converts a Vector3f position from the world into a Position position in model.
	 * @param worldLoc The world position.
	 * @return The model position in the form of a Position.
	 */
	public Position convertWorldToModel(Vector3f worldLoc) {
		float x = worldLoc.x / Constants.INSTANCE.getModelToWorld();
		float y = -worldLoc.y / Constants.INSTANCE.getModelToWorld();
		return new Position(x, y);
	}
	
	/**
	 * Converts a Position position from the model into a Vector3f position in the world.
	 * @param modelLoc The model position.
	 * @return The world position in the form of a Vector3f.
	 */
	public Vector3f convertModelToWorld(Position modelLoc) {
		float x = (float) (modelLoc.getX() * Constants.INSTANCE.getModelToWorld());
		float y = (float) (-modelLoc.getY() * Constants.INSTANCE.getModelToWorld());
		return new Vector3f(x, y, 0);
	}
}
