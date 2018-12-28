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
     * @return the highest tier of energy this device is capable of working with under any circumstances.
     */
    PacketTier getPacketTier();

}