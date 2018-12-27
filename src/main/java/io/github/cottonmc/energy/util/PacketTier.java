package io.github.cottonmc.energy.util;

public class PacketTier {

    //TODO: values not final, discuss
    /**
     * @value TIER_1: base level. Coal-powered, early-game machines.
     * @value TIER_2: mid-level. Coal coke-powered, first industrial machines.
     * @value TIER_3: high-level. ??????-powered, medium-scale machines.
     * @value TIER_4: endgame. Uranium-powered, large-scale industrial setups.
     */
    public static final PacketTier TIER_1 = new PacketTier(40);
    public static final PacketTier TIER_2 = new PacketTier(80);
    public static final PacketTier TIER_3 = new PacketTier(160);
    public static final PacketTier TIER_4 = new PacketTier(320);

    private final int size;

    public PacketTier(int size) {
        this.size = size;
    }

    public int getPacketSize() {
        return size;
    }
}
