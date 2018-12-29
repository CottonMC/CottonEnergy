package io.github.cottonmc.energy.api;

import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;

public class ElectricalEnergyType implements EnergyType {

    private final int lvPerPacket;

    public ElectricalEnergyType(int lvPerPacket) {
        this.lvPerPacket = lvPerPacket;
    }

    public int getPacketSize() {
        return 1;
    }

    @Override
    public int getMaximumPacketSize() {
        return 1;
    }

    /**
     * @return the amount of energy in this packet if it were converted to LV
     */
    public int getLVAmount() {
        return lvPerPacket;
    }

    @Override
    public TextComponent getDisplayAmount(int amount) {
        //TODO: handle KWU, MWU, and GWU
        return new TranslatableTextComponent("info.cotton.energy.electrical.amount", lvPerPacket * amount);
    }
}
