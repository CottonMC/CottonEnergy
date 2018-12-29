package io.github.cottonmc.energy.api;

public interface EnergyType {

	default int getMinimumPacketSize() { return 1; }
	default int getMaximumPacketSize() { return 1; }

}
