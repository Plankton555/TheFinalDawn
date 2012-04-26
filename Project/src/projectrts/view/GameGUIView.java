package projectrts.view;

import java.util.List;

import projectrts.global.utils.ImageManager;
import projectrts.model.entities.IAbility;
import projectrts.model.entities.IEntity;
import projectrts.model.entities.IPlayerControlledEntity;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;

public class GameGUIView {
	private Nifty nifty;
	private Screen screen;
	
	private Element labelName;
	private Element labelInfo;
	private Element labelInfoValues;
	
	private Element panelInfo;
	
	private IPlayerControlledEntity SelectedPce;
	
	
	
	public GameGUIView(Nifty nifty){
		this.nifty = nifty;
	}
	
	public void initialize(){
		screen = nifty.getScreen("Screen_Game");
		labelName = screen.findElementByName("Label_Name");
		labelInfo = screen.findElementByName("Label_Info");
		labelInfoValues = screen.findElementByName("Label_InfoValues");
		panelInfo = screen.findElementByName("Panel_SelectedInfo");
	}
	
	
    /**
     * Updates the view.
     * @param tpf The time passed since the last frame.
     */
    public void update(float tpf) {
    }
    
    /**
	 * Updates the abilities in the GUI
	 * @param selectedEntities the abilities of the selected Entity
	 */
	public void updateAbilities(IPlayerControlledEntity selectedPce){
    	
		List<IAbility> abilities = null;
    		
    	if(selectedPce!=null){
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
    		
    		abilities = selectedPce.getAbilities();
    		
    		panelInfo.setVisible(true);
    	} else {
    		panelInfo.setVisible(false);
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
	
	private void updateSelectedInfo(){
		
	}
}
