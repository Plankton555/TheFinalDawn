package projectrts.view.controls;

import projectrts.controller.InGameState;
import projectrts.model.world.Position;

import com.jme3.math.Vector3f;
import com.jme3.scene.control.AbstractControl;

/**
 * An abstract class for the custom controls
 * 
 * @author Markus Ekström
 * 
 */
abstract class AbstractCustomControl extends AbstractControl {

	/**
	 * Converts a Position position from the model into a Vector3f position in
	 * the world.
	 * 
	 * @param modelLoc
	 *            The model position.
	 * @return The world position in the form of a Vector3f.
	 */
	public Vector3f convertModelToWorld(Position modelLoc) {
		float x = (float) (modelLoc.getX() * InGameState.MODEL_TO_WORLD);
		float y = (float) (-modelLoc.getY() * InGameState.MODEL_TO_WORLD);
		return new Vector3f(x, y, 0);
	}
}
