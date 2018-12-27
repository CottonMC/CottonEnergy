package io.github.cottonmc.energy.util;

public enum ContainerInteraction {
	/**
	 * @value SIMULATE: pretend to interact and return what would be returned if actually attempted
	 * @value EXECUTE: actually attempt interaction, affecting the container
	 */
	EXECUTE, SIMULATE
}