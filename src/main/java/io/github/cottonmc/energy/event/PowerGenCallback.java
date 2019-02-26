package io.github.cottonmc.energy.event;


import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * This event is meant to be fired any time power is generated from source fuel, whether it's
 * generated into an internal buffer or generated directly out to consumers. It is not for transfer
 * of previously-generated power, as with unloading of internal storage. As a rule of thumb, if the
 * machine polluted, this event should be fired when the pollution happens.
 * 
 * <p>That said, if the generator does not pollute, it should still fire this event and let either
 * the mod or the modpack config determine whether to apply those mechanics.
 */
public interface PowerGenCallback {

	Event<PowerGenCallback> EVENT = EventFactory.createArrayBacked(PowerGenCallback.class,
		(listeners) -> (world, pos) -> {
			for (PowerGenCallback event : listeners) {
				event.generate(world, pos);
			}
		});

	void generate(World world, BlockPos pos);

}
