package io.github.cottonmc.energy.impl;

import io.github.cottonmc.energy.api.EnergyProducer;
import io.github.cottonmc.energy.util.PacketTier;
import net.minecraft.util.math.Direction;

public class EnergyProducerHandler implements EnergyProducer {

	@Override
	public void extractPacket(Direction extractingFrom, PacketTier packetSize) {

	}

	@Override
	public boolean canExtractEnergy(Direction extractingFrom, PacketTier size) {
		return false;
	}
}
