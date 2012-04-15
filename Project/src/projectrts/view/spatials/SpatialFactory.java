package projectrts.view.spatials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projectrts.model.core.entities.IEntity;
import projectrts.view.controls.AbstractControl;

import com.jme3.material.Material;
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

	public AbstractSpatial createSpatial(String spatialType, String name, Material material, Box box, IEntity entity) {
		return spatialMap.get(spatialType).createSpatial(name, material, box, entity);
	}
}
