package io.github.cottonmc.energy.impl;

import io.github.cottonmc.energy.api.EnergyStorage;
import io.github.cottonmc.energy.util.PacketTier;

public class EnergyStorageHandler implements EnergyStorage {
	private int currentEnergy;
	private int maxEnergy;
	private PacketTier maxPacket;

	public EnergyStorageHandler(int maxEnergy, PacketTier maxPacketSize) {
		this.maxEnergy = maxEnergy;
		this.maxPacket = maxPacketSize;
	}

	@Override
	public int getMaxCapacity() {
		return maxEnergy;
	}

	@Override
	public int getCurrentEnergy() {
		return currentEnergy;
	}

	@Override
	public PacketTier getPacketTier() {
		return maxPacket;
	}
}
