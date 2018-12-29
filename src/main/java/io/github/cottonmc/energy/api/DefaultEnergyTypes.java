package io.github.cottonmc.energy.api;

import io.github.cottonmc.energy.CottonEnergy;
import net.minecraft.util.Identifier;

public class DefaultEnergyTypes {

	public static final EnergyType ENERGY_TYPE_EMPTY = register("empty", new EmptyEnergyType());

	//TODO: values not final, discuss
	/** Base level. Coal-powered or early-game machines. */
	public static final EnergyType LOW_VOLTAGE = register("low_voltage", new ElectricalEnergyType(40));
	/** Mid-level. Coal coke powered, first industrial machines */
	public static final EnergyType MEDIUM_VOLTAGE = register("medium_voltage", new ElectricalEnergyType(80));
	/** High-level. ??????-powered, medium-scale machines. */
	public static final EnergyType HIGH_VOLTAGE = register("high_voltage", new ElectricalEnergyType(160));
	/** Endgame. Uranium or higher, large-scale industrial setups or high-voltage power lines. */
	public static final EnergyType ULTRA_HIGH_VOLTAGE = register("ultra_high_voltage", new ElectricalEnergyType(320));

	public static void init() {

	}

	public static EnergyType register(String name, EnergyType type) {
		CottonEnergy.ENERGY_REGISTRY.register(new Identifier("cotton", name), type);
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
	}
}
