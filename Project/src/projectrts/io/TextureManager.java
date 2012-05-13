package projectrts.io;

import java.util.HashMap;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.texture.Texture;
/**
 * Handles import of textures
 * @author Jakob Svensson
 *
 */
public enum TextureManager {
	INSTANCE;
	// TODO Jakob: PMD: Avoid using implementation types like 'HashMap'; use the interface instead
	private HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	/**
	 * Initializes the textures
	 * @param app The SimpleApplication
	 */
	public void initializeTextures(SimpleApplication app){
		AssetManager assetManager = app.getAssetManager();

		textures.put("Alpha", assetManager.loadTexture("assets/terrain/alphamap.png"));
   
	    textures.put("Grass", assetManager.loadTexture("Textures/Terrain/splat/grass.jpg"));
	
	    textures.put("Water", assetManager.loadTexture("assets/terrain/Water_Texture.png"));
	    
	    textures.put("Rock", assetManager.loadTexture("Textures/Terrain/splat/road.jpg"));

	    textures.put("HeightMap", assetManager.loadTexture("assets/terrain/mountains512.png"));
	    
	    textures.put("Headquarter", assetManager.loadTexture("assets/sprites/Headquarter.png"));
	    
	    textures.put("Barracks", assetManager.loadTexture("assets/sprites/SC_barracks.png"));
	    
	    textures.put("GoldMine", assetManager.loadTexture("assets/sprites/WC2_goldmine.png"));
	    
	    textures.put("SCV", assetManager.loadTexture("assets/sprites/SC_scv.png"));
	    
	    textures.put("Drone", assetManager.loadTexture("assets/sprites/SC_drone.png"));
	    
	    textures.put("Marine", assetManager.loadTexture("assets/sprites/SC_marine.png"));
	}
	
	/**
	 * Returns a texture with a specified name
	 * @param name The name of the texture
	 * @return The texture with the name
	 */
	public Texture getTexture(String name){
		
			return textures.get(name);
		
	}
}
