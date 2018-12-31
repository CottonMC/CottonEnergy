package io.github.cottonmc.energy.api;

import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;

public class ElectricalEnergyType implements EnergyType {

    private final int maximum;

    public ElectricalEnergyType(int maximum) {
        this.maximum = maximum;
    }

    @Override
    public int getMaximumTransferSize() {
        return maximum;
    }

    @Override
    public TextComponent getDisplayAmount(int amount) {
        //TODO: handle KWU, MWU, and GWU
        return new TranslatableTextComponent("info.cotton.energy.electrical.amount", amount);
    }
}
