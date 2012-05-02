package projectrts.model.abilities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projectrts.model.entities.AbstractUnit;
import projectrts.model.entities.Barracks;
import projectrts.model.entities.EntityFactory;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Headquarter;
import projectrts.model.entities.IEntity;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.PlayerControlledEntity.State;
import projectrts.model.entities.Warrior;
import projectrts.model.entities.Worker;
import projectrts.model.utils.Position;
import projectrts.model.world.INode;
import projectrts.model.world.World;

public class AbilityManager implements PropertyChangeListener, IAbilityManager {

	private HashMap<String, ArrayList<AbstractAbility>> abilityReferenceMap = new HashMap<String, ArrayList<AbstractAbility>>();
	private HashMap<Integer, ArrayList<AbstractAbility>> abilityListsMap = new HashMap<Integer, ArrayList<AbstractAbility>>();
	
	static {
		try
		{
			// Initialize the ability classes.
			Class.forName(AttackAbility.class.getName());
			Class.forName(BuildBarracksAbility.class.getName());
			Class.forName(BuildWallAbility.class.getName());	
			Class.forName(DeliverResourceAbility.class.getName());
			Class.forName(GatherResourceAbility.class.getName());
			Class.forName(MineResourceAbility.class.getName());
			Class.forName(MoveAbility.class.getName());
			Class.forName(OffensiveSpellAbility.class.getName());
			Class.forName(TrainWorkerAbility.class.getName());
			Class.forName(TrainWarriorAbility.class.getName());
						
		}
		catch (ClassNotFoundException any)
		{
			any.printStackTrace();
		}
    }
	
	public AbilityManager() {
		initializeAbilityLists();
		EntityManager.getInstance().addListener(this);
	}
	
	private void initializeAbilityLists() {
		
		
		//Worker
		ArrayList<AbstractAbility> workerAbilities = new ArrayList<AbstractAbility>();
		PlayerControlledEntity worker = EntityFactory.INSTANCE.createPCE(Worker.class.getSimpleName(), null, new Position(-1, -1));
		MoveAbility workerMove = (MoveAbility) AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName(), worker);
		workerAbilities.add(AbilityFactory.INSTANCE.createMAbility(AttackAbility.class.getSimpleName(), worker, workerMove));
		workerAbilities.add(AbilityFactory.INSTANCE.createMAbility(GatherResourceAbility.class.getSimpleName(), worker, workerMove));
		workerAbilities.add(AbilityFactory.INSTANCE.createMAbility(BuildBarracksAbility.class.getSimpleName(), worker, workerMove));
		workerAbilities.add(AbilityFactory.INSTANCE.createMAbility(BuildWallAbility.class.getSimpleName(), worker, workerMove));
		abilityReferenceMap.put(Worker.class.getSimpleName(), workerAbilities);
		
		//Warrior
		PlayerControlledEntity warrior = EntityFactory.INSTANCE.createPCE(Warrior.class.getSimpleName(), null, new Position(-1, -1));
		MoveAbility warriorMove = (MoveAbility) AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName(), worker);
		ArrayList<AbstractAbility> warriorAbilities = new ArrayList<AbstractAbility>();
		warriorAbilities.add(AbilityFactory.INSTANCE.createMAbility(AttackAbility.class.getSimpleName(), warrior, warriorMove));
		abilityReferenceMap.put(Warrior.class.getSimpleName(), warriorAbilities);
		
		//Headquarter
		PlayerControlledEntity headquarter = EntityFactory.INSTANCE.createPCE(Headquarter.class.getSimpleName(), null, new Position(-1, -1));
		ArrayList<AbstractAbility> headquarterAbilities = new ArrayList<AbstractAbility>();
		headquarterAbilities.add(AbilityFactory.INSTANCE.createAbility(TrainWorkerAbility.class.getSimpleName(), headquarter));
		abilityReferenceMap.put(Headquarter.class.getSimpleName(), headquarterAbilities);
		
		//Barracks
		PlayerControlledEntity barracks = EntityFactory.INSTANCE.createPCE(Barracks.class.getSimpleName(), null, new Position(-1, -1));
		ArrayList<AbstractAbility> barracksAbilities = new ArrayList<AbstractAbility>();
		barracksAbilities.add(AbilityFactory.INSTANCE.createAbility(TrainWarriorAbility.class.getSimpleName(), barracks));
		abilityReferenceMap.put(Barracks.class.getSimpleName(), barracksAbilities);
		
		//Wall
		//None so far
	}

	public void update(float tpf) {
		List<IEntity> entities = EntityManager.getInstance().getAllEntities();
		for(IEntity entity : entities) {
			if(entity instanceof PlayerControlledEntity) {
				PlayerControlledEntity pce = (PlayerControlledEntity) entity;
				if(pce != null) {
					if(!pce.isDead() && abilityListsMap.get(pce.getEntityID()) != null) {
							if(!abilityListsMap.get(pce.getEntityID()).isEmpty()) {
							ArrayList<AbstractAbility> abilities = abilityListsMap.get(pce.getEntityID());
							pce.setState(State.IDLE);
							for(AbstractAbility ability: abilities){
								ability.update(tpf);
								if(ability.isActive()) {
									pce.setState(State.BUSY);
								}
							}
						}
					}
				}
			}
		}

	}

	/* (non-Javadoc)
	 * @see projectrts.model.abilities.IAbilityManager#getAbilities(projectrts.model.entities.PlayerControlledEntity)
	 */
	@Override
	public List<IAbility> getAbilities(IPlayerControlledEntity entity) {
		List<IAbility> copy = new ArrayList<IAbility>();
		ArrayList<AbstractAbility> abilities = abilityReferenceMap.get(entity.getClass().getSimpleName());
		for(IAbility ability: abilities){
			copy.add(ability);
		}
		return copy;
	}
	
	/* (non-Javadoc)
	 * @see projectrts.model.abilities.IAbilityManager#doAbility(java.lang.String, projectrts.model.utils.Position, projectrts.model.entities.PlayerControlledEntity)
	 */
	@Override
	public void doAbility(String ability, Position pos, IPlayerControlledEntity entity) {
		ArrayList<AbstractAbility> abilities = abilityListsMap.get(entity.getEntityID());
		AbstractAbility toBeUsedAbility = null;
		if(abilities != null) {
			if(!abilities.isEmpty()) {
				for(AbstractAbility ownAbility: abilities){
					ownAbility.setActive(false); //Make sure that only one ability can be active at once
					ownAbility.setFinished(true);
					if(ability.equals(ownAbility.getClass().getSimpleName())){
						toBeUsedAbility = ownAbility;
					}
				}
				if(toBeUsedAbility != null) {
					toBeUsedAbility.useAbility(pos);
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see projectrts.model.abilities.IAbilityManager#useAbilitySelected(java.lang.String, projectrts.model.utils.Position)
	 */
	@Override
	public void useAbilitySelected(String ability, Position p){
		for(IEntity entity : EntityManager.getInstance().getSelectedEntities()){
			if(entity instanceof PlayerControlledEntity){
				PlayerControlledEntity pce = (PlayerControlledEntity) entity;
				doAbility(ability, p, pce);
			}
		}
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getNewValue() instanceof PlayerControlledEntity) {
			PlayerControlledEntity pce = (PlayerControlledEntity)evt.getNewValue();
			ArrayList<AbstractAbility> abilitiesReferenceList = abilityReferenceMap.get(pce.getClass().getSimpleName());
			ArrayList<AbstractAbility> abilities = new ArrayList<AbstractAbility>();
			if(evt.getNewValue() instanceof AbstractUnit) {
				MoveAbility moveAbility = (MoveAbility) AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName(), pce);
				abilities.add(moveAbility);
				for(AbstractAbility ability : abilitiesReferenceList) {
					if(ability instanceof IUsingMoveAbility) {
						abilities.add(AbilityFactory.INSTANCE.createMAbility(ability.getClass().getSimpleName(), pce, moveAbility));
					} else {
						abilities.add(AbilityFactory.INSTANCE.createAbility(ability.getClass().getSimpleName(), pce));
					}
				}
			} else {
				for(AbstractAbility ability : abilitiesReferenceList) {
					abilities.add(AbilityFactory.INSTANCE.createAbility(ability.getClass().getSimpleName(), pce));
				}
			}
			
			abilityListsMap.put(pce.getEntityID(), abilities);
		}
		
		if(evt.getOldValue() instanceof PlayerControlledEntity) {
			PlayerControlledEntity pce = (PlayerControlledEntity) evt.getOldValue();
			ArrayList<AbstractAbility> abilities = abilityListsMap.get(pce.getEntityID());
			INode occupiedNode = World.getInstance().getNodeAt(pce.getPosition());
			for(AbstractAbility ability: abilities){
				ability.setFinished(true);
				if (ability instanceof MoveAbility)
				{
					MoveAbility mAbility = (MoveAbility) ability;
					occupiedNode = mAbility.getOccupiedNode();
				}
			}
			World.getInstance().setNodesOccupied(occupiedNode, pce.getSize(), 0);
			
			abilityListsMap.remove(pce.getEntityID());
		}
	}
}
