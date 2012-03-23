package projectrts.model.core;

public abstract class AbstractEntity implements IEntity {

	private Player owner;
	protected Position position;
	
	public AbstractEntity(Position spawnPos, Player owner){
		this.position = new Position(spawnPos);
		this.owner = owner;
	}
	
	@Override
	public Position getPosition() {
		return position;
	}


	@Override
	public IPlayer getOwner() {
		return owner;
	}
	

	
}
