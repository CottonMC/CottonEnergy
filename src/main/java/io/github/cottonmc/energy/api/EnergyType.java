package io.github.cottonmc.energy.api;

import net.minecraft.text.TextComponent;

public interface EnergyType {
    /** @return the minimum amount of energy that can be transferred at a time. */
	default int getMinimumTransferSize() { return 0; }
	/** @return the maximum amount of energy that can be transferred at a time. */
	int getMaximumTransferSize();
	/**
	 * Gets human-readable text representing an energy value of this type.
	 * @param amount the total aggregate number of energy units being represented.
	 * @return a TextComponent representing this quantity and its relevant units, such as "300 WU".
	 */
	TextComponent getDisplayAmount(int amount);
}
