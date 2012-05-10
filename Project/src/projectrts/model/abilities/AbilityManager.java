package projectrts.model.abilities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projectrts.model.entities.AbstractUnit;
import projectrts.model.entities.Archer;
import projectrts.model.entities.Barracks;
import projectrts.model.entities.EntityFactory;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Headquarter;
import projectrts.model.entities.IEntity;
import projectrts.model.entities.IPlayer;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.entities.PlayerControlledEntity;
import projectrts.model.entities.PlayerControlledEntity.State;
import projectrts.model.entities.Warrior;
import projectrts.model.entities.Worker;
import projectrts.model.world.INode;
import projectrts.model.world.Position;
import projectrts.model.world.World;

/**
 * A class in charge of managing all the abilities in the game.
 * @author Markus Ekström
 *
 */
public class AbilityManager implements PropertyChangeListener, IAbilityManager {

	private Map<String, ArrayList<AbstractAbility>> abilityReferenceMap = new HashMap<String, ArrayList<AbstractAbility>>();
	private Map<Integer, ArrayList<AbstractAbility>> abilityListsMap = new HashMap<Integer, ArrayList<AbstractAbility>>();
	private PropertyChangeListener pcl;
	
	private static final String[] ABILITY_NAMES = new String[11];
	
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
			Class.forName(TrainWorkerAbility.class.getName());
			Class.forName(TrainWarriorAbility.class.getName());
			Class.forName(TrainArcherAbility.class.getName());
			Class.forName(BuildHeadquarterAbility.class.getName());
			
			//DON'T FORGET TO ADJUST THE ARRAY SIZE IF CREATING/DELETING ABILITIES
			ABILITY_NAMES[0] = AttackAbility.class.getSimpleName();
			ABILITY_NAMES[1] = BuildBarracksAbility.class.getSimpleName();
			ABILITY_NAMES[2] = BuildWallAbility.class.getSimpleName();
			ABILITY_NAMES[3] = DeliverResourceAbility.class.getSimpleName();
			ABILITY_NAMES[4] = GatherResourceAbility.class.getSimpleName();
			ABILITY_NAMES[5] = MineResourceAbility.class.getSimpleName();
			ABILITY_NAMES[6] = MoveAbility.class.getSimpleName();
			ABILITY_NAMES[7] = TrainWorkerAbility.class.getSimpleName();
			ABILITY_NAMES[8] = TrainWarriorAbility.class.getSimpleName();
			ABILITY_NAMES[9] = BuildHeadquarterAbility.class.getSimpleName();
			ABILITY_NAMES[10] = TrainArcherAbility.class.getSimpleName();
						
		}
		catch (ClassNotFoundException any)
		{
			any.printStackTrace();
		}
    }
	
	/**
	 * The constructor for the AbilityManager.
	 */
	public AbilityManager() {
		initializeAbilityLists();
		EntityManager.getInstance().addListener(this);
	}
	
	private void initializeAbilityLists() {
		
		
		//Worker
		ArrayList<AbstractAbility> workerAbilities = new ArrayList<AbstractAbility>();
		PlayerControlledEntity worker = EntityFactory.INSTANCE.createPCE(Worker.class.getSimpleName(), null, new Position(-1, -1));
		MoveAbility workerMove = (MoveAbility) AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName(), worker);
		workerAbilities.add(AbilityFactory.INSTANCE.createUsingMoveAbility(AttackAbility.class.getSimpleName(), worker, workerMove));
		workerAbilities.add(AbilityFactory.INSTANCE.createUsingMoveAbility(GatherResourceAbility.class.getSimpleName(), worker, workerMove));
		workerAbilities.add(AbilityFactory.INSTANCE.createUsingMoveAbility(BuildBarracksAbility.class.getSimpleName(), worker, workerMove));
		workerAbilities.add(AbilityFactory.INSTANCE.createUsingMoveAbility(BuildWallAbility.class.getSimpleName(), worker, workerMove));
		workerAbilities.add(AbilityFactory.INSTANCE.createUsingMoveAbility(BuildHeadquarterAbility.class.getSimpleName(), worker, workerMove));
		workerAbilities.add(workerMove);
		abilityReferenceMap.put(Worker.class.getSimpleName(), workerAbilities);
		
		//Warrior
		PlayerControlledEntity warrior = EntityFactory.INSTANCE.createPCE(Warrior.class.getSimpleName(), null, new Position(-1, -1));
		MoveAbility warriorMove = (MoveAbility) AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName(), worker);
		ArrayList<AbstractAbility> warriorAbilities = new ArrayList<AbstractAbility>();
		warriorAbilities.add(AbilityFactory.INSTANCE.createUsingMoveAbility(AttackAbility.class.getSimpleName(), warrior, warriorMove));
		warriorAbilities.add(warriorMove);
		abilityReferenceMap.put(Warrior.class.getSimpleName(), warriorAbilities);
		
		//Archer
		PlayerControlledEntity archer = EntityFactory.INSTANCE.createPCE(Archer.class.getSimpleName(), null, new Position(-1, -1));
		MoveAbility archerMove = (MoveAbility) AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName(), worker);
		ArrayList<AbstractAbility> archerAbilities = new ArrayList<AbstractAbility>();
		archerAbilities.add(AbilityFactory.INSTANCE.createUsingMoveAbility(AttackAbility.class.getSimpleName(), archer, archerMove));
		archerAbilities.add(archerMove);
		abilityReferenceMap.put(Archer.class.getSimpleName(), archerAbilities);
		
		//Headquarter
		PlayerControlledEntity headquarter = EntityFactory.INSTANCE.createPCE(Headquarter.class.getSimpleName(), null, new Position(-1, -1));
		ArrayList<AbstractAbility> headquarterAbilities = new ArrayList<AbstractAbility>();
		headquarterAbilities.add(AbilityFactory.INSTANCE.createAbility(TrainWorkerAbility.class.getSimpleName(), headquarter));
		abilityReferenceMap.put(Headquarter.class.getSimpleName(), headquarterAbilities);
		
		//Barracks
		PlayerControlledEntity barracks = EntityFactory.INSTANCE.createPCE(Barracks.class.getSimpleName(), null, new Position(-1, -1));
		ArrayList<AbstractAbility> barracksAbilities = new ArrayList<AbstractAbility>();
		barracksAbilities.add(AbilityFactory.INSTANCE.createAbility(TrainWarriorAbility.class.getSimpleName(), barracks));
		barracksAbilities.add(AbilityFactory.INSTANCE.createAbility(TrainArcherAbility.class.getSimpleName(), barracks));
		abilityReferenceMap.put(Barracks.class.getSimpleName(), barracksAbilities);
		
		//Wall
		//None so far
	}

	/**
	 * Updates all abilities.
	 * @param tpf The time passed since the last frame.
	 */
	public void update(float tpf) {
		List<IEntity> entities = EntityManager.getInstance().getAllEntities();
		for(IEntity entity : entities) {
			if(entity instanceof PlayerControlledEntity) {
				PlayerControlledEntity pce = (PlayerControlledEntity) entity;
				if(pce != null) {
					// TODO Markus: PMD: These nested if statements could be combined
					if(!pce.isDead() && abilityListsMap.get(pce.getEntityID()) != null) {
						// TODO Markus: PMD: These nested if statements could be combined
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

	@Override
	public List<IAbility> getAbilities(IPlayerControlledEntity entity) {
		List<IAbility> copy = new ArrayList<IAbility>();
		ArrayList<AbstractAbility> abilities = abilityReferenceMap.get(entity.getClass().getSimpleName());
		if(abilities != null) {
			// TODO Markus: PMD: These nested if statements could be combined
			if(!abilities.isEmpty()) {
				for(IAbility ability: abilities){
					copy.add(ability);
				}
			}
		}
		
		return copy;
	}
	
	@Override
	public void doAbility(String ability, Position pos, IPlayerControlledEntity entity) {
		ArrayList<AbstractAbility> abilities = abilityListsMap.get(entity.getEntityID());
		AbstractAbility toBeUsedAbility = null;
		if(abilities != null) {
			// TODO Markus: PMD: These nested if statements could be combined
			if(!abilities.isEmpty()) {
				for(AbstractAbility ownAbility: abilities){
					if(ownAbility.isActive()){
						ownAbility.abortAbility(); //Make sure that only one ability can be active at once
					}
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
	
	@Override
	public void useAbilitySelected(String ability, Position p, IPlayer owner){
		for(IPlayerControlledEntity pce : EntityManager.getInstance().getSelectedEntitiesOfPlayer(owner)){
			doAbility(ability, p, pce);
		}
	}


	@Override
	// TODO Markus: PMD: The method 'propertyChange' has a Cyclomatic Complexity of 11.
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getNewValue() instanceof PlayerControlledEntity) {
			PlayerControlledEntity pce = (PlayerControlledEntity)evt.getNewValue();
			ArrayList<AbstractAbility> abilitiesReferenceList = abilityReferenceMap.get(pce.getClass().getSimpleName());
			ArrayList<AbstractAbility> abilities = new ArrayList<AbstractAbility>();
			if(abilitiesReferenceList != null) {
				// TODO Markus: PMD: These nested if statements could be combined
				if(!abilitiesReferenceList.isEmpty()) {
					if(evt.getNewValue() instanceof AbstractUnit) {
						MoveAbility moveAbility = (MoveAbility) AbilityFactory.INSTANCE.createAbility(MoveAbility.class.getSimpleName(), pce);
						for(AbstractAbility ability : abilitiesReferenceList) {
							if(ability instanceof IUsingMoveAbility) {
								abilities.add(AbilityFactory.INSTANCE.createUsingMoveAbility(ability.getClass().getSimpleName(), pce, moveAbility));
							} else {
								abilities.add(AbilityFactory.INSTANCE.createAbility(ability.getClass().getSimpleName(), pce));
							}
							abilities.get(abilities.size()-1).addListener(pcl);
						}
						abilities.add(moveAbility);
					} else {
						for(AbstractAbility ability : abilitiesReferenceList) {
							abilities.add(AbilityFactory.INSTANCE.createAbility(ability.getClass().getSimpleName(), pce));
							abilities.get(abilities.size()-1).addListener(pcl);
						}
					}
				}
			}
			
			abilityListsMap.put(pce.getEntityID(), abilities);
		}
		
		if(evt.getOldValue() instanceof PlayerControlledEntity) {
			PlayerControlledEntity pce = (PlayerControlledEntity) evt.getOldValue();
			ArrayList<AbstractAbility> abilities = abilityListsMap.get(pce.getEntityID());
			INode occupiedNode = World.getInstance().getNodeAt(pce.getPosition());
			for(AbstractAbility ability: abilities){
				ability.abortAbility();
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

	@Override
	public String[] getExistingAbilityNames() {
		String[] copy = new String[ABILITY_NAMES.length];
		// TODO Markus: PMD: System.arraycopy is more efficient
		for(int i = 0; i < ABILITY_NAMES.length; i++) {
			copy[i] = ABILITY_NAMES[i];
		}
		return copy;
	}
	
	public void setPropertyChangeLister(PropertyChangeListener pcl){
		this.pcl = pcl;
	}
	
	
}
