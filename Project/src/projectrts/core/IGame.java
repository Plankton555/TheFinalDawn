package projectrts.core;

public interface IGame {

	public void update(float tpf);
	public IPlayer getPlayer();
	public ITile[][] getTileMap();
}
