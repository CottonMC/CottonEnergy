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

    public static final Registry<EnergyType> ENERGY_REGISTRY;
    static { //Be ready before our admirers!
        ModifiableRegistry<EnergyType> temp_init = new DefaultMappedRegistry<EnergyType>("cotton:low_voltage");
        Registry.REGISTRIES.register( new Identifier("cotton", "energy"), temp_init );
        ENERGY_REGISTRY = temp_init;

        DefaultEnergyTypes.init();
    }

    @Override
    public void onInitialize() {
        Cotton.logger.info("Starting Cotton Energy!");
    }

}
