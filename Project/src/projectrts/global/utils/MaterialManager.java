package projectrts.global.utils;

import java.util.HashMap;

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
	private HashMap<String, Material> materials = new HashMap<String, Material>();
	
	// TODO Jakob: Add javadoc
	public void initializeMaterial(SimpleApplication app){
		AssetManager assetManager = app.getAssetManager();
		materials.put("Terrain", new Material(assetManager,"Common/MatDefs/Terrain/Terrain.j3md"));
		materials.put("Unshaded",new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"));

	}
	
	public Material getMaterial(String name){
		return materials.get(name).clone();
	}
	
}
