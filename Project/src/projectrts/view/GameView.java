package projectrts.view;

import projectrts.global.Constants;
import projectrts.model.core.ITile;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class GameView {
	private SimpleApplication app;
    private Node terrain = new Node("terrain");
	
	public GameView(SimpleApplication app, ITile[][] tileMap) {
		this.app = app;
		
		initializeWorld(tileMap);
	}
	
    private void initializeWorld(ITile[][] tileMap) {
    	this.app.getRootNode().attachChild(terrain);
    	
    	Box[][] terrainShapes = new Box[tileMap.length][tileMap[0].length];
    	Geometry[][] terrainSpatials = new Geometry[tileMap.length][tileMap[0].length];
    	Material redMaterial = new Material(this.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
    	Material blueMaterial = new Material(this.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
    	redMaterial.setColor("Color", ColorRGBA.Brown);
    	blueMaterial.setColor("Color", ColorRGBA.DarkGray);
    	
    	float sideLength = Constants.INSTANCE.getBaseLength();
    	
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
