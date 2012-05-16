package projectrts.controller;

import java.util.List;

import projectrts.io.ImageManager;
import projectrts.model.abilities.IAbility;
import projectrts.model.abilities.IAbilityManager;
import projectrts.model.entities.IEntity;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.view.GameGUIView;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.HoverEffectBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * A controller class that handles input from the gui
 * @author Filip Brynfors Modified by Jakob Svensson
 *
 */
// TODO Afton: PMD: This class has too many methods, consider refactoring it.
public class InGameGUIController implements ScreenController {
	private final Nifty nifty;
	private Screen screen;
	private final GameGUIView guiView;
	private final IAbilityManager abilityManager;
	private final InputController input;
	
	private IPlayerControlledEntity selectedPce;
	private int showingTooltipID = 0;
	// TODO Afton: PMD: Private field 'labelColor' could be made final; it is only initialized in the declaration or constructor.
	private String greenColor = "#0F0F";

	/**
	 * Creates a new inputGUIController
	 * @param app the application
	 * @param input the inputController
	 * @param nifty the nifty
	 */
	public InGameGUIController(InputController input, Nifty nifty, GameGUIView guiView, IAbilityManager abilityManager) {
		this.input = input;
		this.nifty = nifty;
		this.guiView = guiView;
		this.abilityManager = abilityManager;
		
		initializeGUI();
		input.setGUIControl(this);
	}
	
	private void initializeGUI() {
	    
	    // <screen>
	    nifty.addScreen("Screen_Game", new ScreenBuilder("GUI Screen"){{
	        controller(InGameGUIController.this); // Screen properties       
	 
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
	    	           
	                panel(createPlayerInfoPanel());
	                
	                panel(createBuildInfoPanel());
	                
	                panel(createMiddlePanel()); 
	                
	                panel(createAbilityPanel());
	                
	            }});
	            // </panel>
	          }});
	        // </layer>
	          layer(new LayerBuilder("Layer_Message"){{
	        	  childLayoutCenter();
	        	  
	        	  panel(createMessagePanel());
	        	
	          }});
	          
	          layer(new LayerBuilder("Layer_Tooltip"){{
	        	  childLayoutAbsolute();
	        	  panel(createTooltipPanel());
	          }});
	          
	          
	      }}.build(nifty));
	    // </screen>
	    
	    screen = nifty.getScreen("Screen_Game");
	    Element guiPanel = screen.findElementByName("Panel_GUI");
	    NiftyImage image = ImageManager.INSTANCE.getImage("GUIBackground");
	    guiPanel.getRenderer(ImageRenderer.class).setImage(image);
	    
		Element panelInfo = screen.findElementByName("Panel_SelectedInfo");
		panelInfo.setVisible(false);
		Element labelMessage = screen.findElementByName("Label_Message");
		labelMessage.setVisible(false);
		
	    nifty.gotoScreen("Screen_Game"); // start the screen

	}
	
	//Creates the panel that shows the player info
	private PanelBuilder createPlayerInfoPanel(){
		PanelBuilder builder = new PanelBuilder("Panel_PlayerInfo"){{
			width("20%");
			childLayoutVertical();
			
			control(new LabelBuilder("Label_Time"){{
				// TODO Afton: PMD: The String literal "100%" appears 11 times in this file; the first occurrence is here
				width("100%");
				textHAlignLeft();
				textVAlignTop();
				color(greenColor);
				
			}});
			
			control(new LabelBuilder("Label_PlayerInfo"){{
				width("100%");
				textHAlignLeft();
				textVAlignTop();
				color(greenColor);
				
			}});
			
		}};
		return builder;
	}
	
	//Creates the panel that shows current hp
	private PanelBuilder createMiddlePanel(){
		PanelBuilder builder = new PanelBuilder("Panel_SelectedInfo"){{
			width("20%");
			childLayoutVertical();
			backgroundColor("#000F");
			
			control(new LabelBuilder("Label_Name"){{
				width("100%");
				color("#00FF");
			}});
			

			panel(new PanelBuilder("Panel_Info"){{
				childLayoutHorizontal();
				
				control(new LabelBuilder("Label_Info"){{
					width("30%");
					height("100%");
					textHAlignLeft();
					textVAlignTop();
					color(greenColor);
					
				}});
				
				control(new LabelBuilder("Label_InfoValues"){{
					width("100%");
					height("100%");
					textHAlignLeft();
					textVAlignTop();
					color(greenColor);
				}});
				
			}});
			


		}};
		return builder;
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
	        
	        onStartHoverEffect(new HoverEffectBuilder("nop"){{
	        	effectParameter("onStartEffect","buttonMouseEnter("+i+")");
	        }});
	        
	        onEndHoverEffect(new HoverEffectBuilder("nop"){{
	        	effectParameter("onStartEffect","buttonMouseLeave("+i+")");
	        }});
	        
	    }};
		return builder;
		
	}
	
	private PanelBuilder createMessagePanel(){
		PanelBuilder builder = new PanelBuilder("Panel_Message"){{
			childLayoutCenter();
			
			control(new LabelBuilder("Label_Message"){{
				width("100%");
				height("100%");
				color("#F00F");
				
			}});
		}};
		
		return builder;
	}
	
	private PanelBuilder createTooltipPanel(){
		PanelBuilder builder = new PanelBuilder("Panel_Tooltip"){{
			childLayoutCenter();
			
			width("150px");
			height("20px");
			backgroundColor("#FFFF");
			visible(false);
			
			control(new LabelBuilder("Label_Tooltip"){{
				width("100%");
				height("100%");
				color("#F00F");
				text("LOTS OF TEXT HERE");
				textVAlignTop();
				
			}});
		}};
		return builder;
	}
	
	//Creates the panel that shows build info
	private PanelBuilder createBuildInfoPanel(){
		PanelBuilder builder = new PanelBuilder("Panel_BuildInfo"){{
			width("20%");
			childLayoutVertical();
			visible(false);
			
			control(new LabelBuilder("Label_BuildText"){{
				width("100%");
				height("100%");
				textVAlignTop();
				color(greenColor);
				
			}});			
		}};
		return builder;
	}
	
	/**
	 * Updates the abilities in the GUI
	 * @param selectedEntities the abilities of the selected Entity
	 */
	public void updateAbilities(List<IEntity> selectedEntities){
    	boolean oneIsSelected = selectedEntities.size()==1;    
    	
    	if(oneIsSelected && selectedEntities.get(0) instanceof IPlayerControlledEntity){
    		selectedPce = (IPlayerControlledEntity) selectedEntities.get(0);
    	} else {
    		// TODO Afton: PMD: Assigning an Object to null is a code smell. Consider refactoring.
    		selectedPce = null;
    	}
    	guiView.updateSelected(selectedPce);
    }

	@Override
	public void bind(Nifty nifty, Screen screen) {
		// TODO Afton: PMD: Document empty method
	}

	@Override
	public void onEndScreen() {
		// TODO Afton: PMD: Document empty method
	}

	@Override
	public void onStartScreen() {
		// TODO Afton: PMD: Document empty method
	}
	
	
	/**
	 * Used when any of the Ability buttons are clicked
	 * @param nr the ID of the clicked button
	 */
	public void buttonClicked(String nr) {
		input.selectAbility(getAbility(nr), selectedPce);
	}
	
	/**
	 * Used when the cursor hovers on the buttons
	 * @param nr the ID of the button which the cursor is on
	 */
	public void buttonMouseEnter(String nr) {
		showingTooltipID = 0;
		try{
			showingTooltipID = Integer.parseInt(nr);
		} catch(NumberFormatException e){
			
		}

		guiView.showTooltip(getAbility(nr));
	
	}
	
	// TODO Afton: Add javadoc
	public void buttonMouseLeave(String nr) {
		int iNr = 0;
		try{
			iNr = Integer.parseInt(nr);
		} catch(NumberFormatException e){
			
		}
		if(iNr == showingTooltipID){
			guiView.showTooltip(null);
		}
	}

	private IAbility getAbility(String nr){
		IAbility ability = null;
		try {

			int iNr = Integer.parseInt(nr);
			
			List<IAbility> abilities = abilityManager.getAbilities(selectedPce);
			if(iNr-1<abilities.size()){
				ability = abilities.get(iNr-1);
			}
		
		
		} catch(NumberFormatException e) {
			
		}
		return ability;
	}
}
