package projectrts.view;

import projectrts.model.IGame;
import projectrts.model.entities.IPlayerControlledEntity;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;

/**
 * A class for updating changes in the GUI
 * 
 * @author Filip Brynfors
 * 
 */
class GUIUpdateHandler {
	// TODO Afton: PMD: Private field 'game' could be made final; it is only initialized in the declaration or constructor.
	private IGame game;
	// TODO Afton: PMD: Private field 'screen' could be made final; it is only initialized in the declaration or constructor.
	private Screen screen;

	/**
	 * Creates a new handler
	 * 
	 * @param game
	 *            the game model
	 * @param screen
	 *            the nifty screen
	 */
	public GUIUpdateHandler(IGame game, Screen screen) {
		this.game = game;
		this.screen = screen;
	}

	/**
	 * Updates the time that is shown in the GUI
	 */
	public void updateTime() {
		int sec = (int) game.getGameTime();

		StringBuffer buffer = new StringBuffer("Time: ");
		if (sec / 60 > 0) {
			buffer.append(sec / 60);
			buffer.append(':');
		}
		buffer.append(sec % 60);
		Element labelTime = screen.findElementByName("Label_Time");
		labelTime.getRenderer(TextRenderer.class).setText(buffer.toString());
	}

	/**
	 * Updates the info about players in the GUI
	 */
	public void updatePlayerInfo() {
		Element labelPlayerInfo = screen.findElementByName("Label_PlayerInfo");
		labelPlayerInfo.getRenderer(TextRenderer.class).setText(
				"Resources: " + game.getHumanPlayer().getResources());
	}

	/**
	 * Shows the timer when constructing buildings or training units
	 * 
	 * @param text
	 */
	public void showBuildInfo(String text) {
		Element buildInfoPanel = screen.findElementByName("Panel_BuildInfo");

		if (text == null || "".equals(text)) {
			buildInfoPanel.setVisible(false);
		} else {
			buildInfoPanel.setVisible(true);

			Element buildTextPanel = screen
					.findElementByName("Label_BuildText");
			buildTextPanel.getRenderer(TextRenderer.class).setText(text);
		}
	}

	/**
	 * Updates the info about the selected pce in the GUi
	 * 
	 * @param selectedPce
	 *            the selected pce
	 */
	public void updateSelectedInfo(IPlayerControlledEntity selectedPce) {
		Element panelInfo = screen.findElementByName("Panel_SelectedInfo");
		if (selectedPce == null) {
			panelInfo.setVisible(false);
		} else {
			// Update the Info about the unit in the GUI
			Element labelName = screen.findElementByName("Label_Name");
			labelName.getRenderer(TextRenderer.class).setText(
					selectedPce.getName());

			// Puts together strings with the info
			StringBuilder infoValuesBuilder = new StringBuilder();
			StringBuilder infoBuilder = new StringBuilder();

			infoBuilder.append("HP:");
			infoValuesBuilder.append(selectedPce.getCurrentHealth() + "/"
					+ selectedPce.getMaxHealth() + " (" + 100
					* selectedPce.getCurrentHealth()
					/ selectedPce.getMaxHealth() + "%)");
			infoBuilder.append("\nDmg:");
			infoValuesBuilder.append("\n" + selectedPce.getDamage());
			infoBuilder.append("\nSpeed:");
			infoValuesBuilder.append("\n" + selectedPce.getSpeed());
			infoBuilder.append("\nRange:");
			infoValuesBuilder.append("\n" + selectedPce.getSightRange());

			Element labelInfo = screen.findElementByName("Label_Info");
			Element labelInfoValues = screen
					.findElementByName("Label_InfoValues");
			labelInfoValues.getRenderer(TextRenderer.class).setText(
					infoValuesBuilder.toString());
			labelInfo.getRenderer(TextRenderer.class).setText(
					infoBuilder.toString());

			panelInfo.setVisible(true);
		}
	}
}