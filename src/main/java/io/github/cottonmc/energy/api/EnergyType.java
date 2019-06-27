package io.github.cottonmc.energy.api;

import net.minecraft.text.TranslatableText;

import java.util.Optional;

public interface EnergyType {
	/** @return the minimum amount of energy that can be transferred at a time. */
	default int getMinimumTransferSize() { return 0; }

	/** @return the maximum amount of energy that can be transferred at a time. */
	int getMaximumTransferSize();

	/**
	 * Gets human-readable text representing an energy value of this type.
	 * @param amount the total aggregate number of energy units being represented.
	 * @return a TextText representing this quantity and its relevant units, such as "300 WU".
	 */
	TranslatableText getDisplayAmount(int amount);
	
	/**
	 * Returns true if this EnergyType knows how to convert energy to and from the specified other
	 * type. Normally this should be false for all other EnergyTypes, as it will result in automatic
	 * conversions, and at that rate why have competing standards?
	 */
	default boolean isCompatibleWith(EnergyType type) {
		return type==this;
	}
	
	/**
	 * Only valid if the EnergyType is compatible; returns true if *inserting* energy of the
	 * specified type is harmful to a recipient of this EnergyType.
	 */
	default boolean isHarmful(EnergyType type) {
		return false;
	}
	
	/**
	 * Converts amount from the specified EnergyType to this EnergyType, rounding down.
	 * @return an equivalent amount of energy in this type, or zero of isCompatible is false.
	 */
	default int convertFrom(EnergyType type, int amount) {
		return (type==this) ? amount : 0;
	}
	
	/**
	 * Converts amount from this EnergyType to an equivalent amount in the specified EnergyType,
	 * rounding down.
	 * @return an equivalent amount of energy in the target type, or zero of isCompatible is false.
	 */
	default int convertTo(EnergyType type, int amount) {
		return (type==this) ? amount : 0;
	}
	
	public static Optional<Integer> convert(EnergyType sourceType, int sourceAmount, EnergyType targetType) {
		if (sourceType.isCompatibleWith(targetType)) return Optional.of(sourceType.convertTo(targetType, sourceAmount));
		if (targetType.isCompatibleWith(sourceType)) return Optional.of(targetType.convertFrom(sourceType, sourceAmount));
		return Optional.empty();
	}
}