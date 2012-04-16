package projectrts.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;

import projectrts.controller.InGameState;
import projectrts.global.utils.MaterialManager;
import projectrts.global.utils.TextureManager;
import projectrts.model.core.GameModel;
import projectrts.model.core.IGame;
import projectrts.view.controls.MoveControl;
import projectrts.view.controls.SelectControl;
import projectrts.view.spatials.SelectSpatial;
import projectrts.view.spatials.UnitSpatial;

/**
 * The top-level controller.
 * 
 * Is the connection to jMonkeyEngine (extends SimpleApplication)
 * and handles top-level stuff like swapping AppStates.
 * @author Markus Ekström
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
			Class.forName(SelectSpatial.class.getName());
		}
		catch (ClassNotFoundException any)
		{
			any.printStackTrace();
		}
	}
	
    @Override
    public void simpleInitApp() {
    	this.cam.setParallelProjection(true);
    	TextureManager.INSTANCE.initializeTextures(this);
    	MaterialManager.INSTANCE.initializeMaterial(this);
        IGame game = new GameModel();
        InGameState inGameState = new InGameState(game);
        this.stateManager.attach(inGameState);
        inGameState.setEnabled(true);
        //inGameState.setEnabled(false);
        
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
