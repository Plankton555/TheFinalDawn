package projectrts.view.controls;

import java.util.HashMap;
import java.util.Map;

import projectrts.model.entities.IEntity;
import projectrts.model.world.INode;

import com.jme3.scene.control.AbstractControl;

/**
 * A factory for custom controls.
 * 
 * @author Markus Ekstr�m
 * 
 */
public final class ControlFactory {

	private static Map<String, AbstractControl> controlMap = new HashMap<String, AbstractControl>();

	/**
	 * Registers a control in the factory. Registering a control enables
	 * creation of that control through createControl methods.
	 * 
	 * @param controlType
	 *            The class name of the control (use class.getSimpleName).
	 * @param control
	 *            An instance of the control you want to register.
	 */
	public static void registerControl(String controlType,
			AbstractControl control) {
		controlMap.put(controlType, control);
	}

	/**
	 * Creates a Control according to specifications. If the control's class
	 * hasn't been registered then it returns null.
	 * 
	 * @param controlType
	 *            The class name of the desired control (use
	 *            class.getSimpleName).
	 * @param entity
	 *            The entity the desired control should control.
	 * @return An instance of the desired control.
	 */
	public static AbstractControl createEntityControl(String controlType,
			IEntity entity) {
		if (controlMap.get(controlType) == null) {
			throw new IllegalStateException("You must register " + controlType
					+ " before you can use it");
		}

		return ((IEntityControl) controlMap.get(controlType))
				.createControl(entity);
	}

	/**
	 * Creates a Control according to specifications. If the control's class
	 * hasn't been registered the it returns null.
	 * 
	 * @param controlType
	 *            The class name of the desired control (use
	 *            class.getSimpleName).
	 * @param node
	 *            The node the desired control should control.
	 * @return An instance of the desired control.
	 */
	public static AbstractControl createNodeControl(String controlType,
			INode node) {
		if (controlMap.get(controlType) == null) {
			throw new IllegalStateException("You must register " + controlType
					+ " before you can use it");
		}
		return ((INodeControl) controlMap.get(controlType)).createControl(node);
	}

	private ControlFactory() {
	}
}