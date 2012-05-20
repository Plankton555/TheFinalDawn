package projectrts.controller;

import projectrts.model.IGame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.Vector3f;

/**
 * An analog listener, use if the input is analog - i.e. it can take on
 * several values, not just "on" and "off".
 * 
 * @author Markus Ekstrom
 */
class AnalogInputHandler implements AnalogListener{
	private final SimpleApplication app;
	private final IGame game;
	private static final float CAMERA_SPEED = InputController.CAMERA_SPEED;
	private boolean mouseActivated = false;
	
	public AnalogInputHandler(SimpleApplication app, IGame game) {
		this.app = app;
		this.game = game;
	}

	// TODO Markus: PMD: The method onAnalog() has an NPath complexity of 488
	// TODO Markus: PMD: The method 'onAnalog' has a Cyclomatic Complexity of 16.
	public void onAnalog(String name, float value, float tpf) {
		Vector3f loc = app.getCamera().getLocation();

		if (name.equals("cameraRightKey")
				&& loc.x <= game.getWorld().getWorldWidth()
						* InGameState.MODEL_TO_WORLD) {
			app.getCamera().setLocation(
					loc.add(new Vector3f(value * CAMERA_SPEED, 0, 0)));
		}
		if (name.equals("cameraLeftKey") && loc.x >= 0) {
			app.getCamera().setLocation(
					loc.add(new Vector3f(-value * CAMERA_SPEED, 0, 0)));
		}
		if (name.equals("cameraUpKey") && loc.y <= 0) {
			app.getCamera().setLocation(
					loc.add(new Vector3f(0, value * CAMERA_SPEED, 0)));
		}
		if (name.equals("cameraDownKey")
				&& loc.y >= -game.getWorld().getWorldHeight()
						* InGameState.MODEL_TO_WORLD) {
			app.getCamera().setLocation(
					loc.add(new Vector3f(0, -value * CAMERA_SPEED, 0)));
		}

		// Bypass the fact that the cursor position is (0, 0) before it
		// is moved,
		// which causes the camera to move towards that location would
		// this not be in place.
		if ((!mouseActivated && name.equals("cameraRightMouse")
				|| name.equals("cameraLeftMouse")
				|| name.equals("cameraUpMouse") || name
				.equals("cameraDownMouse"))) {
			mouseActivated = true;
			app.getInputManager().deleteMapping("cameraRightMouse");
			app.getInputManager().deleteMapping("cameraLeftMouse");
			app.getInputManager().deleteMapping("cameraUpMouse");
			app.getInputManager().deleteMapping("cameraDownMouse");
		}
	}
	
	public boolean getMouseActivated() {
		return mouseActivated;
	}
}
