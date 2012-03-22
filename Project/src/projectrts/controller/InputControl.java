package projectrts.controller;

import projectrts.global.Constants;
import projectrts.model.core.P;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class InputControl {

	private boolean enabled = false;
	private boolean mouseActivated = false;
	
	SimpleApplication app;
	
	public InputControl(SimpleApplication app) {
		this.app = app;
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
