package io.github.cottonmc.energy.api;

import javax.annotation.Nonnull;

import net.minecraft.util.math.Direction;

public interface EnergyProducer {

    /**
     * Find out whether EnergyPackets can be extracted from a given Direction.
     * 
     * @param extractingFrom the side from which to extract, from the perspective of the object being extracted from.
     * @return whether energy can be extracted from this direction.
     */
    boolean canExtractEnergy(Direction extractingFrom);

    /**
     * Attempt to extract an energy packet on an all-or-nothing basis.
     *
     * @param extractingFrom the side from which to extract, from the perspective of the object being extracted from.
     * @param packetTier     what tier of packet to extract.
     * @param actionType     whether to SIMULATE or PERFORM extraction.
     * @return A new, full packet of the intended tier, or EMPTY_PACKET if energy cannot be extracted.
     */
    @Nonnull
    EnergyPacket extractEnergy(Direction extractingFrom, PacketTier packetTier, ActionType actionType);

}
