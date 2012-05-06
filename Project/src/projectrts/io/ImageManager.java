package projectrts.io;

import java.util.HashMap;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.render.NiftyRenderEngine;
import de.lessvoid.nifty.render.image.ImageModeFactory;
import de.lessvoid.nifty.render.image.ImageModeHelper;

/**
 * Handles the images that are needed for the GUI
 * @author Filip Brynfors
 *
 */
public enum ImageManager {
	INSTANCE;
	// TODO Afton: PMD: Avoid using implementation types like 'HashMap'; use the interface instead
	private HashMap<String, NiftyImage> images = new HashMap<String, NiftyImage>();
	private NiftyRenderEngine engine;
	

	/**
	 * Initializes the images that are needed for the GUI
	 * @param nifty
	 */
	public void initializeImages(Nifty nifty){
		engine = nifty.getRenderEngine();
		
		NiftyImage image = engine.createImage("/assets/gui/background.png", false);
	    String imageMode = "repeat:0,0,"+image.getWidth()+","+image.getHeight();
	    ImageModeHelper helper = new ImageModeHelper();
	    String area = helper.getAreaProviderProperty(imageMode);
	    String render = helper.getRenderStrategyProperty(imageMode);
	    image.setImageMode(ImageModeFactory.getSharedInstance().createImageMode(area, render));
	    images.put("GUIBackground", image);
	}
	
	
	/**
	 * Gets the image with the given name
	 * @param name the name of the image
	 * @return the image
	 */
	public NiftyImage getImage(String name){
		return images.get(name);
	}
	
	public void addImage(String key, String imagePath) {
		images.put(key, engine.createImage(imagePath, false));
	}


}

