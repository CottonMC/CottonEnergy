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
     * @return any energy types this device is capable of accepting.
     * The first item in the array is seen as the "primary" energy type.
     */
    EnergyType[] getEnergyTypes();

}