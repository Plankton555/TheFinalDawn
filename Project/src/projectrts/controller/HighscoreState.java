package projectrts.controller;

/**
 * The menu state that controls the menu before the game starts
 * @author Filip Brynfors
 */
import java.beans.PropertyChangeListener;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

import de.lessvoid.nifty.Nifty;

public class HighscoreState extends AbstractAppState {
	private SimpleApplication app;
	private Nifty nifty;
	private HighscoreGUIController hichscoreGuiController;
	private PropertyChangeListener pcl;
	
	/**
	 * Creates a new MenuState
	 * @param nifty the nifty GUI object
	 * @param time 
	 * @param appController the appController
	 */
    public HighscoreState(Nifty nifty, float time) {
		this.nifty = nifty;
	}


	@Override
    public void initialize(AppStateManager stateManager, Application app) {
    	this.app = (SimpleApplication) app;
    	this.app.getInputManager().setCursorVisible(true);
    	this.app.getInputManager().clearMappings();
    	    	
    	hichscoreGuiController = new HighscoreGUIController(app, nifty);
    	hichscoreGuiController.addListener(pcl);
    }
	
	public void addListener(PropertyChangeListener pcl) {
		this.pcl = pcl;
		
	}
}
