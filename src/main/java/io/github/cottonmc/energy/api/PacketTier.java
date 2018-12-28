package io.github.cottonmc.energy.api;

public class PacketTier implements Comparable<PacketTier> {

    //TODO: values not final, discuss
    /** Base level. Coal-powered or early-game machines. */
    public static final PacketTier LOW_VOLTAGE = new PacketTier(40);
    /** Mid-level. Coal coke powered, first industrial machines */
    public static final PacketTier MEDIUM_VOLTAGE = new PacketTier(80);
    /** High-level. ??????-powered, medium-scale machines. */
    public static final PacketTier HIGH_VOLTAGE = new PacketTier(160);
    /** Endgame. Uranium or higher, large-scale industrial setups or high-voltage power lines. */
    public static final PacketTier ULTRA_HIGH_VOLTAGE = new PacketTier(320);

    private final int size;

    public PacketTier(int size) {
        this.size = size;
    }

    public int getPacketSize() {
        return size;
    }

    //implements Comparable<PacketTier> {
        @Override
        public int compareTo(PacketTier other) {
            return Integer.compare(this.getPacketSize(), other.getPacketSize());
        }
    //}
}
