package projectrts.core;

import java.util.List;

public interface IPlayer {

	public void select(Position p);
	public void moveSelectedTo(Position p);
	public List<IUnit> getUnits();
}
