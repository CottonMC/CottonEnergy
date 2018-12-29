package io.github.cottonmc.energy.api;

public interface EnergyStorage extends EnergyProducer, EnergyConsumer {

    /**
     * @return the maximum amount of energy the storage can hold.
     */
    int getMaxEnergy();

    /**
     * @return the current amount of energy the storage is holding.
     */
    int getCurrentEnergy();

    /**
     * @return the primary type of energy this device is capable of working with under any circumstances.
     */
    EnergyType getPrimaryEnergyType();

    /**
     * @return any other energy types this device is capable of accepting.
     */
    EnergyType[] getSecondaryEnergyTypes();

}