package io.github.cottonmc.energy.api;

import javax.annotation.Nonnull;

import alexiil.mc.lib.attributes.Attributes;
import alexiil.mc.lib.attributes.DefaultedAttribute;
import io.github.prospector.silk.util.ActionType;

public interface EnergyComponent {
	public static final EnergyComponent EMPTY_ENERGY = new EnergyComponent() {
		@Override
		public int getMaxEnergy() { return 0; }
		@Override
		public int getCurrentEnergy() { return 0; }
		@Override
		public boolean canInsertEnergy() { return false; }
		@Override
		public int insertEnergy(EnergyType type, int amount, ActionType actionType) { return amount; }
		@Override
		public boolean canExtractEnergy() { return false; }
		@Override
		public int extractEnergy(EnergyType type, int amount, ActionType actionType) { return 0; }
		@Override
		public EnergyType getPreferredType() { return DefaultEnergyTypes.LOW_VOLTAGE; }
	};
	
	public static final DefaultedAttribute<EnergyComponent> ENERGY_COMPONENT = Attributes.createDefaulted(EnergyComponent.class, EnergyComponent.EMPTY_ENERGY);
	
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
	int insertEnergy(EnergyType type, int amount, ActionType actionType);
	
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
	int extractEnergy(EnergyType type, int amount, ActionType actionType);
	
	/**
	 * Gets the typical kind of energy this Component works with.
	 * @return
	 */
	@Nonnull
	EnergyType getPreferredType();
	
	/**
	 * Indicate to this Component that its holder has been exposed to extremely powerful and sudden
	 * electromagnetic radiation. If this Component stores electrical energy, it should at least
	 * remove 'strength' energy from its internal buffer, and can optionally trigger volatile
	 * behavior or shut the machine down temporarily. However, specially designed induction coils
	 * might actually charge if strength is below their capacity.
	 * @param strength The strength of the received electromagnetic pulse.
	 */
	default void emp(int strength) {
		extractEnergy(getPreferredType(), strength, ActionType.PERFORM);
	}
}