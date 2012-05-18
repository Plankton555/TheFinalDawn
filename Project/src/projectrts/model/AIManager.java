package projectrts.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import projectrts.model.abilities.AbilityManager;
import projectrts.model.entities.EntityManager;
import projectrts.model.entities.Player;
import projectrts.model.entities.PlayerControlledEntity;

// TODO Markus: ADD JAVADOC!
class AIManager implements PropertyChangeListener{
	private final AbilityManager abilityManager;
	private final StrategicAI stratAI;
	private final List<MicroAI> microAIs = new ArrayList<MicroAI>();
	
	public AIManager(Player aiPlayer, AbilityManager abilityManager) {
		this.abilityManager = abilityManager;
		stratAI = new StrategicAI(aiPlayer, abilityManager);
		EntityManager.INSTANCE.addListener(this);
	}
	
	public void update(float tpf) {
		for (MicroAI mAI : microAIs) {
			mAI.update(tpf);
		}
		
		stratAI.update(tpf);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getOldValue() instanceof PlayerControlledEntity) {
			for(int i = 0; i < microAIs.size(); i++) {
				if(microAIs.get(i).getEntity().equals(evt.getOldValue())) {
					microAIs.remove(i);
				}
			}
		}
		
		if(evt.getNewValue() instanceof PlayerControlledEntity) {
			microAIs.add(new MicroAI((PlayerControlledEntity)evt.getNewValue(), abilityManager));
		}

	}

}
