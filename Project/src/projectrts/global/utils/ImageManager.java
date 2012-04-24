package projectrts.global.utils;

import java.util.HashMap;

import projectrts.model.entities.abilities.AttackAbility;
import projectrts.model.entities.abilities.MoveAbility;
import projectrts.model.entities.abilities.OffensiveSpellAbility;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.render.NiftyRenderEngine;
import de.lessvoid.nifty.render.image.ImageModeFactory;
import de.lessvoid.nifty.render.image.ImageModeHelper;

/**
 * Handles import of icons for abilities
 * @author Filip Brynfors
 *
 */


public enum ImageManager {
	INSTANCE;
	private HashMap<String, NiftyImage> images = new HashMap<String, NiftyImage>();
	

	// TODO Afton: Add javadoc
	public void initializeImages(Nifty nifty){
		NiftyRenderEngine engine = nifty.getRenderEngine();


		images.put(MoveAbility.class.getSimpleName(), engine.createImage("assets/gui/MoveAbility.bmp", true));
		images.put(AttackAbility.class.getSimpleName(), engine.createImage("/assets/gui/AttackAbility.bmp", false));
		images.put(OffensiveSpellAbility.class.getSimpleName(), engine.createImage("/assets/gui/OffensiveSpellAbility.bmp", false));
		images.put("NoImage", engine.createImage("/assets/gui/NoImage.bmp", false));
		
		NiftyImage image = engine.createImage("/assets/gui/background.png", false);
	    String imageMode = "repeat:0,0,"+image.getWidth()+","+image.getHeight();
	    ImageModeHelper helper = new ImageModeHelper();
	    String area = helper.getAreaProviderProperty(imageMode);
	    String render = helper.getRenderStrategyProperty(imageMode);
	    image.setImageMode(ImageModeFactory.getSharedInstance().createImageMode(area, render));
	    images.put("GUIBackground", image);
	}
	
	
	
	public NiftyImage getImage(String name){
		return images.get(name);
	}


}

