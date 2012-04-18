package projectrts.view.spatials;

import java.util.HashMap;

import projectrts.model.core.entities.IEntity;

import com.jme3.scene.shape.Box;
/**
 * 
 * @author Markus Ekström
 *
 */
public enum SpatialFactory {INSTANCE;
	
	private HashMap<String, AbstractSpatial> spatialMap = new HashMap<String, AbstractSpatial>();

	public void registerSpatial(String spatialType, AbstractSpatial spatial) {
		spatialMap.put(spatialType, spatial);
	}

	// TODO Markus: Add javadoc
	public AbstractSpatial createSpatial(String spatialType, String name, Box box, IEntity entity) {
		return spatialMap.get(spatialType).createSpatial(name, box, entity);
	}
}
