package io.github.cottonmc.energy.event;

import net.fabricmc.fabric.util.HandlerArray;
import net.fabricmc.fabric.util.HandlerRegistry;
import net.minecraft.block.entity.BlockEntity;

import java.util.function.Consumer;

/**
 * This event is meant to be fired any time power is generated from source fuel, whether it's
 * generated into an internal buffer or generated directly out to consumers. It is not for transfer
 * of previously-generated power, as with unloading of internal storage. As a rule of thumb, if the
 * machine polluted, this event should be fired when the pollution happens.
 * 
 * <p>That said, if the generator does not pollute, it should still fire this event and let either
 * the mod or the modpack config determine whether to apply those mechanics.
 */
public final class PowerGenEvent {
	/*TODO: We should probably upgrade this to HandlerRegistry<Consumer<PowerGenEvent>>
	 * so that we can pass along information about how many fuel ticks if any were used, how much
	 * energy was generated, and how dirty the generator estimates itself to be. */
	public static final HandlerRegistry<Consumer<BlockEntity>> GENERATE_POWER = new HandlerArray<>(Consumer.class);

	/**
	 * Fired whenever a (properly-written) generator calls the generateEnergy() method and can generate power.
	 */
	private PowerGenEvent() {

	}
}
