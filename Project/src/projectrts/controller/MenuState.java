package projectrts.controller;

import java.beans.PropertyChangeListener;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

import de.lessvoid.nifty.Nifty;

/**
 * The menu state that controls the menu before the game starts
 * 
 * @author Filip Brynfors
 */
class MenuState extends AbstractAppState {

	private final Nifty nifty;
	private PropertyChangeListener pcl;

	/**
	 * Creates a new MenuState
	 * 
	 * @param nifty
	 *            the nifty GUI object
	 * @param appController
	 *            the appController
	 */
	public MenuState(Nifty nifty) {
		super();
		this.nifty = nifty;
	}

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		app.getInputManager().setCursorVisible(true);
		app.getInputManager().clearMappings();

		MenuGUIController menuGuiController = new MenuGUIController(app, nifty);
		menuGuiController.addListener(pcl);
	}

	/**
	 * Adds a listener to the Menu State
	 * 
	 * @param pcl
	 *            the listener
	 */
	public void addListener(PropertyChangeListener pcl) {
		this.pcl = pcl;

	}
}
