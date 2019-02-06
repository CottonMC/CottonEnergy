package io.github.cottonmc.energy;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cottonmc.energy.api.EnergyType;
import io.github.cottonmc.energy.api.DefaultEnergyTypes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultMappedRegistry;
import net.minecraft.util.registry.ModifiableRegistry;
import net.minecraft.util.registry.Registry;

public class CottonEnergy implements ModInitializer {
    private static final Logger LOG = LogManager.getLogger("Cotton-Energy");
    public static final Registry<EnergyType> ENERGY_REGISTRY;
    static { //Be ready before our admirers!
        ModifiableRegistry<EnergyType> temp_init = new DefaultMappedRegistry<EnergyType>("cotton:low_voltage");
        Registry.REGISTRIES.register( new Identifier("cotton", "energy"), temp_init );
        ENERGY_REGISTRY = temp_init;

        DefaultEnergyTypes.init();
    }

    @Override
    public void onInitialize() {
        LOG.info("Starting Cotton Energy!");
    }

}
