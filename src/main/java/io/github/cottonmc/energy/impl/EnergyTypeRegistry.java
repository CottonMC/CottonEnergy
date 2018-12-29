package io.github.cottonmc.energy.impl;

import io.github.cottonmc.energy.api.EnergyType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultMappedRegistry;

public class EnergyTypeRegistry extends DefaultMappedRegistry<EnergyType> {

	public EnergyTypeRegistry() {
		super("cotton:energy_types");
	}

	public EnergyType register(Identifier id, EnergyType type) {
		super.register(id, type);
		return type;
	}
}
