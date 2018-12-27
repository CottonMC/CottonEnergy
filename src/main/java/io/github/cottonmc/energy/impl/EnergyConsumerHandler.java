package io.github.cottonmc.energy.impl;

import io.github.cottonmc.energy.api.EnergyConsumer;
import io.github.cottonmc.energy.util.PacketTier;
import net.minecraft.util.math.Direction;

public class EnergyConsumerHandler implements EnergyConsumer {

	
	@Override
	public boolean canInsertEnergy(Direction insertingFrom, PacketTier size) {
		return false;
	}

	@Override
	public void insertPacket(Direction insertingFrom, PacketTier packetSize) {

	}
}
