package projectrts.core;

public class Tile implements ITile {

	@Override
	public float getSideLength() {
		return P.INSTANCE.getTileSideLength();
	}

	@Override
	public Texture getTexture() {
		// TODO Tile.getTexture()
		return null;
	}

}
