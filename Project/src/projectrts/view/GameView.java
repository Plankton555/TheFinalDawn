package projectrts.view;

import java.util.List;

import projectrts.controller.GUIControl;
import projectrts.controller.controls.MoveControl;
import projectrts.controller.controls.SelectControl;
import projectrts.global.constants.Constants;
import projectrts.global.utils.ImageManager;
import projectrts.global.utils.MaterialManager;
import projectrts.global.utils.TextureManager;
import projectrts.global.utils.Utils;
import projectrts.model.core.IGame;
import projectrts.model.core.entities.IEntity;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;

/**
 * The in-game view, creating and managing the scene.
 * @author Markus Ekström
 *
 */
public class GameView{
	private SimpleApplication app;
	private IGame game; // The model
    private Node entities = new Node("entities"); // The node for all entities
    private Node selected = new Node("selected"); // The node for the selected graphics
    private Node terrainNode = new Node("terrain"); // The node for all terrain
    private Material matTerrain;
    private TerrainQuad terrain;
    private float mod = Constants.INSTANCE.getModelToWorld(); // The modifier value for converting lengths between model and world.
    private Nifty nifty;
    private int i;
	
	public GameView(SimpleApplication app, IGame model) {
		this.app = app;
		this.game = model;
	}
	
	/**
	 * Initializes the scene.
	 */
	public void initializeView() {
		initializeWorld();
		initializeEntities();
		initializeGUI();
		this.app.getRootNode().attachChild(selected);
	}
	
	/**
	 * Based on Jmonkey terrain example code
	 * http://jmonkeyengine.org/wiki/doku.php/jme3:beginner:hello_terrain
	 */
    private void initializeWorld() {
    	
    	  /** 1. Create terrain material and load four textures into it. */
        matTerrain = MaterialManager.INSTANCE.getMaterial("Terrain");
     
        /** 1.1) Add ALPHA map (for red-blue-green coded splat textures) */
        matTerrain.setTexture("Alpha",TextureManager.INSTANCE.getTexture("Alpha"));
     
        /** 1.2) Add GRASS texture into the red layer (Tex1). */
        Texture grass = TextureManager.INSTANCE.getTexture("Grass");
        grass.setWrap(WrapMode.Repeat);
        matTerrain.setTexture("Tex1", grass);
        matTerrain.setFloat("Tex1Scale", 64f);
     
        /** 1.3) Add WATER texture into the green layer (Tex2) */
        Texture water = TextureManager.INSTANCE.getTexture("Water");
        water.setWrap(WrapMode.Repeat);
        matTerrain.setTexture("Tex2", water);
        matTerrain.setFloat("Tex2Scale", 32f);
     
        /** 1.4) Add ROAD texture into the blue layer (Tex3) */
        Texture rock = TextureManager.INSTANCE.getTexture("Rock");
        rock.setWrap(WrapMode.Repeat);
        matTerrain.setTexture("Tex3", rock);
        matTerrain.setFloat("Tex3Scale", 128f);
     
        /** 2. Create the height map */
        AbstractHeightMap heightmap = null;
        Texture heightMapImage = TextureManager.INSTANCE.getTexture("HeightMap");
        heightmap = new ImageBasedHeightMap(heightMapImage.getImage());
        heightmap.load();
     
        /** 3. We have prepared material and heightmap. 
         * Now we create the actual terrain:
         * 3.1) Create a TerrainQuad and name it "my terrain".
         * 3.2) A good value for terrain tiles is 64x64 -- so we supply 64+1=65.
         * 3.3) We prepared a heightmap of size 512x512 -- so we supply 512+1=513.
         * 3.4) As LOD step scale we supply Vector3f(1,1,1).
         * 3.5) We supply the prepared heightmap itself.
         */
        int patchSize = 65;
        terrain = new TerrainQuad("my terrain", patchSize, 513, heightmap.getHeightMap());
     
        /** 4. We give the terrain its material, position & scale it, and attach it. */
        terrain.setMaterial(matTerrain);
        terrain.setLocalTranslation(0, 0, 0);
        terrain.setLocalScale(.02f, .01f, .02f);
        terrainNode.attachChild(terrain);
        app.getRootNode().attachChild(terrainNode);

        terrainNode.setLocalTranslation(2, -2, -100);
        terrainNode.rotateUpTo(new Vector3f(0f,0f,1f));

     
        /** 5. The LOD (level of detail) depends on were the camera is: */
        TerrainLodControl control = new TerrainLodControl(terrain, app.getCamera());
        terrain.addControl(control);
        
        
    }
    
    private void initializeEntities() {
    	List<IEntity> entitiesList = game.getAllEntities(); 
    	Box[] entityShapes = new Box[entitiesList.size()];
    	Geometry[] entitySpatials = new Geometry[entitiesList.size()];
    	Material entityMaterial = MaterialManager.INSTANCE.getMaterial("Unshaded");
    	entityMaterial.setColor("Color", ColorRGBA.Pink);
    	
    	float mod = Constants.INSTANCE.getModelToWorld();
    	
    	for(int i = 0; i < entitiesList.size(); i++) {
    		// Create shape.
    		// The location of the entity is initialized (0, 0, 0) but is then instantly translated to the correct place by moveControl.
    		// Gets the size from the model and converts it to world size.
    		entityShapes[i] = new Box(new Vector3f(0, 0, 0),  
    									(entitiesList.get(i).getSize() * mod)/2, (entitiesList.get(i).getSize() * mod)/2, 0); 
    		// Create spatial.
    		entitySpatials[i] = new Geometry(entitiesList.get(i).getName() + i, entityShapes[i]);
    		// Apply material.
    		entitySpatials[i].setMaterial(entityMaterial);
    		// Create a MoveControl and add it to the spatial.
    		entitySpatials[i].addControl(new MoveControl(entitiesList.get(i)));
    		// Attach spatial to the entities node.
    		entities.attachChild(entitySpatials[i]);
    	}
    	//Attach the entities node to the root node, connecting it to the world.
    	this.app.getRootNode().attachChild(entities);
    }
    

	private void initializeGUI() {
		NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
	            app.getAssetManager(), app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
	    nifty = niftyDisplay.getNifty();
	    app.getGuiViewPort().addProcessor(niftyDisplay);
	    app.getFlyByCamera().setDragToRotate(true);
	 
	    nifty.loadStyleFile("nifty-default-styles.xml");
	    nifty.loadControlFile("nifty-default-controls.xml");
	 
	    ImageManager.INSTANCE.initializeImages(nifty);
	    
	    // <screen>
	    nifty.addScreen("Screen_ID", new ScreenBuilder("GUI Screen"){{
	        controller(new GUIControl()); // Screen properties       
	 
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
		            	   
		            	   for(i = 1; i<=4; i++){
			                // GUI elements
			                control(new ButtonBuilder("Button_Ability_" + i){{
			                    width("25%");
			                    height("100%");
			                    visible(false);
			                    focusable(false);
			                    interactOnClick("buttonClicked("+i+")");
			                }});
			                
		            	   }
			 /*
			                control(new ButtonBuilder("Button_Ability_2"){{
			                    width("25%");
			                    height("100%");
			                    visible(false);
			                    focusable(false);
			                    interactOnClick("buttonClicked(2)");
			                }});
			                
			                control(new ButtonBuilder("Button_Ability_3"){{
			                    width("25%");
			                    height("100%");
			                    visible(false);
			                    focusable(false);
			                }});
			 
			                control(new ButtonBuilder("Button_Ability_4"){{
			                    width("25%");
			                    height("100%");
			                    visible(false);
			                    focusable(false);
			                }});  
			             */   
		               }});    
		               
		               
		               
		               //Second row with buttons
		               panel(new PanelBuilder("Panel_Abilities_Row2"){{
		            	   height("50%");
		            	   childLayoutHorizontal();
			                
			                control(new ButtonBuilder("Button_Ability_5"){{
			                    width("25%");
			                    height("100%");
			                    visible(false);
			                    focusable(false);
			                }});  
			                
			                control(new ButtonBuilder("Button_Ability_6"){{
			                    width("25%");
			                    height("100%");
			                    visible(false);
			                    focusable(false);
			                }});  
			                
			                control(new ButtonBuilder("Button_Ability_7"){{
			                    width("25%");
			                    height("100%");
			                    visible(false);
			                    focusable(false);
			                }});  
			                
			                control(new ButtonBuilder("Button_Ability_8"){{
			                    width("25%");
			                    height("100%");
			                    visible(false);
			                    focusable(false);
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
	
    
	/**
	 * Returns the nifty object for the GUI
	 * @return the nifty
	 */
	public Nifty getNifty(){
		return nifty;
	}
	
    public void update(float tpf) {
    }
    
    /**
     * Draws the selected graphics for all entities in the passed list.
     * @param entityList A list of selected entities.
     */
    public void drawSelected(List<IEntity> entityList) {
    	// Remove all previously selected graphics
    	selected.detachAllChildren();
    	for(IEntity entity : entityList) {
	    	Vector3f cV = Utils.INSTANCE.convertModelToWorld(entity.getPosition());
	    	// Sets the location of the spatial to (0, 0, -1) to make sure it's behind the entities that use (x, y, 0).
	    	// The selectControl will instantly translate it to the correct location.
	    	Box circle = new Box(new Vector3f(0, 0, -1), 
	    			(entity.getSize() + 0.3f)/2 * mod, (entity.getSize() + 0.3f)/2 * mod, 0);
	    	Geometry circleSpatial = new Geometry(entity.getName(), circle);
	    	Material circleMaterial = MaterialManager.INSTANCE.getMaterial("Unshaded");
	    	circleMaterial.setColor("Color", ColorRGBA.Green);
	    	circleSpatial.setMaterial(circleMaterial);
	    	// Create a SelectControl and add it to the spatial
	    	circleSpatial.addControl(new SelectControl(entity));
	    	// Attach spatial to the selected node, connecting it to the world.
	    	selected.attachChild(circleSpatial);
    	}
    }
}
