package projectrts.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import projectrts.model.Difficulty;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * A class that handles the GUI of the menu
 * 
 * @author Filip Brynfors
 * 
 */
// TODO Afton: PMD: This class has too many methods, consider refactoring it.
public class MenuGUIController implements ScreenController {
	private final SimpleApplication app;
	private final Nifty nifty;
	private final PropertyChangeSupport pcs;
	private Element difficultyPanel;
	private Element menuPanel;
	private Button changeDifficultyButton;
	private Element startButton;
	private Difficulty chosenDifficulty = Difficulty.EASY;

	/**
	 * Creates a new GUI controller
	 * 
	 * @param app the simpleApplication
	 * @param nifty the Nifty GUI object
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

		nifty.addScreen("Screen_StartMenu",new ScreenBuilder("GUI Start Menu") {{
			controller(MenuGUIController.this);
			layer(new LayerBuilder("Layer_DifficultyPopup") {{
				childLayoutCenter();
				panel(createDifficultyPopupPanel());
			}}); // </layer>

			// <layer>
			layer(new LayerBuilder("Layer_Menu") {{
				childLayoutCenter();
				panel(createMenuPanel());
				
			}});// </layer>

		}}.build(nifty));
		// </screen>

		nifty.gotoScreen("Screen_StartMenu"); // start the screen

		Screen screen = nifty.getScreen("Screen_StartMenu");
		startButton = screen.findElementByName("Button_Start");
		startButton.setFocus();

		difficultyPanel = screen.findElementByName("Panel_DifficultyPopup");
		menuPanel = screen.findElementByName("Panel_Menu");
		changeDifficultyButton = screen.findNiftyControl("Button_ChangeDifficulty", Button.class);

		updateDifficultyText();
	}

	private PanelBuilder createDifficultyPopupPanel() {
		PanelBuilder builder = new PanelBuilder("Panel_DifficultyPopup") {{
			childLayoutCenter();
			// TODO Afton: PMD: The String literal "100%" appears 5 times in this file; the first occurrence is here
			width("100%");
			height("100%");
			visible(false);
			
			panel(new PanelBuilder("PanelDifficultyCenter") {{
				childLayoutVertical();

				control(new ButtonBuilder("Button_Easy", "Easy") {{
					alignCenter();
					interactOnClick("buttonEasyClicked()");
				}});

				control(new ButtonBuilder("Button_Medium", "Medium") {{
					alignCenter();
					interactOnClick("buttonMediumClicked()");
				}});

				control(new ButtonBuilder("Button_Hard", "Hard") {{
					alignCenter();
					interactOnClick("buttonHardClicked()");
				}});

				control(new ButtonBuilder("Button_Nightmare","Nightmare") {{
					alignCenter();
					interactOnClick("buttonNightmareClicked()");
				}});

				panel(new PanelBuilder("Panel_Spacer") {{
					childLayoutCenter();
					height("10px");
				}});

				control(new ButtonBuilder("Button_Cancel", "Cancel") {{
					alignCenter();
					interactOnClick("buttonCancelClicked()");
				}});
			}});
		}};

		return builder;
	}

	private PanelBuilder createMenuPanel() {
		PanelBuilder builder = new PanelBuilder("Panel_Menu") {{
			childLayoutVertical();
			valignCenter();
			width("150px");
			
			// GUI elements
			control(new ButtonBuilder("Button_Start", "Start Game") {{
				width("100%");
				alignCenter();
				interactOnClick("buttonStartClicked()");
			}});
			control(new ButtonBuilder("Button_ChangeDifficulty") {{
				width("100%");
				alignCenter();
				interactOnClick("buttonChangeClicked()");	
			}});
				
			panel(new PanelBuilder("Panel_Spacer") {{
				childLayoutCenter();
				height("10px");
			}});
		
			control(new ButtonBuilder("Button_Exit", "Exit Game") {{
				width("100%");
				alignCenter();
				interactOnClick("buttonExitClicked()");
			}});
		}};
		return builder;
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

	// TODO Afton: Add javadoc
	public void buttonChangeClicked() {
		difficultyPanel.show();
		menuPanel.hide();
	}
	
	// TODO Afton: Add javadoc
	public void buttonEasyClicked() {
		chosenDifficulty = Difficulty.EASY;
		buttonCancelClicked();
	}

	// TODO Afton: Add javadoc
	public void buttonMediumClicked() {
		chosenDifficulty = Difficulty.MEDIUM;
		buttonCancelClicked();
	}

	// TODO Afton: Add javadoc
	public void buttonHardClicked() {
		chosenDifficulty = Difficulty.HARD;
		buttonCancelClicked();
	}

	// TODO Afton: Add javadoc
	public void buttonNightmareClicked() {
		chosenDifficulty = Difficulty.NIGHTMARE;
		buttonCancelClicked();
	}

	// TODO Afton: Add javadoc
	public void buttonCancelClicked() {
		difficultyPanel.hide();
		menuPanel.show();
		startButton.setFocus();
		updateDifficultyText();
	}

	private void updateDifficultyText() {
		String difficulty = chosenDifficulty.toString();
		// TODO Afton: PMD: When doing a String.toLowerCase()/toUpperCase() call, use a Locale
		// Vet inte hur viktigt det är dock... /Plankton
		difficulty = difficulty.toLowerCase();
		difficulty = difficulty.substring(0, 1).toUpperCase() + difficulty.substring(1);
		changeDifficultyButton.setText("Difficulty: " + difficulty);
		menuPanel.resetLayout();
	}

	/**
	 * Adds a listener to the controller
	 * 
	 * @param pcl the listener
	 */
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}

}
