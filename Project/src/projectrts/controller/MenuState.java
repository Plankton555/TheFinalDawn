package projectrts.controller;

// TODO Afton(?): ADD JAVADOC
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

public class MenuState extends AbstractAppState {
	SimpleApplication app;
	
	
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
    	this.app = (SimpleApplication) app;
    	this.app.getInputManager().setCursorVisible(true);
    	this.app.getInputManager().clearMappings();
    	
    	
    	MenuGUIController menuGuiController = new MenuGUIController(app);
    }

}
