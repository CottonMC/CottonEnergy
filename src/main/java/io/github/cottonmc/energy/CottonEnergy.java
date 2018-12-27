package io.github.cottonmc.energy;


import net.fabricmc.api.ModInitializer;

public class CottonEnergy implements ModInitializer {


	@Override
	public void onInitialize() {
		System.out.println("Starting Cotton energy");

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
