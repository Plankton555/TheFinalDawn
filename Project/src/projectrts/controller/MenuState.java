package projectrts.controller;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

import de.lessvoid.nifty.Nifty;

public class MenuState extends AbstractAppState {
	SimpleApplication app;
	Nifty nifty;
	
	
    public MenuState(Nifty nifty) {
		this.nifty = nifty;
	}


	@Override
    public void initialize(AppStateManager stateManager, Application app) {
    	this.app = (SimpleApplication) app;
    	this.app.getInputManager().setCursorVisible(true);
    	this.app.getInputManager().clearMappings();
    	
    	
    	MenuGUIController menuGuiController = new MenuGUIController(app, nifty);
    }

}
