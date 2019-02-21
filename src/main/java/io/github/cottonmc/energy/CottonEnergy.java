package io.github.cottonmc.energy;


import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.MutableRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cottonmc.energy.api.EnergyType;
import io.github.cottonmc.energy.api.DefaultEnergyTypes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CottonEnergy implements ModInitializer {
    private static final Logger LOG = LogManager.getLogger("Cotton-Energy");
    public static final Registry<EnergyType> ENERGY_REGISTRY;
    static { //Be ready before our admirers!
        MutableRegistry<EnergyType> temp_init = new DefaultedRegistry<>("cotton:low_voltage");
        Registry.register(Registry.REGISTRIES, new Identifier("cotton", "energy"), temp_init );
        ENERGY_REGISTRY = temp_init;

        DefaultEnergyTypes.init();
    }

    @Override
    public void onInitialize() {
        LOG.info("Starting Cotton Energy!");
    }

}
