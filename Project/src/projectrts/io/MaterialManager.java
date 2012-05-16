package projectrts.io;

import java.util.HashMap;
import java.util.Map;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;

/**
 * Handles import of materials
 * @author Jakob Svensson
 *
 */
public enum MaterialManager {
	INSTANCE;
	private Map<String, Material> materials = new HashMap<String, Material>();
	
	/**
	 * Initializes the materials
	 * @param app The SimpleApplication
	 */
	public void initializeMaterial(SimpleApplication app){
		AssetManager assetManager = app.getAssetManager();
		materials.put("Terrain", new Material(assetManager,"Common/MatDefs/Terrain/Terrain.j3md"));
		materials.put("Unshaded",new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"));

	}
	/**
	 * Returns a material with a specified name
	 * @param name The name of the material
	 * @return The material with the name
	 */
	public Material getMaterial(String name){
		return materials.get(name).clone();
	}
	
}
