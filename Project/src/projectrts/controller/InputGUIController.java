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
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.Color;

/**
 * A controller class that handles input from the gui
 * @author Filip Brynfors Modified by Jakob Svensson
 *
 */
public class InputGUIController implements ScreenController {
	private Nifty nifty;
	private Screen screen;
	
	private InputController input;
	private ScreenController sc;
	
	private List<IAbility> abilities; 

	private IPlayerControlledEntity selectedPce;

	private Element labelName;
	private Element labelInfo;
	private Element labelInfoValues;


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
	             	   width("40%");
	             	   childLayoutVertical();
	                }});
	                
	                
	                panel(createMiddlePanel()); 
	                
	                panel(createAbilityPanel());
	                
	            }});
	            // </panel>
	          }});
	        // </layer>
	      }}.build(nifty));
	    // </screen>
	    
	    Screen screen = nifty.getScreen("Screen_ID");
	    Element guiPanel = screen.findElementByName("Panel_GUI");
	    NiftyImage image = ImageManager.INSTANCE.getImage("GUIBackground");
	    guiPanel.getRenderer(ImageRenderer.class).setImage(image);
	    
	    
		labelName = screen.findElementByName("Label_Name");
		labelName.getRenderer(TextRenderer.class).setColor(new Color("#F00F"));
		
		labelInfo = screen.findElementByName("Label_Info");
		labelInfo.getRenderer(TextRenderer.class).setColor(new Color("#0F0F"));
		
		labelInfoValues = screen.findElementByName("Label_InfoValues");
		labelInfoValues.getRenderer(TextRenderer.class).setColor(new Color("#0F0F"));
		
		
		
	    nifty.gotoScreen("Screen_ID"); // start the screen
	}
	
	//Creates the panel that shows current hp
	private PanelBuilder createMiddlePanel(){
		PanelBuilder builder = new PanelBuilder("Panel_SelectedInfo"){{
			width("20%");
			childLayoutVertical();
			backgroundColor("#000F");
			
			//TODO: Afton: Remove or use this testing code
			/*
			panel(new PanelBuilder("Filler") {{
				height("10px");
				
			}});
			
    		
    		//labelName.getRenderer(TextRenderer.class).setTextMinHeight(new SizeValue("40px"));
    		//labelName.getRenderer(TextRenderer.class).setTextLineHeight(new SizeValue("40px"));
    		//labelName.getRenderer(TextRenderer.class)
			
			text(new TextBuilder() {{
				font("aurulent-sans-16.fnt");
				color("#f00f");
				text("Hello World!");
				
				alignCenter();
				valignCenter();
			}});
			*/
			
			control(new LabelBuilder("Label_Name"){{
				//height("50px");
				width("100%");
				
				//TODO: Afton, fix text size
				/*
				onActiveEffect(new EffectBuilder("textSize") {{
					//effectParameter("", "10px");
				}});
				*/
				
			}});
			

			panel(new PanelBuilder("Panel_Info"){{
				childLayoutHorizontal();
				
				control(new LabelBuilder("Label_Info"){{
					width("30%");
					height("100%");
					textHAlignLeft();
					textVAlignTop();
				}});
				
				control(new LabelBuilder("Label_InfoValues"){{
					width("100%");
					height("100%");
					textHAlignLeft();
					textVAlignTop();
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
    		selectedPce = (IPlayerControlledEntity) selectedEntities.get(0);
    		abilities = selectedPce.getAbilities();
    		
    		//Update the Info about the unit in the GUI

    		labelName.getRenderer(TextRenderer.class).setText(selectedPce.getName());
    		
    		StringBuilder infoValuesBuilder = new StringBuilder();
    		StringBuilder infoBuilder = new StringBuilder();
    		
    		infoBuilder.append("HP:");
    		infoValuesBuilder.append(selectedPce.getCurrentHealth()+"/"+selectedPce.getMaxHealth()+" ("+100*selectedPce.getCurrentHealth()/selectedPce.getMaxHealth()+"%)");
    		infoBuilder.append("\nDmg:");
    		infoValuesBuilder.append("\n"+	selectedPce.getDamage());
    		infoBuilder.append("\nSpeed:");
    		infoValuesBuilder.append("\n" + selectedPce.getSpeed());
    		infoBuilder.append("\nRange:");
    		infoValuesBuilder.append("\n" + selectedPce.getSightRange());
    		
    		labelInfoValues.getRenderer(TextRenderer.class).setText(infoValuesBuilder.toString());
    		labelInfo.getRenderer(TextRenderer.class).setText(infoBuilder.toString());
    		

    		
    	} else {
    		labelName.getRenderer(TextRenderer.class).setText("");
    		labelInfo.getRenderer(TextRenderer.class).setText("");
    		labelInfoValues.getRenderer(TextRenderer.class).setText("");
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
				input.selectAbility(abilities.get(iNr-1), selectedPce);
			}
			
			
		} catch (NumberFormatException e){
			
		}
		
	}

}
