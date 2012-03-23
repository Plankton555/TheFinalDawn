package projectrts.global.utils;

import com.jme3.math.Vector3f;

import projectrts.global.constants.Constants;
import projectrts.model.core.Position;

public enum Utils {INSTANCE;

	public Position convertWorldToModel(Vector3f worldLoc) {
		float x = worldLoc.x / Constants.INSTANCE.getModifier();
		float y = worldLoc.x / Constants.INSTANCE.getModifier();
		return new Position(x, y);
	}
	
	public Vector3f convertModelToWorld(Position modelLoc) {
		float x = modelLoc.getX() * Constants.INSTANCE.getModifier();
		float y = modelLoc.getY() * Constants.INSTANCE.getModifier();
		return new Vector3f(x, y, 0);
	}
}
