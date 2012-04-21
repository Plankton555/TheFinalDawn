package projectrts.model.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2d;

import projectrts.model.player.IPlayer;
import projectrts.model.player.Player;
import projectrts.model.utils.ModelUtils;
import projectrts.model.utils.Position;

/**
 * The singleton entity manager.
 * @author Bjorn Persson Mattsson, Modified by Markus Ekström
 *
 */
public class EntityManager implements IEntityManager{

	private static EntityManager instance = new EntityManager();
	private List<AbstractEntity> allEntities = new ArrayList<AbstractEntity>();
	private List<AbstractEntity> entitiesAddQueue = new ArrayList<AbstractEntity>();
	private List<AbstractEntity> entitiesRemoveQueue = new ArrayList<AbstractEntity>();
	private List<PlayerControlledEntity> selectedEntities = new ArrayList<PlayerControlledEntity>();
	private int idCounter = 0;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	
	/**
	 * @return The instance of this class.
	 */
	public static EntityManager getInstance()
	{
		return instance;
	}
	
	/**
	 * Updates all entities.
	 * @param tpf
	 */
	public void update(float tpf)
	{	
		for (AbstractEntity e : allEntities)
		{
			e.update(tpf);
		}
		
		for (AbstractEntity e : entitiesAddQueue){
			allEntities.add(e);
			pcs.firePropertyChange("entityCreated", null, e);
			
			
		}
		
		for (AbstractEntity e : entitiesRemoveQueue) {
			for (int i = 0; i < allEntities.size(); i++) {
				if (e.equals(allEntities.get(i))) {
					allEntities.remove(i);
					pcs.firePropertyChange("entityRemoved", e, null);
				}
			}
		}
		entitiesAddQueue.clear();
	}
	
	/**
	 * Returns a list of all entities close to the position.
	 * Returns all entities that can be seen from the circle with center in 'p'
	 * and the radius 'range'.
	 * @param entity The entity from which to check.
	 * @return List of all entities close to position.
	 */
	public List<AbstractEntity> getNearbyEntities(IPlayerControlledEntity entity)
	{
		// Preliminary method. May need to be changed to optimize.
		
		List<AbstractEntity> output = new ArrayList<AbstractEntity>();
		
		for (AbstractEntity e : allEntities)
		{
			Vector2d distance = new Vector2d(
					e.getPosition().getX()-entity.getPosition().getX(),
					e.getPosition().getY()-entity.getPosition().getY());
			if (distance.length() - (e.getSize()/2) <= entity.getSightRange())
			{
				output.add(e);
			}
		}
		return output;
	}
	
	/**
	 * Returns all entities that the provided player owns.
	 * @param player Player
	 * @return A list of all entities of player.
	 */
	@Override
	public List<IPlayerControlledEntity> getEntitiesOfPlayer(IPlayer player)
	{
		
		List<IPlayerControlledEntity> output = new ArrayList<IPlayerControlledEntity>();
		for (AbstractEntity e : allEntities)
		{
			if (e instanceof IPlayerControlledEntity)
			{
				IPlayerControlledEntity pce = (IPlayerControlledEntity)e;
				if (pce.getOwner().equals(player))
				{
					output.add(pce);
				}
			}
		}
		return output;
	}
	
	/**
	 * @return All entities.
	 */
	public List<IEntity> getAllEntities()
	{
		List<IEntity> output = new ArrayList<IEntity>();
		for (IEntity e : allEntities)
		{
			output.add(e);
		}
		return output;
	}
	
	/**
	 * Adds a new non-player controlled entity to the EntityManager.
	 * 
	 * @param npce The class name of the npce as a string, e.g. "Rock".
	 * @param pos The position of the entity.
	 */
	public void addNewNPCE(String npce, Position pos)
	{
		NonPlayerControlledEntity newNPCE = EntityFactory.INSTANCE.createNPCE(npce, pos);
		entitiesAddQueue.add(newNPCE);
	}
	
	/**
	 * Adds a new player controlled entity to the EntityManager.
	 * 
	 * @param pce The class name of the npce as a string, e.g. "Worker".
	 * @param owner The player that shall have control over the new entity.
	 * @param pos The position of the entity.
	 */
	public void addNewPCE(String pce, Player owner, Position pos) {
		PlayerControlledEntity newPCE = EntityFactory.INSTANCE.createPCE(pce, owner, pos);
		entitiesAddQueue.add(newPCE);
	}
	

	/**
	 * @return New entity ID.
	 */
	public int requestNewEntityID() {
		idCounter++;
		return idCounter;
	}

	public void removeEntity(AbstractEntity entity) {
		entitiesRemoveQueue.add(entity);
	}
	
	// TODO Markus: Possible duplicated code
	public PlayerControlledEntity getPCEAtPosition(Position pos){
		List<IEntity> entities = EntityManager.getInstance().getAllEntities();
		for(IEntity entity: entities){
			if(entity instanceof PlayerControlledEntity){
				
				float unitSize = entity.getSize();
				Position unitPos = entity.getPosition();
				
				//If the point is within the area of the unit
				if(ModelUtils.INSTANCE.isWithin(pos.getX(), unitPos.getX()-unitSize/2, unitPos.getX()+unitSize/2)
						&& ModelUtils.INSTANCE.isWithin(pos.getY(), unitPos.getY()-unitSize/2, unitPos.getY() + unitSize/2)){
					PlayerControlledEntity pcEntity  = (PlayerControlledEntity) entity; 
					return pcEntity;
					
				}
			}
		}
		return null;
		
	}
	
	/**
	 * If there exists a PCE that the passed player owns at the passed position
	 * it is returned, otherwise this method returns null.
	 * @param pos The position to check.
	 * @param player The hopeful owner.
	 * @return A PCE if there is one on the position that the player owns, otherwise null.
	 */
	public PlayerControlledEntity getPCEAtPosition(Position pos, IPlayer player) {
		if(getPCEAtPosition(pos) != null) {
			if( getPCEAtPosition(pos).getOwner().equals(player)) {
				return getPCEAtPosition(pos);
			}
		}
		return null;
	}
	
	//TODO Anyone: Extraxt common code from getPlayerControlledEntityAtPosition and this method
	public NonPlayerControlledEntity getNonPlayerControlledEntity (Position pos){
		List<IEntity> entities = EntityManager.getInstance().getAllEntities();
		for(IEntity entity: entities){
			if(entity instanceof NonPlayerControlledEntity){
				
				float unitSize = entity.getSize();
				Position unitPos = entity.getPosition();
				
				//If the point is within the area of the unit
				if(ModelUtils.INSTANCE.isWithin(pos.getX(), unitPos.getX()-unitSize/2, unitPos.getX()+unitSize/2)
						&& ModelUtils.INSTANCE.isWithin(pos.getY(), unitPos.getY()-unitSize/2, unitPos.getY() + unitSize/2)){
					NonPlayerControlledEntity npcEntity  = (NonPlayerControlledEntity) entity; 
					return npcEntity;
					
				}
			}
		}
		return null;
	}
	
	// TODO Markus: add support for ai selecting
	

	/**
	 * Selected entities at the specified position
	 * @param pos the position where entities should be selected
	 * @param owner the player that selected the entities
	 */
	public void select(Position pos, IPlayer owner) {
		//TODO Anyone: Add support for selection of multiple units and enemy units.
		selectedEntities.clear();
		PlayerControlledEntity entity = getPCEAtPosition(pos, owner);
		if(entity!=null){ //No entity is at that position
			selectedEntities.add(entity);
		}
	}
	
	/**
	 * Uses the abilities of the seleced entities
	 * @param ability the ability to be used
	 * @param p the position to use the ability at
	 */
	public void useAbilitySelected(String ability, Position p){
		for(IEntity entity: selectedEntities){
			if(entity instanceof PlayerControlledEntity){
				PlayerControlledEntity unit = (PlayerControlledEntity) entity;
				unit.doAbility(ability, p);
			}
		}
	}
	
	/**
	 * Returns the selected entities
	 * @return A list with the selected entities
	 */
	public List<IEntity> getSelectedEntities() {
		List<IEntity> entities = new ArrayList<IEntity>();
		for(IEntity entity: selectedEntities){
			entities.add(entity);
		}
		return entities;
	}
	
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
}
