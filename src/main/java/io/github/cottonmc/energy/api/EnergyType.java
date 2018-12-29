package io.github.cottonmc.energy.api;

import net.minecraft.text.TextComponent;

public interface EnergyType {
    /** @return the minimum amount of energy a packet can ever carry. */
	default int getMinimumPacketSize() { return 1; }
	/** @return the maximum amount of energy a packet can ever carry. */
	default int getMaximumPacketSize() { return 1; }
	/**
	 * Gets human-readable text representing the energy value of one or more packets or energy storage systems of this type.
	 * @param amount the total aggregate number of energy units being represented.
	 * @return a TextComponent representing this quantity and its relevant units, such as "300 RF".
	 */
	TextComponent getDisplayAmount(int amount);
}
