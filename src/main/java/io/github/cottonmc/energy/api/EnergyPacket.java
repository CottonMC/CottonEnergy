package io.github.cottonmc.energy.api;

/**
 * Kind of like an "EnergyStack". Represents energy in a movable or consumable form, ready to do work.
 */
public class EnergyPacket {

    public static final EnergyPacket EMPTY_PACKET = new EnergyPacket(PacketTier.LOW_VOLTAGE, 0);

    protected PacketTier tier;
    protected int amount;

    public EnergyPacket(PacketTier tier) {
        this.tier = tier;
        this.amount = tier.getPacketSize();
    }

    public EnergyPacket(PacketTier tier, int amount) {
        this.tier = tier;
        this.amount = amount;
    }

    public PacketTier getPacketTier() {
        return this.tier;
    }

    public int getAmount() {
        return this.amount;
    }

}
