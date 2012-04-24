package projectrts.global.utils;

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
	private HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	/**
	 * Initializes the textures
	 * @param app The SimpleApplication
	 */
	public void initializeTextures(SimpleApplication app){
		AssetManager assetManager = app.getAssetManager();

		Texture alpha = assetManager.loadTexture("assets/terrain/alphamap.png");
		textures.put("Alpha",alpha);
   
		Texture grass = assetManager.loadTexture(
	            "Textures/Terrain/splat/grass.jpg");
	    textures.put("Grass", grass);
	
	    Texture water = assetManager.loadTexture(
	            "assets/terrain/Water_Texture.png");
	    textures.put("Water", water);
	      
	    Texture rock = assetManager.loadTexture(
	            "Textures/Terrain/splat/road.jpg");
	    textures.put("Rock", rock);

	    Texture heightMapImage = assetManager.loadTexture(
	            "assets/terrain/mountains512.png");
	    textures.put("HeightMap", heightMapImage);
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
