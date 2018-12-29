package io.github.cottonmc.energy.api;

public class ElectricalEnergyType implements EnergyType {

    private final int size;

    public ElectricalEnergyType(int maxSize) {
        this.size = maxSize;
    }

    public int getPacketSize() {
        return size;
    }


    @Override
    public int getMaximumPacketSize() {
        return this.size;
    }
}
