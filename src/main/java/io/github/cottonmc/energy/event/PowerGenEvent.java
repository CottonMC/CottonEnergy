package io.github.cottonmc.energy.event;

import net.fabricmc.fabric.util.HandlerArray;
import net.fabricmc.fabric.util.HandlerRegistry;
import net.minecraft.block.entity.BlockEntity;

import java.util.function.Consumer;

public final class PowerGenEvent {
	public static final HandlerRegistry<Consumer<BlockEntity>> GENERATE_POWER = new HandlerArray<>(Consumer.class);

	/**
	 * Fired whenever a (properly-written) generator calls the generateEnergy() method and can generate power.
	 */
	private PowerGenEvent() {

	}
}
