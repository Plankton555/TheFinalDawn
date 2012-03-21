package projectrts.core;

public class Tile implements ITile {

	@Override
	public float getSideLength() {
		return P.INSTANCE.getUnitLength();
	}
}
