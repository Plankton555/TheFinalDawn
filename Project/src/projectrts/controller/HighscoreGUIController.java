package projectrts.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import projectrts.view.HighScoreGUICreator;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * A class that handles the GUI of the menu
 * 
 * @author Filip Brynfors
 * 
 */
public class HighscoreGUIController implements ScreenController {
	private final SimpleApplication app;
	private final Nifty nifty;
	private final float time;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Creates a new GUI controller
	 * 
	 * @param app
	 *            the simpleApplication
	 * @param nifty
	 *            the Nifty GUI object
	 * @param observer
	 */
	public HighscoreGUIController(Application app, Nifty nifty, float time) {
		this.app = (SimpleApplication) app;
		this.nifty = nifty;
		this.time = time;
		initializeGUI();
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {

	}

	@Override
	public void onEndScreen() {

	}

	@Override
	public void onStartScreen() {

	}

	private void initializeGUI() {

		nifty.addScreen("Screen_Highscore", new ScreenBuilder("GUI Highscore") {
			{
				controller(HighscoreGUIController.this);

				layer(HighScoreGUICreator.createMainLayer(time));
			}
		}.build(nifty));

		nifty.gotoScreen("Screen_Highscore");
	}

	/**
	 * Used when the Exit button is clicked
	 */
	public void buttonExitClicked() {
		app.stop();
	}

	/**
	 * Used when the restartButton is clicked
	 */
	public void buttonRestartClicked() {
		pcs.firePropertyChange("Menu", null, null);
	}

	/**
	 * Adds a listener to the GUI
	 * 
	 * @param pcl
	 *            the listener
	 */
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
}