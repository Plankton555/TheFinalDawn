package projectrts;


import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import projectrts.core.IGame;
import projectrts.core.ITile;
import projectrts.core.P;

public class InGameState extends AbstractAppState {
	
	//TODO This class should be moved to another package
 
    private SimpleApplication app;
    private IGame game;
    private Node terrain = new Node("terrain");
    private boolean enabled;
    private boolean mouseActivated = false;
    
    public InGameState(IGame game) {
        super();
        this.game = game;
    }
 
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app); 
        this.app = (SimpleApplication)app;          // cast to a more specific class
        
        this.app.getRootNode().attachChild(terrain);
        
      // init stuff that is independent of whether state is PAUSED or RUNNING // modify scene graph...
      initializeWorld(game.getTileMap());
      initializeCamera();
      initializeKeys();
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
      this.enabled = enabled;
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
    	  updateCamera(tpf);
      } else {
        // do the following while game is PAUSED, e.g. play an idle animation.
        //...        
      }
    }
    
    private void updateCamera(float tpf) {
    	if(mouseActivated) {
	    	Vector3f loc = app.getCamera().getLocation();
	    	Vector2f mLoc = app.getInputManager().getCursorPosition();
	    	float margin = Constants.INSTANCE.getCameraMoveMargin();
	    	if(mLoc.x >= app.getCamera().getWidth() - margin && loc.x <= P.INSTANCE.getWorldWidth() * Constants.INSTANCE.getBaseLength()) {
	    		app.getCamera().setLocation(loc.add(tpf * Constants.INSTANCE.getCameraSpeed(), 0, 0));
	    	}
	    	if(mLoc.x <= margin && loc.x >= 0) {
	    		app.getCamera().setLocation(loc.add(tpf * -Constants.INSTANCE.getCameraSpeed(), 0, 0));
	    	}
	    	if(mLoc.y <= margin && loc.y >= -P.INSTANCE.getWorldHeight() * Constants.INSTANCE.getBaseLength()) {
	    		app.getCamera().setLocation(loc.add(0, tpf * -Constants.INSTANCE.getCameraSpeed(), 0));
	    	}
	    	if(mLoc.y >= app.getCamera().getHeight() - margin && loc.y <= 0) {
	    		app.getCamera().setLocation(loc.add(0, tpf * Constants.INSTANCE.getCameraSpeed(), 0));
	    	}
    	}
    }
    
    private void initializeWorld(ITile[][] tileMap) {
    	
    	Box[][] terrainShapes = new Box[tileMap.length][tileMap[0].length];
    	Geometry[][] terrainSpatials = new Geometry[tileMap.length][tileMap[0].length];
    	Material redMaterial = new Material(this.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
    	Material blueMaterial = new Material(this.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
    	redMaterial.setColor("Color", ColorRGBA.Brown);
    	blueMaterial.setColor("Color", ColorRGBA.DarkGray);
    	
    	float sideLength = Constants.INSTANCE.getBaseLength();
    	
        for(int i = 0; i < tileMap.length; i++) {
        	for(int j = 0; j < tileMap[0].length; j++) {
        		terrainShapes[i][j] = new Box(new Vector3f(i*sideLength, -j*sideLength, 0), sideLength / 2, sideLength / 2, 0);
        		terrainSpatials[i][j] = new Geometry("tile" + i + "a" + j,terrainShapes[i][j]);
        		
        		if(i % 2 == 0) {
        			if(j % 2 == 0) {
        				terrainSpatials[i][j].setMaterial(blueMaterial);
        			} else {
        				terrainSpatials[i][j].setMaterial(redMaterial);
        			}
        		} else {
        			if(j % 2 == 0) {
        				terrainSpatials[i][j].setMaterial(redMaterial);
        			} else {
        				terrainSpatials[i][j].setMaterial(blueMaterial);
        			}
        		}
        		
        		terrain.attachChild(terrainSpatials[i][j]);
        	}
        }
    }
    
    private void initializeCamera() {
    	app.getCamera().setLocation(app.getCamera().getLocation().add(new Vector3f((P.INSTANCE.getWorldWidth() / 4) * Constants.INSTANCE.getBaseLength(),
    			-(P.INSTANCE.getWorldHeight() / 4) * Constants.INSTANCE.getBaseLength(), 0)));
    }
    
    /** Custom Keybinding: Map named actions to inputs. */
    private void initializeKeys() {
    	this.app.getInputManager().setCursorVisible(true);
    	this.app.getInputManager().clearMappings();
    	
    	// You can map one or several inputs to one named action
    	this.app.getInputManager().addMapping("cameraRightKey",  new KeyTrigger(KeyInput.KEY_RIGHT));
    	this.app.getInputManager().addMapping("cameraLeftKey",   new KeyTrigger(KeyInput.KEY_LEFT));
    	this.app.getInputManager().addMapping("cameraUpKey",  new KeyTrigger(KeyInput.KEY_UP));
    	this.app.getInputManager().addMapping("cameraDownKey", new KeyTrigger(KeyInput.KEY_DOWN));
    	
    	this.app.getInputManager().addMapping("cameraRightMouse",  new MouseAxisTrigger(MouseInput.AXIS_X, true) );
    	this.app.getInputManager().addMapping("cameraLeftMouse",   new MouseAxisTrigger(MouseInput.AXIS_X, false) );
    	this.app.getInputManager().addMapping("cameraUpMouse",  new MouseAxisTrigger(MouseInput.AXIS_Y, true) );
    	this.app.getInputManager().addMapping("cameraDownMouse", new MouseAxisTrigger(MouseInput.AXIS_Y, false) );
    	
    	
    	
    	
    	// Add the names to the action/analog listener.	
    	this.app.getInputManager().addListener(analogListener, new String[]{"cameraRightKey", "cameraLeftKey", "cameraUpKey", "cameraDownKey"});
    	this.app.getInputManager().addListener(actionListener, new String[]{"cameraRightMouse", "cameraLeftMouse", "cameraUpMouse", "cameraDownMouse"});
    	
    	//Debug control mapping
    	this.app.getInputManager().addMapping("exit",  new KeyTrigger(KeyInput.KEY_ESCAPE));
    	
    	
    	//Add debug controls to action/analog listener
    	this.app.getInputManager().addListener(actionListener, new String[]{"exit"});
    }
 
    private AnalogListener analogListener = new AnalogListener() {
	    public void onAnalog(String name, float value, float tpf) {
	    	Vector3f loc = app.getCamera().getLocation();
	    	
	    	if (enabled) {
	            if (name.equals("cameraRightKey") && loc.x <= P.INSTANCE.getWorldWidth() * Constants.INSTANCE.getBaseLength()) {
	            	app.getCamera().setLocation(loc.add(new Vector3f(value*Constants.INSTANCE.getCameraSpeed(), 0, 0)));
	            }
	            if (name.equals("cameraLeftKey") && loc.x >= 0) {
	            	app.getCamera().setLocation(loc.add(new Vector3f(-value*Constants.INSTANCE.getCameraSpeed(), 0, 0)));
	            }
	            if (name.equals("cameraUpKey") && loc.y <= 0) {
	            	app.getCamera().setLocation(loc.add(new Vector3f(0, value*Constants.INSTANCE.getCameraSpeed(), 0)));
	            }
	            if (name.equals("cameraDownKey") && loc.y >= -P.INSTANCE.getWorldHeight() * Constants.INSTANCE.getBaseLength()) {
	            	app.getCamera().setLocation(loc.add(new Vector3f(0, -value*Constants.INSTANCE.getCameraSpeed(), 0)));
	            }
          	} else {
	        	  System.out.println("Press P to unpause.");
          	}
    	}
    };
      
    private ActionListener actionListener = new ActionListener() {
    	public void onAction(String name, boolean keyPressed, float tpf) {
	    	
    		if (name.equals("exit")) {
	            app.stop();
	    	}
	    	
	    	
	    	if(enabled) {
	    		
	    	} else {
	    		
	    	}
	    	
	    	if ((!mouseActivated && name.equals("cameraRightMouse") ||
	    			name.equals("cameraLeftMouse") ||
	    			name.equals("cameraUpMouse") ||
	    			name.equals("cameraDownMouse"))) {
	            mouseActivated = true;
	    	}
    		
	    }
    };
}