package projectrts.model.entities.interfaces;
/**
 * An interface for Abilities which build structures
 * @author Jakob Svensson
 *
 */
public interface IBuildStructureAbility {
	public float getSizeOfBuilding();
	
	// TODO Jakob: Set nodes unoccupied if construction of a building is aborted!
}
