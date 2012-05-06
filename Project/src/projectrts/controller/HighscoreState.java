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
	// TODO Filip: PMD: Perhaps 'app' could be replaced by a local variable.
	private SimpleApplication app;
	// TODO Filip: PMD: Private field 'nifty' could be made final; it is only initialized in the declaration or constructor.
	private Nifty nifty;
	// TODO Filip: PMD: Private field 'time' could be made final; it is only initialized in the declaration or constructor.
	private float time;
	
	/**
	 * Creates a new MenuState
	 * @param nifty the nifty GUI object
	 * @param time 
	 * @param appController the appController
	 */
    public HighscoreState(Nifty nifty, float time) {
    	// TODO Afton: PMD: It is a good practice to call super() in a constructor
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
