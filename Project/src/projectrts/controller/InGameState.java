package projectrts.controller;


import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import projectrts.global.constants.*;
import projectrts.model.core.IGame;
import projectrts.model.core.P;
import projectrts.view.GameView;

public class InGameState extends AbstractAppState {
	
 
    private SimpleApplication app;
    private IGame game;
    private InputControl input;
    private GameView view;
    
    public InGameState(IGame game) {
        super();
        this.game = game;
    }
 
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app); 
        this.app = (SimpleApplication)app;          // cast to a more specific class
      // init stuff that is independent of whether state is PAUSED or RUNNING // modify scene graph...
      input = new InputControl(this.app);
      view = new GameView(this.app, game.getTileMap());
        
      initializeCamera();
   }
 
   @Override
    public void cleanup() {
      super.cleanup();
      // unregister all my listeners, detach all my nodes, etc... // modify scene graph...
      
    }
 
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
 
    @Override
    public void update(float tpf) {
      if(isEnabled()){
        // do the following while game is RUNNING // modify scene graph...
    	  input.update(tpf, this.isEnabled());
      } else {
        // do the following while game is PAUSED, e.g. play an idle animation.
        //...        
      }
    }
    
    private void initializeCamera() {
    	app.getCamera().setLocation(app.getCamera().getLocation().add(new Vector3f((P.INSTANCE.getWorldWidth() / 4) * Constants.INSTANCE.getBaseLength(),
    			-(P.INSTANCE.getWorldHeight() / 4) * Constants.INSTANCE.getBaseLength(), 0)));
    }
}