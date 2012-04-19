package projectrts.model.entities;

import java.util.List;

import projectrts.model.player.IPlayer;
import projectrts.model.utils.Position;

public interface IEntityManager {
	
	public List<IEntity> getAllEntities();
	
	public List<IPlayerControlledEntity> getEntitiesOfPlayer(IPlayer player);
	
	public void select(Position pos, IPlayer owner);
	
	public void useAbilitySelected(String ability, Position p);
	
	public List<IEntity> getSelectedEntities();
	
	
}
