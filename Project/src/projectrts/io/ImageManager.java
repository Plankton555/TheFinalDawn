package projectrts.io;

import java.util.HashMap;
import java.util.Map;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.render.NiftyRenderEngine;
import de.lessvoid.nifty.render.image.ImageModeFactory;
import de.lessvoid.nifty.render.image.ImageModeHelper;

/**
 * Handles the images that are needed for the GUI
 * 
 * @author Filip Brynfors
 * 
 */
public class ImageManager {
	private static Map<String, NiftyImage> images = new HashMap<String, NiftyImage>();
	private static NiftyRenderEngine engine;

	private ImageManager() {
	}; // Empty private constructor to disallow any instances.

	/**
	 * Initializes the images that are needed for the GUI
	 * 
	 * @param nifty
	 */
	public static void initializeImages(Nifty nifty) {
		engine = nifty.getRenderEngine();

		NiftyImage image = engine.createImage("/assets/gui/background.png",
				false);
		String imageMode = "repeat:0,0," + image.getWidth() + ","
				+ image.getHeight();
		ImageModeHelper helper = new ImageModeHelper();
		String area = helper.getAreaProviderProperty(imageMode);
		String render = helper.getRenderStrategyProperty(imageMode);
		image.setImageMode(ImageModeFactory.getSharedInstance()
				.createImageMode(area, render));
		images.put("GUIBackground", image);
	}

	/**
	 * Gets the image with the given name
	 * 
	 * @param name
	 *            the name of the image
	 * @return the image
	 */
	public static NiftyImage getImage(String name) {
		return images.get(name);
	}

	/**
	 * Creates an image and stores it
	 * 
	 * @param key
	 *            the key to getting the image
	 * @param imagePath
	 *            the path for the file of the imagefile
	 */
	public static void addImage(String key, String imagePath) {
		images.put(key, engine.createImage(imagePath, false));
	}
}