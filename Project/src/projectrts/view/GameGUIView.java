package projectrts.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import projectrts.model.IGame;
import projectrts.model.abilities.IAbility;
import projectrts.model.entities.IPlayerControlledEntity;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;

/**
 * A view class for the GUI
 * 
 * @author Filip Brynfors
 * 
 */
public class GameGUIView implements PropertyChangeListener {
	private final Nifty nifty;
	private final IGame game;
	private GUIUpdateHandler updateHandler;
	private GUIMessageHandler messageHandler;
	private GUIAbilityHandler abilityHandler;

	private IPlayerControlledEntity selectedPce;

	/**
	 * Creates a new view
	 * 
	 * @param nifty
	 *            the nifty GUI object
	 * @param game
	 *            the model of the game
	 */
	public GameGUIView(Nifty nifty, IGame game) {
		this.nifty = nifty;
		this.game = game;

		game.getHumanPlayer().addListener(this);
		game.getEntityManager().addListener(this);
		game.getAbilityManager().setPropertyChangeLister(this);
	}

	/**
	 * Initializes the view
	 */
	public void initialize() {
		Screen screen = nifty.getScreen("Screen_Game");

		updateHandler = new GUIUpdateHandler(game, screen);
		updateHandler.updatePlayerInfo();

		messageHandler = new GUIMessageHandler(screen);

		abilityHandler = new GUIAbilityHandler(game, screen, nifty);
	}

	/**
	 * Updates the view.
	 * 
	 * @param tpf
	 *            The time passed since the last frame.
	 */
	public void update(float tpf) {
		updateHandler.updateTime();
		updateHandler.updateSelectedInfo(selectedPce);
		messageHandler.updateMessage(tpf);
	}

	/**
	 * Updates the abilities in the GUI
	 * 
	 * @param selectedEntities
	 *            the abilities of the selected Entity
	 */
	public void updateSelected(IPlayerControlledEntity selectedPce) {
		this.selectedPce = selectedPce;
		updateHandler.showBuildInfo("");
		abilityHandler.updateAbilities(selectedPce);
		messageHandler.showMessage("");
	}

	@Override
	public void propertyChange(PropertyChangeEvent pce) {
		checkForEntityEvent(pce);
		checkForBuildEvent(pce);
	}

	private void checkForEntityEvent(PropertyChangeEvent pce) {
		if ("ResourceChange".equals(pce.getPropertyName())) {
			updateHandler.updatePlayerInfo();
		} else if ("ShowMessage".equals(pce.getPropertyName())) {
			messageHandler.showMessage(pce.getNewValue().toString());

		} else if (pce.getPropertyName().equals("entityRemoved") && pce.getOldValue() == selectedPce) {
			selectedPce = null;
			updateSelected(null);
		} else if ("TargetNotResource".equals(pce.getPropertyName())) {
			messageHandler
					.showMessage("Target is invalid, must target a Resource");
		} else if ("TargetNotPCE".equals(pce.getPropertyName())) {
			messageHandler
					.showMessage("Target is invalid, must target a Unit or Structure");
		}
	}

	private void checkForBuildEvent(PropertyChangeEvent pce) {
		if ("NotEnoughResources".equals(pce.getPropertyName())) {
			messageHandler.showMessage("Not enough resources");
		} else if ("AlreadyTraining".equals(pce.getPropertyName())) {
			messageHandler
					.showMessage("That building is already training a unit");
		} else if ("BuildTimeLeft".equals(pce.getPropertyName())) {
			if (pce.getOldValue() == selectedPce) {
				updateHandler.showBuildInfo("Building structure\nTime left: "
						+ pce.getNewValue());
			}
		} else if ("TrainTimeLeft".equals(pce.getPropertyName())) {
			if (pce.getOldValue() == selectedPce) {
				updateHandler.showBuildInfo("Training Unit\nTime left: "
						+ pce.getNewValue());
			}
		} else if ("BuildCompleted".equals(pce.getPropertyName()) && pce.getOldValue() == selectedPce) {
			updateHandler.showBuildInfo("");
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
		abilityHandler.showTooltip(ability);
	}
}
