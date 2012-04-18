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
	            childLayoutVertical(); // layer properties, add more...

	            
	            
	            panel(new PanelBuilder("panel_main") {{
	                childLayoutVertical();
	                backgroundColor("#0000");
	                height("80%");

	                // <!-- spacer -->
	            }});
	            
	 
	            // <panel>
	            panel(new PanelBuilder("Panel_GUI") {{
	               childLayoutHorizontal(); // panel properties, add more...  
	               backgroundColor("#f00f"); 
		           height("20%");
		           visibleToMouse(true);
		           
		           
	               panel(new PanelBuilder("Panel_Main"){{
	            	   width("60%");
	            	   childLayoutVertical();
	            	   
	               }});
		           
	               panel(new PanelBuilder("Panel_Abilities"){{
	            	   width("40%");
	            	   childLayoutVertical();
	    
	 
	            	   //First row with buttons
		               panel(new PanelBuilder("Panel_Abilities_Row1"){{
		            	   height("50%");
		            	   childLayoutHorizontal();
		            	   

				                // GUI elements
				                control(new ButtonBuilder("Button_Ability_" + 1){{
				                    width("25%");
				                    height("100%");
				                    visible(false);
				                    focusable(false);
				                    interactOnClick("buttonClicked()");
				                }});
			                
		            	   
		
		               
		               
		           
	                
		               }});
	               }});
	 
	            }});
	            // </panel>
	          }});
	        // </layer>
	      }}.build(nifty));
	    // </screen>
	 
	    nifty.gotoScreen("Screen_ID"); // start the screen

		
	}

}
