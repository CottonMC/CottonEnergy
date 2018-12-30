package io.github.cottonmc.energy.api;

import javax.annotation.Nonnull;

public interface EnergyComponent {

    /**
     * @return the maximum amount of energy the storage can hold.
     */
    int getMaxEnergy();

    /**
     * @return the current amount of energy the storage is holding.
     */
    int getCurrentEnergy();

    /**
     * Find out whether EnergyPackets can be inserted from a given Direction.
     * 
     * @return whether energy can be inserted.
     */
    boolean canInsertEnergy();

    /**
     * Attempt to insert energy.
     *
     * @param type          the kind of energy to insert.
     * @param amount        the amount of energy to insert.
     * @param actionType    whether to SIMULATE or PERFORM insertion.
     * @return the amount of leftover energy, or 0 if the insertion was completely successful.
     */
    @Nonnull
    int insertEnergy(EnergyType type, int amount, ActionType actionType);

    /**
     * Find out whether EnergyPackets can be extracted from a given Direction.
     * 
     * @param extractingFrom the side from which to extract, from the perspective of the object being extracted from.
     * @return whether energy can be extracted from this direction.
     */
    boolean canExtractEnergy();

    /**
     * Attempt to extract an energy packet on an all-or-nothing basis.
     *
     * @param extractingFrom the side from which to extract, from the perspective of the object being extracted from.
     * @param energyType     what type of energy to extract.
     * @param amount         the amount of energy to extract.
     * @param actionType     whether to SIMULATE or PERFORM extraction.
     * @return The amount of energy actually extracted, or zero if none was extracted.
     */
    @Nonnull
    int extractEnergy(EnergyType energyType, int amount, ActionType actionType);
}