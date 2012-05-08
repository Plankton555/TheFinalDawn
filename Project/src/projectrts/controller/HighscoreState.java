package projectrts.controller;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

import de.lessvoid.nifty.Nifty;

/**
 * The menu state that controls the menu before the game starts
 * @author Filip Brynfors
 */
public class HighscoreState extends AbstractAppState {
	private final Nifty nifty;
	private final float time;
	
	/**
	 * Creates a new MenuState
	 * @param nifty the nifty GUI object
	 * @param time 
	 * @param appController the appController
	 */
    public HighscoreState(Nifty nifty, float time) {
    	super();
		this.nifty = nifty;
		this.time = time;
	}


	@Override
    public void initialize(AppStateManager stateManager, Application app) {
    	SimpleApplication simpleApp = (SimpleApplication) app;
    	simpleApp.getInputManager().setCursorVisible(true);
    	simpleApp.getInputManager().clearMappings();
    	    	
    	new HighscoreGUIController(app, nifty, time);
    }
}
