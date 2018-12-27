package io.github.cottonmc.energy.api;

import io.github.cottonmc.energy.event.PowerGenEvent;
import io.github.cottonmc.energy.util.PacketTier;
import net.fabricmc.fabric.util.HandlerArray;
import net.minecraft.block.entity.BlockEntity;

import java.util.function.Consumer;

public interface EnergyStorage {

    /**
     * @return the maximum amount of energy your storage can hold.
     */
    int getMaxCapacity();

    /**
     * @return the current amount of energy your storage is holding.
     */
    int getCurrentEnergy();

    /**
     * @return what tier of energy packet your storage can accept.
     */
    PacketTier getPacketTier();

    /**
     * @param amount how much energy to be generated/
     * @return whether the machine has the capacity for generating that amount of energy.
     */
    default boolean canGenerateEnergy(int amount) {
        return (getMaxCapacity() - getCurrentEnergy()) >= amount;
    }

    /**
     * Generate an amount of power, without needing to worry about packet sizes.
     * DO NOT OVERRIDE WITHOUT CALLING THE EVENT HANDLER. DOING SO CAN AND WILL BREAK THINGS.
     *
     * @param amount The amount of energy to generate.
     */
    default void generateEnergy(int amount) {
        if (canGenerateEnergy(amount)) {
            for (Consumer<BlockEntity> handler : ((HandlerArray<Consumer<BlockEntity>>) PowerGenEvent.GENERATE_POWER).getBackingArray()) {
                handler.accept((BlockEntity) this);
            }
        }
    }
}