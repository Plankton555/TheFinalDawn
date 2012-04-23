package projectrts.view.controls;

import java.util.HashMap;

import com.jme3.scene.control.AbstractControl;

import projectrts.model.entities.IEntity;
import projectrts.model.pathfinding.INode;

// TODO Markus: ADD JAVADOC
public enum ControlFactory {INSTANCE;
	
	
	private HashMap<String, AbstractControl> controlMap = new HashMap<String, AbstractControl>();

	public void registerControl(String controlType, AbstractControl control) {
		controlMap.put(controlType, control);
	}

	public AbstractControl createEntityControl(String controlType, IEntity entity) {
		return ((IEntityControl)controlMap.get(controlType)).createControl(entity);
	}

	public AbstractControl createNodeControl(String controlType, INode node) {
		return ((INodeControl)controlMap.get(controlType)).createControl(node);
	}
}
