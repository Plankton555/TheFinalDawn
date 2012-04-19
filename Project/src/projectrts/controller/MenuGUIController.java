package projectrts.controller;

import projectrts.global.utils.ImageManager;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.niftygui.NiftyJmeDisplay;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class MenuGUIController implements ScreenController {
	private SimpleApplication app;
	private ScreenController sc;
	
	public MenuGUIController(Application app){
		this.app = (SimpleApplication) app;
		this.sc = this;
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
		NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
	            app.getAssetManager(), app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
	    Nifty nifty = niftyDisplay.getNifty();
	    app.getGuiViewPort().addProcessor(niftyDisplay);
	    app.getFlyByCamera().setDragToRotate(true);
	 
	    nifty.loadStyleFile("nifty-default-styles.xml");
	    nifty.loadControlFile("nifty-default-controls.xml");
	    
	    // <screen>
	    nifty.addScreen("Screen_Start", new ScreenBuilder("GUI Start Screen"){{
	        controller(sc); // Screen properties       
	 
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
	 
	    nifty.gotoScreen("Screen_Start"); // start the screen

		
	}
	
	public void buttonExitClicked(){
		
	}
	
	public void buttonStartClicked(){
		
	}

}
