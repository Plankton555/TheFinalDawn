package projectrts.view;

import java.util.List;

import projectrts.io.ImageManager;
import projectrts.model.IGame;
import projectrts.model.abilities.IAbility;
import projectrts.model.entities.IPlayerControlledEntity;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;

/**
 * A class for handling the updates in the GUI for the abilitys and ability
 * tooltips
 * 
 * @author Filip Brynfors
 * 
 */
public class GUIAbilityHandler {
	private final IGame game;
	private final Screen screen;
	private final Nifty nifty;

	/**
	 * Creates a new handler
	 * 
	 * @param game
	 *            the game model
	 * @param screen
	 *            the nifty screen
	 * @param nifty
	 *            the nifty object
	 */
	public GUIAbilityHandler(IGame game, Screen screen, Nifty nifty) {
		this.game = game;
		this.screen = screen;
		this.nifty = nifty;
	}

	/**
	 * Updates the abilityButtons in the GUI
	 * 
	 * @param selectedPce
	 */
	public void updateAbilities(IPlayerControlledEntity selectedPce) {
		Element panelAbilities = screen.findElementByName("Panel_Abilities");
		if (selectedPce == null
				|| !selectedPce.getOwner().equals(game.getHumanPlayer())) {
			panelAbilities.setVisible(false);
		} else {
			panelAbilities.setVisible(true);
			List<IAbility> abilities = game.getAbilityManager().getAbilities(
					selectedPce);

			// Loops through every button and sets its attributes
			for (int i = 0; i < 8; i++) {
				Element button = screen.findElementByName("Button_Ability_"
						+ (i + 1));

				if (button != null) {

					if (abilities != null && i < abilities.size()) {
						IAbility ability = abilities.get(i);

						NiftyImage image = ImageManager.getImage(ability
								.getClass().getSimpleName());
						if (image == null) {
							image = ImageManager.getImage("NoImage");
						}

						button.getRenderer(ImageRenderer.class).setImage(image);
						button.setVisible(true);

					} else {
						button.setVisible(false);
					}
				}
			}
		}
	}

	/**
	 * Shows the tooltip of the given ability, hides the tooltip of the given
	 * ability is null
	 * 
	 * @param ability
	 *            shown ability
	 */
	public void showTooltip(IAbility ability) {
		Element panelTooltip = screen.findElementByName("Panel_Tooltip");
		if (ability == null) {
			panelTooltip.hide();
		} else {
			Element labelTooltipHeader = screen
					.findElementByName("Label_TooltipHeader");
			Element labelTooltipInfo = screen
					.findElementByName("Label_TooltipInfo");
			panelTooltip.setVisible(true);

			panelTooltip.setConstraintX(new SizeValue(nifty.getNiftyMouse()
					.getX() - panelTooltip.getWidth() + "px"));
			panelTooltip.setConstraintY(new SizeValue(nifty.getNiftyMouse()
					.getY() - panelTooltip.getHeight() + "px"));

			// Update the screen so it moves the panel
			screen.layoutLayers();

			labelTooltipHeader.getRenderer(TextRenderer.class).setText(
					ability.getName());
			labelTooltipInfo.getRenderer(TextRenderer.class).setText(
					ability.getInfo());
		}
	}
}
