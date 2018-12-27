package io.github.cottonmc.energy.api;

import io.github.cottonmc.energy.util.ContainerInteraction;
import io.github.cottonmc.energy.util.PacketTier;
import net.minecraft.util.math.Direction;

public interface EnergyConsumer {

    /**
     * @param insertingFrom the side energy is being inserted from.
     * @param size          the size of packet that is being inserted.
     * @return whether energy can be inserted from this direction and at this packet size.
     */
    boolean canInsertEnergy(Direction insertingFrom, PacketTier size);

    /**
     * Attempt to insert an energy packet on an all-or-nothing basis.
     *
     * @param insertingFrom the side from which to insert.
     * @param packetSize    what size of packet to insert.
     * @param interaction   whether to SIMULATE or EXECUTE insertion.
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
     * Internal energy-insertion mechanism, used by tryInsertPacket.
     * This will always insert energy, and may cause an overflow.
     * Don't use this unless you know what you're doing.
     *
     * @param insertingFrom the side from which to insert.
     * @param packetSize    what size of packet to insert.
     */
    void insertPacket(Direction insertingFrom, PacketTier packetSize);
}
