package io.github.cottonmc.energy.api;

import javax.annotation.Nonnull;

import net.minecraft.util.math.Direction;

public interface EnergyConsumer {

    /**
     * Find out whether EnergyPackets can be inserted from a given Direction.
     * 
     * @param insertingFrom the side from which to insert, from the perspective of the object being inserted into.
     * @return whether energy can be inserted from this direction.
     */
    boolean canInsertEnergy(Direction insertingFrom);

    /**
     * Attempt to insert an energy packet. It's up to the Consumer whether packets are accepted partially or on an
     * all-or-nothing basis.
     *
     * @param insertingFrom the side from which to insert, from the perspective of the object being inserted into.
     * @param packet        the packet to insert.
     * @param actionType    whether to SIMULATE or PERFORM insertion.
     * @return a packet containing any leftover energy, or EMPTY_PACKET if the insertion was completely successful.
     */
    @Nonnull
    EnergyPacket insertEnergy(Direction insertingFrom, EnergyPacket packet, ActionType actionType);

}
