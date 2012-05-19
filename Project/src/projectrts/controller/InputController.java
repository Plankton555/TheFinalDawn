package projectrts.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import projectrts.model.IGame;
import projectrts.model.abilities.AttackAbility;
import projectrts.model.abilities.GatherResourceAbility;
import projectrts.model.abilities.IAbility;
import projectrts.model.abilities.IBuildStructureAbility;
import projectrts.model.abilities.ITargetAbility;
import projectrts.model.abilities.MoveAbility;
import projectrts.model.entities.IEntity;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.Resource;
import projectrts.model.world.Position;
import projectrts.view.GameView;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

/**
 * A class for handling all input.
 * 
 * @author Markus Ekstr�m Modifed by Jakob Svensson
 * 
 */
// TODO Markus: PMD: This class has too many methods, consider refactoring it.
// TODO Markus: PMD: The class 'InputController' has a Cyclomatic Complexity of 5 (Highest = 16).
class InputController {

	// Before the mouse is moved it has the position (0, 0), causing the camera to move in that direction.
	// mouseActivated suppresses the camera until set to true (which is done when the mouse is first moved).
	private final SimpleApplication app;
	private final IGame game; // The model
	private final GameView view;
	private boolean choosingPosition = false;
	private IAbility currentAbility;
	private IPlayerControlledEntity selectedEntity;
	private float buildingSize;
	private boolean choosingTarget;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private InGameGUIController guiControl;
	private final AnalogInputHandler analogInputHandler;

	public static final float CAMERA_SPEED = 1f;
	private static final float CAMERA_MOVE_MARGIN = 5f;

	// TODO Markus: Add javadoc
	public InputController(SimpleApplication app, IGame game, GameView view) {
		this.app = app;
		this.game = game;
		this.view = view;
		analogInputHandler = new AnalogInputHandler(app, game);
		initializeKeys();
	}

	/**
	 * Updates the input, should be hooked into the main update loop
	 * 
	 * @param tpf
	 *            Time-per-frame
	 */
	public void update(float tpf) {

		if (app.getStateManager().getState(InGameState.class).isEnabled()) {
			// do the following while game is RUNNING // modify scene graph...
			updateCamera(tpf);
			if (choosingPosition) {
				Position pos = InGameState.convertWorldToModel(app.getCamera()
						.getWorldCoordinates(
								app.getInputManager().getCursorPosition(), 0));
				view.drawNodes(game.getWorld().getNodesAt(pos, buildingSize));
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
	// TODO Markus: PMD: The method 'updateCamera' has a Cyclomatic Complexity of 10.
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

		// TODO Markus: PMD: The 4 following String literals appears 4 times in this file; the first occurrence is here
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
		this.app.getInputManager().addListener(actionListener,
				new String[] { "mouseLeftButton", "mouseRightButton" });
		// Debug control mapping
		this.app.getInputManager().addMapping("exit",
				new KeyTrigger(KeyInput.KEY_ESCAPE));
		this.app.getInputManager().addMapping("checkMouseLoc",
				new KeyTrigger(KeyInput.KEY_M));

		// Add debug controls to action/analog listener
		this.app.getInputManager().addListener(actionListener,
				new String[] { "exit", "checkMouseLoc" });
	}

	/**
	 * A digital listener, use if the input is digital - i.e. it can only be
	 * either "on" or "off".
	 */
	private final ActionListener actionListener = new ActionListener() {
		// TODO Markus: PMD: The method 'onAction' has a Cyclomatic Complexity of 10.
		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals("exit") && keyPressed) {
				app.stop();
			}

			if (app.getStateManager().getState(InGameState.class).isEnabled()) {
				if (name.equals("mouseLeftButton") && keyPressed) {
					handleLeftClick();
				}
				if (name.equals("mouseRightButton") && keyPressed) {
					handleRightClick();
				}

				// Debugging
				if (name.equals("checkMouseLoc") && keyPressed) {
					// TODO Markus: PMD: System.out.print is used
					System.out.println("mLoc = "
							+ app.getInputManager().getCursorPosition()
									.toString());
					System.out.println("wLoc = "
							+ app.getCamera().getWorldCoordinates(
									app.getInputManager().getCursorPosition(),
									0));
				}
			}
		}

		private void handleLeftClick() {
			Position pos = InGameState.convertWorldToModel(app.getCamera()
					.getWorldCoordinates(
							app.getInputManager().getCursorPosition(), 0));
			if (choosingPosition) {
				if (!game.isAnyNodeOccupied(game.getWorld().getNodesAt(pos,
						buildingSize))) {

					game.getAbilityManager().doAbility(
							currentAbility.getClass().getSimpleName(),
							game.getWorld().getNodeAt(pos).getPosition(),
							selectedEntity);
					choosingPosition = false;
					view.clearNodes();
				}
			} else if (choosingTarget) {
				game.getAbilityManager().doAbility(
						currentAbility.getClass().getSimpleName(), pos,
						selectedEntity);
				choosingTarget = false;

			} else {
				game.getEntityManager().select(pos, game.getHumanPlayer());
				guiControl.updateAbilities(game.getEntityManager()
						.getSelectedEntities());
			}
		}

		private void handleRightClick() {
			Position click = InGameState.convertWorldToModel(app.getCamera()
					.getWorldCoordinates(
							app.getInputManager().getCursorPosition(), 0));
			if (choosingPosition || choosingTarget) {
				choosingPosition = false;
				view.clearNodes();
				choosingTarget = false;
			} else {
				IEntity e = getEntityAtPosition(click);
				if (e == null) {
					doMoveAbility();
				} else {
					if (e instanceof Resource) {
						game.getAbilityManager().useAbilitySelected(
								GatherResourceAbility.class.getSimpleName(),
								click, game.getHumanPlayer());

					} else if (e instanceof PlayerControlledEntity) {
						PlayerControlledEntity pce = (PlayerControlledEntity) e;
						if (pce.getOwner().equals(game.getHumanPlayer())) {
							doMoveAbility();
						} else {
							game.getAbilityManager().useAbilitySelected(
									AttackAbility.class.getSimpleName(),
									pce.getPosition(), game.getHumanPlayer());
						}
					} else {
						doMoveAbility();
					}
				}
			}
		}

		private void doMoveAbility() {
			game.getAbilityManager().useAbilitySelected(
					MoveAbility.class.getSimpleName(),
					InGameState.convertWorldToModel(app.getCamera()
							.getWorldCoordinates(
									app.getInputManager().getCursorPosition(),
									0)), game.getHumanPlayer());
		}

		private IEntity getEntityAtPosition(Position pos) {
			List<IEntity> entities = game.getEntityManager().getAllEntities();
			for (IEntity entity : entities) {
				float unitSize = entity.getSize();
				Position unitPos = entity.getPosition();

				// If the point is within the area of the unit
				if (se.chalmers.pebjorn.javautils.Math.isWithin(pos.getX(),
						unitPos.getX() - unitSize / 2, unitPos.getX()
								+ unitSize / 2)
						&& se.chalmers.pebjorn.javautils.Math.isWithin(
								pos.getY(), unitPos.getY() - unitSize / 2,
								unitPos.getY() + unitSize / 2)) {
					return entity;
				}
			}
			return null;
		}
	};

	/**
	 * Sets the GUI Control
	 * 
	 * @param guiControl
	 */
	public void setGUIControl(InGameGUIController guiControl) {
		this.guiControl = guiControl;
	}

	/**
	 * Selects an ability
	 * 
	 * @param ability
	 *            the ability to become selected
	 */
	public void selectAbility(IAbility ability, IPlayerControlledEntity e) {
		if (e.getOwner().equals(game.getHumanPlayer())) {
			IPlayerControlledEntity pce = (IPlayerControlledEntity) e;
			currentAbility = ability;
			selectedEntity = pce;
			choosingPosition = false;
			choosingTarget = false;
			if (currentAbility instanceof IBuildStructureAbility) {
				choosingPosition = true;
				IBuildStructureAbility ab = (IBuildStructureAbility) ability;
				buildingSize = ab.getSizeOfBuilding();
			} else if (currentAbility instanceof ITargetAbility) {
				choosingTarget = true;
			} else {
				game.getAbilityManager().doAbility(
						currentAbility.getClass().getSimpleName(),
						pce.getPosition(), pce);
			}
		}
	}

	// TODO Markus: Add javadoc
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
}
