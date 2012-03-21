package projectrts;


import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import projectrts.core.IGame;
import projectrts.core.ITile;
import projectrts.core.P;

public class InGameState extends AbstractAppState {
 
    private SimpleApplication app;
    private IGame game;
    private Node terrain = new Node("terrain");
    
    public InGameState(IGame game) {
        super();
        this.game = game;
    }
 
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app); 
        this.app = (SimpleApplication)app;          // cast to a more specific class
        
        this.app.getRootNode().attachChild(terrain);
        
      // init stuff that is independent of whether state is PAUSED or RUNNING // modify scene graph...
      initializeWorld(game.getTileMap());
   }
 
   @Override
    public void cleanup() {
      super.cleanup();
      // unregister all my listeners, detach all my nodes, etc... // modify scene graph...
      
    }
 
    @Override
    public void setEnabled(boolean enabled) {
      // Pause and unpause
      super.setEnabled(enabled);
      if(enabled){
        // init stuff that is in use while this state is RUNNING // modify scene graph...

        
      } else {
        // take away everything not needed while this state is PAUSED
      }
    }
 
    @Override
    public void update(float tpf) {
      if(isEnabled()){
        // do the following while game is RUNNING // modify scene graph...
        // call some methods...
      } else {
        // do the following while game is PAUSED, e.g. play an idle animation.
        //...        
      }
    }
    
    private void initializeWorld(ITile[][] tileMap) {
    	
    	Box[][] terrainShapes = new Box[tileMap.length][tileMap[0].length];
    	Geometry[][] terrainSpatials = new Geometry[tileMap.length][tileMap[0].length];
    	Material redMaterial = new Material(this.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
    	Material blueMaterial = new Material(this.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
    	redMaterial.setColor("Color", ColorRGBA.Brown);
    	blueMaterial.setColor("Color", ColorRGBA.DarkGray);
    	
    	float sideLength = P.INSTANCE.getUnitLength();
    	
        for(int i = 0; i < tileMap.length; i++) {
        	for(int j = 0; j < tileMap[0].length; j++) {
        		terrainShapes[i][j] = new Box(new Vector3f(i*sideLength, -j*sideLength, 0), sideLength / 2, sideLength / 2, 0);
        		terrainSpatials[i][j] = new Geometry("tile" + i + "a" + j,terrainShapes[i][j]);
        		
        		if(i % 2 == 0) {
        			if(j % 2 == 0) {
        				terrainSpatials[i][j].setMaterial(blueMaterial);
        			} else {
        				terrainSpatials[i][j].setMaterial(redMaterial);
        			}
        		} else {
        			if(j % 2 == 0) {
        				terrainSpatials[i][j].setMaterial(redMaterial);
        			} else {
        				terrainSpatials[i][j].setMaterial(blueMaterial);
        			}
        		}
        		
        		terrain.attachChild(terrainSpatials[i][j]);
        	}
        }
    }
 
}