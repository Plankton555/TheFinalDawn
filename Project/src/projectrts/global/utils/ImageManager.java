package projectrts.global.utils;

import java.util.HashMap;

import projectrts.model.entities.abilities.AttackAbility;
import projectrts.model.entities.abilities.BuildWallAbility;
import projectrts.model.entities.abilities.GatherResourceAbility;
import projectrts.model.entities.abilities.MoveAbility;
import projectrts.model.entities.abilities.OffensiveSpellAbility;
import projectrts.model.entities.abilities.TrainWarriorAbility;
import projectrts.model.entities.abilities.TrainWorkerAbility;
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
	private HashMap<String, NiftyImage> images = new HashMap<String, NiftyImage>();
	

	/**
	 * Initializes the images that are needed for the GUI
	 * @param nifty
	 */
	public void initializeImages(Nifty nifty){
		NiftyRenderEngine engine = nifty.getRenderEngine();


		images.put(MoveAbility.class.getSimpleName(), engine.createImage("assets/gui/MoveAbility.png", false));
		images.put(AttackAbility.class.getSimpleName(), engine.createImage("/assets/gui/AttackAbility.png", false));
		images.put(OffensiveSpellAbility.class.getSimpleName(), engine.createImage("/assets/gui/OffensiveSpellAbility.bmp", false));
		images.put(BuildWallAbility.class.getSimpleName(), engine.createImage("assets/gui/BuildWallAbility.png", false));
		images.put(TrainWorkerAbility.class.getSimpleName(), engine.createImage("assets/gui/TrainWorkerAbility.png", false));
		images.put(TrainWarriorAbility.class.getSimpleName(), engine.createImage("assets/gui/TrainWarriorAbility.png", false));
		images.put(GatherResourceAbility.class.getSimpleName(), engine.createImage("assets/gui/GatherResourceAbility.png", true));
		
		images.put("NoImage", engine.createImage("/assets/gui/NoImage.bmp", false));
		
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


}

