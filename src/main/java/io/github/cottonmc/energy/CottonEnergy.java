package io.github.cottonmc.energy;


import io.github.cottonmc.cotton.Cotton;
import io.github.cottonmc.energy.api.EnergyType;
import io.github.cottonmc.energy.api.DefaultEnergyTypes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultMappedRegistry;
import net.minecraft.util.registry.ModifiableRegistry;
import net.minecraft.util.registry.Registry;

public class CottonEnergy implements ModInitializer {

	public static final ModifiableRegistry<EnergyType> ENERGY_REGISTRY = new DefaultMappedRegistry("cotton:empty");

	@Override
	public void onInitialize() {
		Cotton.logger.info("Starting Cotton Energy!");
		Registry.REGISTRIES.register(new Identifier("cotton", "energy"), ENERGY_REGISTRY);
		DefaultEnergyTypes.init();
	}

}
