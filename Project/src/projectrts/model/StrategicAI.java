package projectrts.model;

import java.util.List;

import projectrts.model.abilities.AbilityManager;
import projectrts.model.abilities.AttackAbility;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.IPlayerControlledEntity;
import projectrts.model.entities.Player;
import projectrts.model.entities.PlayerControlledEntity;

// TODO Markus: ADD JAVADOC!!!
class StrategicAI {
	private List<IPlayerControlledEntity> entities;
	// TODO Markus: PMD: Variables that are final and static should be in all caps.
	private static final float cooldownInterval = 0.5f;
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
			// TODO Markus: Save the EntityManager instance and use that instead of asking for it every time?
			entities = entityManager.getEntitiesOfPlayer(aiPlayer);
			for (IPlayerControlledEntity entity : entities) {
				if (entity instanceof PlayerControlledEntity) {
					PlayerControlledEntity pce = (PlayerControlledEntity) entity;
					if (pce.getState() == PlayerControlledEntity.State.IDLE
							&& entityManager.getClosestEnemyStructure(pce) != null) {
						abilityManager.doAbility(AttackAbility.class
								.getSimpleName(), entityManager
								.getClosestEnemyStructure(pce).getPosition(),
								entity);
					}
				}
			}
			cooldownRemaining = cooldownInterval;
		} else {
			cooldownRemaining -= tpf;
		}
	}
}