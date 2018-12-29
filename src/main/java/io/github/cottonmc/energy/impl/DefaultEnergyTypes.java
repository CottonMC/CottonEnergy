package io.github.cottonmc.energy.impl;

import io.github.cottonmc.energy.CottonEnergy;
import io.github.cottonmc.energy.api.ElectricalEnergyType;
import io.github.cottonmc.energy.api.EnergyType;

public class DefaultEnergyTypes {

	//TODO: values not final, discuss
	/** Base level. Coal-powered or early-game machines. */
	public static final EnergyType LOW_VOLTAGE = CottonEnergy.ENERGY_TYPES.register("cotton:low_voltage", new ElectricalEnergyType(40));
	/** Mid-level. Coal coke powered, first industrial machines */
	public static final EnergyType MEDIUM_VOLTAGE = CottonEnergy.ENERGY_TYPES.register("cotton:medium_voltage", new ElectricalEnergyType(80));
	/** High-level. ??????-powered, medium-scale machines. */
	public static final EnergyType HIGH_VOLTAGE = CottonEnergy.ENERGY_TYPES.register("cotton:high_voltage", new ElectricalEnergyType(160));
	/** Endgame. Uranium or higher, large-scale industrial setups or high-voltage power lines. */
	public static final EnergyType ULTRA_HIGH_VOLTAGE = CottonEnergy.ENERGY_TYPES.register("cotton:ultra_high_voltage", new ElectricalEnergyType(320));

	public void init() {

	}
}
