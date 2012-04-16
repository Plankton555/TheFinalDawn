package projectrts.controller;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * A controller class that handles input from the gui
 * @author Filip Brynfors
 *
 */
public class GUIControl implements ScreenController {
	Nifty nifty;
	Screen screen;
	
	@Override
	public void bind(Nifty nifty, Screen screen) {
		this.nifty = nifty;
		this.screen = screen;
		
	}

	@Override
	public void onEndScreen() {
		
	}

	@Override
	public void onStartScreen() {
		
	}
	
	public void buttonClicked(String nr) {
		System.out.println("BUTTON "+ nr + " IS CLICKED");
	}

}
