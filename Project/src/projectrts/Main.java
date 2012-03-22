package projectrts;



import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;

import projectrts.model.core.GameModel;
import projectrts.model.core.IGame;

public class Main extends SimpleApplication {

	//TODO This class should only contain main method
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
    	this.cam.setParallelProjection(true);
    	
    	
        IGame game = new GameModel();
        InGameState inGameState = new InGameState(game);
        inGameState.initialize(stateManager, this);
        this.stateManager.attach(inGameState);
        inGameState.setEnabled(true);
        //inGameState.setEnabled(false);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
