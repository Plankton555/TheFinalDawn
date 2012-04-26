package projectrts.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import projectrts.global.utils.ImageManager;
import projectrts.global.utils.MaterialManager;
import projectrts.global.utils.TextureManager;
import projectrts.model.GameModel;
import projectrts.model.IGame;
import projectrts.view.controls.MoveControl;
import projectrts.view.controls.NodeControl;
import projectrts.view.controls.SelectControl;
import projectrts.view.spatials.BarracksSpatial;
import projectrts.view.spatials.DebugNodeSpatial;
import projectrts.view.spatials.HeadquarterSpatial;
import projectrts.view.spatials.ResourceSpatial;
import projectrts.view.spatials.SelectSpatial;
import projectrts.view.spatials.WallSpatial;
import projectrts.view.spatials.WarriorSpatial;
import projectrts.view.spatials.WorkerSpatial;

import com.jme3.app.SimpleApplication;
import com.jme3.niftygui.NiftyJmeDisplay;

import de.lessvoid.nifty.Nifty;

/**
 * The top-level controller.
 * 
 * Is the connection to jMonkeyEngine (extends SimpleApplication)
 * and handles top-level stuff like swapping AppStates.
 * @author Markus Ekström Modifed by Jakob Svensson, Filip Brynfors
 *
 */
public class AppController extends SimpleApplication{
	private Nifty nifty;

	static{
		try
		{
			// Initialize the control classes.
			Class.forName(MoveControl.class.getName());
			Class.forName(SelectControl.class.getName());
			Class.forName(NodeControl.class.getName());
			
			// Initialize the spatial classes.
			Class.forName(WarriorSpatial.class.getName());
			Class.forName(WorkerSpatial.class.getName());
			Class.forName(HeadquarterSpatial.class.getName());
			Class.forName(BarracksSpatial.class.getName());
			Class.forName(ResourceSpatial.class.getName());
			Class.forName(SelectSpatial.class.getName());
			Class.forName(DebugNodeSpatial.class.getName());
			Class.forName(WallSpatial.class.getName());
		}
		catch (ClassNotFoundException any)
		{
			any.printStackTrace();
		}
	}
	
    @Override
    public void simpleInitApp() {
    	
		NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
	            getAssetManager(), getInputManager(), getAudioRenderer(), getGuiViewPort());
	    nifty = niftyDisplay.getNifty();
	    
	    getGuiViewPort().addProcessor(niftyDisplay);

	 
	    nifty.loadStyleFile("nifty-default-styles.xml");
	    nifty.loadControlFile("nifty-default-controls.xml");
	    
	    
    	
    	this.cam.setParallelProjection(true);
    	TextureManager.INSTANCE.initializeTextures(this);
    	MaterialManager.INSTANCE.initializeMaterial(this);
    	ImageManager.INSTANCE.initializeImages(nifty);

    	
    	//TODO Afton: Should not send itself as parameter. Too strong connections
    	MenuState menuState = new MenuState(nifty, this);
    	
    	menuState.setEnabled(true);
              	
       	this.stateManager.attach(menuState);
       	
        
        // Set logger level
        Logger.getLogger("").setLevel(Level.SEVERE);
         
    }
    
    public void startIngameState(){
        IGame game = new GameModel();
    	InGameState ingameState = new InGameState(game, nifty);
    	this.stateManager.attach(ingameState);
    	ingameState.setEnabled(true);
    }
}
