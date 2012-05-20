package projectrts.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import projectrts.model.EasyDifficulty;
import projectrts.model.HardDifficulty;
import projectrts.model.IDifficulty;
import projectrts.model.MediumDifficulty;
import projectrts.model.NightmareDifficulty;
import projectrts.view.MenuGUICreator;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * A class that handles the GUI of the menu
 * 
 * @author Filip Brynfors
 * 
 */
public class MenuGUIController implements ScreenController {
	private final SimpleApplication app;
	private final Nifty nifty;
	private final PropertyChangeSupport pcs;
	private Element difficultyPanel;
	private Element menuPanel;
	private Button changeDifficultyButton;
	private Element startButton;
	private IDifficulty chosenDifficulty = new MediumDifficulty();

	/**
	 * Creates a new GUI controller
	 * 
	 * @param app
	 *            the simpleApplication
	 * @param nifty
	 *            the Nifty GUI object
	 * @param observer
	 */
	public MenuGUIController(Application app, Nifty nifty) {
		this.app = (SimpleApplication) app;
		this.nifty = nifty;
		pcs = new PropertyChangeSupport(this);
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

		nifty.addScreen("Screen_StartMenu",
				new ScreenBuilder("GUI Start Menu") {
					{
						controller(MenuGUIController.this);
						layer(MenuGUICreator.createDifficultyPopupLayer());

						layer(MenuGUICreator.createMainLayer());

					}
				}.build(nifty));

		nifty.gotoScreen("Screen_StartMenu"); // start the screen

		Screen screen = nifty.getScreen("Screen_StartMenu");
		startButton = screen.findElementByName("Button_Start");
		startButton.setFocus();

		difficultyPanel = screen.findElementByName("Panel_DifficultyPopup");
		menuPanel = screen.findElementByName("Panel_Menu");
		changeDifficultyButton = screen.findNiftyControl(
				"Button_ChangeDifficulty", Button.class);

		updateDifficultyText();
	}

	/**
	 * Used when the start Game button is clicked
	 */
	public void buttonStartClicked() {
		pcs.firePropertyChange("Start", null, chosenDifficulty);
	}

	/**
	 * Used when the Exit button is clicked
	 */
	public void buttonExitClicked() {
		app.stop();
	}

	/**
	 * Used when the change difficulty button is clicked
	 */
	public void buttonChangeClicked() {
		difficultyPanel.show();
		menuPanel.hide();
	}

	/**
	 * Used when the Easy Difficulty button is clicked
	 */
	public void buttonEasyClicked() {
		chosenDifficulty = new EasyDifficulty();
		buttonCancelClicked();
	}

	/**
	 * Used when the Medium Difficulty button is clicked
	 */
	public void buttonMediumClicked() {
		chosenDifficulty = new MediumDifficulty();
		buttonCancelClicked();
	}

	/**
	 * Used when the Hard Difficulty button is clicked
	 */
	public void buttonHardClicked() {
		chosenDifficulty = new HardDifficulty();
		buttonCancelClicked();
	}

	/**
	 * Used when the Nightmare Difficulty button is clicked
	 */
	public void buttonNightmareClicked() {
		chosenDifficulty = new NightmareDifficulty();
		buttonCancelClicked();
	}

	/**
	 * Used when the Cancel button is clicked
	 */
	public void buttonCancelClicked() {
		difficultyPanel.hide();
		menuPanel.show();
		startButton.setFocus();
		updateDifficultyText();
	}

	private void updateDifficultyText() {
		String difficulty = chosenDifficulty.toString();
		difficulty = difficulty.toLowerCase();
		difficulty = difficulty.substring(0, 1).toUpperCase()
				+ difficulty.substring(1);
		changeDifficultyButton.setText("Difficulty: " + difficulty);
		menuPanel.resetLayout();
	}

	/**
	 * Adds a listener to the controller
	 * 
	 * @param pcl
	 *            the listener
	 */
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
}