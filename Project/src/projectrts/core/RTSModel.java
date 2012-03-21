package projectrts.core;

public class RTSModel implements IGame {
	private World world = new World(P.INSTANCE.getWorldHeight(), P.INSTANCE.getWorldWidth());
	
	public RTSModel() {
		
	}
	
	@Override
	public void update(float tpf) {
		// TODO RTSModel.update()
		
	}

	@Override
	public IPlayer getPlayer() {
		// TODO RTSModel.getPlayer()
		return null;
	}

	@Override
	public ITile[][] getTileMap() {
		return world.getTileMap();
	}
}
