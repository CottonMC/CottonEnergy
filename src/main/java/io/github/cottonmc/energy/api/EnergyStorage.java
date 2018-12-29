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
     * @return the primary energy type to display in guis and probes.
     */
    EnergyType getPrimaryEnergyType();

    /**
     * @param type the type of energy that querying for connection.
     * @return     whether that type of energy can interact with the storage.
     */
    boolean canConnectTo(EnergyType type);

}