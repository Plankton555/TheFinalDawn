package projectrts;

import projectrts.controller.AppController;

/**
 * The entry point of the application.
 * 
 * @author Markus Ekström
 * 
 */
public class Main {
	public static void main(String[] args) {
		AppController app = new AppController();
		app.start();
	}
}