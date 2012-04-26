package projectrts.controller;

/**
 * The menu state that controls the menu before the game starts
 * @author Filip Brynfors
 */
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

import de.lessvoid.nifty.Nifty;

public class MenuState extends AbstractAppState {
	private SimpleApplication app;
	private Nifty nifty;
	private AppController appController;
	
	/**
	 * Creates a new MenuState
	 * @param nifty the nifty GUI object
	 * @param appController the appController
	 */
    public MenuState(Nifty nifty, AppController appController) {
		this.nifty = nifty;
		this.appController = appController;
	}


	@Override
    public void initialize(AppStateManager stateManager, Application app) {
    	this.app = (SimpleApplication) app;
    	this.app.getInputManager().setCursorVisible(true);
    	this.app.getInputManager().clearMappings();
    	
    	
    	MenuGUIController menuGuiController = new MenuGUIController(app, nifty, appController);
    }

}
