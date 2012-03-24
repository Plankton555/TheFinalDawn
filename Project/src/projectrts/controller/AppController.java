package projectrts.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;

import projectrts.controller.InGameState;
import projectrts.model.core.GameModel;
import projectrts.model.core.IGame;

public class AppController extends SimpleApplication{

    @Override
    public void simpleInitApp() {
    	this.cam.setParallelProjection(true);
        IGame game = new GameModel();
        InGameState inGameState = new InGameState(game);
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
