package projectrts.controller;

import projectrts.global.constants.*;
import projectrts.global.utils.Utils;
import projectrts.model.core.IGame;
import projectrts.model.core.P;
import projectrts.view.GameView;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class InputControl {

	private boolean enabled = false;
	private boolean mouseActivated = false;
	private SimpleApplication app;
	private IGame model;
	private GameView view;
	
	
	public InputControl(SimpleApplication app, IGame model, GameView view) {
		this.app = app;
		this.model = model;
		this.view = view;
		initializeKeys();
	}
	
    public void update(float tpf, boolean enabled) {
    	this.enabled = enabled;
    	
        if(enabled){
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
	    	if(mLoc.x >= app.getCamera().getWidth() - margin && loc.x <= P.INSTANCE.getWorldWidth() * Constants.INSTANCE.getModifier()) {
	    		app.getCamera().setLocation(loc.add(tpf * Constants.INSTANCE.getCameraSpeed(), 0, 0));
	    	}
	    	if(mLoc.x <= margin && loc.x >= 0) {
	    		app.getCamera().setLocation(loc.add(tpf * -Constants.INSTANCE.getCameraSpeed(), 0, 0));
	    	}
	    	if(mLoc.y <= margin && loc.y >= -P.INSTANCE.getWorldHeight() * Constants.INSTANCE.getModifier()) {
	    		app.getCamera().setLocation(loc.add(0, tpf * -Constants.INSTANCE.getCameraSpeed(), 0));
	    	}
	    	if(mLoc.y >= app.getCamera().getHeight() - margin && loc.y <= 0) {
	    		app.getCamera().setLocation(loc.add(0, tpf * Constants.INSTANCE.getCameraSpeed(), 0));
	    	}
    	}
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
    	
    	// Map left and right mouse buttons
    	this.app.getInputManager().addMapping("mouseLeftButton", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    	this.app.getInputManager().addMapping("rightMouseButton", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
    	
    	
    	
    	
    	// Add the names to the action/analog listener.	
    	this.app.getInputManager().addListener(analogListener, new String[]{"cameraRightKey", "cameraLeftKey", "cameraUpKey", "cameraDownKey"});
    	
    	this.app.getInputManager().addListener(actionListener, new String[]{"cameraRightMouse", "cameraLeftMouse", "cameraUpMouse", "cameraDownMouse", 
    																		"mouseLeftButton", "mouseRightButton"});
    	
    	//Debug control mapping
    	this.app.getInputManager().addMapping("exit",  new KeyTrigger(KeyInput.KEY_ESCAPE));
    	this.app.getInputManager().addMapping("checkMouseLoc", new KeyTrigger(KeyInput.KEY_M));
    	
    	
    	//Add debug controls to action/analog listener
    	this.app.getInputManager().addListener(actionListener, new String[]{"exit", "checkMouseLoc"});
    }
    
    private AnalogListener analogListener = new AnalogListener() {
	    public void onAnalog(String name, float value, float tpf) {
	    	Vector3f loc = app.getCamera().getLocation();
	    	
	    	if (enabled) {
	            if (name.equals("cameraRightKey") && loc.x <= P.INSTANCE.getWorldWidth() * Constants.INSTANCE.getModifier()) {
	            	app.getCamera().setLocation(loc.add(new Vector3f(value*Constants.INSTANCE.getCameraSpeed(), 0, 0)));
	            }
	            if (name.equals("cameraLeftKey") && loc.x >= 0) {
	            	app.getCamera().setLocation(loc.add(new Vector3f(-value*Constants.INSTANCE.getCameraSpeed(), 0, 0)));
	            }
	            if (name.equals("cameraUpKey") && loc.y <= 0) {
	            	app.getCamera().setLocation(loc.add(new Vector3f(0, value*Constants.INSTANCE.getCameraSpeed(), 0)));
	            }
	            if (name.equals("cameraDownKey") && loc.y >= -P.INSTANCE.getWorldHeight() * Constants.INSTANCE.getModifier()) {
	            	app.getCamera().setLocation(loc.add(new Vector3f(0, -value*Constants.INSTANCE.getCameraSpeed(), 0)));
	            }
          	} else {
	        	  System.out.println("Press P to unpause.");
          	}
    	}
    };
      
    private ActionListener actionListener = new ActionListener() {
    	public void onAction(String name, boolean keyPressed, float tpf) {
    		if (name.equals("exit") && keyPressed) {
	            app.stop();
	    	}
    		
	    	if(enabled) {
	    		if (name.equals("mouseLeftButton") && keyPressed) {
	    			model.getPlayer().select(Utils.INSTANCE.convertWorldToModel(app.getCamera().getWorldCoordinates(app.getInputManager().getCursorPosition(), 0)));
	    		}
	    		if (name.equals("mouseRightButton") && keyPressed) {
	    			model.getPlayer().moveSelectedTo(Utils.INSTANCE.convertWorldToModel(app.getCamera().getWorldCoordinates(app.getInputManager().getCursorPosition(), 0)));
	    		}
	    		
	    		//Debugging
	    		if (name.equals("checkMouseLoc") && keyPressed) {
	    			System.out.println(app.getInputManager().getCursorPosition().toString());
	    		}
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
