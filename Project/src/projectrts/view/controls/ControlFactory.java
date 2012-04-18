package projectrts.view.controls;

import java.util.HashMap;

import projectrts.model.core.entities.IEntity;

// TODO Markus: ADD JAVADOC
public enum ControlFactory {INSTANCE;
	
	
	private HashMap<String, AbstractControl> controlMap = new HashMap<String, AbstractControl>();

	public void registerControl(String controlType, AbstractControl control) {
		controlMap.put(controlType, control);
	}

	public AbstractControl createControl(String controlType, IEntity entity) {
		return controlMap.get(controlType).createControl(entity);
	}
}
