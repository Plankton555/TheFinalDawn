package projectrts.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import projectrts.model.IGame;
import projectrts.model.abilities.IAbility;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.world.Position;
import projectrts.view.GameView;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

/**
 * A class for handling all input.
 * 
 * @author Markus Ekström modified by Jakob Svensson
 * 
 */
class InputController {

	// Before the mouse is moved it has the position (0, 0), causing the camera
	// to move in that direction.
	// mouseActivated suppresses the camera until set to true (which is done
	// when the mouse is first moved).
	private final SimpleApplication app;
	private final IGame game; // The model
	private final GameView view;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final AnalogInputHandler analogInputHandler;
	private final ActionInputHandler actionInputHandler;
	public static final float CAMERA_SPEED = 1f;
	private static final float CAMERA_MOVE_MARGIN = 5f;

	// TODO Markus: Add javadoc
	public InputController(SimpleApplication app, IGame game, GameView view) {
		this.app = app;
		this.game = game;
		this.view = view;
		analogInputHandler = new AnalogInputHandler(app, game);
		actionInputHandler = new ActionInputHandler(app, game, view);
		initializeKeys();
	}

	/**
	 * Updates the input, should be hooked into the main update loop
	 * 
	 * @param tpf
	 *            Time-per-frame
	 */
	public void update(float tpf, boolean enabled) {

		if (enabled) {
			// do the following while game is RUNNING // modify scene graph...
			updateCamera(tpf);
			if (actionInputHandler.isChoosingPosition()) {
				Position pos = ActionInputHandler.convertWorldToModel(app.getCamera()
						.getWorldCoordinates(
								app.getInputManager().getCursorPosition(), 0));
				view.drawNodes(game.getWorld().getNodesAt(pos,
						actionInputHandler.getBuildingSize()));
			}
		}
		// do something in an else statement while game is PAUSED, e.g. play an
		// idle animation.
		// ...
	}

	/**
	 * Updates the camera.
	 * 
	 * If the mouse cursor is close enough to one of the edges, it moves the
	 * camera a certain amount in that direction. The amount is decided by the
	 * getCameraSpeed method in the Constants class.
	 * 
	 * @param tpf
	 */
	private void updateCamera(float tpf) {

		if (analogInputHandler.getMouseActivated()) {
			Vector3f loc = app.getCamera().getLocation();
			Vector2f mLoc = app.getInputManager().getCursorPosition();
			float margin = CAMERA_MOVE_MARGIN;
			if (mLoc.x >= app.getCamera().getWidth() - margin
					&& loc.x <= game.getWorld().getWorldWidth()
							* InGameState.MODEL_TO_WORLD) {
				app.getCamera().setLocation(loc.add(tpf * CAMERA_SPEED, 0, 0));
			}
			if (mLoc.x <= margin && loc.x >= 0) {
				app.getCamera().setLocation(loc.add(tpf * -CAMERA_SPEED, 0, 0));
			}
			if (mLoc.y <= margin
					&& loc.y >= -game.getWorld().getWorldHeight()
							* InGameState.MODEL_TO_WORLD) {
				app.getCamera().setLocation(loc.add(0, tpf * -CAMERA_SPEED, 0));
			}
			if (mLoc.y >= app.getCamera().getHeight() - margin && loc.y <= 0) {
				app.getCamera().setLocation(loc.add(0, tpf * CAMERA_SPEED, 0));
			}
		}
	}

	/**
	 * Initializes all keybinds.
	 * 
	 * Maps named actions to inputs and assigns listeners.
	 */
	private void initializeKeys() {
		this.app.getInputManager().setCursorVisible(true);
		this.app.getInputManager().clearMappings();

		// You can map one or several inputs to one named action
		this.app.getInputManager().addMapping("cameraRightKey",
				new KeyTrigger(KeyInput.KEY_RIGHT));
		this.app.getInputManager().addMapping("cameraLeftKey",
				new KeyTrigger(KeyInput.KEY_LEFT));
		this.app.getInputManager().addMapping("cameraUpKey",
				new KeyTrigger(KeyInput.KEY_UP));
		this.app.getInputManager().addMapping("cameraDownKey",
				new KeyTrigger(KeyInput.KEY_DOWN));

		this.app.getInputManager().addMapping("cameraRightMouse",
				new MouseAxisTrigger(MouseInput.AXIS_X, true));
		this.app.getInputManager().addMapping("cameraLeftMouse",
				new MouseAxisTrigger(MouseInput.AXIS_X, false));
		this.app.getInputManager().addMapping("cameraUpMouse",
				new MouseAxisTrigger(MouseInput.AXIS_Y, true));
		this.app.getInputManager().addMapping("cameraDownMouse",
				new MouseAxisTrigger(MouseInput.AXIS_Y, false));

		// Map left and right mouse buttons
		this.app.getInputManager().addMapping("mouseLeftButton",
				new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		this.app.getInputManager().addMapping("mouseRightButton",
				new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));

		// Add the names to the action/analog listener.
		this.app.getInputManager().addListener(
				analogInputHandler,
				new String[] { "cameraRightMouse", "cameraLeftMouse",
						"cameraUpMouse", "cameraDownMouse", "cameraRightKey",
						"cameraLeftKey", "cameraUpKey", "cameraDownKey" });
		this.app.getInputManager().addListener(actionInputHandler,
				new String[] { "mouseLeftButton", "mouseRightButton" });
		// Debug control mapping
		this.app.getInputManager().addMapping("exit",
				new KeyTrigger(KeyInput.KEY_ESCAPE));

		// Add debug controls to action/analog listener
		this.app.getInputManager().addListener(actionInputHandler,
				new String[] { "exit" });
	}

	/**
	 * Sets the GUI Control
	 * 
	 * @param guiControl
	 */
	public void setGUIControl(InGameGUIController guiControl) {
		actionInputHandler.setGUIControl(guiControl);
	}

	/**
	 * Selects an ability
	 * 
	 * @param ability
	 *            the ability to become selected
	 */
	public void selectAbility(IAbility ability, IPlayerControlledEntity e) {
		actionInputHandler.selectAbility(ability, e);
	}

	// TODO Markus: Add javadoc
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
	
	
}
