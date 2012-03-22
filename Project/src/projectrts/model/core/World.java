package projectrts.model.core;

public class World {
	private ITile[][] tileMap;
	
	public World(int height, int width) {
		initializeTileMap(height, width);
	}
	
	private void initializeTileMap(int height, int width) {
		tileMap = new ITile[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				tileMap[i][j] = new Tile();
			}
		}
	}
	
	public ITile[][] getTileMap()
	{
		return tileMap;
	}
}
