package projectrts.controller;


import java.util.ArrayList;
import java.util.List;

import projectrts.global.constants.Constants;
import projectrts.model.core.IGame;
import projectrts.model.core.P;
import projectrts.view.GameView;
import projectrts.view.controls.AbstractControl;
import projectrts.view.controls.ControlFactory;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;

/**
 * The in-game state that controls everything inside the game.
 * @author Markus Ekström
 *
 */
public class InGameState extends AbstractAppState {
	
 
    private SimpleApplication app;
    private IGame game;
    private InputController input;
    private GameView view;
    
    public InGameState(IGame game) {
        super();
        this.game = game;
    }
    
    /**
     * Initializes the state. 
     * 
     * Do not manually call this method! This method is invoked automatically
     * by SimpleApplication.
     * 
     * @param stateManager SimpleApplication's stateManager
     * @param app A class extending SimpleApplication
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app); 
        this.app = (SimpleApplication)app;          // cast to a more specific class
      // init stuff that is independent of whether state is PAUSED or RUNNING // modify scene graph...
      view = new GameView(this.app, game);
      input = new InputController(this.app, game, view);
      initializeCamera();
      // Initialize view last, after model and controller, since its initialization is dependent on the other's.
      view.initialize();
   }
 
    /**
     * This method is probably called automatically when changing states, I haven't looked it up
     * though but I think it is not supposed to be called manually.
     */
   @Override
    public void cleanup() {
      super.cleanup();
      // unregister all my listeners, detach all my nodes, etc... // modify scene graph...
    }
 
   /**
    * Sets whether this state is enabled or not.
    * @param enabled Decides if the state is enabled or not.
    */
    @Override
    public void setEnabled(boolean enabled) {
      // Pause and unpause
      super.setEnabled(enabled);
      if(enabled){
        // init stuff that is in use while this state is RUNNING // modify scene graph...
    	  
        
      } else {
        // take away everything not needed while this state is PAUSED
      }
    }
 
    /**
     * The update loop, do not call manually!
     * 
     * Invoked automatically by SimpleApplication.
     * @param tpf Time-per-frame
     */
    @Override
    public void update(float tpf) {
      if(isEnabled()){
        // do the following while game is RUNNING // modify scene graph...
    	  input.update(tpf);
    	  game.update(tpf);
    	  view.update(tpf);
      } else {
        // do the following while game is PAUSED, e.g. play an idle animation.
        //...        
      }
    }
    
    /**
     * Initializes the camera to the center of the playable world.
     */
    private void initializeCamera() {
    	app.getCamera().setLocation(app.getCamera().getLocation().add(new Vector3f((P.INSTANCE.getWorldWidth() / 2) * Constants.INSTANCE.getModelToWorld(),
    			-(P.INSTANCE.getWorldHeight() / 2) * Constants.INSTANCE.getModelToWorld(), 0)));
    }
}