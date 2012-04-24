package projectrts.controller;

import java.util.List;

import projectrts.global.utils.ImageManager;
import projectrts.model.entities.IAbility;
import projectrts.model.entities.IEntity;
import projectrts.model.entities.IPlayerControlledEntity;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * A controller class that handles input from the gui
 * @author Filip Brynfors
 *
 */
public class InputGUIController implements ScreenController {
	private Nifty nifty;
	private Screen screen;
	
	private InputController input;
	private ScreenController sc;
	
	private List<IAbility> abilities; 

	/**
	 * Creates a new inputGUIController
	 * @param app the application
	 * @param input the inputController
	 * @param nifty the nifty
	 */
	public InputGUIController(InputController input, Nifty nifty) {
		sc = this;
		this.input = input;
		this.nifty = nifty;
		
		initializeGUI();
		input.setGUIControl(this);
	}
	
	private void initializeGUI() {
	    
	    // <screen>
	    nifty.addScreen("Screen_ID", new ScreenBuilder("GUI Screen"){{
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
	    	        visibleToMouse(true);
	    	           
	                panel(new PanelBuilder("Panel_Main"){{
	             	   width("60%");
	             	   childLayoutVertical();
	                }});
	    	           
	                
	                panel(createAbilityPanel());
	                
	            }});
	            // </panel>
	          }});
	        // </layer>
	      }}.build(nifty));
	    // </screen>
	    
	    
	    Element element = nifty.getScreen("Screen_ID").findElementByName("Panel_GUI");
	    NiftyImage image = ImageManager.INSTANCE.getImage("GUIBackground");
	    element.getRenderer(ImageRenderer.class).setImage(image);
	 
	    nifty.gotoScreen("Screen_ID"); // start the screen
	}
	
	//Creates the panel for the ability buttons
	private PanelBuilder createAbilityPanel(){
		PanelBuilder builder = new PanelBuilder("Panel_Abilities"){{
			width("40%");
         	childLayoutVertical();
 
         	   		//First row with buttons
         	panel(new PanelBuilder("Panel_Abilities_Row1"){{
         		height("50%");
         		childLayoutHorizontal();
        	   
         		for(int i = 1; i<=4; i++){
         			// GUI elements
         			control(createAbilityButton(i));
         		}
           }});    
           
         
           //Second row with buttons
           panel(new PanelBuilder("Panel_Abilities_Row2"){{
        	   height("50%");
        	   childLayoutHorizontal();
                
        	   for(int i = 5; i<=8; i++){
	                // GUI elements
	                control(createAbilityButton(i));
        	   }
     
           }});
		}};
		return builder;
		
	}
	
	//Creates an abilityButton
	private ButtonBuilder createAbilityButton(final int i){
		ButtonBuilder builder = new ButtonBuilder("Button_Ability_" + i){{
	        width("25%");
	        height("100%");
	        visible(false);
	        focusable(false);
	        interactOnClick("buttonClicked("+i+")");
	    }};
		return builder;
		
	}
	
	/**
	 * Updates the abilities in the GUI
	 * @param selectedEntities the abilities of the selected Entity
	 */
	public void updateAbilities(List<IEntity> selectedEntities){
    	
    	boolean oneIsSelected = selectedEntities.size()==1;
    	abilities = null;
    
    	
    	if(oneIsSelected && selectedEntities.get(0) instanceof IPlayerControlledEntity){
    		IPlayerControlledEntity pce = (IPlayerControlledEntity) selectedEntities.get(0);
    		abilities = pce.getAbilities();
    	}
    	
    	//Loops through every button and sets its attributes
    	for(int i = 0; i<8; i++){
    		Element button = screen.findElementByName("Button_Ability_" + (i+1));
  
    		if(button != null){
    			
		    	if(abilities != null && i<abilities.size()){
		    		IAbility ability = abilities.get(i);
		    		//button.setVisibleToMouseEvents(true);
		    		
		    		NiftyImage image = ImageManager.INSTANCE.getImage(ability.getClass().getSimpleName());
		    		if(image==null){
		    			image = ImageManager.INSTANCE.getImage("NoImage");
		    		}
		    		
		    		button.getRenderer(ImageRenderer.class).setImage(image);
		    		button.setVisible(true);
		    		
		    	} else {
		    		button.setVisible(false);
		    	}
    		}

    	}
    }

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
	
	
	/**
	 * Used when any of the Ability buttons are clicked
	 * @param nr the ID of the clicked button
	 */
	public void buttonClicked(String nr) {
		try {
			
			int iNr = Integer.parseInt(nr);
			
			if(iNr-1<abilities.size()){
				input.selectAbility(abilities.get(iNr-1));
			}
			
			
		} catch (NumberFormatException e){
			
		}
		
	}

}
