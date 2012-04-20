package projectrts.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import projectrts.global.utils.MaterialManager;
import projectrts.global.utils.TextureManager;
import projectrts.model.GameModel;
import projectrts.model.IGame;
import projectrts.view.controls.MoveControl;
import projectrts.view.controls.SelectControl;
import projectrts.view.spatials.HeadquarterSpatial;
import projectrts.view.spatials.ResourceSpatial;
import projectrts.view.spatials.SelectSpatial;
import projectrts.view.spatials.UnitSpatial;
import projectrts.view.spatials.WorkerSpatial;

import com.jme3.app.SimpleApplication;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;

import de.lessvoid.nifty.Nifty;

/**
 * The top-level controller.
 * 
 * Is the connection to jMonkeyEngine (extends SimpleApplication)
 * and handles top-level stuff like swapping AppStates.
 * @author Markus Ekström Modifed by Jakob Svensson 
 *
 */
public class AppController extends SimpleApplication{

	static{
		try
		{
			// Initialize the control classes.
			Class.forName(MoveControl.class.getName());
			Class.forName(SelectControl.class.getName());
			
			// Initialize the spatial classes.
			Class.forName(UnitSpatial.class.getName());
			Class.forName(WorkerSpatial.class.getName());
			Class.forName(HeadquarterSpatial.class.getName());
			Class.forName(ResourceSpatial.class.getName());
			Class.forName(SelectSpatial.class.getName());
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
	    Nifty nifty = niftyDisplay.getNifty();
	    
	    getGuiViewPort().addProcessor(niftyDisplay);
	    //app.getFlyByCamera().setDragToRotate(true);
	 
	    nifty.loadStyleFile("nifty-default-styles.xml");
	    nifty.loadControlFile("nifty-default-controls.xml");
	    
    	
    	this.cam.setParallelProjection(true);
    	TextureManager.INSTANCE.initializeTextures(this);
    	MaterialManager.INSTANCE.initializeMaterial(this);
        /*
    	IGame game = new GameModel();
        InGameState inGameState = new InGameState(game);
        this.stateManager.attach(inGameState);
        inGameState.setEnabled(true);
        */
        //inGameState.setEnabled(false);
    	
        IGame game = new GameModel();
    	InGameState ingameState = new InGameState(game, nifty);
    	MenuState menuState = new MenuState(nifty);
    	
    	this.stateManager.attach(menuState);
    	this.stateManager.attach(ingameState);
    	
    	menuState.setEnabled(true);
        
        
        // Set logger level
        Logger.getLogger("").setLevel(Level.SEVERE);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO Markus: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO Markus: add render code
    }
}
