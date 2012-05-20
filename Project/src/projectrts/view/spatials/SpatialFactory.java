package projectrts.view.spatials;

import java.util.HashMap;
import java.util.Map;

import projectrts.model.entities.IEntity;
import projectrts.model.world.INode;

import com.jme3.scene.shape.Box;

/**
 * A factory for spatials.
 * 
 * @author Markus Ekström
 * 
 */
public final class SpatialFactory {
	private static Map<String, AbstractSpatial> spatialMap = new HashMap<String, AbstractSpatial>();

	/**
	 * Registers a spatial in the factory. Registering a spatial enables
	 * creation of that spatial through createSpatial methods.
	 * 
	 * @param spatialType
	 *            The class name of the spatial (use class.getSimpleName()).
	 * @param spatial
	 *            An instance of the spatial you want to register.
	 */
	public static void registerSpatial(String spatialType,
			AbstractSpatial spatial) {
		spatialMap.put(spatialType, spatial);
	}

	/**
	 * Creates a EntitySpatial according to specifications. If the spatial'
	 * class hasn't been registered then it returns null.
	 * 
	 * @param spatialType
	 *            The class name of the desired spatial (use
	 *            class.getSimpleName()).
	 * @param name
	 *            The name of the desired spatial.
	 * @param box
	 *            The shape of the desired spatial.
	 * @param entity
	 *            The entity the desired spatial represents.
	 * @return An instance of the desired spatial.
	 */
	public static AbstractSpatial createEntitySpatial(String spatialType,
			String name, Box box, IEntity entity) {
		if (spatialMap.get(spatialType) == null) {
			throw new IllegalStateException("You must register " + spatialType
					+ " before you can use it");
		}

		return ((IEntitySpatial) spatialMap.get(spatialType)).createSpatial(
				name, box, entity);
	}

	/**
	 * Creates a NodeSpatial according to specifications. If the spatial's class
	 * hasn't been registered then it returns null.
	 * 
	 * @param spatialType
	 *            The class name of the desired spatial (use
	 *            class.getSimpleName()).
	 * @param name
	 *            The name of the desired spatial.
	 * @param box
	 *            The shape of the desired spatial.
	 * @param node
	 *            The shape of the desired spatial.
	 * @return An instance of the desired spatial.
	 */
	public static AbstractSpatial createNodeSpatial(String spatialType,
			String name, Box box, INode node) {
		if (spatialMap.get(spatialType) == null) {
			throw new IllegalStateException("You must register " + spatialType
					+ " before you can use it");
		}

		return ((INodeSpatial) spatialMap.get(spatialType)).createSpatial(name,
				box, node);
	}

	private SpatialFactory() {
	}
}