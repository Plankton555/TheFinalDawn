package projectrts.controller;


import java.io.File;

import projectrts.io.ImageManager;
import projectrts.model.GameModel;
import projectrts.model.IGame;
import projectrts.model.world.Position;
import projectrts.view.GameGUIView;
import projectrts.view.GameView;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;

import de.lessvoid.nifty.Nifty;

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
    private GameGUIView guiView;
    private InputGUIController guiControl;
    private Nifty nifty;
    
    public static final float MODEL_TO_WORLD = 0.05f;
    
	/**
	 * Converts a Vector3f position from the world into a Position position in model.
	 * @param worldLoc The world position.
	 * @return The model position in the form of a Position.
	 */
	public static Position convertWorldToModel(Vector3f worldLoc) {
		float x = worldLoc.x / InGameState.MODEL_TO_WORLD;
		float y = -worldLoc.y / InGameState.MODEL_TO_WORLD;
		return GameModel.getPosition(x, y);
	}
    
    public InGameState(IGame game, Nifty nifty) {
        super();
        this.game = game;
        this.nifty = nifty;
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
      initializeImages();
      view = new GameView(this.app, game);
      guiView = new GameGUIView(nifty, game);
      
      input = new InputController(this.app, game, view);
      guiControl = new InputGUIController(input, nifty, guiView, game.getAbilityManager()); 

      initializeCamera();
      // Initialize view last, after model and controller, since its initialization is dependent on the other's.
      view.initialize();
      guiView.initialize();
      input.addListener(guiView);
   }
 
    private void initializeImages() {
    	//Add images to ImageManager
    	String[] names = game.getAbilityManager().getExistingAbilityNames();
    	for(int i = 0; i < names.length; i++) {
    		File file = new File("src/assets/gui/" + names[i] + ".png");
    		if(file.exists()) {
    			ImageManager.INSTANCE.addImage(names[i], "assets/gui/" + names[i] + ".png");
    		}
    	}
    	ImageManager.INSTANCE.addImage("NoImage", "/assets/gui/NoImage.bmp");
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
    	  guiView.update(tpf);
      } else {
        // do the following while game is PAUSED, e.g. play an idle animation.
        //...        
      }
    }
    
    /**
     * Initializes the camera to the center of the playable world.
     */
    private void initializeCamera() {
    	int worldWidth = game.getWorld().getWorldWidth();
    	int worldHeight = game.getWorld().getWorldHeight();
    	app.getCamera().setLocation(app.getCamera().getLocation().add(new Vector3f((worldWidth / 2) * MODEL_TO_WORLD,
    			-(worldHeight / 2) * MODEL_TO_WORLD, 0)));
    }
}