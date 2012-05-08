package projectrts.view.spatials;

import java.util.HashMap;

import projectrts.model.entities.IEntity;
import projectrts.model.world.INode;
import projectrts.view.controls.MoveControl;
import projectrts.view.controls.NodeControl;
import projectrts.view.controls.SelectControl;

import com.jme3.scene.shape.Box;
/**
 * A factory for spatials.
 * @author Markus Ekström
 *
 */
public enum SpatialFactory {INSTANCE;
	
	// TODO Markus: PMD: Avoid using implementation types like 'HashMap'; use the interface instead
	private HashMap<String, AbstractSpatial> spatialMap = new HashMap<String, AbstractSpatial>();
	
	static{
		try
		{
			// Initialize the control classes.
			Class.forName(MoveControl.class.getName());
			Class.forName(SelectControl.class.getName());
			Class.forName(NodeControl.class.getName());
			
			// Initialize the spatial classes.
			Class.forName(WarriorSpatial.class.getName());
			Class.forName(WorkerSpatial.class.getName());
			Class.forName(HeadquarterSpatial.class.getName());
			Class.forName(BarracksSpatial.class.getName());
			Class.forName(ResourceSpatial.class.getName());
			Class.forName(SelectSpatial.class.getName());
			Class.forName(DebugNodeSpatial.class.getName());
			Class.forName(WallSpatial.class.getName());
		}
		catch (ClassNotFoundException any)
		{
			any.printStackTrace();
		}
	}
	
	/**
	 * Registers a spatial in the factory. Registering a spatial enables
	 * creation of that spatial through createSpatial methods.
	 * @param spatialType The class name of the spatial (use class.getSimpleName()).
	 * @param spatial An instance of the spatial you want to register.
	 */
	public void registerSpatial(String spatialType, AbstractSpatial spatial) {
		spatialMap.put(spatialType, spatial);
	}

	/**
	 * Creates a EntitySpatial according to specifications. If the spatial' class hasn't been
	 * registered then it returns null.
	 * @param spatialType The class name of the desired spatial (use class.getSimpleName()).
	 * @param name The name of the desired spatial.
	 * @param box The shape of the desired spatial.
	 * @param entity The entity the desired spatial represents.
	 * @return An instance of the desired spatial.
	 */
	public AbstractSpatial createEntitySpatial(String spatialType, String name, Box box, IEntity entity) {
		if(spatialMap.get(spatialType) == null) {
			throw new IllegalStateException("You must register "+ spatialType +
					" before you can use it");
		}
		
		return ((IEntitySpatial)spatialMap.get(spatialType)).createSpatial(name, box, entity);
	}
	
	/**
	 * Creates a NodeSpatial according to specifications. If the spatial's class hasn't been
	 * registered then it returns null.
	 * @param spatialType The class name of the desired spatial (use class.getSimpleName()).
	 * @param name The name of the desired spatial.
	 * @param box The shape of the desired spatial.
	 * @param node The shape of the desired spatial.
	 * @return An instance of the desired spatial.
	 */
	public AbstractSpatial createNodeSpatial(String spatialType, String name, Box box, INode node) {
		if(spatialMap.get(spatialType) == null) {
			throw new IllegalStateException("You must register "+ spatialType +
					" before you can use it");
		}
		
		return ((INodeSpatial)spatialMap.get(spatialType)).createSpatial(name, box, node);
	}
}
