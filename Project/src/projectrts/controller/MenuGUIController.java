package projectrts.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * A class that handles the GUI of the menu
 * @author Filip Brynfors
 *
 */
public class MenuGUIController implements ScreenController {
	// TODO Afton: PMD: Private field 'app' could be made final; it is only initialized in the declaration or constructor.
	private SimpleApplication app;
	// TODO Afton: PMD: Private field 'nifty' could be made final; it is only initialized in the declaration or constructor.
	private Nifty nifty;
	// TODO Afton: PMD: Private field 'pcs' could be made final; it is only initialized in the declaration or constructor.
	private PropertyChangeSupport pcs;
	
	/**
	 * Creates a new GUI controller
	 * @param app the simpleApplication
	 * @param nifty the Nifty GUI object
	 * @param observer 
	 */
	public MenuGUIController(Application app, Nifty nifty){
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

	    nifty.addScreen("Screen_StartMenu", new ScreenBuilder("GUI Start Menu"){{
	        controller(MenuGUIController.this); // Screen properties       
	 
	        // <layer>
	        layer(new LayerBuilder("Layer_ID") {{
	            childLayoutCenter(); // layer properties, add more...
	 
	            // <panel>
	            panel(new PanelBuilder("Panel_GUI") {{
	               childLayoutVertical();            
	               valignCenter();

		                // GUI elements
		                control(new ButtonBuilder("Button_Start", "Start Game"){{
		                	alignCenter();
		                    interactOnClick("buttonStartClicked()");
		                }});   
		                control(new ButtonBuilder("Button_Exit", "Exit Game"){{
		                	alignCenter();
			                interactOnClick("buttonExitClicked()");
			            }}); 
		                
	              }});
	            // </panel>
	          }});
	        // </layer>
	      }}.build(nifty));
	    // </screen>
	    
	    nifty.gotoScreen("Screen_StartMenu"); // start the screen	
	}

	/**
	 * Used when the start Game button is clicked
	 */
	public void buttonStartClicked(){		
		pcs.firePropertyChange("Start", null, null);
	}
	
	/**
	 * Used when the Exit button is clicked
	 */
	public void buttonExitClicked(){
		app.stop();
	}
	
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}

}
