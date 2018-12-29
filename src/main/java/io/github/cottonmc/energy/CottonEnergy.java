package io.github.cottonmc.energy;


import io.github.cottonmc.cotton.Cotton;
import io.github.cottonmc.energy.api.EnergyType;
import io.github.cottonmc.energy.impl.EnergyTypeRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.DefaultMappedRegistry;

import java.util.function.Supplier;

public class CottonEnergy implements ModInitializer {

	public static final DefaultMappedRegistry<EnergyType> ENERGY_TYPES = new EnergyTypeRegistry();

	@Override
	public void onInitialize() {
		Cotton.logger.info("Starting Cotton Energy!");

		/**
		 * Not sure if we really want to turn cotton energy into a mod. I wrote some ideas
		 * about this project into the Let's talk: energy issue of the main cotton mod.
		 * my idea is to cleanly seperate the api from the implementation, so that there can
		 * be different energy mods (mods that add cables and transformers) that all have the same api.
		 * Please have a look at it before continuing.
		 * 	- Ansraer
		 */
	}

}
