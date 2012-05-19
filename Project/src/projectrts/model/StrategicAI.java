package projectrts.model;

import java.util.List;

import projectrts.model.abilities.AbilityManager;
import projectrts.model.abilities.AttackAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.entities.Player;
import projectrts.model.entities.AbstractPlayerControlledEntity;

// TODO Markus: ADD JAVADOC!!!
class StrategicAI {
	private List<IPlayerControlledEntity> entities;
	private static final float CHECK_INTERVAL = 0.5f;
	private float cooldownRemaining = 0;
	private final Player aiPlayer;
	private final AbilityManager abilityManager;
	private final EntityManager entityManager;

	// TODO Markus: Add javadoc
	public StrategicAI(Player aiPlayer, AbilityManager abilityManager) {
		this.aiPlayer = aiPlayer;
		this.abilityManager = abilityManager;
		this.entityManager = EntityManager.INSTANCE;
	}

	// TODO Markus: Add javadoc
	public void update(float tpf) {
		if (cooldownRemaining <= 0) {
			entities = entityManager.getEntitiesOfPlayer(aiPlayer);
			for(IPlayerControlledEntity entity : entities) {
				if(entity instanceof AbstractPlayerControlledEntity) {
					AbstractPlayerControlledEntity pce = (AbstractPlayerControlledEntity) entity;
					if(pce.getState() == AbstractPlayerControlledEntity.State.IDLE && 
							entityManager.getClosestEnemyStructure(pce) != null) {
						abilityManager.doAbility(AttackAbility.class.getSimpleName(), 
								entityManager.getClosestEnemyStructure(pce).getPosition(), entity);
					}
				}
			}
			cooldownRemaining = CHECK_INTERVAL;
		} else {
			cooldownRemaining -= tpf;
		}
	}
}
