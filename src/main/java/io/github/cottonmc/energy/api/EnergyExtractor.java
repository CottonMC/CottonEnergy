package io.github.cottonmc.energy.api;

import io.github.cottonmc.energy.util.ContainerInteraction;
import io.github.cottonmc.energy.util.PacketTier;
import net.minecraft.util.math.Direction;

public interface EnergyExtractor {
    /**
     * Attempt to extract an energy packet on an all-or-nothing basis.
     *
     * @param extractingFrom the side from which to extract.
     * @param packetSize     what size of packet to extract.
     * @param interaction    whether to SIMULATE or EXECUTE extraction.
     * @return whether the extraction was/would be successful.
     */
    default boolean tryExtractPacket(Direction extractingFrom, PacketTier packetSize, ContainerInteraction interaction) {
        if (canExtractEnergy(extractingFrom, packetSize)) {
            if (interaction == ContainerInteraction.EXECUTE) extractPacket(extractingFrom, packetSize);
            return true;
        }
        return false;
    }

    /**
     * Internal energy-extraction mechanism, used by tryExtractPacket.
     * This will always extract energy, and may cause an underflow.
     * Don't use this unless you know what you're doing.
     *
     * @param extractingFrom the side from which to extract.
     * @param packetSize     what size of packet to extract.
     */
    void extractPacket(Direction extractingFrom, PacketTier packetSize);

    /**
     * @param extractingFrom the side energy is being extracted from.
     * @param size           the size of packet that is being extracted.
     * @return whether energy can be extracted from this direction and at this packet size.
     */
    boolean canExtractEnergy(Direction extractingFrom, PacketTier size);
}
