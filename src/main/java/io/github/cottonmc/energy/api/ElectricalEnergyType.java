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
        if (amount < 1000) { // x < 1M
            return new TranslatableTextComponent("info.cotton.energy.electrical.amount", amount);
        }
        else if (amount < 1_000_000) { // 1K < x < 1M
            float tAmount = amount / 1000;
            return new TranslatableTextComponent("info.cotton.energy.electrical.amount.k", tAmount);
        }
        else if (amount < 1_000_000_000) { // 1M < x < 1G
            float tAmount = amount / 1_000_1000;
            return new TranslatableTextComponent("info.cotton.energy.electrical.amount.m", tAmount);
        }
        else { // 1G < x
            float tAmount = amount / 1_000_000_000;
            return new TranslatableTextComponent("info.cotton.energy.electrical.amount.g", tAmount);
        }
    }
}
