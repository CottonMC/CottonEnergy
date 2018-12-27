package io.github.cottonmc.energy.api;

import io.github.cottonmc.energy.event.PowerGenEvent;
import io.github.cottonmc.energy.util.ContainerInteraction;
import io.github.cottonmc.energy.util.PacketTier;
import net.fabricmc.fabric.util.HandlerArray;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.Direction;

import java.util.function.Consumer;

public interface EnergyStorage {

	/**
	 * @return the maximum amount of energy your storage can hold.
	 */
	int getMaxCapacity();

	/**
	 * @return the current amount of energy your storage is holding.
	 */
	int getCurrentCharge();

	/**
	 * @return what tier of energy packet your storage can accept.
	 */
	PacketTier getPacketTier();

	/**
	 * @param insertingFrom the side energy is being inserted from.
	 * @param size the size of packet that is being inserted.
	 * @return whether energy can be inserted from this direction and at this packet size.
	 */
	boolean canInsertEnergy(Direction insertingFrom, PacketTier size);

	/**
	 * @param extractingFrom the side energy is being extracted from.
	 * @param size the size of packet that is being extracted.
	 * @return whether energy can be extracted from this direction and at this packet size.
	 */
	boolean canExtractEnergy(Direction extractingFrom, PacketTier size);

	/**
	 * @param amount how much energy to be generated/
	 * @return whether the machine has the capacity for generating that amount of energy.
	 */
	default boolean canGenerateEnergy(int amount) {
		return (getMaxCapacity() - getCurrentCharge()) >= amount;
	}

	/**
	 * Attempt to insert an energy packet on an all-or-nothing basis.
	 *
	 * @param insertingFrom the side from which to insert.
	 * @param packetSize what size of packet to insert.
	 * @param interaction whether to SIMULATE or EXECUTE insertion.
	 * @return whether the insertion was/would be successful.
	 */
	default boolean tryInsertPacket(Direction insertingFrom, PacketTier packetSize, ContainerInteraction interaction) {
		if (canInsertEnergy(insertingFrom, packetSize)) {
			if (interaction == ContainerInteraction.EXECUTE) insertPacket(insertingFrom, packetSize);
			return true;
		}
		return false;
	}

	/**
	 * Attempt to extract an energy packet on an all-or-nothing basis.
	 *
	 * @param extractingFrom the side from which to extract.
	 * @param packetSize what size of packet to extract.
	 * @param interaction whether to SIMULATE or EXECUTE extraction.
	 * @return whether the extraction was/would be successful.
	 */
	default boolean tryExtractPacket(Direction extractingFrom, PacketTier packetSize, ContainerInteraction interaction) {
		if (canInsertEnergy(extractingFrom, packetSize)) {
			if (interaction == ContainerInteraction.EXECUTE) extractPacket(extractingFrom, packetSize);
			return true;
		}
		return false;
	}

	/**
	 * Internal energy-insertion mechanism, used by tryInsertPacket.
	 * This will always insert energy, and may cause an overflow.
	 * Don't use this unless you know what you're doing.
	 *
	 * @param insertingFrom the side from which to insert.
	 * @param packetSize what size of packet to insert.
	 */
	void insertPacket(Direction insertingFrom, PacketTier packetSize);

	/**
	 * Internal energy-extraction mechanism, used by tryExtractPacket.
	 * This will always extract energy, and may cause an underflow.
	 * Don't use this unless you know what you're doing.
	 *
	 *
	 * @param extractingFrom the side from which to extract.
	 * @param packetSize what size of packet to extract.
	 */
	void extractPacket(Direction extractingFrom, PacketTier packetSize);

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
