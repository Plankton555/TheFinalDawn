package projectrts.controller;

import projectrts.model.IGame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.Vector3f;

/**
 * An analog listener, use if the input is analog - i.e. it can take on several
 * values, not just "on" and "off".
 * 
 * @author Markus Ekstrom
 */
class AnalogInputHandler implements AnalogListener {
	private final SimpleApplication app;
	private final IGame game;
	private static final float CAMERA_SPEED = InputController.CAMERA_SPEED;
	private boolean mouseActivated = false;

	public AnalogInputHandler(SimpleApplication app, IGame game) {
		this.app = app;
		this.game = game;
	}

	public void onAnalog(String name, float value, float tpf) {
		if (!mouseActivated) {
			checkIfMouseMovement(name);
		}
		
		checkIfArrowKeys(name, value);
	}
	
	private void checkIfArrowKeys(String eventName, float eventValue) {
		Vector3f loc = app.getCamera().getLocation();
		if (eventName.equals("cameraRightKey")
				&& loc.x <= game.getWorld().getWorldWidth()
						* InGameState.MODEL_TO_WORLD) {
			app.getCamera().setLocation(
					loc.add(new Vector3f(eventValue * CAMERA_SPEED, 0, 0)));
		}
		if (eventName.equals("cameraLeftKey") && loc.x >= 0) {
			app.getCamera().setLocation(
					loc.add(new Vector3f(-eventValue * CAMERA_SPEED, 0, 0)));
		}
		if (eventName.equals("cameraUpKey") && loc.y <= 0) {
			app.getCamera().setLocation(
					loc.add(new Vector3f(0, eventValue * CAMERA_SPEED, 0)));
		}
		if (eventName.equals("cameraDownKey")
				&& loc.y >= -game.getWorld().getWorldHeight()
						* InGameState.MODEL_TO_WORLD) {
			app.getCamera().setLocation(
					loc.add(new Vector3f(0, -eventValue * CAMERA_SPEED, 0)));
		}
	}
	
	private void checkIfMouseMovement(String eventName) {
		// Bypass the fact that the cursor position is (0, 0) before it
		// is moved,
		// which causes the camera to move towards that location would
		// this not be in place.
		if ((eventName.equals("cameraRightMouse")
				|| eventName.equals("cameraLeftMouse")
				|| eventName.equals("cameraUpMouse") 
				|| eventName.equals("cameraDownMouse"))) {
			mouseActivated = true;
			app.getInputManager().deleteMapping("cameraRightMouse");
			app.getInputManager().deleteMapping("cameraLeftMouse");
			app.getInputManager().deleteMapping("cameraUpMouse");
			app.getInputManager().deleteMapping("cameraDownMouse");
		}
	}

	public boolean isMouseActivated() {
		return mouseActivated;
	}
}
