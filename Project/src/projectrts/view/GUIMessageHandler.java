package projectrts.view;

import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;

/**
 * A class that handles the updating of the Messages in the GUI
 * 
 * @author Filip Brynfors
 * 
 */
class GUIMessageHandler {

	private boolean activeMessage = false;
	private float messageTimer = 0;
	private static final float MESSAGE_MAX_TIME = 3;
	// TODO Afton: PMD: Private field 'labelMessage' could be made final; it is
	// only initialized in the declaration or constructor.
	private Element labelMessage;

	/**
	 * Creates a new handler
	 * 
	 * @param screen
	 *            the nifty screen
	 */
	public GUIMessageHandler(Screen screen) {
		labelMessage = screen.findElementByName("Label_Message");
	}

	/**
	 * Shows a message in the GUI
	 * 
	 * @param message
	 *            the message
	 */
	public void showMessage(String message) {

		labelMessage.getRenderer(TextRenderer.class).setText(message);
		activeMessage = true;
		labelMessage.setVisible(true);
	}

	/**
	 * Updates the timer of the message, and hides the message after a while
	 * 
	 * @param tpf
	 *            time per frame
	 */
	public void updateMessage(float tpf) {
		if (activeMessage) {
			messageTimer += tpf;
			if (messageTimer >= MESSAGE_MAX_TIME) {
				activeMessage = false;
				messageTimer = 0;
				labelMessage.setVisible(false);
			}

		}
	}
}
