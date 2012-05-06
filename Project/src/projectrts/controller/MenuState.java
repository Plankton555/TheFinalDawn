package projectrts.controller;

import java.beans.PropertyChangeListener;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

import de.lessvoid.nifty.Nifty;

/**
 * The menu state that controls the menu before the game starts
 * @author Filip Brynfors
 */
public class MenuState extends AbstractAppState {
	// TODO Afton: PMD: Perhaps 'app' could be replaced by a local variable.
	private SimpleApplication app;
	// TODO Afton: PMD: Private field 'nifty' could be made final; it is only initialized in the declaration or constructor.
	private Nifty nifty;
	// TODO Afton: PMD: Private field 'menuGuiController' could be made final; it is only initialized in the declaration or constructor.
	private MenuGUIController menuGuiController;
	private PropertyChangeListener pcl;
	
	/**
	 * Creates a new MenuState
	 * @param nifty the nifty GUI object
	 * @param appController the appController
	 */
    public MenuState(Nifty nifty) {
		this.nifty = nifty;
	}


	@Override
    public void initialize(AppStateManager stateManager, Application app) {
    	this.app = (SimpleApplication) app;
    	this.app.getInputManager().setCursorVisible(true);
    	this.app.getInputManager().clearMappings();
    	    	
    	menuGuiController = new MenuGUIController(app, nifty);
    	menuGuiController.addListener(pcl);
    }
	
	public void addListener(PropertyChangeListener pcl) {
		this.pcl = pcl;
		
	}
}
