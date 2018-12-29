package io.github.cottonmc.energy.api;

public class EnergyType implements Comparable<EnergyType> {

    //TODO: values not final, discuss
    /** Base level. Coal-powered or early-game machines. */
    public static final EnergyType LOW_VOLTAGE = new EnergyType(40);
    /** Mid-level. Coal coke powered, first industrial machines */
    public static final EnergyType MEDIUM_VOLTAGE = new EnergyType(80);
    /** High-level. ??????-powered, medium-scale machines. */
    public static final EnergyType HIGH_VOLTAGE = new EnergyType(160);
    /** Endgame. Uranium or higher, large-scale industrial setups or high-voltage power lines. */
    public static final EnergyType ULTRA_HIGH_VOLTAGE = new EnergyType(320);

    private final int size;

    public EnergyType(int maxSize) {
        this.size = maxSize;
    }

    public int getPacketSize() {
        return size;
    }

    //implements Comparable<PacketTier> {
        @Override
        public int compareTo(EnergyType other) {
            return Integer.compare(this.getPacketSize(), other.getPacketSize());
        }
    //}
}
