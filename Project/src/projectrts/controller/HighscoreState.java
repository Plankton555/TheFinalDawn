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

public class HighscoreState extends AbstractAppState {
	private SimpleApplication app;
	private Nifty nifty;
	private float time;
	
	/**
	 * Creates a new MenuState
	 * @param nifty the nifty GUI object
	 * @param time 
	 * @param appController the appController
	 */
    public HighscoreState(Nifty nifty, float time) {
		this.nifty = nifty;
		this.time = time;
	}


	@Override
    public void initialize(AppStateManager stateManager, Application app) {
    	this.app = (SimpleApplication) app;
    	this.app.getInputManager().setCursorVisible(true);
    	this.app.getInputManager().clearMappings();
    	    	
    	// TODO Afton: The value of the local variable hichscoreGuiController is not used
    	// Räcker det inte med att bara köra new *...* ?
    	HighscoreGUIController hichscoreGuiController = new HighscoreGUIController(app, nifty, time);
    }
}
