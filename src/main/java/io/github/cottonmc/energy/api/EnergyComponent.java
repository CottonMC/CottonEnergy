package io.github.cottonmc.energy.api;

import javax.annotation.Nonnull;

import io.github.prospector.silk.util.ActionType;

import io.github.cottonmc.ecs.api.Component;

public interface EnergyComponent extends Component {
	
	/**
	 * @return the maximum amount of energy the storage can hold.
	 */
	int getMaxEnergy();
	
	/**
	 * @return the current amount of energy the storage is holding.
	 */
	int getCurrentEnergy();
	
	/**
	 * Find out whether EnergyPackets can be inserted.
	 * @return whether energy can be inserted.
	 */
	boolean canInsertEnergy();
	
	/**
	 * Attempt to insert energy.
	 *
	 * @param amount        the amount of energy to insert.
	 * @param actionType    whether to SIMULATE or PERFORM insertion.
	 * @return the amount of leftover energy, or 0 if the insertion was completely successful.
	 */
	@Nonnull
	int insertEnergy(int amount, ActionType actionType);
	
	/**
	 * Find out whether energy can be extracted.
	 * 
	 * @return whether energy can be extracted.
	 */
	boolean canExtractEnergy();
	
	/**
	 * Attempt to extract energy.
	 *
	 * @param amount         the amount of energy to extract.
	 * @param actionType     whether to SIMULATE or PERFORM extraction.
	 * @return The amount of energy actually extracted, or zero if none was extracted.
	 */
	@Nonnull
	int extractEnergy(int amount, ActionType actionType);
}