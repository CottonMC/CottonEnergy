package io.github.cottonmc.energy.api;

/**
 * Kind of like an "EnergyStack". Represents energy in a movable or consumable form, ready to do work.
 */
public class EnergyPacket {

    public static final EnergyPacket EMPTY_PACKET = new EnergyPacket(DefaultEnergyTypes.ENERGY_TYPE_EMPTY, 0);

    protected EnergyType type;
    protected int amount;

    public EnergyPacket(EnergyType type) {
        this.type = type;
        this.amount = type.getMaximumPacketSize();
    }

    public EnergyPacket(EnergyType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public EnergyType getEnergyType() {
        return this.type;
    }

    public int getAmount() {
        return this.amount;
    }

}
