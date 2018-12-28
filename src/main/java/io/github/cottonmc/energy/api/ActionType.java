package io.github.cottonmc.energy.api;

/**
 * Represents whether an action is simulated or performed, allowing objects to report what *would* happen if a method
 * was called.
 */
public enum ActionType {
	/** Actually perform the action, which may affect the internal state of the object. */
	PERFORM,
	
	/** Predict what would be returned if the action was performed, but do not affect the internal state of the object.
	 * 
	 * <p>Any such simulation should be considered invalid at the end of the tick or if the object is asked
	 * to PERFORM an action.
	 */
	SIMULATE;
}