package projectrts.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import projectrts.io.ImageManager;
import projectrts.model.IGame;
import projectrts.model.abilities.IAbility;
import projectrts.model.entities.IEntity;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.view.GameGUIView;
import projectrts.view.InGameGuiCreator;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * A controller class that handles input from the gui
 * 
 * @author Filip Brynfors Modified by Jakob Svensson
 * 
 */
public class InGameGUIController implements ScreenController, PropertyChangeListener {
	private final Nifty nifty;
	private Screen screen;
	private final GameGUIView guiView;
	private final IGame game;
	private final InputController input;

	private IPlayerControlledEntity selectedPce;
	private int showingTooltipID = 0;

	/**
	 * Creates a new inputGUIController
	 * 
	 * @param app
	 *            the application
	 * @param input
	 *            the inputController
	 * @param nifty
	 *            the nifty
	 */
	public InGameGUIController(InputController input, Nifty nifty,
			GameGUIView guiView, IGame game) {
		this.input = input;
		this.nifty = nifty;
		this.guiView = guiView;
		this.game = game;

		initializeGUI();
		game.getEntityManager().addListener(this);
	}

	private void initializeGUI() {
		nifty.addScreen("Screen_Game", new ScreenBuilder("GUI Screen") {
			{
				controller(InGameGUIController.this);

				layer(InGameGuiCreator.createMainLayer());

				layer(InGameGuiCreator.createMessageLayer());

				layer(InGameGuiCreator.createTooltipLayer());
			}
		}.build(nifty));

		screen = nifty.getScreen("Screen_Game");
		Element guiPanel = screen.findElementByName("Panel_GUI");
		NiftyImage image = ImageManager.getImage("GUIBackground");
		guiPanel.getRenderer(ImageRenderer.class).setImage(image);

		nifty.gotoScreen("Screen_Game"); // start the screen
	}

	/**
	 * Updates the abilities in the GUI
	 * 
	 * @param selectedEntities
	 *            the abilities of the selected Entity
	 */
	private void updateAbilities(List<IEntity> selectedEntities) {
		boolean oneIsSelected = selectedEntities.size() == 1;

		if (oneIsSelected
				&& selectedEntities.get(0) instanceof IPlayerControlledEntity) {
			selectedPce = (IPlayerControlledEntity) selectedEntities.get(0);
		} else {
			selectedPce = null;
		}
		guiView.updateSelected(selectedPce);
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

	/**
	 * Used when any of the Ability buttons are clicked
	 * 
	 * @param nr
	 *            the ID of the clicked button
	 */
	public void buttonClicked(String nr) {
		input.selectAbility(getAbility(nr), selectedPce);
	}

	/**
	 * Used when the cursor hovers on the buttons
	 * 
	 * @param nr
	 *            the ID of the button which the cursor is on
	 */
	public void buttonMouseEnter(String nr) {
		try {
			showingTooltipID = Integer.parseInt(nr);
		} catch (NumberFormatException e) {
			showingTooltipID = 0;
		}

		guiView.showTooltip(getAbility(nr));
	}

	/**
	 * Used when the cursor leaves any of the ability buttons
	 * 
	 * @param nr
	 *            the ID of the button that the cursor left
	 */
	public void buttonMouseLeave(String nr) {
		int iNr;
		try {
			iNr = Integer.parseInt(nr);
		} catch (NumberFormatException e) {
			iNr = 0;
		}

		if (iNr == showingTooltipID) {
			guiView.showTooltip(null);
		}
	}

	private IAbility getAbility(String nr) {
		IAbility ability = null;
		try {

			int iNr = Integer.parseInt(nr);

			List<IAbility> abilities = game.getAbilityManager().getAbilities(selectedPce);
			if (iNr - 1 < abilities.size()) {
				ability = abilities.get(iNr - 1);
			}

		} catch (NumberFormatException e) {
			// TODO Afton: PMD: Avoid empty catch blocks
		}
		return ability;
	}

	@Override
	public void propertyChange(PropertyChangeEvent pce) {
		if("entitySelected".equals(pce.getPropertyName())){
			updateAbilities(game.getEntityManager().getSelectedEntities());
		}
	}
}
