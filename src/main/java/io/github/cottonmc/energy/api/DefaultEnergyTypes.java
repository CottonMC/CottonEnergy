package io.github.cottonmc.energy.api;

import io.github.cottonmc.energy.CottonEnergy;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DefaultEnergyTypes {

    public static final EnergyType ENERGY_TYPE_EMPTY = register("cotton:empty", new EmptyEnergyType());

    //TODO: values not final, discuss
    /** Base level. Coal-powered or early-game machines. */
    public static final EnergyType LOW_VOLTAGE = register("low_voltage", new ElectricalEnergyType(1));
    /** Mid-level. Coal coke powered, first industrial machines */
    public static final EnergyType MEDIUM_VOLTAGE = register("medium_voltage", new ElectricalEnergyType(10));
    /** High-level. ??????-powered, medium-scale machines. */
    public static final EnergyType HIGH_VOLTAGE = register("high_voltage", new ElectricalEnergyType(100));
    /** Endgame. Uranium or higher, large-scale industrial setups or high-voltage power lines. */
    public static final EnergyType ULTRA_HIGH_VOLTAGE = register("ultra_high_voltage", new ElectricalEnergyType(1000));

    public static void init() {

    }

    public static EnergyType register(String name, EnergyType type) {
        Registry.register(CottonEnergy.ENERGY_REGISTRY, new Identifier("cotton", name), type);
        return type;
    }

    public static class EmptyEnergyType implements EnergyType {
        public EmptyEnergyType() {

        }
        @Override
        public int getMaximumPacketSize() {
            return 0;
        }

        @Override
        public int getMinimumPacketSize() {
            return 0;
        }

        @Override
        public TextComponent getDisplayAmount(int amount) {
            return new TranslatableTextComponent("info.cotton.energy.empty");
        }
    }
}
